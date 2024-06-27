package org.wyj.ds.linkedlist.bothway;

import java.util.NoSuchElementException;

/**
 * 自定义双向链表，实现:
 * 从头部或者尾部添加元素
 * 遍历元素
 * 判断元素是不是在链表之中
 * 查找指定位置的元素、在指定位置修改元素、在指定位置添加元素
 * <p>
 * 链表内有三种特殊的元素：指向前一个节点的指针为空的元素、指向后一个节点的指针为空的元素、指向前一个节点和
 * 后一个节点的指针都为空的元素
 */

public class MyDoublyLinked<E> {

    // 类中持有到链表第一个节点和最后一个节点的指针以及链表中共有多少个元素的记录
    private Node<E> first;
    private Node<E> last;
    private int size;

    // 向头部添加元素
    public void addFirst(E addEle) {
        final Node<E> eNode = new Node<>(null, addEle, first);
        if (first == null) {
            first = last = eNode;
        } else {
            first.prev = eNode;
            first = eNode;
        }
        size++;
    }

    // 移除头部的元素
    public void removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("removeFirst:链表为空");
        }
        final Node<E> f = first;
        final Node<E> next = f.next;
        f.ele = null;
        f.next = null;
        first = next;
        if (next == null) {
            last = null;
        } else {
            first.prev = null;
        }
        size--;
    }

    // 向尾部添加元素
    public void addLast(E addEle) {
        Node<E> eNode = new Node<>(last, addEle, null);
        if (last == null) {
            last = first = eNode;
        } else {
            last.next = eNode;
            last = eNode;
        }
        size++;
    }

    // 移除尾部的元素
    public void removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("removeLast:链表为空");
        }
        Node<E> l = last;
        Node<E> prev = last.prev;
        l.ele = null;
        l.prev = null;
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            last.next = null;
        }
        size--;
    }

    // 获取头部的元素
    public E getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("getFirst:链表为空");
        } else {
            return first.ele;
        }
    }

    // 获取尾部的元素
    public E getLast() {
        if (size == 0) {
            throw new NoSuchElementException("getLast:链表为空");
        } else {
            return last.ele;
        }
    }

    // 返回链表的大小
    public int getSize() {
        return size;
    }

    // 判断是否包含某个元素
    public boolean contains(E element) {
        if (element == null) {
            for (Node<E> e = first; e != null; e = e.next) {
                if (e.ele == null) {
                    return true;
                }
            }
        } else {
            for (Node<E> e = first; e != null; e = e.next) {
                if (element.equals(e.ele)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 获取指定位置的元素
    public E getElementByIndex(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("index 大于等于size：" + index + " >= " + size);
        }
        return getNodeByIndex(index).ele;
    }

    // 更改指定位置的元素
    public E setElementByIndex(int index, E element) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("index 大于等于size："
                    + index + " >= " + size);
        }
        Node<E> node = getNodeByIndex(index);
        E oldValue = node.ele;
        node.ele = element;
        return oldValue;
    }

    // 根据下标，获取元素的方法，如果下标大于链表大小的一半，从后向前找，否则，从前向后找
    private Node<E> getNodeByIndex(int index) {
        if (index > (size >> 1)) {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        } else {
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        }
    }

    // 在指定位置添加元素
    public boolean addElement(int index, E element) {
        checkRange(index);

        Node<E> node = getNodeByIndex(index);
        Node<E> prev = node.prev;
        if (prev == null) {
            Node<E> eNode = new Node<>(null, element, node);
            node.prev = eNode;
            first = eNode;
        } else {
            Node<E> eNode = new Node<>(prev, element, node);
            prev.next = eNode;
            node.prev = eNode;
        }
        size++;
        return true;
    }

    // 移除指定位置的元素
    public E removeElement(int index) {
        checkRange(index);
        Node<E> node = getNodeByIndex(index);

        Node<E> prev = node.prev;
        E ele = node.ele;
        Node<E> next = node.next;

        if (prev == null && next == null) {
            first = last = null;
        } else if (prev == null) {
            first = next;
            first.prev = null;
        } else if (next == null) {
            last = prev;
            last.next = null;
        } else {
            prev.next = next;
            next.prev = prev;
        }
        node.ele = null;
        size--;
        return ele;
    }

    public void checkRange(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("index 大于等于size："
                    + index + " >= " + size);
        }
    }

    /**
     * 链表中每个节点的数据结构：包括指向上一个节点的指针，本节点中包含的元素，指向下一个节点的指针
     */
    private static class Node<E> {
        Node<E> prev;
        E ele;
        Node<E> next;

        public Node(Node<E> prev, E ele, Node<E> next) {
            this.prev = prev;
            this.ele = ele;
            this.next = next;
        }

        @Override
        public String toString() {
            return ele.toString();
        }
    }

    // 按照顺序，把链表中的元素拼接到一个字符串中，然后返回
    public String toString() {
        Node<E> f = first;
        StringBuilder stringBuilder = new StringBuilder();
        while (f != null) {
            if (f.next != null) {
                stringBuilder.append(f.ele).append(", ");
            } else {
                stringBuilder.append(f.ele);
            }
            f = f.next;
        }
        return "[" + stringBuilder + "]";
    }

}
