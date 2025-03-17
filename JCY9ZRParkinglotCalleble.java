import java.util.concurrent.*;

public class JCY9ZRParkinglotCalleble {
    public static void main(String[] args) {
        ParkingLot parkingLot=new ParkingLot();
        ExecutorService executorService= Executors.newFixedThreadPool(5);

        for (int i = 0; i < 100; i++) {
            executorService.submit(new Car(parkingLot));
        }
        executorService.shutdown();
    }
}

class ParkingLot{
    private final Semaphore semaphore=new Semaphore(10);
    int bent=0;

    public void enter(){
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        bent++;
        System.out.println("Car is entering");
    }

    public void parking(){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void leave(){
        System.out.println("Car is leaving");
        bent--;
        semaphore.release();
    }
}

class Car implements Callable<Void>{
    private ParkingLot parkingLot;

    public Car(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public Void call() {
        parkingLot.enter();
        parkingLot.parking();
        parkingLot.leave();
        return null;
    }
}