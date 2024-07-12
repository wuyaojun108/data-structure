package org.wyj.ds.p2_linkedlist;

import java.util.Iterator;

/**
 * @auther 武耀君
 * @date 2024/7/9
 *
 * 单向链表
 */
public class OneWayLinkedList<E> implements Iterable<E> {
    /**
     * 头节点
     */
    private Node head;
    /**
     * 链表中的元素个数
     */
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 在链表头部插入元素
     */
    public void addFirst(E value) {
        head = new Node(value, head);
        size++;
    }

    /**
     * 在链表尾部插入元素
     */
    public void addLast(E value) {
        Node node = new Node(value, null);
        if (head == null) {
            head = node;
        } else {
            Node h = head;
            while (h.next != null) {
                h = h.next;
            }
            h.next = node;
        }
        size++;
    }

    /**
     * 获取链表头部的元素
     */
    public E getFirst() {
        if (head == null) {
            return null;
        }
        return head.value;
    }

    /**
     * 获取链表尾部元素
     */
    public E getLast() {
        if (head == null) {
            return null;
        }
        Node h = head;
        while (h.next != null) {
            h = h.next;
        }
        return h.value;
    }

    /**
     * 移除链表头部的元素
     */
    public E removeFirst() {
        if (head == null) {
            return null;
        }
        E value = head.value;
        head.value = null;
        head = head.next;
        size--;
        return value;
    }

    /**
     * 移除链表尾部元素
     */
    public E removeLast() {
        if (head == null) {
            return null;
        }
        Node prev = null;
        Node h = head;
        while (h.next != null) {
            prev = h;
            h = h.next;
        }
        E value = h.value;
        if (prev != null) {
            prev.next = null;
        } else {
            head = null;
        }
        size--;
        return value;
    }

    /**
     * 迭代器，遍历元素
     */
    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    /**
     * 获取元素在链表中第一次出现的下标
     */
    public int indexOf(E value) {
        int idx = 0;
        Node h = head;
        while (h != null) {
            if (h.value.equals(value)) {
                return idx;
            }
            idx++;
            h = h.next;
        }
        return -1;
    }

    /**
     * 在指定下标处插入元素
     */
    public void add(int idx, E value) {
        checkIdx(idx);
        Node node = getNode(idx);
        Node next = node.next;
        node.next = new Node(value, next);
        size++;
    }

    /**
     * 设置指定下标处元素的值
     */
    public E set(int idx, E value) {
        checkIdx(idx);
        Node node = getNode(idx);
        E oldValue = node.value;
        node.value = value;
        return oldValue;
    }

    /**
     * 移除指定下标处元素的值
     */
    public E remove(int idx) {
        checkIdx(idx);

        int i = 0;
        Node h = head;
        Node prev = null;
        while (i < idx) {
            i++;
            prev = h;
            h = h.next;
        }
        E oldValue;
        if (prev == null) {
            oldValue = h.value;
            head = h.next;
        } else {
            Node next = h.next;
            oldValue = h.value;
            h.value = null;
            prev.next = next;
        }
        size--;
        return oldValue;
    }

    /**
     * 链表反转
     */
    public void reverse() {
        Node prev = null;
        Node h = head;
        while (h != null) {
            Node next = h.next;
            h.next = prev;
            prev = h;
            h = next;
        }
        head = prev;
    }

    /**
     * 移除链表中的指定元素
     */
    public void remove(E value) {
        if (head == null) {
            return;
        }

        Node prev = null;
        Node h = head;
        while (h != null) {
            Node next = h.next;
            if ((h.value == null && value == null)
                    || (h.value != null && h.value.equals(value))) {
                if (prev != null) {
                    prev.next = next;
                } else {
                    head = next;
                }
                size--;
            } else {
                prev = h;
            }
            h = next;
        }
    }

    /**
     * 清空链表
     */
    public void clear() {
        Node h = head;
        while (h != null) {
            Node next = h.next;
            h.value = null;
            h.next = null;
            h = next;
        }
        size = 0;
    }

    private void checkIdx(int idx) {
        if (idx < 0 || idx > size) {
            throw new RuntimeException("下标越界：" + idx);
        }
    }

    /**
     * 获取指定下标处的节点
     */
    private Node getNode(int idx) {
        int i = 0;
        Node h = head;
        while (i < idx) {
            i++;
            h = h.next;
        }
        return h;
    }

    /**
     * 迭代器
     */
    private class Itr implements Iterator<E> {
        private Node curNode;

        public Itr() {
            curNode = head;
        }

        @Override
        public boolean hasNext() {
            return curNode != null;
        }

        @Override
        public E next() {
            Node c = curNode;
            curNode = curNode.next;
            return c.value;
        }
    }

    private class Node {
        private E value;
        private Node next;

        public Node(E value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
