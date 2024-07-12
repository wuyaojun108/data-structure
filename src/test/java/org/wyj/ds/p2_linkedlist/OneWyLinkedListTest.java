package org.wyj.ds.p2_linkedlist;

import org.junit.Test;

import java.util.Iterator;

/**
 * @auther 武耀君
 * @date 2024/7/9
 */
public class OneWyLinkedListTest {
    // 测试向头结点中添加元素
    @Test
    public void test1() {
        OneWayLinkedList<String> list = new OneWayLinkedList<>();
        list.addFirst("aaa");

        assert list.size() == 1;
        assert list.getFirst().equals("aaa");
    }

    // 测试向头结点中添加元素
    @Test
    public void test2() {
        OneWayLinkedList<String> list = new OneWayLinkedList<>();
        list.addFirst("aaa");
        list.addFirst("bbb");
        list.addFirst("ccc");

        assert list.size() == 3;
        assert list.getFirst().equals("ccc");
    }

    // 测试向尾结点中添加元素
    @Test
    public void test3() {
        OneWayLinkedList<String> list = new OneWayLinkedList<>();
        list.addLast("aaa");

        assert list.size() == 1;
        assert list.getFirst().equals("aaa");
    }

    // 测试向尾结点中添加元素
    @Test
    public void test4() {
        OneWayLinkedList<String> list = new OneWayLinkedList<>();
        list.addLast("aaa");
        list.addLast("bbb");
        list.addLast("ccc");

        assert list.size() == 3;
        assert list.getFirst().equals("aaa");
    }

    // 测试获取尾结点中的元素
    @Test
    public void test5() {
        OneWayLinkedList<String> list = new OneWayLinkedList<>();
        list.addFirst("aaa");
        list.addFirst("bbb");
        list.addFirst("ccc");

        assert list.size() == 3;
        assert list.getLast().equals("aaa");
    }

    // 测试isEmpty函数
    @Test
    public void test6() {
        OneWayLinkedList<String> list = new OneWayLinkedList<>();
        list.addFirst("aaa");
        list.addFirst("bbb");
        list.addFirst("ccc");

        assert list.size() == 3;
        assert !list.isEmpty();
        OneWayLinkedList<String> list2 = new OneWayLinkedList<>();
        assert list2.isEmpty();
    }

    // 测试移除头部节点
    @Test
    public void test7() {
        OneWayLinkedList<String> list = new OneWayLinkedList<>();

        assert list.removeFirst() == null;
        assert list.removeLast() == null;

        list.addFirst("aaa");
        list.addFirst("bbb");
        list.addFirst("ccc");

        assert list.size() == 3;
        assert list.removeFirst().equals("ccc");
        assert list.removeLast().equals("aaa");
        assert list.removeLast().equals("bbb");
        assert list.size() == 0;
        assert list.getFirst() == null;
        assert list.getLast() == null;
    }

    // 测试迭代器
    @Test
    public void test8() {
        OneWayLinkedList<String> list = new OneWayLinkedList<>();
        list.addFirst("aaa");
        list.addFirst("bbb");
        list.addFirst("ccc");

        for (String next : list) {
            System.out.println("next = " + next);
        }

        Iterator<String> iterator2 = list.iterator();
        assert iterator2.hasNext();
        assert iterator2.next().equals("ccc");
        assert iterator2.hasNext();
        assert iterator2.next().equals("bbb");
        assert iterator2.hasNext();
        assert iterator2.next().equals("aaa");
        assert !iterator2.hasNext();
    }

    // indexOf方法
    @Test
    public void test9() {
        OneWayLinkedList<String> list = new OneWayLinkedList<>();
        list.addFirst("aaa");
        list.addFirst("bbb");
        list.addFirst("ccc");

        assert list.indexOf("aaa") == 2;
        assert list.indexOf("bbb") == 1;
        assert list.indexOf("ccc") == 0;
        assert list.indexOf("ddd") == -1;
    }

    // set方法
    @Test
    public void test10() {
        OneWayLinkedList<String> list = new OneWayLinkedList<>();
        list.addFirst("aaa");
        list.addFirst("bbb");
        list.addFirst("ccc");

        String s = list.set(1, "111");
        assert s.equals("bbb");
        assert list.indexOf("111") == 1;

        String msg = null;
        try {
            String s1 = list.set(-1, "111");
        } catch (Exception e) {
            msg = e.getMessage();
        }
        assert msg.equals("下标越界：" + -1);
        try {
            String s1 = list.set(10, "111");
        } catch (Exception e) {
            msg = e.getMessage();
        }
        assert msg.equals("下标越界：" + 10);
    }

    // 测试set方法
    @Test
    public void test11() {
        OneWayLinkedList<String> list = new OneWayLinkedList<>();
        list.addFirst("aaa");
        list.addFirst("bbb");
        list.addFirst("ccc");

        list.add(1, "111");

        Iterator<String> iterator2 = list.iterator();

        assert list.size() == 4;
        assert iterator2.hasNext();
        assert iterator2.next().equals("ccc");
        assert iterator2.hasNext();
        assert iterator2.next().equals("bbb");
        assert iterator2.hasNext();
        assert iterator2.next().equals("111");
        assert iterator2.hasNext();
        assert iterator2.next().equals("aaa");
        assert !iterator2.hasNext();
    }

    // 测试remove方法
    @Test
    public void test12() {
        OneWayLinkedList<String> list = new OneWayLinkedList<>();
        list.addFirst("aaa");
        list.addFirst("bbb");
        list.addFirst("ccc");

        assert list.remove(0).equals("ccc");
        assert list.remove(0).equals("bbb");
        assert list.remove(0).equals("aaa");
        assert list.size() == 0;
        assert list.getFirst() == null;
        assert list.getLast() == null;

        list.addFirst("aaa");
        list.addFirst("bbb");
        list.addFirst("ccc");

        assert list.remove(2).equals("aaa");
        assert list.size() == 2;
    }

    // 测试clear方法
    @Test
    public void test13() {
        OneWayLinkedList<String> list = new OneWayLinkedList<>();
        list.addFirst("aaa");
        list.addFirst("bbb");
        list.addFirst("ccc");

        list.clear();
        assert list.size() == 0;
        assert list.getFirst() == null;
        assert list.getLast() == null;
    }

    // 测试reverse方法
    @Test
    public void test14() {
        OneWayLinkedList<String> list = new OneWayLinkedList<>();
        list.addFirst("aaa");
        list.addFirst("bbb");
        list.addFirst("ccc");

        list.reverse();
        Iterator<String> iterator2 = list.iterator();
        assert iterator2.hasNext();
        assert iterator2.next().equals("aaa");
        assert iterator2.hasNext();
        assert iterator2.next().equals("bbb");
        assert iterator2.hasNext();
        assert iterator2.next().equals("ccc");
        assert !iterator2.hasNext();
    }

    // 测试删除指定元素
    @Test
    public void test15() {
        OneWayLinkedList<String> list = new OneWayLinkedList<>();
        list.remove("aaa");

        assert list.size() == 0;
        assert list.getFirst() == null;
        assert list.getLast() == null;

        list.addFirst("aaa");
        list.addFirst("bbb");
        list.addFirst("ccc");
        list.addFirst("bbb");
        list.addFirst("ddd");
        list.addFirst(null);
        list.addFirst("bbb");

        list.remove("bbb");
        list.remove(null);
        list.remove("aaa");

        Iterator<String> iterator2 = list.iterator();
        assert iterator2.hasNext();
        assert iterator2.next().equals("ddd");
        assert iterator2.hasNext();
        assert iterator2.next().equals("ccc");
        // assert iterator2.hasNext();
//        assert iterator2.next().equals("aaa");
//        assert !iterator2.hasNext();
        assert list.size() == 2;
    }

}
