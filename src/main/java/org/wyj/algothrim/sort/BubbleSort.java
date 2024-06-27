package org.wyj.algothrim.sort;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        // 声明并初始化数组
        Integer[] arr = new Integer[50000]; // 32
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
        // 调用排序算法并计算排序所用时间
        long start = System.currentTimeMillis();
        bubbleSort(arr); // 9208
        long end = System.currentTimeMillis();
        long sub = end - start;
        System.out.println("arr = " + Arrays.toString(arr));
        System.out.println("排序所用时间：" + sub + "毫秒");
    }


    /**
     * 冒泡排序：两两交换，大数下沉
     * 第一趟循环，确定数组的最后一位元素，第二趟循环，确定数组的倒数第二位元素，
     * 以此类推，直到数组的第一位元素，因为当其它元素都有序时，数组的第一位元素
     * 就是有序的，所以共需要进行n-1次循环，n-1次循环是外层循环，在外层循环中，
     * 还需要进行n-1-i次内层循环，用于实现两两交换的比较
     */
    public static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }
}
