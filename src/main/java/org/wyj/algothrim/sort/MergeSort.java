package org.wyj.algothrim.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        // 声明并初始化数组
        Integer[] arr = new Integer[50000]; // 32
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
        // 调用排序算法并计算排序所用时间
        long start = System.currentTimeMillis();
        mergeSort(arr); // 13
        long end = System.currentTimeMillis();
        long sub = end - start;
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        System.out.println("排序所用时间：" + sub + "毫秒");
    }


    /**
     * 归并排序：将序列从左往右，进行对半拆分，拆分出来的一半再次进行对半拆分，一直拆分到每个子序列都只有两个元素，
     * 将这两个子元素视为两个有序序列，使用一个辅助数组，把两个序列合并，合并时，序列中的第一个元素相互比较，小的元素
     * 先放进辅助数组中，然后再和另外一个数组中的下一个元素进行比较，直到其中一个数组为空，另一个数组的剩余元素也放进
     * 辅助数组中。再把合并后的序列进行两两合并，直到最终将所有的子序列全部合并
     *
     * 拆分数组的逻辑和排序的逻辑
     */
    public static <T extends Comparable<T>> void mergeSort(T[] arr) {
        ms(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void ms(T[] arr,
                                                    int start, int end) {
        if (start >= end)
            return;
        int mid = (start + end) / 2;
        ms(arr, start, mid);
        ms(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }

    // start到mid是第一个数组的元素，mid+1到end是第二个数组的元素，对这两个数组进行归并操作
    public static <T extends Comparable<T>> void merge(T[] arr, int start,
                                                       int mid, int end) {
        Object[] tmpArr = new Object[end - start + 1];
        int k = 0;
        int i = start;
        int j = mid + 1;
        while (i <= mid && j <= end) {
            if (arr[i].compareTo(arr[j]) < 0) {
                tmpArr[k++] = arr[i++];
            } else {
                tmpArr[k++] = arr[j++];
            }
        }
        while (i <= mid)
            tmpArr[k++] = arr[i++];
        while (j <= end)
            tmpArr[k++] = arr[j++];
        System.arraycopy(tmpArr, 0, arr, start, tmpArr.length);
    }

}
