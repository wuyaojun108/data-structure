package org.wyj.algothrim.sort;

import java.util.Arrays;

public class InsertSort {

    public static void main(String[] args) {
        // 声明并初始化数组
        Integer[] arr = new Integer[50000]; // 32
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
        // 调用排序算法并计算排序所用时间
        long start = System.currentTimeMillis();
        insertSort(arr); // 2694
        long end = System.currentTimeMillis();
        long sub = end - start;
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        System.out.println("排序所用时间：" + sub + "毫秒");
    }

    /**
     *   插入排序：类似于整理手中的扑克牌。把数组的第一个元素看成是一个有序数组，第二个元素和第一个元素进行比较，
     * 如果比它小，则第一个元素后移一位，第二个元素放到一个元素的位置，否则，第二个元素放到原来的位置，此时，第
     * 一个元素和第二个元素组成了一个包含两个元素的有序数组，第三个、第四个元素依次类推，直到所有元素有序
     */
    public static <T extends Comparable<T>> void insertSort(T[] arr){
        for(int i = 1; i < arr.length; i++){
            T current = arr[i];
            int preIndex = i - 1;
            while (preIndex >= 0 && current.compareTo(arr[preIndex]) < 0){
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
    }


}
