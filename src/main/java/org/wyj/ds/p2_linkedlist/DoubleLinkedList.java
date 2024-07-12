package org.wyj.ds.p2_linkedlist;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

/**
 * @auther 武耀君
 * @date 2024/7/7
 */
public class DoubleLinkedList<E> implements List<E>, Serializable, Cloneable {
    private static final long serialVersionUID = 1234L;

    /**
     * 链表大小
     */
    private int size;

    /**
     * 头节点
     */
    private Node head;

    /**
     * 尾节点
     */
    private Node tail;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向列表的头部添加元素
     */
    public void addFirst(E value) {
        Node node = new Node(null, value, head);
        if (head == null) {
            tail = head = node;
        } else {
            head.prev = node;
            head = node;
        }
        size++;
    }

    /**
     * 向链表的尾部添加元素
     */
    public void addLast(E value) {
        Node node = new Node(tail, value, null);
        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    /**
     * 获取链表的头部元素
     */
    public E getFirst() {
        return head != null ? head.value : null;
    }

    /**
     * 获取链表的尾部元素
     */
    public E getLast() {
        return tail != null ? tail.value : null;
    }

    public E removeFirst() {
        if (head == null) {
            return null;
        }
        E value = head.value;
        Node next = head.next;
        if (next != null) {
            next.prev = null;
            head.next = null;
        } else {
            tail = null;
        }

        head = next;
        size--;
        return value;
    }

    public E removeLast() {
        if (tail == null) {
            return null;
        }
        E value = tail.value;
        Node prev = tail.prev;
        if (prev != null) {
            prev.next = null;
            tail.prev = null;
        } else {
            head = null;
        }

        tail = prev;
        size--;
        return value;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[size];
        Node h = head;
        int i = 0;
        while (h != null) {
            objects[i++] = h.value;
            h = h.next;
        }
        return objects;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        // getComponentType，返回数组中元素的类型
        T[] arr = (T[]) Array.newInstance(a.getClass().getComponentType(), size);

        Node h = head;
        int i = 0;
        while (h != null) {
            ((Object[]) arr)[i++] = h.value;
            h = h.next;
        }
        return arr;
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int idx = indexOf(o);
        while (idx != -1) {
            remove(idx);
            idx = indexOf(o);
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (indexOf(o) == -1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            addLast(e);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        int i = index;
        for (E e : c) {
            add(i, e);
            i++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            int idx = indexOf(o);
            if (idx != -1) {
                remove(idx);
            }
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Node h = head;
        int idx = 0;
        while (h != null) {
            if (!c.contains(h.value)) {
                remove(idx);
            }
            idx++;
            h = h.next;
        }
        return true;
    }

    @Override
    public void clear() {
        if (head == null) {
            return;
        }
        while (head != null) {
            Node tmp = head.next;
            head.value = null;
            head.prev = null;
            head.next = null;
            head = tmp;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return getNode(index).value;
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        Node node = getNode(index);
        E oldValue = node.value;
        node.value = element;
        return oldValue;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new RuntimeException("下标不正确：" + index);
        }
        if (index == size) {
            addLast(element);
        } else {
            Node node = getNode(index);
            Node prev = node.prev;
            Node curNode = new Node(prev, element, node);
            if (prev != null) {
                prev.next = curNode;
            } else {
                head = curNode;
            }
            node.prev = curNode;
            size++;
        }
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        Node node = getNode(index);
        E oldValue = node.value;
        Node prev = node.prev;
        Node next = node.next;

        if (prev != null) {
            prev.next = next;
        } else {
            head = next;
        }
        if (next != null) {
            next.prev = prev;
        } else {
            tail = prev;
        }

        node.prev = null;
        node.next = null;
        size--;
        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        Node curNode = head;
        int idx = 0;
        while (curNode != null) {
            if (curNode.value == null) {
                if (o == null) {
                    return idx;
                }
            } else if (curNode.value.equals(o)) {
                return idx;
            }
            idx++;
            curNode = curNode.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node curNode = tail;
        int idx = size - 1;
        while (curNode != null) {
            if (curNode.value == null) {
                if (o == null) {
                    return idx;
                }
            } else if (curNode.value.equals(o)) {
                return idx;
            }
            idx--;
            curNode = curNode.prev;
        }
        return -1;
    }

    /**
     * 提供一个可以双向遍历的迭代器
     */
    @Override
    public ListIterator<E> listIterator() {
        return new ListItr();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return Collections.emptyList();
    }

    /**
     * 深拷贝
     */
    @Override
    @SuppressWarnings("unchecked")
    public DoubleLinkedList<E> clone() {
        try {
            // 浅拷贝
            DoubleLinkedList<E> clone = (DoubleLinkedList<E>) super.clone();
            // 清空clone对象中的成员变量的值
            clone.size = 0;
            clone.head = clone.tail = null;
            // 重新赋值
            Node h = head;
            while (h != null) {
                clone.addLast(h.value);
                h = h.next;
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeInt(size);
        Node h = head;
        while (h != null) {
            oos.writeObject(h.value);
            h = h.next;
        }
    }

    @SuppressWarnings("unchecked")
    public void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        int size = ois.readInt();
        int i = 0;
        while (i < size) {
            E o = (E) ois.readObject();
            this.addLast(o);
            i++;
        }
    }

    /**
     * 链表反转
     */
    public void reverse() {
        Node node = head;
        while (node != null) {
            Node prev = node.prev;
            Node next = node.next;
            if (prev == null) {
                tail = node;
            }
            if (next == null) {
                head = node;
            }
            node.prev = next;
            node.next = prev;

            node = next;
        }
    }

    private Node getNode(int index) {
        Node curNode;
        if (index > (size >> 1)) {  // 如果下标大于 size / 2，从后遍历，否则从前遍历
            curNode = tail;
            int i = size - 1;
            while (i > index) {
                curNode = curNode.prev;
                i--;
            }
        } else {
            curNode = head;
            int i = 0;
            while (i < index) {
                curNode = curNode.next;
                i++;
            }
        }
        return curNode;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("下标越界：" + index);
        }
    }

    private class ListItr implements ListIterator<E> {
        /**
         * 从前向后迭代
         */
        private Node hNode;
        private int hIdx;

        /**
         * 从后向前迭代
         */
        private Node tNode;
        private int tIdx;

        /**
         * 当前操作的节点
         */
        private Node curNode;
        private int cIdx;

        public ListItr() {
            hNode = head;
            tNode = tail;
            hIdx = 0;
            tIdx = size - 1;
        }

        @Override
        public boolean hasNext() {
            return hNode != null;
        }

        @Override
        public E next() {
            curNode = hNode;
            E value = hNode.value;
            hNode = hNode.next;
            cIdx = hIdx;
            hIdx++;
            return value;
        }

        @Override
        public boolean hasPrevious() {
            return tNode != null;
        }

        @Override
        public E previous() {
            curNode = tNode;
            E value = tNode.value;
            tNode = tNode.prev;
            cIdx = tIdx;
            tIdx--;
            return value;
        }

        @Override
        public int nextIndex() {
            return hIdx;
        }

        @Override
        public int previousIndex() {
            return tIdx;
        }

        @Override
        public void remove() {
            DoubleLinkedList.this.remove(cIdx);
        }

        @Override
        public void set(E e) {
            curNode.value = e;
        }

        @Override
        public void add(E e) {
            DoubleLinkedList.this.add(cIdx, e);
        }
    }

    /**
     * 链表中节点的数据类型
     */
    private class Node {
        private Node prev;
        private E value;
        private Node next;

        public Node(Node prev, E value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
}
