import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

public class Osszeg {
    public static void main(String[] args) {
        ExecutorService executorService=Executors.newCachedThreadPool();
        ArrayList<Future<Integer>> futures=new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            MyCallable myCallable=new MyCallable(executorService);
            Future<Integer> future=executorService.submit(myCallable);
            futures.add(future);
        }

        int osszeg=0;

        for (Future<Integer> future:futures) {
            try {
                osszeg+=future.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Az osszeg: " + osszeg);
        executorService.shutdown();


    }
}


class MyCallable implements Callable<Integer> {
    private ExecutorService executorService;

    public MyCallable(ExecutorService executorService){
        this.executorService=executorService;
    }

    @Override
    public Integer call(){
        int szam=new Random().nextInt(100);
        System.out.println("A callable generálja a számot: " + szam);
        return szam;
    }
}



