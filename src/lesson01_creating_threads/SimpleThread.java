package lesson01_creating_threads;

public class SimpleThread extends Thread {

    @Override
    public void run() {
        System.out.println("Hello from Thread descendant: " + getName());
    }
}
