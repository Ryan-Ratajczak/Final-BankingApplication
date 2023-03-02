package bankingapplication.models.accounts;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>abstract bank account class</p>
 * Has a name and balance and allows Users to view, deposit, withdraw, and transfer from the balance
 * @author Ryan Ratajczak
 */
public abstract class BankAccount
{
    private String name;
    private double balance;
    private double interestRate;
    private int withdrawals = 6;
    private boolean withdrawalsReset = false;
    private List<String> transactions = new ArrayList<>();

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("'name' cannot be null or blank");
        }
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getWithdrawals() {
        return withdrawals;
    }

    public void setWithdrawals(int withdrawals) {
        this.withdrawals = withdrawals;
    }

    public boolean isWithdrawalsReset() {
        return withdrawalsReset;
    }

    public void setWithdrawalsReset(boolean withdrawalsReset) {
        this.withdrawalsReset = withdrawalsReset;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    private void addTransaction(String transaction) {
        if (transaction == null || transaction.isBlank()) {
            throw new IllegalArgumentException("'transaction' message cannot be null or blank");
        }
        this.transactions.add(transaction);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("'amount' cannot be less than or equal to 0");
        }
        setBalance(getBalance() + amount);
        addTransaction("+ $" + amount);
    }

    public void withdraw(double amount) {
        if (amount > getBalance() || amount <= 0) {
            throw new IllegalArgumentException("'amount' cannot be greater than balance or less than or equal to zero");
        }
        setBalance(getBalance() - amount);
        addTransaction("- $" + amount);
    }

    public void transfer(BankAccount sender, BankAccount receiver, double amount) {
        if (sender == null || receiver == null) {
            throw new IllegalArgumentException("Either BankAccount cannot be null");
        }
        if (amount > sender.getBalance()) {
            throw new IllegalArgumentException("'amount' cannot be greater than sender's current balance");
        }
        sender.withdraw(amount);
        receiver.deposit(amount);
        addTransaction(sender.name + "->" + receiver.name);
    }
}
