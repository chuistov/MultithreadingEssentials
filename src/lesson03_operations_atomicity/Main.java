package lesson03_operations_atomicity;

/**
 * Операция count++ неатомарна,
 * она состоит из трех операций:
 * а) считывание значения из памяти,
 * б) увеличение значения,
 * в) запись значения в память.
 *
 * Два потока могут одновременно выполнять эти операции:
 * поток1: ---чтение(5) инкремент(6) запись(6)---
 * поток2: ---чтение(5) инкремент(6) запись(6)---
 * Один инкремент потерян!!!
 */
class Counter {

    private int count;

    public int getCount() {
        return count;
    }

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }
}

class CounterThread extends Thread {

    final private Counter counter;

    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for(int i = 0; i < 1_000_000; i++) {
            counter.increment();
        }
    }
}

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();

        CounterThread thread1 = new CounterThread(counter);
        CounterThread thread2 = new CounterThread(counter);
        CounterThread thread3 = new CounterThread(counter);
        CounterThread thread4 = new CounterThread(counter);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        Thread.sleep(1000);

        System.out.println(counter.getCount());
    }
}






















