package com.example.java_learn.basic;

public class LoopFor {

    public static void main(String[] args) {
        int anchor = 8;
        int sumOfEvenNumbers = 0;

        for (int i = 0; i <= anchor; i++) {
            if (i % 2 == 0)
                sumOfEvenNumbers += i;
            else
                System.out.print(i + " ");
        }
        System.out.println();

        System.out.println(sumOfEvenNumbers);
    }
}
