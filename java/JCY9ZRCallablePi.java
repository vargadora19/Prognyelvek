import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class JCY9ZRCallablePi {
    public static void main(String[] args){
        double side_size=2.0;

        ExecutorService executor= Executors.newCachedThreadPool();
        List<MyCallable> callableList=new ArrayList<>();
        List<Future<Point>> futures=new ArrayList<Future<Point>>();

        for(int i=0;i<100000;++i){
            callableList.add(new MyCallable(side_size));
        }

        try {
            futures=executor.invokeAll(callableList);
            int insideCircleCount =0;
            for(int i=0;i<futures.size();++i){
                if(futures.get(i).get().incircle){
                    insideCircleCount ++;
                }
            }
            double pi = 4.0 * insideCircleCount / 100000;

            System.out.println("Körön belüli pontok száma: " + insideCircleCount);
            System.out.println("A Pi közelített értéke: " + pi);


        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        executor.shutdown();

    }
}
class Point{
    double x;
    double y;
    boolean incircle;

    public Point(double side) {
        this.x = new Random().nextDouble(-side/2,side/2); //[-1,1]
        this.y = new Random().nextDouble(-side/2,side/2);
        this.incircle = (x * x + y * y) <= (side / 2) * (side / 2); //benne van-e
    }
}

class MyCallable implements Callable<Point>{
    double side;

    public MyCallable(double side) {
        this.side = side;
    }

    //váletlen pontok
    @Override
    public Point call(){
        return new Point(side);
    }
}