import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class JCY9ZRSemaphoreSinc {
    public static void main(String[] args) {
        double[] tomb={-10, -10};
        Semaphore semaphore=new Semaphore(0);

        Thread1 thread1=new Thread1(tomb, semaphore);
        Thread2 thread2=new Thread2(tomb, semaphore);

        thread1.start();
        thread2.start();

        System.out.println(Arrays.toString(tomb));
    }
}

class Thread1 extends Thread{
    private final double[] ertekek;
    private final Semaphore semaphore;

    public Thread1(double[] ertekek, Semaphore semaphore) {
        this.ertekek = ertekek;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        ertekek[0]=-12;
        semaphore.release();
    }
}

class Thread2 extends Thread{
    private final double[] ertekek;
    private final Semaphore semaphore;

    public Thread2(double[] ertekek, Semaphore semaphore) {
        this.ertekek = ertekek;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ertekek[1]+=ertekek[0];
    }
}