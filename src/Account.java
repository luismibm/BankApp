import static java.lang.Math.abs;

public class Account {

    private final String iban;
    private final String holder;

    private double balance;
    private double[] transactions;
    private int nTransactions;

    private static final int maxTransactions = 100;
    private static final double minBalance = -50.0;
    private static final double alertTreasury = 3000.0;

    private boolean valid;

    public Account(String iban, String holder) {

        this.iban = iban;
        this.holder = holder;

        this.balance = 0.0;
        this.transactions = new double[maxTransactions];
        this.nTransactions = 0;

        if (!iban.matches("^[A-Z]{2}\\d{22}")) {
            System.err.println("Wrong IBAN");
            this.valid = false;
        } else {
            this.valid = true;
        }

    }

    public String getIban() {
        return iban;
    }

    public String getHolder() {
        return holder;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isValid() {
        return valid;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }
        return operate(amount);
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            return false;
        }
        return operate(-amount);
    }

    private boolean operate(double amount) {

        if ((balance + amount < minBalance) || (nTransactions >= maxTransactions)) {
            return false;
        }

        balance += amount;
        transactions[nTransactions] = amount;
        nTransactions++;

        if (balance < 0.0) {
            System.err.println("WARNING: Negative Balance");
        }
        if (abs(amount) > alertTreasury) {
            System.err.println("WARNING: Alert Treasury");
        }

        return true;

    }

    public void printAll() {
        printData();
        printTransactions();
    }

    public void printData() {
        System.out.println(getIban() + " | " + getHolder() + " | " + getBalance());
    }

    public void printTransactions() {
        System.out.println("Number of Transactions: " + nTransactions);
        for (int i = 0; i < nTransactions; i++) {
            System.out.println("#" + (i+1) + ": " + transactions[i]);
        }
    }

}