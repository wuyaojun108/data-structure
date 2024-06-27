package org.wyj.algothrim.sort;

import java.util.Arrays;

public class SelectSortTest {
    public static void main(String[] args) {
        int[] ints = {5, 2, 1, 3, 4};
        selectSort(ints);
        System.out.println("ints = " + Arrays.toString(ints));
    }

    public static void selectSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            if(min != i){
                int tmp = arr[min];
                arr[min] = arr[i];
                arr[i] = tmp;
            }
        }
    }
}
