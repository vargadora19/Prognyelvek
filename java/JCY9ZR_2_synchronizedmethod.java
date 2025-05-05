import java.util.ArrayList;

public class JCY9ZR {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();
        for (int i = 0; i < 100; i++) {
            Car thread=new Car(parkingLot);
            thread.start();
        }
    }
}

class ParkingLot{
    private int maxCar=10;
//    private int cars=0;
    private ArrayList<Car> cars=new ArrayList<>();

    synchronized void enter(Car car){
        while (cars.size()==maxCar){
            try {
                wait();
                System.out.println("Car is waiting");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        cars.add(car);
        System.out.println("Car is entering");
    }

    synchronized void leave(Car car){
        cars.remove(car);
        notify();
        System.out.println("Car is leaving");
    }
}

class Car extends Thread{
    ParkingLot parkingLot;

    public Car(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        parkingLot.enter(this);
        try {
            sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        parkingLot.leave(this);
    }
}