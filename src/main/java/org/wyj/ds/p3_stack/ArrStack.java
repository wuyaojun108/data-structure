package org.wyj.ds.p3_stack;

/**
 * @auther 武耀君
 * @date 2024/7/10
 *
 * 基于数组的栈
 */
public class ArrStack<E> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final int MAX_CAPACITY = 1024;
    private int size;
    private int capacity;
    private Object[] arr;

    public ArrStack() {
        capacity = DEFAULT_CAPACITY;
        arr = new Object[capacity];
    }

    /**
     * 元素入栈
     */
    public void push(E value) {
        checkCapacity();
        arr[size++] = value;
    }

    /**
     * 元素出栈
     */
    @SuppressWarnings("unchecked")
    public E pop() {
        if (size == 0) {
            throw new RuntimeException("栈中没有元素");
        }
        E e = (E) arr[size - 1];
        size--;
        return e;
    }

    /**
     * 查看栈顶元素
     */
    @SuppressWarnings("unchecked")
    public E peek() {
        if (size == 0) {
            throw new RuntimeException("栈中没有元素");
        }
        return (E) arr[size - 1];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void checkCapacity() {
        if (size >= capacity) {
            // 如果capacity达到最大之后还要扩容，抛异常
            if (capacity >= MAX_CAPACITY) {
                throw new RuntimeException("栈溢出：size == " + size);
            }
            capacity *= 2;
            Object[] objects = new Object[capacity];
            System.arraycopy(arr, 0, objects, 0, size);
            arr = objects;
        }
    }
}
