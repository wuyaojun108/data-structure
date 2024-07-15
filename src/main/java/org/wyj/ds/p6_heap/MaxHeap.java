package org.wyj.ds.p6_heap;

import java.lang.reflect.Array;

/**
 * @auther 武耀君
 * @date 2024/7/12
 *
 * 大顶堆，最大的元素在根节点
 */
public class MaxHeap<E extends Comparable<E>> {
    private static final int DEFAULT_CAPACITY = 8;
    private int capacity;
    private int size;
    private E[] arr;  // 数组中的第一位不存储元素

    @SuppressWarnings("unchecked")
    public MaxHeap() {
        size = 0;
        capacity = DEFAULT_CAPACITY;
        arr = (E[]) new Comparable[capacity];
    }

    /**
     * 向堆中添加元素
     */
    public void add(E value) {
        checkCapacity();
        size++;
        arr[size] = value;
        swim(arr, size);
    }

    /**
     * 删除堆中的最大元素
     */
    public E delMax() {
        E max = arr[1];
        arr[1] = arr[size];
        arr[size] = null;
        size--;
        sink(arr, 1, size);
        return max;
    }

    /**
     * 删除堆中的某个元素
     */
    public void remove(E ele) {
        int i = indexOf(ele);
        if (i < 1) {  // 数组中的第一个位置不存储元素
            return;
        }
        arr[i] = arr[size];
        arr[size] = null;
        size--;
        sink(arr, i, size);
    }

    /**
     * 堆排序
     * 1. 首先把数组转换为一个堆，从数组的中间开始遍历，使用下沉算法，调整数组中元素的位置，直到下标为1，让数组中的元素符合堆的规范，
     *    之所以要从中间开始下沉，是因为对没有子结点的元素执行下沉算法是没有意义的。
     * 2. 堆中的第一个元素就是数组中的最大值，把和数组末尾的元素交换，然后对第一个元素执行下沉算法，下沉过程中不包括数组的最后一个元素，
     *    此时，数组的最后一个元素就是有序的，依次类推，数组的第一个元素和倒数第二个元素交换，然后执行下沉算法，直到数组开头
     *
     */
    @SuppressWarnings("unchecked")
    public E[] sort(E[] source) {
        E[] heapArr = (E[]) new Comparable[source.length + 1];
        System.arraycopy(source, 0, heapArr, 1, source.length);
        int size = source.length;

        // 构建一个堆
        int k = size / 2;
        while (k >= 1) {
            sink(heapArr, k, size);
            k--;
        }

        // 堆排序
        int tmpSize = size;
        while (tmpSize > 1) {  // 不对排序过程中的最后一个元素排序，当所有元素都有序后，最后一个自然是有序的
            exchange(heapArr, 1, tmpSize);

            sink(heapArr, 1, tmpSize - 1);
            tmpSize--;
        }

        // E[] target = source.clone();
        E[] target = (E[]) Array.newInstance(source.getClass().getComponentType(), size);
        System.arraycopy(heapArr, 1, target, 0, size);
        return target;
    }

    /**
     * 获取元素下标
     */
    public int indexOf(E ele) {
        for (int i = 1; i < size; i++) {
            if (arr[i].equals(ele)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 判断堆中是否包含某个元素
     */
    public boolean contains(E ele) {
        return indexOf(ele) != -1;
    }

    /**
     * 获取指定下标处的元素
     */
    public E get(int idx) {
        return arr[idx];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 下沉算法，比较下标k处的节点和它子节点的值，如果比子节点小，就交换，依次类推，直到当前节点没有子节点
     */
    private void sink(E[] arr, int k, int size) {
        while (k * 2 <= size) {
            // 比较父节点中的两个子节点哪个大
            int max = k * 2;
            if (k * 2 + 1 <= size) {
                if (arr[k * 2].compareTo(arr[k * 2 + 1]) < 0) {
                    max = k * 2 + 1;
                } else {
                    max = k * 2;
                }
            }
            if (arr[k].compareTo(arr[max]) < 0) {
                exchange(arr, k, max);
            } else {
                // 如果父节点的值比子节点的值大，结束循环，没有下沉的必要
                break;
            }
            k = max;
        }
    }

    /**
     * 上浮算法，比较下标k处的节点和它父节点的值，如果比父节点大，就交换，依次类推，直到当前节点没有父节点
     */
    private void swim(E[] arr, int k) {
        while (k / 2 > 0) {
            if (arr[k].compareTo(arr[k / 2]) > 0) {
                exchange(arr, k, k / 2);
            } else {
                break;  // 如果k处的值比父节点小，不需要再上浮
            }
            k = k / 2;
        }
    }

    /**
     * 交换两个下标处的元素
     */
    private void exchange(E[] items, int i, int j) {
        E tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }

    @SuppressWarnings("unchecked")
    private void checkCapacity() {
        if (size >= capacity - 1) { // 堆中的第一位不存储元素
            capacity += capacity >> 1;
            E[] objects = (E[]) new Comparable[capacity];
            System.arraycopy(arr, 1, objects, 1, size);
            arr = objects;
        }
    }
}
