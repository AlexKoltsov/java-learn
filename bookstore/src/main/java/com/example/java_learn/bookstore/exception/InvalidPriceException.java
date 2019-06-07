package com.example.java_learn.bookstore.exception;

public class InvalidPriceException extends Exception {

    public InvalidPriceException(String message) {
        super(message);
    }

    public InvalidPriceException() {
        super();
    }
}
