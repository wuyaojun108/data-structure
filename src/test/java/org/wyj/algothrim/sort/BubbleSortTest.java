package org.wyj.algothrim.sort;

import java.util.Arrays;

public class BubbleSortTest {

    public static void main(String[] args) {
        int[] ints = {5, 2, 1, 3, 4};
        bubbleSort(ints);
        System.out.println("ints = " + Arrays.toString(ints));
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

}
