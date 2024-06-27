package org.wyj.algothrim.sort;

import java.util.Arrays;

public class MergeSortTest {
    public static void main(String[] args) {
        int[] ints = {5, 2, 1, 3, 4};
        mergeSort(ints);
        System.out.println("ints = " + Arrays.toString(ints));
    }

    public static void mergeSort(int[] arr) {
        ms(arr, 0, arr.length - 1);
    }

    private static void ms(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        ms(arr, start, mid);
        ms(arr, mid + 1, end);
        mSort(arr, start, mid, end);
    }

    private static void mSort(int[] arr, int start, int mid, int end) {
        int[] tmpArr = new int[end - start + 1];
        int k = 0;
        int i = start;
        int j = mid + 1;
        while (i <= mid && j <= end) {
            if (arr[i] < arr[j]) {
                tmpArr[k++] = arr[i++];
            } else {
                tmpArr[k++] = arr[j++];
            }
        }
        while (i <= mid){
            tmpArr[k++] = arr[i++];
        }
        while (j <= end){
            tmpArr[k++] = arr[j++];
        }
        System.arraycopy(tmpArr, 0, arr, start, tmpArr.length);

    }
}
