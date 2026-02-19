package com.hexsoftwares.banking;

/**
 * Demo application showcasing the banking system functionality.
 */
public class BankingApp {
    public static void main(String[] args) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("HexSoftwares Banking System - Demo Application");
        System.out.println("=".repeat(70) + "\n");

        try {
            // Create customers
            System.out.println(">>> Creating customers...");
            Customer customer1 = new Customer("CUST001", "John Doe", "john@email.com");
            Customer customer2 = new Customer("CUST002", "Jane Smith", "jane@email.com");
            System.out.println("✓ Customers created successfully\n");

            // Create accounts
            System.out.println(">>> Creating bank accounts...");
            customer1.createAccount("ACC123456789", 5000.00);
            customer2.createAccount("ACC987654321", 3000.00);
            System.out.println("✓ Accounts created successfully\n");

            // Get accounts
            BankAccount account1 = customer1.getAccount();
            BankAccount account2 = customer2.getAccount();

            // Display initial balance
            System.out.println(">>> Initial Balances:");
            System.out.println(customer1.getName() + ": $" + String.format("%.2f", account1.getBalance()));
            System.out.println(customer2.getName() + ": $" + String.format("%.2f", account2.getBalance()) + "\n");

            // Perform transactions
            System.out.println(">>> Performing transactions for " + customer1.getName() + "...");
            account1.deposit(1500.00);
            account1.deposit(2500.50);
            account1.withdraw(2000.00);
            account1.withdraw(1500.25);
            System.out.println();

            System.out.println(">>> Performing transactions for " + customer2.getName() + "...");
            account2.deposit(5000.00);
            account2.withdraw(2500.00);
            System.out.println();

            // Display final balances
            System.out.println(">>> Final Balances:");
            System.out.println(customer1.getName() + ": $" + String.format("%.2f", account1.getBalance()));
            System.out.println(customer2.getName() + ": $" + String.format("%.2f", account2.getBalance()) + "\n");

            // Print account statements
            System.out.println(">>> Account Statements:");
            account1.printStatement();
            account2.printStatement();

            // Test error handling
            System.out.println(">>> Testing Error Handling...");
            System.out.println("\n1. Attempting to withdraw more than available balance:");
            try {
                account1.withdraw(50000.00);
            } catch (InsufficientFundsException e) {
                System.out.println("✗ Error caught: " + e.getMessage() + "\n");
            }

            System.out.println("2. Attempting to deposit negative amount:");
            try {
                account2.deposit(-500.00);
            } catch (IllegalArgumentException e) {
                System.out.println("✗ Error caught: " + e.getMessage() + "\n");
            }

            System.out.println("3. Attempting to withdraw zero amount:");
            try {
                account1.withdraw(0);
            } catch (IllegalArgumentException e) {
                System.out.println("✗ Error caught: " + e.getMessage() + "\n");
            }

            System.out.println("=".repeat(70));
            System.out.println("Demo completed successfully!");
            System.out.println("=".repeat(70) + "\n");

        } catch (Exception e) {
            System.err.println("✗ Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}