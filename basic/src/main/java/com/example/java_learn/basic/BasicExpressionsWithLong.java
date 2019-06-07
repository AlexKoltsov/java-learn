package com.example.java_learn.basic;

public class BasicExpressionsWithLong {

    public static void main(String[] args) {
        Long x1 = 42L;
        Long x2 = -17L;

        if (x1 % 2 == 0) {
            System.out.println(x1 + x2);
        }

        if (x2 % 2 == 0) {
            System.out.println(x1 - x2);
        }

        if (x1 > 0) {
            System.out.println(x1 * x2);
        }

        if (x2 > 0) {
            System.out.println(x1 / x2);
        }
    }
}
