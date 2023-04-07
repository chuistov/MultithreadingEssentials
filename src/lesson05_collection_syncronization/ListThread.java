package lesson05_collection_syncronization;

import java.util.List;

public class ListThread extends Thread {

    private final List<Integer> list;

    public ListThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 400; i++) {
            list.add(i);

            /**
             * Вариант 1.
             * Это не решает проблему, поскольку методы list не синхронизированы;
             * другой поток может вызвать другой метод.
             */
//            synchronized (list) {
//                list.add(i);
//            }
        }
    }
}
