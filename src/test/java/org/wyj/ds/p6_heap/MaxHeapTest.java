package org.wyj.ds.p6_heap;

import org.junit.Test;

import java.util.Arrays;

/**
 * @auther 武耀君
 * @date 2024/7/14
 */
public class MaxHeapTest {
    // 测试堆的增删改查
    @Test
    public void test1() {
        MaxHeap<Integer> heap = new MaxHeap<>();
        heap.add(50);
        heap.add(75);
        heap.add(25);

        assert heap.size() == 3;
        assert !heap.isEmpty();
        assert heap.delMax().equals(75);
        assert heap.delMax().equals(50);
        assert heap.delMax().equals(25);
        assert heap.isEmpty();

        heap.add(50);
        heap.add(75);
        heap.add(25);
        assert heap.delMax().equals(75);
        assert heap.delMax().equals(50);
        assert heap.delMax().equals(25);
    }

    // 测试从堆中移除最大元素
    @Test
    public void test2() {
        MaxHeap<Integer> heap = new MaxHeap<>();
        heap.add(50);
        heap.add(75);
        heap.add(100);
        heap.add(20);

        assert heap.delMax().equals(100);
        assert heap.delMax().equals(75);
        assert heap.delMax().equals(50);
        assert heap.delMax().equals(20);
    }

    // 测试根据下标操作元素
    @Test
    public void test3() {
        MaxHeap<Integer> heap = new MaxHeap<>();
        heap.add(50);
        heap.add(75);
        heap.add(100);
        heap.add(20);

        assert heap.indexOf(75) == 3;
        assert heap.indexOf(101) == -1;
        assert heap.contains(75);
        assert !heap.contains(102);

        assert heap.get(1).equals(100);
        assert heap.get(2).equals(50);
        assert heap.get(3).equals(75);
        assert heap.get(4).equals(20);
    }

    // 测试扩容方法
    @Test
    public void test4() {
        MaxHeap<Integer> heap = new MaxHeap<>();
        heap.add(50);
        heap.add(75);
        heap.add(85);
        heap.add(90);

        heap.add(95);
        heap.add(35);
        heap.add(140);
        heap.add(23);

        heap.add(24);
        heap.add(11);
        heap.add(9);
        heap.add(190);

        assert heap.delMax() == 190;
        assert heap.delMax() == 140;
        assert heap.delMax() == 95;
        assert heap.delMax() == 90;

        assert heap.delMax() == 85;
        assert heap.delMax() == 75;
        assert heap.delMax() == 50;
        assert heap.delMax() == 35;

        assert heap.delMax() == 24;
        assert heap.delMax() == 23;
        assert heap.delMax() == 11;
        assert heap.delMax() == 9;
    }

    // 测试remove方法
    @Test
    public void test5() {
        MaxHeap<Integer> heap = new MaxHeap<>();
        heap.add(50);
        heap.add(75);
        heap.add(85);
        heap.add(90);

        heap.add(95);
        heap.add(35);
        heap.add(140);
        heap.add(23);

        heap.remove(95);

        assert heap.size() == 7;

        assert heap.get(1) == 140;
        assert heap.get(2) == 90;
        assert heap.get(3) == 75;
        assert heap.get(4) == 50;

        assert heap.get(5) == 85;
        assert heap.get(6) == 35;
        assert heap.get(7) == 23;

        heap.remove(55);
    }

    @Test
    public void test6() {
        MaxHeap<Integer> heap = new MaxHeap<>();

        Integer[] integers = new Integer[8];
        integers[0] = 50;
        integers[1] = 75;
        integers[2] = 85;
        integers[3] = 90;
        integers[4] = 95;
        integers[5] = 35;
        integers[6] = 140;
        integers[7] = 23;

        Integer[] sort = heap.sort(integers);
        assert sort[0] == 23;
        assert sort[1] == 35;
        assert sort[2] == 50;
        assert sort[3] == 75;
        assert sort[4] == 85;
        assert sort[5] == 90;
        assert sort[6] == 95;
        assert sort[7] == 140;
    }

    @Test
    public void test7() {
        Integer[] integers = {36, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 15, 25, 6, 12, -24};
        MaxHeap<Integer> heap = new MaxHeap<>();
        Integer[] sort = heap.sort(integers);
        System.out.println("integers = " + Arrays.toString(integers));
        System.out.println("sort = " + Arrays.toString(sort));

        int i = 0;
        assert sort[i++] == -24;
        assert sort[i++] == 1;
        assert sort[i++] == 2;
        assert sort[i++] == 3;
        assert sort[i++] == 4;
        assert sort[i++] == 5;
        assert sort[i++] == 6;
        assert sort[i++] == 6;
        assert sort[i++] == 7;
        assert sort[i++] == 9;
        assert sort[i++] == 10;
        assert sort[i++] == 11;
        assert sort[i++] == 12;
        assert sort[i++] == 15;
        assert sort[i++] == 25;
        assert sort[i] == 36;
    }

}
