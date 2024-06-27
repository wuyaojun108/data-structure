package org.wyj.algothrim.sort;

import java.util.Arrays;

public class QuickSortTest {
    public static void main(String[] args) {
        int[] ints = {3, 4, 2, 3, 6, 1, 5, 6, 3};
        quickSort(ints);
        System.out.println("ints = " + Arrays.toString(ints));
    }

    public static void quickSort(int[] arr) {
        qs(arr, 0, arr.length - 1);
    }

    private static void qs(int[] arr, int left, int right) {
        if(left >= right){
            return;
        }
        int i = left;
        int j = right;
        // 基准数
        int base = arr[left];
        while (i < j) {
            // 从后向前找，找到比基准数小的数，也就是说，如果基准数小于等于当前数，则继续向前
            while (i < j && base <= arr[j])
                j--;
            // 从前向后找，找到比基准数大的数，也就是说，如果基准数大于等于当前数，则继续向后
            while (i < j && base >= arr[i])
                i++;
            if (i < j) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        arr[left] = arr[i];
        arr[i] = base;
        if(left < i - 1)
            qs(arr, left, i - 1);
        if(i + 1 < right)
            qs(arr, i + 1, right);
    }
}
