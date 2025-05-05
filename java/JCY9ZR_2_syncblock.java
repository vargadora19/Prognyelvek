import java.util.ArrayList;
import java.util.Random;

public class JCY9ZR {
    public static void main(String[] args) {
        SharedInt s = new SharedInt();
        ArrayList<Thread> threads=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Thread thread=new Thread(new Modifier(s));
            threads.add(thread);
            thread.start();
            System.out.println(s.getValue());
        }
        for (int i = 0; i < threads.size(); i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("utolso:" + s.getValue());
    }
}

class SharedInt{
    private int value;

    public synchronized void setValue(int newValue) {
        this.value = newValue;
    }
    
    public void decrement(){
        synchronized (this){
            value--;
        }
    }

    public int getValue() {
        return value;
    }
}

class Modifier implements Runnable{
    SharedInt sharedInt;
    public Modifier(SharedInt sharedInt) {
      this.sharedInt=sharedInt;
    }

    @Override
    public void run() {
        int melyik=new Random().nextInt(2);
        if(melyik==0){
            sharedInt.setValue(new Random().nextInt(100));
        }else{
            sharedInt.decrement();
        }
    }
}