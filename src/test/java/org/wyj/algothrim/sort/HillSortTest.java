package org.wyj.algothrim.sort;

import java.util.Arrays;

public class HillSortTest {
    public static void main(String[] args) {
        int[] ints = {5, 2, 1, 3, 4};
        hillSort(ints);
        System.out.println("ints = " + Arrays.toString(ints));
    }

    public static void hillSort(int[] arr) {
        int gap = arr.length / 2;
        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                int current = arr[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && current < arr[preIndex]) {
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }
                arr[preIndex + gap] = current;
            }
            gap = gap / 2;
        }

    }
}
