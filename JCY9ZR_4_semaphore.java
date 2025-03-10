import java.util.concurrent.Semaphore;

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
    private final Semaphore semaphore=new Semaphore(10);

    public void enter(){
        try {
            semaphore.acquire();
            System.out.println("Car is waiting");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Car is entering");
    }

    public void leave(){
        System.out.println("Car is leaving");
        semaphore.release();
    }
}

class Car extends Thread{
    private final ParkingLot parkingLot;

    public Car(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        parkingLot.enter();
        parkingLot.leave();
    }
}