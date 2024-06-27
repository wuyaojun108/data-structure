package org.wyj.ds.heap;

import java.util.Arrays;

// 堆，大顶堆，最大的元素在根节点
public class Heap<E extends Comparable<E>> {
    // 用于存储数据的数组
    private final E[] items;
    // 堆中元素的个数
    private int size;
    // 数组的大小
    private final int capacity;

    public Heap(int capacity) {
        // 父类引用指向子类实例
        items = (E[]) new Comparable[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    // 交换两个下标所指位置的变量
    public void exchange(E[] items, int i, int j) {
        E tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }

    public int size() {
        return size;
    }

    // 向堆中添加元素
    public void add(E ele) {
        size++;
        items[size] = ele;
        swim(items, size);
    }

    // 使用上浮算法，使索引k处的值在堆中处于一个合适的位置
    private void swim(E[] items, int k) {
        // 如果k等于1，表示k在最顶点，没有必要使用上浮算法进行比较
        while (k > 1) {
            //索引k处的值和它的父节点进行比较，如果k处的值比它的父节点的值大，那么就和它的父节点的值进行交换，
            //然后将索引k重新赋值为它的父节点的索引值，继续向上比较；否则，证明k处的值的位置是合适的，退出循环
            int cmp = items[k].compareTo(items[k / 2]);
            if (cmp > 0) {
                exchange(items, k, k / 2);
                k = k / 2;
            } else {
                break;
            }
        }
    }

    // 判断堆中是否包含某个元素
    public boolean contains(E ele) {
        for (int i = 1; i <= size; i++) {
            if (items[i] == ele) {
                return true;
            }
        }
        return false;
    }

    // 返回某个元素的下标
    public int getIndex(E ele) {
        for (int i = 1; i <= size; i++) {
            if (items[i] == ele) {
                return i;
            }
        }
        return -1;
    }

    // 根据下标取值
    public E get(int index) {
        if (1 <= index && index <= size) {
            return items[index];
        } else {
            return null;
        }
    }

    // 删除堆中元素
    public void remove(E ele) {
        // 判断元素是否存在于堆中，如果不存在，直接返回
        int index = getIndex(ele);
        if (index == -1) {
            return;
        }
        // 把堆中最后一个位置的元素赋值给要删除的元素，同时删除最后一个位置的元素，size--，然后调用下沉算法，
        // 调整堆中元素的位置
        items[index] = items[size];
        items[size] = null;
        size--;
        sink(items, index, size);
    }

    // 使用下沉算法，使索引k处的值在堆中能处于一个合适的位置
    private void sink(E[] items, int k, int size) {
        // 判断k处的值是否有右子节点，如果有，进入循环：
        // 判断k处的左子节点和右子结点哪个位置的元素比较大，取较大元素的下标
        // k处的值和子结点中较大的比较，如果比它小，和它交换位置，同时将子结点的值赋给k，继续循环，进行比叫，否则退出循环

        // 针对一个父节点只有左子结点的情况
        while ((2 * k) <= size) {
            int max;
            if(2 * k + 1 <= size){
                if (items[2 * k].compareTo(items[2 * k + 1]) > 0) {
                    max = 2 * k;
                } else {
                    max = 2 * k + 1;
                }
            }else{
                max = 2 * k;
            }
            if (items[k].compareTo(items[max]) < 0) {
                exchange(items, k, max);
                k = max;
            } else {
                break;
            }
        }
    }

    // 使用堆排序算法，对指定的数组进行排序。使用一个临时的堆来进行排序
    public E[] sort(E[] source){
        E[] heap = (E[])new Comparable[source.length + 1];
        System.arraycopy(source, 0, heap, 1, source.length);
        int heapSize = heap.length - 1;
        createHeap(heap, heapSize);
        int tmpHeapSize = heapSize;
        // 不用对最后一位进行排序，因为当其它位上的元素都有序后，最后一位自然就是有序的
        while (tmpHeapSize > 1){
            exchange(heap, 1, tmpHeapSize);
            sink(heap, 1, tmpHeapSize - 1);
            tmpHeapSize--;
        }
        System.arraycopy(heap, 1, source, 0, heapSize);
        return source;
    }

    // 把数组修改成一个对，从数组的中间，使用下沉算法，调整数组中元素的位置，直到下标为1，可以让数组中的元素符合堆的规范
    // 之所以要从中间开始下沉，是因为对没有子结点的元素执行下沉算法是没有意义的。
    private void createHeap(E[] heap, int heapSize){
        // heapSize是数组的长度减1，因为堆中的0号索引是不使用的，如果长度是7，那么里面只存储了6个元素；
        // k是中间元素的索引，由于0号索引是废弃的，所以 heapSize / 2，正好是中间元素的索引
        int k = heapSize / 2;
        while (k >= 1) {
            sink(heap, k, heapSize);
            k--;
        }
    }

    @Override
    public String toString() {
        return "Heap{" +
                "items=" + Arrays.toString(items) +
                ", size=" + size +
                ", capacity=" + capacity +
                '}';
    }
}
