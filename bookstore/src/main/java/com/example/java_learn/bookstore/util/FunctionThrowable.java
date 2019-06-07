package com.example.java_learn.bookstore.util;

@FunctionalInterface
public interface FunctionThrowable<T, R, E extends Throwable> {
    R apply(T var1) throws E;
}
