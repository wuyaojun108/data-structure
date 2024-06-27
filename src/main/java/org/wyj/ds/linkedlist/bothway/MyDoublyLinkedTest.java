package org.wyj.ds.linkedlist.bothway;

public class MyDoublyLinkedTest {
    public static void main(String[] args) {
        MyDoublyLinked<String> linked = new MyDoublyLinked<>();

//         test(linked);
//         test2(linked);
         test3(linked);


    }

    // 在指定位置添加和删除元素的方法
    public static void test3(MyDoublyLinked<String> linked){
        linked.addFirst("aaa");
        linked.addFirst("bbb");
        linked.addFirst("ccc");

        linked.addElement(1, "ddd");
        System.out.println(linked);

        linked.removeElement(2);
        System.out.println(linked);

    }

    public static void test2(MyDoublyLinked<String> linked){
        linked.addFirst("aaa");
        linked.addFirst("bbb");
        linked.addFirst("ccc");
        System.out.println(linked);

        System.out.println("linked.getElementByIndex(1) = " + linked.getElementByIndex(1));
        linked.setElementByIndex(0, "ddd");
        System.out.println(linked);
    }

    // 测试操作头元素和尾元素的方法
    public static void test(MyDoublyLinked<String> linked){
        linked.addFirst("aaa");
        linked.removeLast();
        System.out.println(linked);

        linked.addLast("bbb");
        linked.removeFirst();
        System.out.println(linked);

        linked.addFirst("aaa");
        linked.addFirst("bbb");
        linked.addFirst("ccc");
        System.out.println(linked);
        System.out.println("linked.getLast() = " + linked.getLast());
        System.out.println("linked.getFirst() = " + linked.getFirst());
        System.out.println("linked.getSize() = " + linked.getSize());
    }

}
