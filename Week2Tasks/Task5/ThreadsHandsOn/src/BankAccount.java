import javax.swing.table.TableRowSorter;

class Transaction extends Thread {
    private final BankAccount account;
    private final boolean isDeposit;
    private final int amount;
    public Transaction(BankAccount account, boolean  isDeposit, int amount) {
        this.account = account;
        this.isDeposit = isDeposit;
        this.amount = amount;
    }
    @Override
    public void run() {
        if(isDeposit) {
            account.deposit(amount);
        } else {
            account.withdraw(amount);
        }
    }
}

public class BankAccount {
    private int balance;
    public BankAccount(int balance) {
        this.balance = balance;
    }
    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() + " deposited " + amount);
        System.out.println("Curr balance: " + balance);
    }
    public synchronized void withdraw(int amount) {
        if(balance >= amount) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " withdrew " + amount);
            System.out.println("Curr balance: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " attempted to withdraw " + amount);
            System.out.println("Curr balance: " + balance);
        }
    }
    public int getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(10000);
        Thread t1 = new Transaction(account, true, 200);
        Thread t2 = new Transaction(account, false, 10);
        Thread t3 = new Transaction(account, true, 20);
        Thread t4 = new Transaction(account, false, 10);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException err) {
            err.printStackTrace();
        }
        System.out.println("Balance: " + account.getBalance());
    }
}
