package org.wyj.algothrim.sort;

import java.util.Arrays;

public class SelectSort {

    public static void main(String[] args) {
        // 声明并初始化数组
        Integer[] arr = new Integer[50000]; // 32
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
        // 调用排序算法并计算排序所用时间
        long start = System.currentTimeMillis();
        selectSort(arr); // 2184
        long end = System.currentTimeMillis();
        long sub = end - start;
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        System.out.println("排序所用时间：" + sub + "毫秒");
    }


    /**
     *  选择排序：第一个数和后面的所有数进行比较，找出最小的，和第一个数进行交换，第二个数再次进行这样的比较，
     * 直到倒数第一个数，因为当前面所有数有序之后，倒数第一个数一定是有序的
     */
    public static <T extends Comparable<T>> void selectSort(T[] arr){
        for(int i = 0; i < arr.length; i++){
            int min = i;
            for(int j = i; j < arr.length; j++){
                if(arr[min].compareTo(arr[j]) > 0){
                    min = j;
                }
            }
            if(min != i){
                T tmp = arr[min];
                arr[min] = arr[i];
                arr[i] = tmp;
            }
        }
    }
}
