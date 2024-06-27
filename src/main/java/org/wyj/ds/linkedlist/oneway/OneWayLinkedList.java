package org.wyj.ds.linkedlist.oneway;

import java.util.Iterator;

// 单向链表
public class OneWayLinkedList<E extends Comparable<E>> {
    // 头节点
    private Node<E> head;

    private int length;

    public OneWayLinkedList() { }

    public boolean isEmpty() {
        return length == 0;
    }

    public int size() {
        return length;
    }


    // 添加元素的方法
    public void addElement(E data) {
        final Node<E> node = new Node<>(data, null);
        if (isEmpty()) {
            head = node;
            length++;
            return;
        }
        Node<E> tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = node;
        length++;
    }

    // 遍历链表
    public void traverseLinkedList() {
        Node<E> tmp = head;
        while (tmp != null) {
            System.out.println(tmp);
            tmp = tmp.next;
        }
    }

    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        private int index;
        private Node<E> current_node;

        @Override
        public boolean hasNext() {
            return index < length;
        }

        @Override
        public E next() {
            if (index >= length) {
                throw new RuntimeException("下标越界");
            }
            if (index == 0) {
                current_node = head;
            } else {
                current_node = current_node.next;
            }
            index++;
            return current_node.data;
        }
    }

    // 删除指定元素
    public void deleteElement(E ele) {
        if (isEmpty()) {
            return;
        }
        while (head.data == ele) {
            head = head.next;
            length--;
        }
        Node<E> tmp = head;
        while (tmp.next != null) {
            if (tmp.next.data == ele) {
                tmp.next = tmp.next.next;
                length--;
            } else {
                tmp = tmp.next;
            }
        }
    }

    // 链表中节点的数据类型
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data + "}";
        }
    }
}
