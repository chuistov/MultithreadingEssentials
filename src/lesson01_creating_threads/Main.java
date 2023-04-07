package lesson01_creating_threads;

public class Main {

    public static void main(String[] args) {

        // наследование от Thread
        var simpleThread = new SimpleThread();

        // получение состояния потока
        Thread.State state = simpleThread.getState();

        // реализация Runnable
        // более гибкий вариант, можно еще наследоваться от класса
        var simpleRunnable = new Thread(new SimpleRunnable());

        // лямбда от Runnable
        var simpleLambdaRunnable = new Thread(() -> System.out.println("Hello from lambda: " + Thread.currentThread().getName()));

        // запуск потоков
        simpleThread.start();
        simpleRunnable.start();
        simpleLambdaRunnable.start();

        // раньше можно было остановить поток, но это deprecated, т. к. возможна утечка ресурсов
//        simpleLambdaRunnable.stop();

        // так можно дать потоку команду прерваться
//        simpleLambdaRunnable.interrupt();

        // потоки дожидаются, пока завершится предыдущий поток
        try {
            simpleThread.join();
            simpleRunnable.join();
            simpleLambdaRunnable.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread: " + Thread.currentThread().getName());
    }

}
