package com.example.java_learn.bookstore.util;

import com.example.java_learn.bookstore.exception.UnknownCreatorException;

@FunctionalInterface
public interface BiFunctionThrowable<T, U, R, E extends Throwable> {
    R apply(T var1, U var2) throws E, UnknownCreatorException;
}
