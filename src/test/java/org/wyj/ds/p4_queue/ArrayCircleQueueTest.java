package org.wyj.ds.p4_queue;

import org.junit.Test;

import java.util.Iterator;

/**
 * @auther 武耀君
 * @date 2024/7/10
 */
public class ArrayCircleQueueTest {
    @Test
    public void test1() {
        ArrayCircleQueue<String> strings = new ArrayCircleQueue<>();

        assert strings.isEmpty();
        assert !strings.isFull();

        strings.enQueue("aaa");
        strings.enQueue("bbb");
        strings.enQueue("ccc");

        assert strings.size() == 3;
        assert strings.exQueue().equals("aaa");
        assert strings.exQueue().equals("bbb");
        assert !strings.isEmpty();
        assert !strings.isFull();

        strings.enQueue("ddd");
        strings.enQueue("eee");
        strings.enQueue("fff");

        assert !strings.isEmpty();
        assert strings.isFull();
        assert strings.size() == 4;

        Iterator<String> iterator = strings.iterator();
        assert iterator.hasNext();
        assert iterator.next().equals("ccc");
        assert iterator.hasNext();
        assert iterator.next().equals("ddd");
        assert iterator.hasNext();
        assert iterator.next().equals("eee");
        assert iterator.hasNext();
        assert iterator.next().equals("fff");
        assert !iterator.hasNext();

        assert strings.peek().equals("ccc");
    }

    @Test
    public void test2() {
        ArrayCircleQueue<String> strings = new ArrayCircleQueue<>(10);
        String msg = null;
        try {
            String peek = strings.peek();
        } catch (Exception e) {
            msg = e.getMessage();
        }
        assert msg.equals("队列为空");

        msg = "";
        try {
            String exQueue = strings.exQueue();
        } catch (Exception e) {
            msg = e.getMessage();
        }
        assert msg.equals("队列为空");

        msg = "";
        try {
            strings.enQueue("aaa");
            strings.enQueue("bbb");
            strings.enQueue("ccc");
            strings.enQueue("ddd");
            strings.enQueue("eee");
            strings.enQueue("aaa");
            strings.enQueue("bbb");
            strings.enQueue("ccc");
            strings.enQueue("ddd");
            strings.enQueue("eee");
        } catch (Exception e) {
            msg = e.getMessage();
        }
        assert msg.equals("队列已满");

    }
}
