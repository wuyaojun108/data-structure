package org.wyj.ds.stack;

import java.util.NoSuchElementException;

/**
 * 我自己实现的，基于数组的栈，使用数组时要注意：
 * 添加元素注意数组的容量，如果不够要扩容，
 * 删除元素注意数组是否为空，为空抛异常
 * 功能：
 * push：在栈顶添加元素
 * pop：弹出栈顶的元素
 * top：查看栈的的元素
 */

public class MyStack<E> {
    private Object[] elementData;
    // 数组内存储元素的数量，通过它来实现入栈和出栈
    private int size;
    private int capacity;

    public MyStack(int capacity) {
        size = 0;
        this.capacity = capacity;
        elementData = new Object[capacity];
    }

    public void ensureCapacity() {
        if (size >= capacity) {
            capacity += 8;
            Object[] dest = new Object[capacity];
            System.arraycopy(elementData, 0, dest, 0, size);
            elementData = dest;
        }
    }

    public void push(E ele) {
        ensureCapacity();
        elementData[size] = ele;
        size++;
    }

    public E pop() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        size--;
        return (E) elementData[size];
    }

    public E top() {
        return (E) elementData[size - 1];
    }


}
