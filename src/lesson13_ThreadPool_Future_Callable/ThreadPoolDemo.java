package lesson13_ThreadPool_Future_Callable;

import java.util.concurrent.*;

public class ThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        Future<Integer> future = threadPool.submit(() -> {
            Thread.sleep(2000L);
            System.out.println("It's a callable");
            return 1;
        });

        System.out.println("Result: " + future.get());
        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.SECONDS);

        System.out.println("main thread");

        ScheduledExecutorService threadPool1 = Executors.newScheduledThreadPool(5);
        threadPool1.schedule(() -> System.out.println("Hello from scheduled"), 3L, TimeUnit.SECONDS);

        threadPool1.scheduleAtFixedRate(
                () -> System.out.println("Hi again!"),
                0L, 3L, TimeUnit.SECONDS
        );




    }
}
