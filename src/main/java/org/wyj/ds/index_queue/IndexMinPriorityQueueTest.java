package org.wyj.ds.index_queue;

public class IndexMinPriorityQueueTest {
    public static void main(String[] args) {
        IndexMinPriorityQueue<String> queue = new IndexMinPriorityQueue<>(10);
        queue.add("H");
        queue.add("X");
        queue.add("Z");
        queue.add("G");
        queue.add("B");
        queue.add("I");
        queue.add("L");
        queue.add("O");
        System.out.println(queue);

        queue.change("I", "A");
        System.out.println(queue);


    }
}
