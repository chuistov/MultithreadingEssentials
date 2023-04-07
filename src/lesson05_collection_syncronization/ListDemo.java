package lesson05_collection_syncronization;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {

/**
 * Может быть просто произвольное количество элементов в списке.
 *
 * Может вылететь ArrayIndexOutOfBoundsException.
 * Это связано с кэшированием значения в потоках,
 * из-за чего потоки не знают об изменениях этого значения в других потоках.
 *
 * */
    public static void main(String[] args) throws InterruptedException {

        /**
         * Вариант 2.
         * Создать синхронизированную коллекцию на базе списка.
         * Это обертка вокруг списка, где все методы переопределены
         * с использованием блока synchronized, где в качестве монитора
         * используется не сам объект, а mutex - отдельный объект для захвата монитора.
         * Тем самым разделяются обязанности.
         *
         * Однако обращение с монитором - затратная операция.
         * Поэтому такую конструкцию следует использовать, если уже имеется коллекция
         * и нужно работать именно с ней, без вариантов.
         */
//        List<Integer> list = Collections.synchronizedList(new ArrayList<>());

        /**
         * Вариант 3.
         * Начиная с Java 1.5, имеется пакет util.concurrent.
         * Он содержит потокобезопасные коллекции.
         */
        List<Integer> list = new ArrayList<>();

        ListThread thread1 = new ListThread(list);
        ListThread thread2 = new ListThread(list);
        ListThread thread3 = new ListThread(list);
        ListThread thread4 = new ListThread(list);
        ListThread thread5 = new ListThread(list);
        ListThread thread6 = new ListThread(list);
        ListThread thread7 = new ListThread(list);
        ListThread thread8 = new ListThread(list);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();

        Thread.sleep(100);

        System.out.println(list.size());
    }
}
