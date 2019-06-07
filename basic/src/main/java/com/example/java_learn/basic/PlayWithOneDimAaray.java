package com.example.java_learn.basic;

public class PlayWithOneDimAaray {

    public static void main(String[] args) {
        int[] arr = {1, 5, 101, 102, 3, 3, 3, 3, 3, -2};
        int max = arr[0];
        int indexOfMax = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                indexOfMax = i;
            }

            if (arr[i] > 100)
                break;
        }

        System.out.println(indexOfMax);
    }
}
