package lesson09_deadlock;

public class AccountThread extends Thread {

    private final Account accountFrom;
    private final Account accountTo;

    public AccountThread(Account accountFrom, Account accountTo) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
    }

    @Override
    public void run() {
        /**
         * Нельзя брать несколько мониторов, только по одному,
         * поэтому несколько вложенных synchronized подряд.
         *
         * Так работает нормально.
         */
//        synchronized (accountFrom) {
//            synchronized (accountTo) {
//                for (int i = 0; i < 2000; i++) {
//                    if(accountFrom.takeOff(10)) {
//                        accountTo.add(10);
//                    }
//                }
//            }
//        }

        /**
         * Но вдруг хотим сделать синхронизированные блоки
         * не для всего цикла, а внутри цикла.
         *
         * Зависнет из-за дедлока.
         * Как в задаче про обедающих философов.
         *
         * 1 ---- account1_monitor -> account2_monitor
         * 2 ---- account2_monitor -> account1_monitor
         * Оба потока взаимно ждут освобождения второго монитора.
         * Ожидание бесконечное.
         *
         * Эта проблема решается путем использования классов Lock.
         */
        for (int i = 0; i < 20; i++) {
            synchronized (accountFrom) {
                synchronized (accountTo) {
                    if(accountFrom.takeOff(10)) {
                        accountTo.add(10);
                    }
                }
            }
        }
    }
}
