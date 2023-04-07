package lesson13_ThreadPool_Future_Callable;

import java.util.Optional;
import java.util.Queue;

public class PoolThread extends Thread {


    // Список задач для выполнения потоком
    // Когда поток освобождается, выполняет очередную задачу
    private final Queue<Runnable> tasks;

    public PoolThread(Queue<Runnable> tasks) {
        this.tasks = tasks;
    }

    // TODO Реализовать то же с BlockingQueue
    @Override
    public void run() {
        while (true) {
            Optional<Runnable> task = Optional.empty();
            synchronized (tasks) {
                // если в очереди есть задача, забираем эту задачу из очереди
                if (!tasks.isEmpty()) {
                    task = Optional.of(tasks.remove());
                }
            }

            // если взяли задачу, выполняем
            task.ifPresent(Runnable::run);
        }
    }
}
