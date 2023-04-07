package lesson08_wait_notify_notifyAll;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

public class CashBoxRunner {

    public static void main(String[] args) throws InterruptedException {
        Queue<CashBox> cashBoxes = new ArrayDeque<>(List.of(new CashBox(), new CashBox()));

        // Красиво создаем и стартуем потоки через стримы.
        List<Thread> threads = Stream.of(
                        new BuyerThread(cashBoxes),
                        new BuyerThread(cashBoxes),
                        new BuyerThread(cashBoxes),
                        new BuyerThread(cashBoxes),
                        new BuyerThread(cashBoxes),
                        new BuyerThread(cashBoxes),
                        new BuyerThread(cashBoxes),
                        new BuyerThread(cashBoxes),
                        new BuyerThread(cashBoxes),
                        new BuyerThread(cashBoxes)
                )
                .map(Thread::new)
                .peek(Thread::start)
                .toList();

        for (Thread thread: threads) {
            thread.join();
        }
    }
}
