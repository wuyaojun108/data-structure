package org.wyj.algothrim.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        // 声明并初始化数组
        Integer[] arr = new Integer[50000]; // 32
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
        // 调用排序算法并计算排序所用时间
        long start = System.currentTimeMillis();
        quickSort(arr); // 12
        long end = System.currentTimeMillis();
        long sub = end - start;
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        System.out.println("排序所用时间：" + sub + "毫秒");
    }


    /**
     * 快速排序：
     * 第一步：选择数组最左侧的元素作为基准数。初始化两个指针，一个指向数组的左侧基准数之后的元素，
     *       叫做i，一个指向数组的最右侧，叫做j，
     * 第二步：在i小于j的情况下，从后向前找，找到比基准数小的数，也就是说，如果基准数小于等于当前数，则继续向前
     * 第三步：在i小于j的情况下，从前向后找，找到比基准数大的数，也就是说，如果基准数大于等于当前数，则继续向后
     * 第三步：在两次寻找结束后，如果i<j，则交换i和j位置上的变量
     * 第四步：重复第二步的动作，直到i=j，此时i指向的元素一定比基准数小，或者就是基准数本身。
     *       因为先从后向前找比基准数小的数字，然后再从前向后找比基准数大的数字
     *       如果i=j，此时j一定停留在一个比基准数小的数字上，或者停留在基准数上，所以i指向的数字一定小于等于基准数。
     *       然后i指向的元素和基准数交换位置
     * 第五步：此时i指针指向的元素就是基准数，如果i指针左边的元素的个数大于1，递归地执行第1到3步，如果i指针右边
     *       的元素个数大于1，也递归地执行第1到3步。
     *
     * 基准数
     * 一趟排序将数组分为两个部分
     */
    public static <T extends Comparable<T>> void quickSort(T[] arr){
        qs(arr, 0, arr.length - 1);
    }

    public static <T extends Comparable<T>> void qs(T[] arr, int left, int right){
        T base = arr[left];
        int i = left, j = right;
        while (i < j){
            while (arr[j].compareTo(base) >= 0 && i < j) j--;
            while (arr[i].compareTo(base) <= 0 && i < j) i++;
            if(i < j){
                T tmp = arr[i];
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
