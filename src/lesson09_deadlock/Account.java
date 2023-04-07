package lesson09_deadlock;

public class Account {

    private static int generator = 1;

    private final int id;
    private int amount;

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

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }
}
