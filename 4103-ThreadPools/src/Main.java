import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("A1 " + Thread.currentThread());
                System.out.println("A2 " + Thread.currentThread());
            }
        };
        Runnable r2 = () -> {
            System.out.println("B1 " + Thread.currentThread());
            System.out.println("B1 " + Thread.currentThread());
        };
        //Erzeuge CachedThreadPool
        ExecutorService executor = Executors.newCachedThreadPool();

        //Thread t1 = new Thread(r1); t1.start();
        executor.execute(r1);
        executor.execute(r2);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Starte threads erneut
        executor.execute(r1);
        executor.execute(r2);

        //bitte executor runter zu fahren...
        executor.shutdown();
    }
}
