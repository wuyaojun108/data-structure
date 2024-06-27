package org.wyj.ds.list;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 基于数组的集合，集合内使用一个Object类型的空数组用来存放元素，
 * 使用一个字段来描述集合的容量，使用一个字段来描述集合内共有多少元素
 * 功能：
 * 添加元素，在指定位置插入元素
 * 删除指定位置的元素
 * 判断集合是否为空
 * 返回某个元素的下标
 * 判断集合是否包含某个元素
 * 把集合的大小变成它实际存储数据所需要的大小
 * 一个迭代器，来遍历集合
 */

public class MyArrayList<E> {
    //一个空数组用来存放集合内的数据，一个描述集合容量的字段，一个描述集合内共有多少元素的字段
    private Object[] arr;
    private int capacity;
    private int size;

    // 默认初始化一个容量为8的数组
    public MyArrayList() {
        size = 0;
        this.capacity = 8;
        arr = new Object[capacity];
    }

    public MyArrayList(int capacity) {
        size = 0;
        this.capacity = capacity;
        arr = new Object[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 添加元素之前确认集合的大小是否充足，如果不充足，则新建一个容量比原来大8的数组
    private void ensureCapacity() {
        if (size >= capacity) {
            this.capacity = capacity + (capacity >> 1);
            Object[] arrDest = new Object[capacity];
            System.arraycopy(arr, 0, arrDest, 0, arr.length);
            arr = arrDest;
        }
    }

    // 裁剪集合占用的内存空间，使它刚好能够存储它的元素
    public void trimToSize() {
        capacity = size;
        arr = Arrays.copyOf(arr, size);
    }

    // 向集合中添加元素
    public void add(E ele) {
        ensureCapacity();
        arr[size] = ele;
        size++;
    }

    // 判断下标是否越界
    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    // 获取指定位置的元素
    public E get(int index) {
        rangeCheck(index);
        return (E) arr[index];
    }


    // 向集合中的指定位置插入元素
    public E set(E ele, int index) {
        rangeCheck(index);
        E oldValue = (E) arr[index];
        arr[index] = ele;
        return oldValue;
    }

    // 移除指定位置的元素
    public void remove(int index) {
        rangeCheck(index);
        System.arraycopy(arr, index + 1, arr, index, size - index);
        size--;
    }

    // 判断集合中是否包含某个元素
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    private int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (arr[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(arr[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public Iterator<E> iterator() {
        return new MyItr();
    }


    // 关于本集合的迭代器，使用它来遍历集合中的元素
    private class MyItr implements Iterator<E> {
        int index = 0;

        @Override
        public boolean hasNext() {
            return index != size;
        }

        @Override
        public E next() {
            if (index < 0 || index > arr.length) {
                throw new IndexOutOfBoundsException();
            }
            E ele = (E) arr[index];
            index++;
            return ele;
        }
    }


}
