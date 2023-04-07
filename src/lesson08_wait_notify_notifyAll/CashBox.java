package lesson08_wait_notify_notifyAll;

public class CashBox {

    private static int generator = 1;
    private int id;

    public CashBox() {
        /**
         * Эта реализация не потокобезопасна, так как при одновременном вызове new CashBox()
         * из двух разных потоков будут созданы два экземпляра CashBox с одинаковым id.
         * Поэтому все CashBox будут созданы из одного метода main.
         */
        this.id = generator++;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CashBox{" +
                "id=" + id +
                '}';
    }
}
