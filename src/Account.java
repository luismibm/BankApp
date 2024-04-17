import java.util.ArrayList;

import static java.lang.Math.abs;

public class Account {

    private final String iban;
    private final String holder;

    private double balance;
    private ArrayList<Double> transactions;
    private int nTransactions;

    private static final double minBalance = -50.0;
    private static final double alertTreasury = 3000.0;

    private boolean valid;

    public Account(String iban, String holder) throws AccountException {

        this.iban = iban;
        this.holder = holder;

        this.balance = 0.0;
        this.transactions = new ArrayList<>();
        this.nTransactions = 0;

        if (!iban.matches("^[A-Z]{2}\\d{22}")) {
            throw new AccountException("Wrong IBAN");
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

    public boolean deposit(double amount) throws AccountException, AlertTreasuryException{
        if (amount <= 0) {
            throw new AccountException("Deposit amount must be greater than 0");
        }
        return operate(amount);
    }

    public boolean withdraw(double amount) throws AccountException, AlertTreasuryException{
        if (amount <= 0) {
            throw new AccountException("Withdrawal amount must be greater than 0");
        }
        return operate(-amount);
    }

    private boolean operate(double amount) throws AccountException, AlertTreasuryException{

        if (balance + amount < minBalance) {
            throw new AccountException("Insuficient balance");
        }

        balance += amount;
        transactions.add(amount);

        if (balance < 0.0) {
            throw new AccountException("WARNING: Negative Balance");
        }
        if (abs(amount) > alertTreasury) {
            throw new AlertTreasuryException("WARNING: Alert Treasury: ", iban, holder, amount);
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
            System.out.println("#" + (i+1) + ": " + transactions.get(i));
        }
    }

}