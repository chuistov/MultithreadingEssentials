package lesson08_wait_notify_notifyAll;

import java.util.Queue;

public class BuyerThread implements Runnable {

    private final Queue<CashBox> cashBoxes;

    public BuyerThread(Queue<CashBox> cashBoxes) {
        this.cashBoxes = cashBoxes;
    }

    @Override
    public void run() {
        try {
            while (true) {

                /**
                 * Здесь возможна ситуация, когда одну кассу занимает не один покупатель, а два или более.
                 * Также возможен NoSuchElementException, если два потока увидят,
                 * что !cashBoxes.isEmpty() и оба сделают cashBoxes.remove().
                 */
//                if(!cashBoxes.isEmpty()) {
//                    CashBox cashBox = cashBoxes.remove();
//                    System.out.println(Thread.currentThread().getName()
//                            + " is being serviced by cashbox #"
//                            + cashBox.getId());
//
//                    Thread.sleep(5L);
//
//                    System.out.println(Thread.currentThread().getName()
//                            + " has released cashbox #"
//                            + cashBox.getId());
//                    cashBoxes.add(cashBox);
//
//                    break;
//                } else {
//                    System.out.println(Thread.currentThread().getName()
//                            + " is waiting for a spare cashbox");
//                    Thread.sleep(5L);
//                }

                /**
                 * Чтобы решить эту проблему, поместим if-else в блок synchronized.
                 * Получается последовательное выполнение, ведь "занимаются" сразу все кассы.
                 * Метод sleep() удерживает монитор.
                 */
//                synchronized (cashBoxes) {
//                    if(!cashBoxes.isEmpty()) {
//                        CashBox cashBox = cashBoxes.remove();
//                        System.out.println(Thread.currentThread().getName()
//                                + " is being serviced by cashbox #"
//                                + cashBox.getId());
//
//                        Thread.sleep(5L);
//
//                        System.out.println(Thread.currentThread().getName()
//                                + " has released cashbox #"
//                                + cashBox.getId());
//                        cashBoxes.add(cashBox);
//
//                        break;
//                    } else {
//                        System.out.println(Thread.currentThread().getName()
//                                + " is waiting for a spare cashbox");
//                        Thread.sleep(5L);
//                    }
//                }

                /**
                 * Поэтому надо вместо Thread.sleep() вызвать метод wait()
                 * для занятого объекта: cashBoxes.wait(5L).
                 * Метод sleep() освобождают монитор, в это время
                 * его могут захватить другие потоки.
                 * После завершения заданного периода нужно вновь захватить монитор
                 * (мы же все еще в блоке synchronized).
                 * wait() можно вызывать только в синхронизированном блоке, иначе исключение.
                 *
                 * Также в блоке else нужно вызвать cashBoxes.wait(),
                 * причем время лучше не задавать, чтобы ждать, пока касса не освободится.
                 *
                 * Когда вызван метод wait():
                 * a) если указано время, то состояние потока TimeWaiting,
                 * b) если время не указано, то состояние Waiting.
                 *
                 * Однако если вызван wait(), то поток будет ждать вечно,
                 * если не придет уведомление notify() или notifyAll().
                 *
                 * notify() уведомляет один ждущий поток (рандомно);
                 * notifyAll() уведомляет все потоки.
                 *
                 * notify() или notifyAll() нужно вызвать,
                 * когда вернули кассу в коллекцию cashBoxes.
                 *
                 * Когда вызван notifyAll(), все потоки не сразу кидаются за монитором.
                 * Они остаются в состоянии Waiting, пока вызвавший notifyAll() поток
                 * не выйдет из своего блока synchronized.
                 *
                 * И более правильно цикл while поставить внутрь блока synchronized.
                 * При этом сначала будет захвачен монитор на cashboxes,
                 * затем будет цикл, где потоки борются за этот монитор.
                 */
                synchronized (cashBoxes) {
                    if(!cashBoxes.isEmpty()) {
                        CashBox cashBox = cashBoxes.remove();
                        System.out.println(Thread.currentThread().getName()
                                + " is being serviced by cashbox #"
                                + cashBox.getId());

                        cashBoxes.wait(5L);

                        System.out.println(Thread.currentThread().getName()
                                + " has released cashbox #"
                                + cashBox.getId());
                        cashBoxes.add(cashBox);

                        cashBoxes.notify();
//                        cashBoxes.notifyAll(); // или так

                        break;
                    } else {
                        System.out.println(Thread.currentThread().getName()
                                + " is waiting for a spare cashbox");
                        cashBoxes.wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
