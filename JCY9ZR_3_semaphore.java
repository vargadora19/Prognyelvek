import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.Semaphore;


public class JCY9ZR {
    public static void main(String[] args) {
        SharedInt s = new SharedInt();
        ArrayList<Thread> threads=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Thread thread=new Thread(new Modifier(s));
            System.out.println(s.getValue());
            thread.start();
            threads.add(thread);
        }
        for (Thread thread:threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("utolsó:" + s.getValue());
    }
}

class SharedInt{
    private int value;
    private final Semaphore semaphore=new Semaphore(1);

    public void setValue(int newValue) {
        try {
            semaphore.acquire();
            this.value = newValue;
            semaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void decrement(){
        try {
            semaphore.acquire();
            value--;
            semaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int getValue() {
        return value;
    }
}

class Modifier implements Runnable{
    private SharedInt sharedInt;

    public Modifier(SharedInt sharedInt) {
        this.sharedInt=sharedInt;
    }

    @Override
    public void run() {
        int seged=new Random().nextInt(2);
        if(seged==0){
            sharedInt.setValue(new Random().nextInt(100));
        } else{
            sharedInt.decrement();
        }
    }
}