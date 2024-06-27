package org.wyj.ds.queue;

/*
    使用数组模拟循环队列：使用一个数组来作为队列的容器，一个变量记录队列的容量，一个变量记录队列中元素的个数，
一个变量指向队列头，一个变量指向队列尾，如果队列为空，那么这两个变量的值是-1，在使用时，应该先移动，后取数或赋
值。使用容量变量和个数变量来判断队列是否已满或为空。

 */
public class ArrayCircleQueue<E> {
    private final Object[] arr;
    private final int capacity;
    private int front;
    private int rear;
    private int count;

    public ArrayCircleQueue(int capacity){
        this.capacity = capacity;
        arr = new Object[capacity];
        this.rear = -1;
        this.front = -1;
    }

    public int getCapacity(){
        return capacity;
    }

    public int getFront(){
        return front;
    }

    public int getRear(){
        return rear;
    }

    public int getCount(){
        return count;
    }

    // 判断队列是否为空
    public boolean isEmpty(){
        return count == 0;
    }

    // 判断队列是否已满
    public boolean isFull(){
        return count == capacity;
    }

    // 入队
    public void enQueue(E ele){
        if(isFull()){
            throw new RuntimeException("队列已满，无法添加元素");
        }
        rear = (rear + 1) % capacity;
        arr[rear] = ele;
        count++;
    }

    // 出队
    public E exQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空，无法获取元素");
        }
        front = (front + 1) % capacity;
        E value = (E) arr[front];
        count--;
        return value;
    }

    // 遍历队列
    public void traverseQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空，无法遍历");
        }
        int tmp_front = front;
        int tmp_count = count;
        while (tmp_count != 0) {
            tmp_front = (tmp_front + 1) % capacity;
            System.out.println(arr[tmp_front]);
            tmp_count--;
        }
    }

    // 返回数组中元素的个数
    public int size(){
        return count;
    }
}
