package org.wyj.ds.p1_list;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * @auther 武耀君
 * @date 2024/7/5
 *
 * 基于数组的列表
 */
public class ArrList<E> implements List<E>, Iterable<E>, Serializable, Cloneable {
    private static final long serialVersionUID = 1234L;

    private static final int DEFAULT_CAPACITY = 8;
    private Object[] arr;
    private int size;
    private int capacity;

    public ArrList() {
        capacity = DEFAULT_CAPACITY;
        size = 0;
        arr = new Object[capacity];
    }

    /**
     * 返回列表中元素的个数
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 判断列表是否为空，如果是空，返回true
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断列表是否包含指定元素
     */
    @Override
    public boolean contains(Object ele) {
        return indexOf(ele) > -1;
    }

    /**
     * 向列表的末尾添加元素
     */
    @Override
    public boolean add(E ele) {
        checkCapacity();
        arr[size++] = ele;
        return true;
    }

    /**
     * 向列表的指定位置插入元素
     * @param idx 下标，要向哪个下标处插入元素
     * @param element 要插入的元素
     */
    @Override
    public void add(int idx, E element) {
        checkIdx(idx);
        checkCapacity();

        System.arraycopy(arr, idx, arr, idx + 1, size - idx);
        arr[idx] = element;
        size++;
    }

    /**
     * 移除列表中的指定元素，如果有多个，会全部移除
     */
    @Override
    public boolean remove(Object ele) {
        int i = indexOf(ele);
        while (i != -1) {
            remove(i);
            i = indexOf(ele);
        }
        return true;
    }

    /**
     * 判断列表中是否包含参数1中指定的元素
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E t : c) {
            add(t);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        int i = index;
        for (E t : c) {
            add(i, t);
            i++;
        }
        return true;
    }

    /**
     * 批量移除列表中的元素
     * @param c 指定有哪些元素需要移除
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        batchRemove(c, false);
        return true;
    }

    /**
     * 移除列表中除了参数1指定的元素之外的所有元素
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        batchRemove(c, true);
        return true;
    }

    /**
     * 修改指定下标处的元素
     */
    @Override
    public E set(int idx, E ele) {
        checkIdx(idx);
        E oldValue = arrData(idx);
        arr[idx] = ele;
        return oldValue;
    }

    @Override
    public E get(int i) {
        checkIdx(i);
        return arrData(i);
    }

    @Override
    public E remove(int idx) {
        checkIdx(idx);
        E value = arrData(idx);
        if (idx < size - 1) {
            System.arraycopy(arr, idx + 1, arr, idx, size - (idx + 1));
        }
        arr[--size] = null;
        return value;
    }

    @Override
    public int indexOf(Object ele) {
        for (int i = 0; i < size; i++) {
            if (ele == null) {
                if (arr[i] == null) {
                    return i;
                }
            } else {
                if (ele.equals(arr[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object ele) {
        for (int i = size - 1; i >= 0; i--) {
            if (ele == null) {
                if (arr[i] == null) {
                    return i;
                }
            } else {
                if (ele.equals(arr[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return Collections.emptyList();
    }

    @Override
    public void clear() {
        arr = new Object[DEFAULT_CAPACITY];
        size = 0;
        capacity = DEFAULT_CAPACITY;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(arr, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T1> T1[] toArray(T1[] a) {
        return (T1[]) Arrays.copyOf(arr, size, a.getClass());
    }

    /**
     * 序列化
     */
    public void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeInt(size);
        oos.writeInt(capacity);
        oos.writeObject(arr);
    }

    /**
     * 反序列化
     */
    public void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        int size = ois.readInt();
        int capacity = ois.readInt();
        Object[] arr = (Object[]) ois.readObject();
        this.size = size;
        this.capacity = capacity;
        this.arr = Arrays.copyOf(arr, capacity);
    }

    private void checkIdx(int i) {
        if (i < 0 || i >= size) {
            throw new RuntimeException("下标越界：" + i);
        }
    }

    private void checkCapacity() {
        if (size >= capacity) {
            capacity += (capacity >> 1);  // 每次扩容之前的一半
            arr = Arrays.copyOf(arr, capacity);
        }
    }

    @SuppressWarnings("unchecked")
    private E arrData(int idx) {
        return (E) arr[idx];
    }

    /**
     * 批量移除或保留参数1中指定的元素，参数2是true，表示保留元素，否则移除
     */
    private void batchRemove(Collection<?> c, boolean isKeep) {
        if (c == null || c.isEmpty()) {
            return;
        }

        int w = 0;
        for (int r = 0; r < size; r++) {
            // w指针，所有需要被移除的元素，都会被它后面不需要被移除的元素覆盖，w指针负责记录覆盖情况，
            // 随后会将w指针后的数据全部赋值为null
            if (c.contains(arrData(r)) == isKeep) {
                if (w != r) {
                    arr[w++] = arr[r];
                } else {
                    w++;
                }
            }
        }
        for (int i = w; i < size; i++) {
            arr[i] = null;
        }
        size = w;
    }

    /**
     * 深拷贝
     */
    @Override
    @SuppressWarnings("unchecked")
    public ArrList<E> clone() {
        try {
            ArrList<E> arrList = (ArrList<E>) super.clone();
            arrList.arr = Arrays.copyOf(this.arr, capacity);
            arrList.size = this.size;
            arrList.capacity = this.capacity;
            return arrList;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    private class Itr implements Iterator<E> {
        private int idx;

        @Override
        public boolean hasNext() {
            return idx < size;
        }

        @Override
        public E next() {
            checkIdx(idx);
            return get(idx++);
        }
    }
}
