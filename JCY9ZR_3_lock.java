import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
    private Lock lock=new ReentrantLock();

    public void setValue(int newValue) {
        lock.lock();
        this.value = newValue;
        lock.unlock();
    }

    public void decrement(){
        lock.lock();
        value--;
        lock.unlock();
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