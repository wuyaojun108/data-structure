package org.wyj.ds.p4_queue;

import java.util.Iterator;

/**
 * @auther 武耀君
 * @date 2024/7/10
 *
 * 使用数组来模拟循环队列
 */
public class ArrayCircleQueue<E> implements Iterable<E> {
    private static final int DEFAULT_CAPACITY = 5;
    private final int capacity;
    private int front;
    private int rear;
    private final Object[] arr;

    public ArrayCircleQueue() {
        capacity = DEFAULT_CAPACITY;
        arr = new Object[capacity];
        front = 0;
        rear = 0;
    }

    public ArrayCircleQueue(int capacity) {
        this.capacity = capacity;
        arr = new Object[capacity];
        front = 0;
        rear = 0;
    }

    public int size() {
        return (rear + capacity - front) % capacity;

        // 将下面这段代码提取为上面的公式
//        int count;
//        if (rear > front) {
//            count = rear - front;
//        } else {
//            count = rear + capacity - front;
//        }
//        return count;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }

    public void enQueue(E value) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        arr[rear] = value;
        rear = (rear + 1) % capacity;
    }

    @SuppressWarnings("unchecked")
    public E exQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        E value = (E) arr[front];
        front = (front + 1) % capacity;
        return value;
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return (E) arr[front];
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        private int tmpFront;

        public Itr() {
            tmpFront = front;
        }

        @Override
        public boolean hasNext() {
            return tmpFront != rear;
        }

        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            E value = (E) arr[tmpFront];
            tmpFront = (tmpFront + 1) % capacity;
            return value;
        }
    }
}
