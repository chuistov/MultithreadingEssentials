package lesson13_ThreadPool_Future_Callable;

import java.util.concurrent.Executors;

public class ThreadPoolDemo {

    public static void main(String[] args) {

        Executors.newSingleThreadExecutor();
        Executors.newFixedThreadPool(5);
        Executors.newCachedThreadPool();

        Executors.newScheduledThreadPool(5);
        Executors.newWorkStealingPool();


    }
}
