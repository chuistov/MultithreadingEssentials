package lesson12_locks;

public class AccountThread extends Thread {

    private final Account accountFrom;
    private final Account accountTo;

    public AccountThread(Account accountFrom, Account accountTo) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2000; i++) {
            /**
             * Вместо synchronized вызываем метод блокировки аккаунтов
              */
            lockAccounts();

            /**
             * После перевода средств нужно отпустить локи, но лучше это делать в блоке finally,
             * потому что во время транзакции высока вероятность исключительной ситуации.
             */
            try {
                if (accountFrom.takeOff(10)) {
                    accountTo.add(10);
                }
            } finally {
                unlockAccounts();
            }
        }
    }

    private void lockAccounts() {
        while (true) {
            /**
             * Одинарная попытка захватить монитор
             */
            boolean fromLockResult = accountFrom.getLock().tryLock();
            boolean toLockResult = accountTo.getLock().tryLock();

            /**
             * Если оба лока успешно захвачены, переходим к транзакции
             */
            if (fromLockResult && toLockResult) {
                break;
            }

            /**
             * Если хотя бы один лок не захвачен,
             * нужно отпустить захваченный, чтобы не попасть в дедлок.
             *
             * Нельзя делать unlock, если монитор не захвачен, поэтому проверяем.
             */
            if (fromLockResult) {
                accountFrom.getLock().unlock();
            }
            if (toLockResult) {
                accountTo.getLock().unlock();
            }
        }
    }

    private void unlockAccounts() {
        accountTo.getLock().unlock();
        accountFrom.getLock().unlock();
    }
}