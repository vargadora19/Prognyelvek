import java.util.concurrent.atomic.AtomicReference;

public class JCY9ZRAtomic  {
    public static void main(String[] args) {
        AtomicReference<String> idle=new AtomicReference<>("Idle");

        for (int i = 0; i < 10; i++) {
            AtomicThread uj = new AtomicThread(idle);
            uj.start();
        }
    }
}

class AtomicThread extends Thread{
    AtomicReference<String> idle;

    public AtomicThread(AtomicReference<String> idle) {
        this.idle = idle;
    }

    @Override
    public void run() {
        while (!idle.compareAndSet("Idle", "InProgress")) {
            // Tevékeny várakozás, amíg nem lehet módosítani az állapotot
            System.out.println(this.getName()+"várakozik");
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(this.getName() + " dolgozik");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        
        //"Idle" állapotra visszarakjuk, mire eljut idáig sokszor lefut
        idle.set("Idle");
    }
}
