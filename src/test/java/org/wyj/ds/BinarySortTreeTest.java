package org.wyj.ds;

import org.junit.Test;
import org.wyj.ds.p5_bin_sort_tree.BinarySortTree;

import java.util.List;

/**
 * @auther 武耀君
 * @date 2024/7/12
 */
public class BinarySortTreeTest {
    // 测试树的基本增删改功能
    @Test
    public void test1() {
        BinarySortTree<Integer, String> tree = new BinarySortTree<>();
        tree.add(111, "aaa");
        tree.add(222, "bbb");
        tree.add(100, "ccc");
        tree.add(99, "ccc");

        assert tree.size() == 4;
        assert tree.getValue(111).equals("aaa");
        assert tree.getValue(222).equals("bbb");
        assert tree.getValue(100).equals("ccc");

        tree.remove(111);
        assert tree.getValue(111) == null;
        assert tree.size() == 3;

        tree.remove(100);
        assert tree.size() == 2;
        assert tree.getValue(100) == null;
        assert tree.getValue(111) == null;
        assert tree.getValue(222).equals("bbb");
        assert tree.contains(222);
        assert !tree.contains(111);
        assert !tree.contains(100);

        tree.remove(222);
        tree.remove(99);
        assert tree.isEmpty();
        assert tree.getValue(222) == null;
    }

    // 树中的最大值和最小值
    @Test
    public void test2() {
        BinarySortTree<Integer, String> tree = new BinarySortTree<>();
        tree.add(111, "aaa");
        tree.add(222, "bbb");
        tree.add(100, "ccc");
        tree.add(99, "ccc");

        assert tree.getMaxK() == 222;
        assert tree.getMinK() == 99;
    }

    // 测试前序遍历
    @Test
    public void test3() {
        BinarySortTree<Integer, String> tree = new BinarySortTree<>();
        tree.add(10, "aaa");
        tree.add(8, "bbb");
        tree.add(7, "bbb");
        tree.add(15, "ccc");
        tree.add(9, "aaa");
        tree.add(11, "bbb");
        tree.add(16, "ccc");

        List<BinarySortTree.Entry<Integer, String>> entries = tree.preTraverse();
        assert entries.get(0).getKey().equals(10);
        assert entries.get(1).getKey().equals(8);
        assert entries.get(2).getKey().equals(7);
        assert entries.get(3).getKey().equals(9);
        assert entries.get(4).getKey().equals(15);
        assert entries.get(5).getKey().equals(11);
        assert entries.get(6).getKey().equals(16);
    }

    // 测试中序遍历
    @Test
    public void test4() {
        BinarySortTree<Integer, String> tree = new BinarySortTree<>();
        tree.add(10, "aaa");
        tree.add(8, "bbb");
        tree.add(7, "bbb");
        tree.add(15, "ccc");
        tree.add(9, "aaa");
        tree.add(11, "bbb");
        tree.add(16, "ccc");

        List<BinarySortTree.Entry<Integer, String>> entries = tree.midTraverse();
        assert entries.get(0).getKey().equals(7);
        assert entries.get(1).getKey().equals(8);
        assert entries.get(2).getKey().equals(9);
        assert entries.get(3).getKey().equals(10);
        assert entries.get(4).getKey().equals(11);
        assert entries.get(5).getKey().equals(15);
        assert entries.get(6).getKey().equals(16);
    }

    // 测试后序遍历
    @Test
    public void test5() {
        BinarySortTree<Integer, String> tree = new BinarySortTree<>();
        tree.add(10, "aaa");
        tree.add(8, "bbb");
        tree.add(7, "bbb");
        tree.add(15, "ccc");
        tree.add(9, "aaa");
        tree.add(11, "bbb");
        tree.add(16, "ccc");

        List<BinarySortTree.Entry<Integer, String>> entries = tree.postTraverse();
        assert entries.get(0).getKey().equals(7);
        assert entries.get(1).getKey().equals(9);
        assert entries.get(2).getKey().equals(8);
        assert entries.get(3).getKey().equals(11);
        assert entries.get(4).getKey().equals(16);
        assert entries.get(5).getKey().equals(15);
        assert entries.get(6).getKey().equals(10);
    }

    // 测试层序遍历
    @Test
    public void test6() {
        BinarySortTree<Integer, String> tree = new BinarySortTree<>();
        tree.add(10, "aaa");
        tree.add(8, "bbb");
        tree.add(7, "bbb");
        tree.add(15, "ccc");
        tree.add(9, "aaa");
        tree.add(11, "bbb");
        tree.add(16, "ccc");

        List<BinarySortTree.Entry<Integer, String>> entries = tree.levelTraverse();
        assert entries.get(0).getKey().equals(10);
        assert entries.get(1).getKey().equals(8);
        assert entries.get(2).getKey().equals(15);
        assert entries.get(3).getKey().equals(7);
        assert entries.get(4).getKey().equals(9);
        assert entries.get(5).getKey().equals(11);
        assert entries.get(6).getKey().equals(16);
    }

    // 树的最大深度
    @Test
    public void test7() {
        BinarySortTree<Integer, String> tree = new BinarySortTree<>();
        tree.add(10, "aaa");
        tree.add(8, "bbb");
        tree.add(7, "bbb");
        tree.add(15, "ccc");
        tree.add(9, "aaa");
        tree.add(11, "bbb");
        tree.add(16, "ccc");
        tree.add(19, "ccc");
        tree.add(25, "ccc");

        assert tree.maxDepth() == 5;
    }

    // 树的最小深度
    @Test
    public void test8() {
        BinarySortTree<Integer, String> tree = new BinarySortTree<>();
        tree.add(10, "aaa");
        tree.add(8, "bbb");
        tree.add(7, "bbb");
        tree.add(15, "ccc");
        tree.add(9, "aaa");
        tree.add(11, "bbb");
        tree.add(16, "ccc");
        tree.add(19, "ccc");
        tree.add(25, "ccc");

        assert tree.minDepth() == 3;
    }

    @Test
    public void test9() {
        BinarySortTree<Integer, String> tree = new BinarySortTree<>();
        tree.add(20, "aaa");
        tree.add(11, "bbb");
        tree.add(5, "ccc");
        tree.add(7, "ddd");
        tree.add(30, "fff");
        tree.add(25, "GGG");
        tree.add(22, "HHH");
        tree.add(39, "YYY");
        tree.add(15, "XXX");
        tree.add(17, "WWW");
        tree.add(3, "GGG");
        tree.add(14, "HHH");
        tree.add(38, "YYY");
        tree.add(40, "XXX");
        tree.add(26, "WWW");
        tree.add(4, "abc");

        tree.printTree();

        System.out.println("---------------------");
        BinarySortTree<Integer, String> tree2 = new BinarySortTree<>();
        tree2.printTree();
        tree2.add(50, "aaa");
        tree2.add(25, "bbb");
        tree2.add(15, "bbb");
        tree2.add(10, "ccc");
        tree2.add(40, "aaa");
        tree2.add(45, "bbb");
        tree2.add(30, "ccc");
        tree2.add(75, "ccc");
        tree2.add(65, "aaa");
        tree2.add(86, "bbb");
        tree2.add(90, "ccc");
        tree2.printTree();
    }

    // 测试基本的增删改查功能
    @Test
    public void test10() {
        BinarySortTree<Integer, String> tree = new BinarySortTree<>();

        assert tree.minDepth() == 0;
        assert tree.maxDepth() == 0;

        tree.add(20, "aaa");
        tree.add(11, "bbb");
        tree.add(25, "ccc");
        assert tree.minDepth() == 2;

        tree.clear();
        assert tree.size() == 0;
        assert tree.isEmpty();
        assert tree.getValue(20) == null;


        tree.add(20, "aaa");
        tree.add(11, "bbb");
        tree.add(5, "ccc");
        tree.add(7, "ddd");
        tree.add(30, "fff");
        tree.add(25, "GGG");
        tree.add(22, "HHH");
        tree.add(39, "YYY");
        tree.add(15, "XXX");
        tree.add(17, "WWW");
        tree.add(3, "GGG");
        tree.add(14, "HHH");
        tree.add(38, "YYY");
        tree.add(40, "XXX");
        tree.add(26, "WWW");
        tree.add(4, "abc");

        assert tree.getValue(7).equals("ddd");
        tree.add(7, "FFF");
        assert tree.getValue(7).equals("FFF");

    }

    // 测试clear方法
    @Test
    public void test11() {
        BinarySortTree<Integer, String> tree = new BinarySortTree<>();
        tree.add(20, "aaa");
        tree.add(11, "bbb");
        tree.add(25, "ccc");

        assert tree.minDepth() == 2;
        assert !tree.isEmpty();

        tree.clear();
        assert tree.size() == 0;
        assert tree.isEmpty();
        assert tree.getValue(20) == null;
    }

    // 测试最大深度和最小深度
    @Test
    public void test12() {
        BinarySortTree<Integer, String> tree = new BinarySortTree<>();

        tree.add(20, "aaa");
        tree.add(11, "bbb");

        assert tree.maxDepth() == 2;
        assert tree.minDepth() == 1;

        tree.remove(11);
        assert tree.maxDepth() == 1;
        assert tree.minDepth() == 1;
    }

    // 测试remove方法
    @Test
    public void test13() {
        BinarySortTree<Integer, String> tree = new BinarySortTree<>();

        tree.add(20, "aaa");
        tree.add(11, "bbb");
        tree.add(25, "fff");
        tree.add(28, "fff");

        assert tree.getValue(28).equals("fff");
        tree.remove(28);
        assert tree.getValue(28) == null;

        tree.add(30, "www");
        assert tree.getValue(30).equals("www");

        tree.remove(25);
        assert tree.getValue(25) == null;
    }

    @Test
    public void test14() {
        BinarySortTree<Integer, String> tree = new BinarySortTree<>();

        tree.add(20, "aaa");
        tree.add(11, "bbb");
        tree.add(25, "fff");
        tree.add(28, "fff");

        List<BinarySortTree.Entry<Integer, String>> list = tree.midTraverse();
        assert list.get(0).getKey().equals(11);
        assert list.get(0).getValue().equals("bbb");
    }

    @Test
    public void test15() {
        BinarySortTree<Integer, String> tree = new BinarySortTree<>();
        tree.add(10, "aaa");
        tree.add(8, "bbb");
        tree.add(7, "bbb");
        tree.add(15, "ccc");
        tree.add(9, "aaa");
        tree.add(11, "bbb");
        tree.add(16, "ccc");

        List<BinarySortTree.Entry<Integer, String>> entries = tree.preOrder();
        assert entries.get(0).getKey().equals(10);
        assert entries.get(1).getKey().equals(8);
        assert entries.get(2).getKey().equals(7);
        assert entries.get(3).getKey().equals(9);
        assert entries.get(4).getKey().equals(15);
        assert entries.get(5).getKey().equals(11);
        assert entries.get(6).getKey().equals(16);
    }

    @Test
    public void test16() {
        BinarySortTree<Integer, String> tree = new BinarySortTree<>();
        tree.add(50, "aa1");
        tree.add(25, "aa2");
        tree.add(15, "aa3");
        tree.add(10, "aa4");
        tree.add(40, "aa5");
        tree.add(45, "aa6");
        tree.add(30, "aa7");
        tree.add(75, "aa8");
        tree.add(65, "aa9");
        tree.add(86, "aa10");
        tree.add(90, "aa11");

        assert tree.getValue(65).equals("aa9");
        tree.remove(65);
        assert tree.getValue(65) == null;

        tree.add(65, "aa9");

        List<BinarySortTree.Entry<Integer, String>> entries = tree.preTraverse();
        int i = 0;
        assert entries.get(i++).getKey() == 50;
        assert entries.get(i++).getKey() == 25;
        assert entries.get(i++).getKey() == 15;
        assert entries.get(i++).getKey() == 10;
        assert entries.get(i++).getKey() == 40;
        assert entries.get(i++).getKey() == 30;
        assert entries.get(i++).getKey() == 45;
        assert entries.get(i++).getKey() == 75;
        assert entries.get(i++).getKey() == 65;
        assert entries.get(i++).getKey() == 86;
        assert entries.get(i).getKey() == 90;
    }

    // 前序遍历
    @Test
    public void test17() {
        BinarySortTree<Integer, String> tree = new BinarySortTree<>();
        tree.add(50, "aa1");
        tree.add(25, "aa2");
        tree.add(15, "aa3");
        tree.add(10, "aa4");
        tree.add(40, "aa5");
        tree.add(45, "aa6");
        tree.add(30, "aa7");
        tree.add(75, "aa8");
        tree.add(65, "aa9");
        tree.add(86, "aa10");
        tree.add(90, "aa11");

        List<BinarySortTree.Entry<Integer, String>> entries = tree.preOrder();
        int i = 0;
        assert entries.get(i++).getKey() == 50;
        assert entries.get(i++).getKey() == 25;
        assert entries.get(i++).getKey() == 15;
        assert entries.get(i++).getKey() == 10;
        assert entries.get(i++).getKey() == 40;
        assert entries.get(i++).getKey() == 30;
        assert entries.get(i++).getKey() == 45;
        assert entries.get(i++).getKey() == 75;
        assert entries.get(i++).getKey() == 65;
        assert entries.get(i++).getKey() == 86;
        assert entries.get(i).getKey() == 90;
    }

    // 中序遍历
    @Test
    public void test18() {
        BinarySortTree<Integer, String> tree = new BinarySortTree<>();
        tree.add(50, "aa1");
        tree.add(25, "aa2");
        tree.add(15, "aa3");
        tree.add(10, "aa4");
        tree.add(40, "aa5");
        tree.add(45, "aa6");
        tree.add(30, "aa7");
        tree.add(75, "aa8");
        tree.add(65, "aa9");
        tree.add(86, "aa10");
        tree.add(90, "aa11");

        List<BinarySortTree.Entry<Integer, String>> entries = tree.midOrder();
        int i = 0;
        assert entries.get(i++).getKey() == 10;
        assert entries.get(i++).getKey() == 15;
        assert entries.get(i++).getKey() == 25;
        assert entries.get(i++).getKey() == 30;
        assert entries.get(i++).getKey() == 40;
        assert entries.get(i++).getKey() == 45;
        assert entries.get(i++).getKey() == 50;
        assert entries.get(i++).getKey() == 65;
        assert entries.get(i++).getKey() == 75;
        assert entries.get(i++).getKey() == 86;
        assert entries.get(i).getKey() == 90;

        entries = tree.midTraverse();
        i = 0;
        assert entries.get(i++).getKey() == 10;
        assert entries.get(i++).getKey() == 15;
        assert entries.get(i++).getKey() == 25;
        assert entries.get(i++).getKey() == 30;
        assert entries.get(i++).getKey() == 40;
        assert entries.get(i++).getKey() == 45;
        assert entries.get(i++).getKey() == 50;
        assert entries.get(i++).getKey() == 65;
        assert entries.get(i++).getKey() == 75;
        assert entries.get(i++).getKey() == 86;
        assert entries.get(i).getKey() == 90;
    }

    // 后序遍历
    @Test
    public void test19() {
        BinarySortTree<Integer, String> tree = new BinarySortTree<>();
        tree.add(50, "aa1");
        tree.add(25, "aa2");
        tree.add(15, "aa3");
        tree.add(10, "aa4");
        tree.add(40, "aa5");
        tree.add(45, "aa6");
        tree.add(30, "aa7");
        tree.add(75, "aa8");
        tree.add(65, "aa9");
        tree.add(86, "aa10");
        tree.add(90, "aa11");
        tree.add(20, "aa12");

        List<BinarySortTree.Entry<Integer, String>> entries = tree.postOrder();
        int i = 0;
        assert entries.get(i++).getKey() == 10;
        assert entries.get(i++).getKey() == 20;
        assert entries.get(i++).getKey() == 15;
        assert entries.get(i++).getKey() == 30;
        assert entries.get(i++).getKey() == 45;
        assert entries.get(i++).getKey() == 40;
        assert entries.get(i++).getKey() == 25;
        assert entries.get(i++).getKey() == 65;
        assert entries.get(i++).getKey() == 90;
        assert entries.get(i++).getKey() == 86;
        assert entries.get(i).getKey() == 75;

        entries = tree.postTraverse();
        i = 0;
        assert entries.get(i++).getKey() == 10;
        assert entries.get(i++).getKey() == 20;
        assert entries.get(i++).getKey() == 15;
        assert entries.get(i++).getKey() == 30;
        assert entries.get(i++).getKey() == 45;
        assert entries.get(i++).getKey() == 40;
        assert entries.get(i++).getKey() == 25;
        assert entries.get(i++).getKey() == 65;
        assert entries.get(i++).getKey() == 90;
        assert entries.get(i++).getKey() == 86;
        assert entries.get(i).getKey() == 75;
    }

}
