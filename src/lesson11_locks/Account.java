package lesson11_locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private static int generator = 1;

    private final int id;
    private int amount;

    /**
     * добавляем лок
      */
    private final Lock lock = new ReentrantLock();

    public Account(int amount) {
        this.amount = amount;
        this.id = generator++;
    }

    public void add(int amount) {
        this.amount += amount;
    }

    public boolean takeOff(int amount) {
        if(this.amount >= amount) {
            this.amount -= amount;
            return true;
        }
        return false;
    }

    public Lock getLock() {
        return lock;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }
}
