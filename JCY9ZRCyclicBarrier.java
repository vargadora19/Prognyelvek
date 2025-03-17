import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class JCY9ZRCyclicBarrier {
    public static void main(String[] args) {
        int threadCount=5;
        Resource resource=new Resource(threadCount);
        ArrayList<Calculator> calculators=new ArrayList<>();
        CyclicBarrier cyclicBarrier=new CyclicBarrier(threadCount, new Reszszamlalas(calculators));

//        int intervallum = resource.getArray().length / threadCount;
//        for (int i = 0; i < threadCount; i++) {
//            int start = i * intervallum;
//            int end = i == threadCount - 1 ? resource.getArray().length : start + intervallum;
//            Calculator calculator = new Calculator(resource, start, end, cyclicBarrier);
//            calculators.add(calculator);
//            calculator.start();
//        }

        for (int i = 0; i < threadCount; i++) {
            int start = i * 100;
            int end = (i+1)*100;
            Calculator calculator = new Calculator(resource, start, end, cyclicBarrier);
            calculators.add(calculator);
            calculator.start();
        }
    }
}

class Resource{
    private int howMany;
    private int[] array;

    public Resource(int howMany) {
        this.howMany = howMany;
        this.array = new int[howMany*100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
    }

    public int[] getArray() {
        return array;
    }
}

class Calculator extends Thread{
    private Resource resource;
    private int first;
    private int last;
    private CyclicBarrier cyclicBarrier;
    private int sum=0;

    public Calculator(Resource resource, int first, int last, CyclicBarrier cyclicBarrier) {
        this.resource = resource;
        this.first = first;
        this.last = last;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        for (int i = first; i < last; i++) {
            sum+=resource.getArray()[i];
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    public int getSum() {
        return sum;
    }
}

class Reszszamlalas implements Runnable{
    private ArrayList<Calculator> calculators;

    public Reszszamlalas(ArrayList<Calculator> calculators) {
        this.calculators = calculators;
    }

    @Override
    public void run() {
        int sum=0;
        for (int i = 0; i < calculators.size(); i++) {
            sum+=calculators.get(i).getSum();
            System.out.println("összeg: " + sum);
        }
    }
}