package org.wyj.algothrim.sort;

import java.util.Arrays;

public class InsertSortTest {
    public static void main(String[] args) {
        int[] ints = {5, 2, 1, 3, 4};
        insertSortTest(ints);
        System.out.println("ints = " + Arrays.toString(ints));
    }

    public static void insertSortTest(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            int preIndex = i - 1;
            while (preIndex >= 0 && current < arr[preIndex]){
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
    }
}
