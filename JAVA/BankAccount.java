package com.hexsoftwares.banking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a bank account with deposit, withdrawal, and balance tracking.
 */
public class BankAccount {
    private String accountNumber;
    private String customerId;
    private double balance;
    private List<Transaction> transactionHistory;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Inner class to represent a transaction.
     */
    private static class Transaction {
        String type; // DEPOSIT or WITHDRAWAL
        double amount;
        double balanceAfter;
        LocalDateTime timestamp;

        Transaction(String type, double amount, double balanceAfter) {
            this.type = type;
            this.amount = amount;
            this.balanceAfter = balanceAfter;
            this.timestamp = LocalDateTime.now();
        }
    }

    /**
     * Constructor to create a new bank account.
     *
     * @param accountNumber  Unique account number
     * @param customerId     Associated customer ID
     * @param initialDeposit Initial deposit amount
     * @throws IllegalArgumentException if initial deposit is negative
     */
    public BankAccount(String accountNumber, String customerId, double initialDeposit) {
        if (initialDeposit < 0) {
            throw new IllegalArgumentException("Initial deposit cannot be negative");
        }
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.balance = initialDeposit;
        this.transactionHistory = new ArrayList<>();

        if (initialDeposit > 0) {
            transactionHistory.add(new Transaction("DEPOSIT", initialDeposit, balance));
        }
    }

    /**
     * Deposits money into the account.
     *
     * @param amount Amount to deposit
     * @throws IllegalArgumentException if amount is negative or zero
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }
        balance += amount;
        transactionHistory.add(new Transaction("DEPOSIT", amount, balance));
        System.out.println("✓ Successfully deposited $" + String.format("%.2f", amount));
    }

    /**
     * Withdraws money from the account.
     *
     * @param amount Amount to withdraw
     * @throws IllegalArgumentException if amount is negative or zero
     * @throws InsufficientFundsException if amount exceeds balance
     */
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero");
        }
        if (amount > balance) {
            throw new InsufficientFundsException(
                    "Insufficient funds! Attempted to withdraw: $" + String.format("%.2f", amount) +
                            ", Available balance: $" + String.format("%.2f", balance)
            );
        }
        balance -= amount;
        transactionHistory.add(new Transaction("WITHDRAWAL", amount, balance));
        System.out.println("✓ Successfully withdrew $" + String.format("%.2f", amount));
    }

    /**
     * Gets the current account balance.
     *
     * @return Current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Gets the account number.
     *
     * @return Account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Gets the customer ID.
     *
     * @return Customer ID
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Gets the transaction history.
     *
     * @return List of transactions
     */
    public List<String> getTransactionHistory() {
        List<String> history = new ArrayList<>();
        for (Transaction t : transactionHistory) {
            history.add(t.type + ": $" + String.format("%.2f", t.amount) +
                    " | Balance: $" + String.format("%.2f", t.balanceAfter) +
                    " | " + t.timestamp.format(formatter));
        }
        return history;
    }

    /**
     * Prints a formatted account statement.
     */
    public void printStatement() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("ACCOUNT STATEMENT");
        System.out.println("=".repeat(70));
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Customer ID: " + customerId);
        System.out.println("Current Balance: $" + String.format("%.2f", balance));
        System.out.println("=".repeat(70));
        System.out.println("TRANSACTION HISTORY:");
        System.out.println("-".repeat(70));
        System.out.printf("%-20s %-15s %-15s %s\n", "Type", "Amount", "Balance", "Timestamp");
        System.out.println("-".repeat(70));

        for (Transaction t : transactionHistory) {
            System.out.printf("%-20s $%-14.2f $%-14.2f %s\n",
                    t.type,
                    t.amount,
                    t.balanceAfter,
                    t.timestamp.format(formatter));
        }
        System.out.println("=".repeat(70) + "\n");
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", customerId='" + customerId + '\'' +
                ", balance=" + balance +
                ", transactions=" + transactionHistory.size() +
                '}';
    }
}