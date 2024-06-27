package org.wyj.ds.bin_sort_tree;

import java.util.Iterator;

public class BinaryTreeTest {
    public static void main(String[] args) {
        BinaryTree<Integer, String> tree = new BinaryTree<>();

        tree.put(20, "aaa");
        tree.put(11, "bbb");
        tree.put(5, "ccc");
        tree.put(7, "ddd");
        tree.put(30, "fff");
        tree.put(25, "GGG");
        tree.put(22, "HHH");
        tree.put(39, "YYY");
        tree.put(15, "XXX");
        tree.put(17, "WWW");
        tree.put(3, "GGG");
        tree.put(14, "HHH");
        tree.put(38, "YYY");
        tree.put(40, "XXX");
        tree.put(26, "WWW");
        tree.put(4, "abc");

/*
        tree.put(20, "aaa");
        tree.put(11, "bbb");
        tree.put(30, "ccc");
        tree.put(25, "GGG");
        tree.put(39, "YYY");
*/

        System.out.println(tree);
        System.out.println("---------------");
        System.out.println("tree.contains(26) = " + tree.contains(26));
        System.out.println("---------------");
        System.out.println("tree.getValue(26) = " + tree.getValue(26));
        System.out.println("---------------");
        System.out.println(tree);
        tree.remove(5);
        System.out.println(tree);
        System.out.println("---------------");
        System.out.println("使用递归实现前序遍历：");
        tree.preOrderTraverse().forEach(System.out::println);
        System.out.println("---------------");
        System.out.println("使用循环实现前序遍历：");
        tree.preOrder().forEach(System.out::println);
        System.out.println("---------------");
        System.out.println("使用递归实现中序遍历：");
        tree.midOrderTraverse().forEach(System.out::println);
        System.out.println("---------------");
        System.out.println("使用循环实现中序遍历：");
        tree.midOrder().forEach(System.out::println);
        System.out.println("---------------");
        System.out.println("使用递归实现后序遍历：");
        tree.postOrderTraverse().forEach(System.out::println);
        System.out.println("---------------");
        System.out.println("使用循环实现后序遍历：");
        tree.postOrder().forEach(System.out::println);
        System.out.println("---------------");
        System.out.println("使用循环实现层序遍历：");
        tree.levelOrder().forEach(System.out::println);
        System.out.println("---------------");
        System.out.println("tree.maxDepth() = " + tree.maxDepth());
        System.out.println("tree.minDepth() = " + tree.minDepth());
        System.out.println("---------------");
        Iterator<String> iterator = tree.iterator();
        System.out.println("使用迭代器来遍历元素");
        iterator.forEachRemaining(System.out::println);
    }
}
