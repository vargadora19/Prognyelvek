import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
    private Condition notFull;
    private Lock lock=new ReentrantLock();
    private ArrayList<Car> list=new ArrayList<>();

    public ParkingLot() {
        this.notFull = lock.newCondition();
    }

    public void enter(Car car){
        lock.lock();
        while (list.size()==10){
            try {
                notFull.await();
                System.out.println("Car is waiting");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        list.add(car);
        System.out.println("Car is entering");
        lock.unlock();
    }

    public void leave(){
        lock.lock();
        list.removeFirst();
        notFull.signal();
        System.out.println("Car is leaving");
        lock.unlock();
    }
}

class Car extends Thread{
    private ParkingLot parkingLot;

    public Car(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        parkingLot.enter(this);
        parkingLot.leave();
    }
}