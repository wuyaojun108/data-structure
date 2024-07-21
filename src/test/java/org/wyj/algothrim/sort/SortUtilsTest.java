package org.wyj.algothrim.sort;

import org.junit.Test;

/**
 * @auther 武耀君
 * @date 2024/7/18
 */
public class SortUtilsTest {
    // 测试冒泡排序
    @Test
    public void test1() {
        // 声明并初始化数组
        Integer[] arr = new Integer[50000]; // 32
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
        // 调用排序算法并计算排序所用时间
        long start = System.currentTimeMillis();
        SortUtils.bubbleSort(arr); // 9208
        long end = System.currentTimeMillis();
        long sub = end - start;
//        System.out.println("arr = " + Arrays.toString(arr));
        System.out.println("冒泡排序所用时间：" + sub + "毫秒");
    }

    // 测试选择排序
    @Test
    public void test2() {
        // 声明并初始化数组
        Integer[] arr = new Integer[50000]; // 32
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
        // 调用排序算法并计算排序所用时间
        long start = System.currentTimeMillis();
        SortUtils.selectSort(arr); // 2184
        long end = System.currentTimeMillis();
        long sub = end - start;
//        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        System.out.println("选择排序所用时间：" + sub + "毫秒");
    }

    // 测试插入排序
    @Test
    public void test3() {
        // 声明并初始化数组
        Integer[] arr = new Integer[50000]; // 32
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
        // 调用排序算法并计算排序所用时间
        long start = System.currentTimeMillis();
        SortUtils.insertSort(arr); // 2694
        long end = System.currentTimeMillis();
        long sub = end - start;
//        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        System.out.println("插入排序所用时间：" + sub + "毫秒");
    }

    // 测试希尔排序
    @Test
    public void test4() {
        // 声明并初始化数组
        int[] arr = new int[50000]; // 32
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
        // 调用排序算法并计算排序所用时间
        long start = System.currentTimeMillis();
        SortUtils.hillSort(arr); // 16
        long end = System.currentTimeMillis();
        long sub = end - start;
//        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        System.out.println("希尔排序所用时间：" + sub + "毫秒");
    }

    // 测试归并排序
    @Test
    public void test5() {
        // 声明并初始化数组
        Integer[] arr = new Integer[50000]; // 32
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
        // 调用排序算法并计算排序所用时间
        long start = System.currentTimeMillis();
        SortUtils.mergeSort(arr); // 13
        long end = System.currentTimeMillis();
        long sub = end - start;
//        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        System.out.println("归并排序所用时间：" + sub + "毫秒");
    }

    // 测试快速排序
    @Test
    public void test6() {
        // 声明并初始化数组
        Integer[] arr = new Integer[50000]; // 32
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
        // 调用排序算法并计算排序所用时间
        long start = System.currentTimeMillis();
        SortUtils.quickSort(arr); // 12
        long end = System.currentTimeMillis();
        long sub = end - start;
//        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        System.out.println("快速排序所用时间：" + sub + "毫秒");
    }

    // 测试堆排序
    @Test
    public void test7() {
        // 声明并初始化数组
        Integer[] arr = new Integer[100000]; // 32
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000000);
        }
        assert !SortUtils.checkSortProp(arr);

        // 调用排序算法并计算排序所用时间
        long start = System.currentTimeMillis();
        SortUtils.heapSort(arr); // 12
        long end = System.currentTimeMillis();
        long sub = end - start;
        // System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        System.out.println("堆排序所用时间：" + sub + "毫秒");

        assert SortUtils.checkSortProp(arr);
    }
}
