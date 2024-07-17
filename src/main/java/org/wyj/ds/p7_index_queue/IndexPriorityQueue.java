package org.wyj.ds.p7_index_queue;

/**
 * @auther 武耀君
 * @date 2024/7/15
 *
 * 索引优先队列，在这里，元素的值越大，优先级越大
 */
public class IndexPriorityQueue<E extends Comparable<E>> {
    private static final int DEFAULT_CAPACITY = 8;
    private int capacity;
    /**
     * 元素个数
     */
    private int size;
    /**
     * 存储队列中的元素
     */
    private E[] items;
    /**
     * PriorityQueue，优先队列，存储items中元素的下标，并把这些下标组织成一个优先队列
     */
    private int[] pq;
    /**
     * 快速索引到优先队列中的元素
     * 1. 记录了items中指定下标的元素在pq中的位置，例如，items中第i个下标处的元素在pq中
     * 的下标，就是qp[i]，items[i] = items[pq[qp[i]]]，通过这种方式，当用户修改
     * 了items中的元素后，可以定位到元素在优先队列中的位置，然后维护优先队列。
     * 2. 没有qp，只能实现优先队列，有了qp，就是索引优先队列，有了它，就可以支持修改和删除队列中的元素
     */
    private int[] qp;

    @SuppressWarnings("unchecked")
    public IndexPriorityQueue() {
        capacity = DEFAULT_CAPACITY;
        size = 0;
        items = (E[]) new Comparable[capacity];
        pq = new int[capacity + 1];
        qp = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            qp[i] = -1;
        }
    }

    /**
     * 向队列中添加元素
     */
    public void add(E ele) {
        checkCap();
        items[size] = ele;
        size++;
        pq[size] = size - 1;  // 优先队列的第一位不存储元素
        qp[size - 1] = size;
        swim(size);
    }

    /**
     * 移除队列中优先级最高的元素
     */
    public E removeMaxPri() {
        E max = items[pq[1]];

        items[pq[1]] = items[pq[size]];
        size--;
        sink(1);
        return max;
    }

    /**
     * 查看当前优先级最高的元素
     */
    public E peek() {
        return items[pq[1]];
    }

    /**
     * 修改指定元素的值
     */
    public void change(E oldVal, E newVal) {
        int i = indexOf(oldVal);
        if (i == -1) {
            return;
        }
        items[i] = newVal;
        int k = qp[i];
        sink(k);
        swim(k);
    }

    /**
     * 删除指定元素
     */
    public void delete(E ele) {
        int i = indexOf(ele);
        if (i == -1) {
            return;
        }
        // 删除items数组中的值
        items[i] = null;
        int k = qp[i];    // 找到被删除的元素在优先队列中的索引，也就是在pq中的索引
        exchange(k, size); // 将优先队列中的最后一位元素移到被删除的元素所在位置
        qp[pq[size]] = -1;
        pq[size] = -1;
        size--;
        sink(k);
    }

    public int indexOf(E ele) {
        for (int i = 0; i < size; i++) {
            if (ele.equals(items[i])) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(E ele) {
        return indexOf(ele) != -1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void exchange(int i, int j) {
        int tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;

        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    /**
     * 下沉算法
     */
    private void sink(int k) {
        while (k * 2 <= size) { // 确认当前节点至少有一个子节点
            int max = k * 2; // 找到两个子节点中的较大值
            if (k * 2 + 1 <= size) {
                if (items[pq[k * 2]].compareTo(items[pq[k * 2 + 1]]) < 0) {
                    max = k * 2 + 1;
                }
            }
            // 比较当前节点和子节点的值，如果比子节点小，则交换，否则退出，没有继续下沉的必要
            if (items[pq[k]].compareTo(items[pq[max]]) < 0) {
                exchange(k , max);
            } else {
                break;
            }
            k = max;
        }
    }

    /**
     * 上浮算法，参数k是pq数组的索引
     */
    private void swim(int k) {
        while (k > 1) {
            if (items[pq[k]].compareTo(items[pq[k / 2]]) > 0) {
                exchange(k, k / 2);
            } else {
                break;
            }
            k = k / 2;
        }
    }

    @SuppressWarnings("unchecked")
    private void checkCap() {
        if (size >= capacity) {
            capacity += capacity >> 1;
            E[] items1 = (E[]) new Comparable[capacity];
            int[] pq1 = new int[capacity + 1];
            int[] qp1 = new int[capacity + 1];

            System.arraycopy(items, 0, items1, 0, size);
            System.arraycopy(pq, 1, pq1, 1, size);
            System.arraycopy(qp, 0, qp1, 0, size);

            items = items1;
            pq = pq1;
            qp = qp1;
        }
    }
}
