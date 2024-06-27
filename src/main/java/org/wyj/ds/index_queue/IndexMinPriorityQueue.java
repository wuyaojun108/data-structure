package org.wyj.ds.index_queue;

import java.util.Arrays;

// 索引最小优先队列
public class IndexMinPriorityQueue<E extends Comparable<E>> {
    private final E[] items;
    private final int[] pq;
    private final int[] qp;
    private int size;

    public int size(){ return size; }

    public boolean isEmpty(){ return size == 0; }

    // 初始化索引优先队列，qp数组存储pq数组的下标，由于此时pq数组中没有元素，所以qp数组中的值要变为-1.
    public IndexMinPriorityQueue(int capacity){
        items = (E[])new Comparable[capacity];
        pq = new int[capacity + 1];
        qp = new int[capacity];
        Arrays.fill(qp, -1);
        Arrays.fill(pq, -1);
    }

    // 向队列中添加元素。
    public void add(E ele){
        // 在items数组中，size = 当前数组中最后一个元素的下标 + 1，所以将新添加的元素存放在下标为size的位置即可；
        int i = size;
        items[i] = ele;
        // 在pq数组中，因为不使用第0号索引，所以，需要在size加1的下标处存放items数组中新增元素的索引
        size++;
        pq[size] = i;
        // 在qp数组中，pq数组中的索引存放在值所对应的位置处
        qp[i] = size;
        // 向数组中添加完元素之后，使用上浮算法，调整pq数组中元素的值
        swim(size);
    }

    // 上浮算法
    public void swim(int k){
        while (k > 1){
            if(isLess(k, k / 2)){ exchange(k, k / 2); }
            k = k / 2;
        }
    }


    // i和j是pq中的索引，pq中存储的是items数组中的索引，并且pq中的元素被组织成堆数据结构
    public boolean isLess(int i, int j){ return items[pq[i]].compareTo(items[pq[j]]) < 0; }

    // i和j是pq中的索引
    public void exchange(int i, int j){
        int tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
        // 更新qp数组中的值。qp中存储的值是pq中的下标，下标存放的位置依据下标在pq对应的值。pq[i]是下标
        // i处的值，所以在qp数组中，pq[i]处的位置存储的值是i。
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    public void deleteMin(){
        delete(items[pq[1]]);
    }

    // 删除数组中的元素
    public void delete(E ele){
        // 遍历items数组，获取要删除的值的下标
        if(ele == null){ return; }
        int index = getIndex(ele);
        if(index == -1){ return; }

        // 删除items数组中的值
        items[index] = null;
        // 获取pq数组中，要删除的元素所对应的下标
        int k = qp[index];
        // 交换k下标处的值和size下标处的值
        exchange(k, size);
        // 删除qp数组和pq数组中的值
        qp[pq[size]] = -1;
        pq[size] = -1;
        size--;
        // 调整堆
        sink(k);
    }

    public void change(E oldEle, E newEle){
        int index = getIndex(oldEle);
        if(index == -1){ return; }

        items[index] = newEle;
        int k = qp[index];
        sink(k);
        swim(k);
    }

    // 下沉算法
    public void sink(int k){
        while(k * 2 <= size){
            int min;
            if(k * 2 + 1 <= size){
                if(isLess(k * 2, k * 2 + 1)){
                    min = k * 2;
                }else{
                    min = k * 2 + 1;
                }
            }else{
                min = k * 2;
            }
            // 如果子结点比父节点大，子结点和父节点交换位置
            if(isLess(min, k)){
                exchange(k, min);
            }
            // 把父节点的下标赋值为子结点，继续循环，向下比较
            k = min;
        }
    }

    // 获取元素在items数组中的下标
    public int getIndex(E ele){
        for (int i = 0; i < items.length; i++) {
            if(items[i].equals(ele)){ return i; }
        }
        return -1;
    }

    public boolean contains(E ele){
        return getIndex(ele) != -1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i = 1; i <= size - 1; i++){
            builder.append(items[pq[i]]).append(", ");
        }
        builder.append(items[pq[size]]);
        return builder.toString();
    }

/*    // 测试时使用的方法，可以看清类内部的细节
    @Override
    public String toString() {
        return "IndexMinPriorityQueue{" +
                "items=" + Arrays.toString(items) +
                ", pq=" + Arrays.toString(pq) +
                ", qp=" + Arrays.toString(qp) +
                ", size=" + size +
                '}';
    }*/
}
