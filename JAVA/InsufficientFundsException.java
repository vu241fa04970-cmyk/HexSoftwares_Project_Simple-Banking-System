package com.hexsoftwares.banking;

/**
 * Custom exception thrown when attempting to withdraw more funds than available.
 */
public class InsufficientFundsException extends RuntimeException {
    /**
     * Constructor for InsufficientFundsException.
     *
     * @param message Detailed error message
     */
    public InsufficientFundsException(String message) {
        super(message);
    }

    /**
     * Constructor for InsufficientFundsException with cause.
     *
     * @param message Detailed error message
     * @param cause   The cause of the exception
     */
    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}