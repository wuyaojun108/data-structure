package org.wyj.ds.p7_index_queue;

import org.junit.Test;

/**
 * @auther 武耀君
 * @date 2024/7/14
 */
public class IndexIndexPriorityQueueTest {
    // 测试添加和移除
    @Test
    public void test1() {
        IndexPriorityQueue<Integer> queue = new IndexPriorityQueue<>();
        queue.add(10);
        queue.add(15);
        queue.add(19);

        assert queue.size() == 3;
        assert !queue.isEmpty();
        assert queue.contains(10);
        assert queue.contains(15);
        assert queue.contains(19);
        assert !queue.contains(20);


        assert queue.peek() == 19;
        assert queue.removeMaxPri() == 19;
        assert queue.peek() == 15;
        assert queue.removeMaxPri() == 15;
        assert queue.peek() == 10;
        assert queue.removeMaxPri() == 10;

        assert queue.size() == 0;
        assert queue.isEmpty();
    }

    // 测试修改方法
    @Test
    public void test2() {
        IndexPriorityQueue<Integer> queue = new IndexPriorityQueue<>();
        queue.add(10);
        queue.add(15);
        queue.add(19);

        queue.change(10, 20);

        assert queue.size() == 3;
        assert queue.removeMaxPri() == 20;
        assert queue.removeMaxPri() == 19;
        assert queue.removeMaxPri() == 15;
    }

    // 测试删除方法
    @Test
    public void test3() {
        IndexPriorityQueue<Integer> queue = new IndexPriorityQueue<>();
        queue.add(10);
        queue.add(15);
        queue.add(19);

        queue.delete(15);
        assert queue.size() == 2;
        assert queue.removeMaxPri() == 19;
        assert queue.removeMaxPri() == 10;
    }

    // 测试扩容方法
    @Test
    public void test4() {
        IndexPriorityQueue<Integer> queue = new IndexPriorityQueue<>();
        queue.add(10);
        queue.add(15);
        queue.add(19);
        queue.add(100);

        queue.add(89);
        queue.add(99);
        queue.add(67);
        queue.add(78);

        queue.add(54);
        queue.add(59);
        queue.add(60);
        queue.add(34);

        queue.change(34, 102);
        queue.delete(60);

        queue.change(999, 888);
        queue.delete(888);

        assert queue.size() == 11;

        assert queue.removeMaxPri() == 102;
        assert queue.removeMaxPri() == 100;
        assert queue.removeMaxPri() == 99;
        assert queue.removeMaxPri() == 89;

        assert queue.removeMaxPri() == 78;
        assert queue.removeMaxPri() == 67;
        assert queue.removeMaxPri() == 59;
        assert queue.removeMaxPri() == 54;

        assert queue.removeMaxPri() == 19;
        assert queue.removeMaxPri() == 15;
        assert queue.removeMaxPri() == 10;
        assert queue.isEmpty();
    }

    @Test
    public void test5() {
        IndexPriorityQueue<Integer> queue = new IndexPriorityQueue<>();
        queue.add(10);
        queue.add(15);
        queue.add(19);
        queue.add(100);

        queue.add(89);
        queue.add(99);
        queue.add(67);
        queue.add(78);

        queue.add(54);
        queue.add(59);
        queue.add(60);
        queue.add(34);

        queue.delete(43);
        queue.delete(34);
        queue.delete(78);
        queue.delete(100);

        assert queue.size() == 9;


        assert queue.removeMaxPri() == 99;
        assert queue.removeMaxPri() == 89;

        assert queue.removeMaxPri() == 67;
        assert queue.removeMaxPri() == 60;
        assert queue.removeMaxPri() == 59;
        assert queue.removeMaxPri() == 54;

        assert queue.removeMaxPri() == 19;
        assert queue.removeMaxPri() == 15;
        assert queue.removeMaxPri() == 10;

        assert queue.isEmpty();
    }
}
