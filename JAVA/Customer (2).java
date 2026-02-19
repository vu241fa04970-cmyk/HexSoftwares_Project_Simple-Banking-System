package com.hexsoftwares.banking;

/**
 * Represents a customer in the banking system.
 */
public class Customer {
    private String customerId;
    private String name;
    private String email;
    private BankAccount account;

    /**
     * Constructor to create a new customer.
     *
     * @param customerId Unique customer identifier
     * @param name       Customer's full name
     * @param email      Customer's email address
     */
    public Customer(String customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.account = null;
    }

    /**
     * Creates a new bank account for the customer.
     *
     * @param accountNumber Unique account number
     * @param initialDeposit Initial deposit amount
     * @throws IllegalArgumentException if account number is empty or initial deposit is invalid
     */
    public void createAccount(String accountNumber, double initialDeposit) {
        if (accountNumber == null || accountNumber.isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be empty");
        }
        if (initialDeposit < 0) {
            throw new IllegalArgumentException("Initial deposit cannot be negative");
        }
        this.account = new BankAccount(accountNumber, customerId, initialDeposit);
    }

    /**
     * Gets the customer's bank account.
     *
     * @return The BankAccount object, or null if no account has been created
     */
    public BankAccount getAccount() {
        return account;
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
     * Gets the customer's name.
     *
     * @return Customer's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the customer's email.
     *
     * @return Customer's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the customer's email.
     *
     * @param email New email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}