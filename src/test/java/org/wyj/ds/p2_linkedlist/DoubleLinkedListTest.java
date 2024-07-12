package org.wyj.ds.p2_linkedlist;

import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * @auther 武耀君
 * @date 2024/7/8
 */
public class DoubleLinkedListTest {
    // 测试向链表的尾部添加元素
    @Test
    public void test1() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("aa1");
        list.addLast("aa2");
        list.addLast("aa3");

        assert list.size() == 3;
        assert list.getFirst().equals("aa1");
        assert list.getLast().equals("aa3");
    }

    // 测试向列表头部添加元素
    @Test
    public void test2() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addFirst("aa1");
        list.addFirst("aa2");
        list.addFirst("aa3");

        assert list.size() == 3;
        assert list.getFirst().equals("aa3");
        assert list.getLast().equals("aa1");
    }

    // 测试双向迭代器
    @Test
    public void test3() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("aa1");
        list.addLast("aa2");
        list.addLast("aa3");

        ListIterator<String> iterator = list.listIterator();
        while (iterator.hasNext()) {
            System.out.println("iterator.next() = " + iterator.next());
        }

        System.out.println("-----------");
        while (iterator.hasPrevious()) {
            System.out.println("iterator.previous() = " + iterator.previous());
        }

        ListIterator<String> iterator2 = list.listIterator();

        assert iterator2.next().equals("aa1");
        assert iterator2.next().equals("aa2");
        assert iterator2.next().equals("aa3");

        assert iterator2.previous().equals("aa3");
        assert iterator2.previous().equals("aa2");
        assert iterator2.previous().equals("aa1");
    }

    // 测试移除头部元素
    @Test
    public void test5() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("aa1");
        list.addLast("aa2");
        list.addLast("aa3");

        assert list.removeFirst().equals("aa1");
        assert list.removeFirst().equals("aa2");
        assert list.removeFirst().equals("aa3");
        assert list.isEmpty();
        assert list.getFirst() == null;
        assert list.getLast() == null;
        assert list.removeFirst() == null;
        assert list.removeFirst() == null;
    }

    // 测试移除尾部元素
    @Test
    public void test6() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("aa1");
        list.addLast("aa2");
        list.addLast("aa3");

        assert list.removeLast().equals("aa3");
        assert list.removeLast().equals("aa2");
        assert list.removeLast().equals("aa1");
        assert list.isEmpty();
        assert list.getFirst() == null;
        assert list.getLast() == null;
        assert list.removeLast() == null;
        assert list.removeLast() == null;
    }

    // 测试清空链表
    @Test
    public void test7() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("aa1");
        list.addLast("aa2");
        list.addLast("aa3");

        list.clear();
        assert list.getFirst() == null;
        assert list.getLast() == null;
        assert list.isEmpty();

        list.clear();
        assert list.getFirst() == null;
        assert list.getLast() == null;
        assert list.isEmpty();

    }

    // 测试indexOf方法
    @Test
    public void test8() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("aa1");
        list.addLast("aa2");
        list.addLast("aa3");
        list.addLast("aa4");
        list.addLast("aa5");
        list.addLast(null);
        list.addLast("aaa");

        assert list.indexOf("aa4") == 3;
        assert list.indexOf("aa6") == -1;
        assert list.indexOf(null) == 5;

        DoubleLinkedList<String> list2 = new DoubleLinkedList<>();
        assert list2.indexOf("aa4") == -1;
    }

    // 测试lastIndexOf方法
    @Test
    public void test9() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("aa1");
        list.addLast(null);
        list.addLast("aa2");
        list.addLast("aa3");
        list.addLast("aa1");
        list.addLast(null);
        list.addLast("aa5");

        assert list.lastIndexOf("aa1") == 4;
        assert list.lastIndexOf(null) == 5;

        DoubleLinkedList<String> list2 = new DoubleLinkedList<>();
        assert list2.lastIndexOf("aaa") == -1;
    }

    // 测试get方法，获取指定下标处的元素
    @Test
    public void test10() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("aa1");
        list.addLast("aa2");
        list.addLast("aa3");
        list.addLast("aa4");
        list.addLast("aa5");

        assert list.get(0).equals("aa1");
        assert list.get(4).equals("aa5");

        String msg = null;
        try {
            String s = list.get(5);
        } catch (Exception e) {
            msg = e.getMessage();
        }
        assert msg.equals("下标越界：" + 5);

        try {
            String s = list.get(-1);
        } catch (Exception e) {
            msg = e.getMessage();
        }
        assert msg.equals("下标越界：" + -1);

        DoubleLinkedList<String> list2 = new DoubleLinkedList<>();
        list2.addLast("aa1");
        assert list2.get(0).equals("aa1");
    }

    // 测试set方法，设置指定下标处元素的值
    @Test
    public void test11() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("aa1");
        list.addLast("aa2");
        list.addLast("aa3");
        list.addLast("aa4");
        list.addLast("aa5");

        String s = list.set(2, "fff");
        assert s.equals("aa3");
        assert list.get(2).equals("fff");
    }

    // 测试add方法，在指定下标处添加元素
    @Test
    public void test12() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.add(0, "aaa");
        list.add(1, "bbb");
        list.add(2, "ccc");

        assert list.get(0).equals("aaa");
        assert list.get(1).equals("bbb");
        assert list.get(2).equals("ccc");

        list.add(1, "ddd");

        assert list.get(0).equals("aaa");
        assert list.get(1).equals("ddd");
        assert list.get(2).equals("bbb");
        assert list.get(3).equals("ccc");
        assert list.getFirst().equals("aaa");
        assert list.getLast().equals("ccc");

        list.add(0, "111");
        assert list.get(0).equals("111");
        assert list.getFirst().equals("111");

        String msg = null;
        try {
            list.add(-1, "aaa");
        } catch (Exception e) {
            msg = e.getMessage();
        }
        assert msg.equals("下标不正确：-1");
        try {
            list.add(10, "bbb");
        } catch (Exception e) {
            msg = e.getMessage();
        }
        assert msg.equals("下标不正确：10");

    }

    // 测试contains方法
    @Test
    public void test13() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("aa1");
        list.addLast("aa2");
        list.addLast("aa3");
        list.addLast("aa4");
        list.addLast("aa5");

        assert !list.contains("fff");
        assert list.contains("aa1");
        assert list.contains("aa5");
    }

    // 测试根据下标移除元素
    @Test
    public void test14() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("aa1");

        list.remove(0);
        assert list.getFirst() == null;
        assert list.getLast() == null;
        assert list.isEmpty();

        DoubleLinkedList<String> list2 = new DoubleLinkedList<>();
        list2.addLast("aa1");
        list2.addLast("aa2");

        list2.remove(1);
        assert Objects.equals(list2.getFirst(), "aa1");
        assert Objects.equals(list2.getLast(), "aa1");
        assert Objects.equals(list2.get(0), "aa1");
        assert list2.size() == 1;

        DoubleLinkedList<String> list3 = new DoubleLinkedList<>();
        list3.addLast("aa1");
        list3.addLast("aa2");

        list3.remove(0);
        assert Objects.equals(list3.getFirst(), "aa2");
        assert Objects.equals(list3.getLast(), "aa2");
        assert Objects.equals(list3.get(0), "aa2");
    }

    // 测试根据内容移除元素
    @Test
    public void test15() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("aa1");
        list.addLast("aa2");
        list.addLast("aa3");
        list.addLast("aa2");

        list.remove("aa2");
        assert list.getFirst().equals("aa1");
        assert list.getLast().equals("aa3");
        assert list.get(0).equals("aa1");
        assert list.get(1).equals("aa3");
    }

    // 测试addAll方法
    @Test
    public void test16() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();

        List<String> list1 = new ArrayList<>();
        list1.add("aaa");
        list1.add("bbb");
        list1.add("ccc");
        list1.add("ddd");
        list.addAll(list1);

        assert list.get(0).equals("aaa");
        assert list.get(1).equals("bbb");
        assert list.get(2).equals("ccc");
        assert list.get(3).equals("ddd");
    }

    // 测试addAll方法，在指定下标后添加元素
    @Test
    public void test17() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("111");
        list.addLast("222");
        list.addLast("333");

        List<String> list1 = new ArrayList<>();
        list1.add("aaa");
        list1.add("bbb");
        list1.add("ccc");
        list1.add("ddd");
        list.addAll(2, list1);

        assert list.get(0).equals("111");
        assert list.get(1).equals("222");
        assert list.get(2).equals("aaa");
        assert list.get(3).equals("bbb");
        assert list.get(4).equals("ccc");
        assert list.get(5).equals("ddd");
        assert list.get(6).equals("333");
    }

    // 测试containsAll方法
    @Test
    public void test18() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("111");
        list.addLast("222");
        list.addLast("333");

        List<String> list1 = new ArrayList<>();
        list1.add("111");
        list1.add("222");
        assert list.containsAll(list1);

        List<String> list2 = new ArrayList<>();
        list2.add("222");
        list2.add("444");
        assert !list.containsAll(list2);
    }

    // 测试removeAll方法
    @Test
    public void test19() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("111");
        list.addLast("222");
        list.addLast("aaa");
        list.addLast("333");

        List<String> list1 = new ArrayList<>();
        list1.add("111");
        list1.add("222");
        list1.add("333");
        list1.add("444");
        list.removeAll(list1);

        assert list.get(0).equals("aaa");
        assert list.getFirst().equals("aaa");
        assert list.getLast().equals("aaa");
    }

    // 测试retainAll方法
    @Test
    public void test20() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("111");
        list.addLast("222");
        list.addLast("aaa");
        list.addLast("333");

        List<String> list1 = new ArrayList<>();
        list1.add("111");
        list1.add("222");
        list1.add("333");
        list.retainAll(list1);

        assert list.get(0).equals("111");
        assert list.get(1).equals("222");
        assert list.get(2).equals("333");
        assert list.getFirst().equals("111");
        assert list.getLast().equals("333");
    }

    // 测试迭代器，从前向后遍历、从后向前遍历
    @Test
    public void test21() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("111");
        list.addLast("222");
        list.addLast("333");
        list.addLast("444");
        list.addLast("555");

        // 从前向后遍历
        ListIterator<String> iterator = list.listIterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println("iterator.next() = " + next);
        }


        System.out.println("--------------------");
        // 从后向前遍历
        ListIterator<String> iterator2 = list.listIterator();
        while (iterator2.hasPrevious()) {
            String previous = iterator2.previous();
            System.out.println("iterator2.previous() = " + previous);
        }

        Iterator<String> iterator1 = list.listIterator();
        assert iterator1.hasNext();
        assert iterator1.next().equals("111");
        assert iterator1.hasNext();
        assert iterator1.next().equals("222");
        assert iterator1.hasNext();
        assert iterator1.next().equals("333");
        assert iterator1.hasNext();
        assert iterator1.next().equals("444");
        assert iterator1.hasNext();
        assert iterator1.next().equals("555");
        assert !iterator1.hasNext();

        ListIterator<String> iterator3 = list.listIterator();
        assert iterator3.hasPrevious();
        assert iterator3.previous().equals("555");
        assert iterator3.hasPrevious();
        assert iterator3.previous().equals("444");
        assert iterator3.hasPrevious();
        assert iterator3.previous().equals("333");
        assert iterator3.hasPrevious();
        assert iterator3.previous().equals("222");
        assert iterator3.hasPrevious();
        assert iterator3.previous().equals("111");
        assert !iterator3.hasPrevious();
    }

    // 迭代器：遍历过程中修改元素
    @Test
    public void test22() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("111");
        list.addLast("222");
        list.addLast("333");
        list.addLast("444");
        list.addLast("555");

        // 迭代过程中修改元素
        ListIterator<String> iterator3 = list.listIterator();
        while (iterator3.hasNext()) {
            String next = iterator3.next();
            if (next.equals("222")) {
                iterator3.set("bbb");
            }
            System.out.println("iterator3.next() = " + next);
        }

        System.out.println("--------------------");
        // 迭代过程中修改元素
        ListIterator<String> iterator4 = list.listIterator();
        while (iterator4.hasNext()) {
            String next = iterator4.next();
            System.out.println("iterator4.next() = " + next);
        }
        assert list.get(1).equals("bbb");
    }

    // 迭代器：遍历过程中删除元素
    @Test
    public void test23() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("111");
        list.addLast("222");
        list.addLast("333");
        list.addLast("444");
        list.addLast("555");

        // 迭代过程中修改元素
        ListIterator<String> iterator3 = list.listIterator();
        while (iterator3.hasNext()) {
            String next = iterator3.next();
            if (next.equals("222")) {
                iterator3.remove();
            }
            System.out.println("iterator3.next() = " + next);
        }

        System.out.println("--------------------");
        // 迭代过程中修改元素
        ListIterator<String> iterator4 = list.listIterator();
        while (iterator4.hasNext()) {
            String next = iterator4.next();
            System.out.println("iterator4.next() = " + next);
        }
        assert list.get(1).equals("333");
    }

    // 迭代器：遍历过程中添加元素
    @Test
    public void test24() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("111");
        list.addLast("222");
        list.addLast("333");
        list.addLast("444");
        list.addLast("555");

        // 迭代过程中修改元素
        ListIterator<String> iterator3 = list.listIterator();
        while (iterator3.hasNext()) {
            String next = iterator3.next();
            if (next.equals("222")) {
                iterator3.add("bbb");
            }
            System.out.println("iterator3.next() = " + next);
        }

        System.out.println("--------------------");
        // 迭代过程中修改元素
        ListIterator<String> iterator4 = list.listIterator();
        while (iterator4.hasNext()) {
            String next = iterator4.next();
            System.out.println("iterator4.next() = " + next);
        }
        assert list.get(1).equals("bbb");
        assert list.get(2).equals("222");
    }

    // 迭代器：测试移除边界值
    @Test
    public void test25() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("111");
        list.addLast("222");
        list.addLast("333");
        list.addLast("444");
        list.addLast("555");

        ListIterator<String> iterator = list.listIterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.equals("555")) {
                iterator.remove();
            }
            System.out.println("next = " + next);
        }

        System.out.println("--------------------");
        ListIterator<String> iterator2 = list.listIterator();
        while (iterator2.hasNext()) {
            String next = iterator2.next();
            System.out.println("next = " + next);
        }
    }

    // toArray方法，没有指定返回数组的类型
    @Test
    public void test26() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("111");
        list.addLast("222");
        list.addLast("333");
        list.addLast("444");
        list.addLast("555");

        Object[] array = list.toArray();

        assert array != null;
        assert array[0].equals("111");
        assert array[1].equals("222");
        assert array[2].equals("333");
        assert array[3].equals("444");
        assert array[4].equals("555");
    }

    // toArray方法，指定返回数组的类型
    @Test
    public void test27() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("111");
        list.addLast("222");
        list.addLast("333");
        list.addLast("444");
        list.addLast("555");

        String[] array = list.toArray(new String[0]);

        assert array != null;
        assert array[0].equals("111");
        assert array[1].equals("222");
        assert array[2].equals("333");
        assert array[3].equals("444");
        assert array[4].equals("555");
    }

    // 测试clone方法
    @Test
    public void test28() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("111");
        list.addLast("222");
        list.addLast("333");
        list.addLast("444");
        list.addLast("555");

        DoubleLinkedList<String> clone = list.clone();
        clone.set(0, "aaa");
        clone.set(4, "bbb");

        assert list.get(0).equals("111");
        assert list.get(4).equals("555");
    }

    // 测试序列化和反序列化
    @Test
    public void test29() throws IOException, ClassNotFoundException {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("111");
        list.addLast("222");
        list.addLast("333");
        list.addLast("444");
        list.addLast("555");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
        list.writeObject(oos);

        // [-84, -19, 0, 5, 119, 4, 0, 0, 0, 5, 116, 0, 3, 49, 49, 49, 116, 0, 3, 50, 50, 50, 116, 0, 3, 51, 51, 51, 116, 0, 3, 52, 52, 52, 116, 0, 3, 53, 53, 53]
        System.out.println("byteArrayOutputStream = " +
                Arrays.toString(byteArrayOutputStream.toByteArray()));

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        DoubleLinkedList<String> arrList = new DoubleLinkedList<>();
        arrList.readObject(objectInputStream);

        assert arrList.get(0).equals("111");
        assert arrList.get(1).equals("222");
        assert arrList.get(2).equals("333");
        assert arrList.get(3).equals("444");
        assert arrList.get(4).equals("555");
    }

    // 测试reverse方法
    @Test
    public void test30() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("111");
        list.addLast("222");
        list.addLast("333");
        list.addLast("444");
        list.addLast("555");

        list.reverse();
        assert list.get(0).equals("555");
        assert list.get(1).equals("444");
        assert list.get(2).equals("333");
        assert list.get(3).equals("222");
        assert list.get(4).equals("111");
    }

    @Test
    public void test31() {
        LinkedList<String> list = new LinkedList<>();
        list.addLast("111");
        list.addLast("222");
        list.addLast("333");
        list.addLast("444");
        list.addLast("555");

        ListIterator<String> iterator = list.listIterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.equals("222")) {
                iterator.add("aaa");
            }
            System.out.println("iterator.next() = " + next);
        }
    }

    // 测试isEmpty函数
    @Test
    public void test32() {
        List<String> list = new DoubleLinkedList<>();
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("444");
        list.add("555");

        assert !list.isEmpty();
        list.clear();
        assert list.isEmpty();
    }

    // 测试双向迭代器中的nextIndex函数
    @Test
    public void test33() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("aa1");
        list.addLast("aa2");
        list.addLast("aa3");

        ListIterator<String> iterator = list.listIterator();

        assert iterator.hasNext();
        assert iterator.nextIndex() == 0;
        assert iterator.next().equals("aa1");
        assert iterator.hasNext();
        assert iterator.nextIndex() == 1;
        assert iterator.next().equals("aa2");
        assert iterator.hasNext();
        assert iterator.nextIndex() == 2;
        assert iterator.next().equals("aa3");
        assert !iterator.hasNext();
    }

    // 测试双向迭代器中的prevIndex函数
    @Test
    public void test34() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("aa1");
        list.addLast("aa2");
        list.addLast("aa3");

        ListIterator<String> iterator = list.listIterator();

        assert iterator.hasPrevious();
        assert iterator.previousIndex() == 2;
        assert iterator.previous().equals("aa3");
        assert iterator.hasPrevious();
        assert iterator.previousIndex() == 1;
        assert iterator.previous().equals("aa2");
        assert iterator.hasPrevious();
        assert iterator.previousIndex() == 0;
        assert iterator.previous().equals("aa1");
        assert !iterator.hasPrevious();
    }


}
