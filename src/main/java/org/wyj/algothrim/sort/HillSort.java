package org.wyj.algothrim.sort;

import java.util.Arrays;

public class HillSort {

    public static void main(String[] args) {
        // 声明并初始化数组
        int[] arr = new int[50000]; // 32
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
        // 调用排序算法并计算排序所用时间
        long start = System.currentTimeMillis();
        hillSort(arr); // 16
        long end = System.currentTimeMillis();
        long sub = end - start;
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        System.out.println("排序所用时间：" + sub + "毫秒");

    }


    /**
      * 希尔排序：插入排序的优化版，又称缩小增量排序。插入排序会按部就班地将每个元素和它前面的所有元素进行比较，直到
      * 找到比自己小的，插入到它的后面，而希尔排序，是在插入排序的基础上增加了一个增量的概念，例如，在插入排序中，
      * 第5个元素中会和它前面的所有元素进行比较，但是在希尔排序中，加入增量是4，第5个元素会直接和第1个元素进行比较，
      * 然后第6个和第3个比较，第7个和第4个比较，这种比较方式减少了元素的交换次数，原先需要交换多次才能实现目的，现在
      * 交换依次即可。在希尔排序的实现上，增量通常是 数组的长度 / 2，在比较完一次之后，增量再次除以2，直到增量为0
     */
    public static void hillSort(int[] arr){
        int gap = arr.length / 2;
        while (gap > 0){
            for(int i = gap; i < arr.length; i++){
                int current = arr[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && current < arr[preIndex]){
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex = preIndex - gap;
                }
                arr[preIndex + gap] = current;
            }
            gap = gap / 2;
        }
    }
}
