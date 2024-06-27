package org.wyj.ds.linkedlist.oneway;

import java.util.Iterator;

public class OneWayLinkedListTest {
    public static void main(String[] args) {
        OneWayLinkedList<String> list = new OneWayLinkedList<>();
        Iterator<String> iterator = list.iterator();
        iterator.next();
    }

    public static void test(){
        final OneWayLinkedList<String> list = new OneWayLinkedList<>();
        list.addElement("aaa");
        list.addElement("aaa");
        list.addElement("aaa");
        list.addElement("bbb");
        list.addElement("eee");
        list.addElement("eee");
        list.addElement("g");
        list.traverseLinkedList();
        System.out.println("list.size() = " + list.size());

        list.deleteElement("aaa");

        System.out.println("-------------------");
        list.traverseLinkedList();
        System.out.println("list.size() = " + list.size());

        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}
