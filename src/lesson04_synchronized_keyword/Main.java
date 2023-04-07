package lesson04_synchronized_keyword;

class Counter {

    private static String description;

    /**
     * Для синхронизации статической переменной в качестве монитора
     * используем название класса.
     */
    private static void init() {
        synchronized (Counter.class) {
            if(description == null) {
                description = "init";
            }
        }
    }

    private int count;

    public int getCount() {
        return count;
    }

    /**
     * Первый способ синхронизации -
     * synchronized на уровне метода
     */
    public synchronized void increment() {
        count++;
    }

    /**
     * Второй способ синхронизации - блок synchronized,
     * в скобках - синхронизируемый объект, используется его монитор.
     * Поток, захвативший монитор объекта в одном из методов,
     * может вызывать другие синхронизированные методы для работы с этим объектом.
     * Другие же потоки не могут вызвать синхронизированные методы и блоки,
     * связанные с этим объектом, до освобождения монитора.
     *
     * synchronized делает операцию атомарной и гарантирует,
     * что метод или блок кода выполняется только в одном потоке
     * в один момент времени.
     */
    public void decrement() {
        synchronized (this) {
            count--;
        }
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
        for(int i = 0; i < 1_000_000; i++) {
            counter.decrement();
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






















