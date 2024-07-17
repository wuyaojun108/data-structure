package org.wyj.ds.p8_rb_tree;

import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

/**
 * @auther 武耀君
 * @date 2024/7/15
 */
public class RBTreeTest {
    // 测试树的最大深度、最小深度
    @Test
    public void test1() {
        RBTree<Integer, String> tree = new RBTree<>();
        assert tree.getMaxDepth() == 0;
        assert tree.getMinDepth() == 0;

        tree.add(20, "aaa");
        assert tree.getMaxDepth() == 1;
        assert tree.getMinDepth() == 1;

        tree.add(10, "bbb");
        assert tree.getMaxDepth() == 2;
        assert tree.getMinDepth() == 1;

        tree.add(30, "bbb");
        assert tree.getMaxDepth() == 2;
        assert tree.getMinDepth() == 2;

        tree.add(5, "bbb");
        assert tree.getMaxDepth() == 3;
        assert tree.getMinDepth() == 2;
        tree.printTree();
    }

    /**
     * 测试右旋
     */
    @Test
    public void test2() {
        RBTree<Integer, String> tree = new RBTree<>();
        tree.add(50, "aa1");
        tree.add(25, "aa2");
        tree.add(75, "aa3");
        tree.add(15, "aa4");
        tree.add(10, "aa5");

        tree.printTree();

        RBTree<Integer, String> tree2 = new RBTree<>();
        tree2.add(50, "aa1");
        tree2.add(25, "aa2");
        tree2.add(15, "aa4");

        System.out.println("------------");
        tree2.printTree();
    }

    /**
     * 测试左旋
     */
    @Test
    public void test3() {
        RBTree<Integer, String> tree = new RBTree<>();
        tree.add(50, "aa1");
        tree.add(25, "aa2");
        tree.add(30, "aa4");

        tree.printTree();

        RBTree<Integer, String> tree2 = new RBTree<>();
        tree2.add(50, "aa1");
        tree2.add(75, "aa2");
        tree2.add(65, "aa4");

        System.out.println("----------------");
        tree2.printTree();


        RBTree<Integer, String> tree3 = new RBTree<>();
        tree3.add(50, "aa1");
        tree3.add(75, "aa2");
        tree3.add(90, "aa4");

        System.out.println("----------------");
        tree3.printTree();
    }

    // 测试add方法：父节点为null、父节点是黑色、父节点是红色；测试get方法
    @Test
    public void test4() {
        RBTree<Integer, String> tree = new RBTree<>();
        tree.add(50, "aa1");
        tree.add(25, "aa2");
        tree.add(75, "aa3");
        tree.add(15, "aa4");
        tree.add(10, "aa5");
        tree.add(40, "aa5");
        tree.add(65, "aa6");
        tree.add(80, "aa7");
        tree.add(10, "aa8");
        tree.add(63, "aa9");
        tree.printTree();

        assert tree.get(50).equals("aa1");
        assert tree.get(10).equals("aa8");
    }

    @Test
    public void test5() {
        RBTree<Integer, String> tree = new RBTree<>();
        tree.add(50, "aa1");
        tree.add(75, "aa2");
        tree.add(100, "aa3");
        tree.add(150, "aa3");
        tree.add(200, "aa3");
        tree.add(80, "aa3");
        tree.add(78, "aa3");
        tree.add(79, "aa3");

        tree.printTree();

        assert tree.get(50).equals("aa1");
        // assert tree.get(10).equals("aa8");
    }

    // 测试remove方法：移除红色叶子节点、移除有两个子节点的节点、移除有一个红色子节点的节点
    @Test
    public void test6() {
        RBTree<Integer, String> tree = new RBTree<>();
        tree.add(200, "a1");
        tree.add(100, "a2");
        tree.add(250, "a3");
        tree.add(300, "a4");
        tree.add(350, "a5");
        tree.add(400, "a6");
        tree.printTree();

        String val = tree.remove(400); // 移除红色叶子节点
        assert val.equals("a6");
        assert tree.get(400) == null;
        assert tree.size() == 5;
        assert !tree.isEmpty();
        System.out.println("-------------------");
        tree.printTree();

        tree.add(370, "a7");
        tree.add(345, "a8");
        System.out.println("-----------");
        tree.printTree();

        val = tree.remove(350);  // 移除有两个子节点的节点
        assert val.equals("a5");
        assert tree.get(350) == null;
        assert tree.size() == 6;
        System.out.println("-----------");
        tree.printTree();

        val = tree.remove(345);   // 移除有一个红色子节点的节点
        assert val.equals("a8");
        assert tree.get(345) == null;
        assert tree.size() == 5;
        System.out.println("-----------");
        tree.printTree();

        val = tree.remove(1000);
        assert val == null;
    }

    // 测试remove方法：移除根节点
    @Test
    public void test7() {
        RBTree<Integer, String> tree = new RBTree<>();
        tree.add(200, "a1");

        tree.printTree();
        String val = tree.remove(200);
        assert val.equals("a1");
        assert tree.size() == 0;
        assert tree.isEmpty();
        tree.printTree();
        System.out.println("-----------");
    }

    // 测试remove方法：删除黑色节点，并且它的兄弟节点是红色
    @Test
    public void test8() {
        RBTree<Integer, String> tree = new RBTree<>();
        tree.add(200, "a1");
        tree.add(100, "a2");
        tree.add(250, "a3");
        tree.add(300, "a4");
        tree.add(350, "a5");
        tree.add(400, "a6");

        tree.printTree();

        String val = tree.remove(100);
        assert val.equals("a2");
        assert tree.get(100) == null;
        System.out.println("-----------------");
        tree.printTree();
    }

    // 测试remove方法：删除黑色节点，并且它的兄弟节点是黑色、有一个方向相反的子节点
    @Test
    public void test9() {
        RBTree<Integer, String> tree = new RBTree<>();
        tree.add(200, "a1");
        tree.add(100, "a2");
        tree.add(250, "a3");
        tree.add(230, "a5");

        tree.printTree();
        String val = tree.remove(100);

        assert val.equals("a2");
        assert tree.get(100) == null;

        System.out.println("-----------------");
        tree.printTree();
    }

    // 测试remove方法：删除黑色节点，并且它的兄弟节点是黑色、有两个子节点
    @Test
    public void test10() {
        RBTree<Integer, String> tree = new RBTree<>();
        tree.add(200, "a1");
        tree.add(100, "a2");
        tree.add(250, "a3");
        tree.add(230, "a5");
        tree.add(300, "a5");

        tree.printTree();
        tree.remove(100);
        tree.printTree();
    }

    // 测试remove方法：删除黑色节点，并且它的兄弟节点是黑色、有一个方向相同的子节点
    @Test
    public void test11() {
        RBTree<Integer, String> tree = new RBTree<>();
        tree.add(200, "a1");
        tree.add(100, "a2");
        tree.add(250, "a3");
        tree.add(300, "a5");

        tree.printTree();
        tree.remove(100);
        tree.printTree();
    }


    @Test
    public void test12() {
        RBTree<Integer, String> tree = new RBTree<>();
        tree.add(200, "a1");
        tree.add(100, "a2");
        tree.add(250, "a3");
        tree.add(50, "a5");

        tree.printTree();
        tree.remove(250);

        System.out.println("--------------");
        tree.printTree();
    }

    @Test
    public void test13() {
        RBTree<Integer, String> tree = new RBTree<>();
        tree.add(200, "a1");
        tree.add(100, "a2");
        tree.add(250, "a3");
        tree.add(150, "a5");

        tree.printTree();
        tree.remove(250);

        System.out.println("--------------");
        tree.printTree();
    }

    @Test
    public void test14() {
        RBTree<Integer, String> tree = new RBTree<>();
        tree.add(200, "a1");
        tree.add(100, "a2");
        tree.add(250, "a3");
        tree.add(50, "a4");
        tree.add(150, "a5");
        tree.add(25, "a7");

        tree.printTree();
        tree.remove(250);

        System.out.println("--------------");
        tree.printTree();
    }

    @Test
    public void test15() {
        RBTree<Integer, String> tree = new RBTree<>();
        tree.add(300, "a1");
        tree.add(200, "a2");
        tree.add(500, "a3");
        tree.add(400, "a4");
        tree.add(600, "a4");

        tree.printTree();

        tree.add(650, "a6");
        System.out.println("-----------------");
        tree.printTree();

        tree.remove(650);
        System.out.println("-----------------");
        tree.printTree();

        tree.remove(400);
        System.out.println("-----------------");
        tree.printTree();

        tree.remove(600);
        System.out.println("-----------------");
        tree.printTree();

        tree.remove(200);
        System.out.println("-----------------");
        tree.printTree();
    }

    @Test
    public void test16() {
        RBTree<Integer, String> tree = new RBTree<>();
        tree.add(300, "aaa");
        tree.add(200, "bbb");
        tree.add(500, "ccc");
        tree.add(100, "ddd");
        tree.add(250, "eee");
        tree.add(400, "fff");
        tree.add(600, "hhh");
        tree.add(50, "iii");
        tree.add(125, "jjj");
        tree.add(220, "kkk");
        tree.add(270, "lll");
        tree.add(350, "mmm");
        tree.add(450, "nnn");
        tree.add(550, "ooo");
        tree.add(650, "ppp");
        tree.add(25, "qqq");

        tree.printTree();

        String val = tree.remove(300);
        assert val.equals("aaa");
        assert tree.get(300) == null;
        assert tree.isValidRBTree();
        System.out.println("------------------");
        tree.printTree();

        val = tree.remove(25);
        assert val.equals("qqq");
        assert tree.get(25) == null;
        assert tree.isValidRBTree();
        System.out.println("------------------");
        tree.printTree();

        val = tree.remove(100);
        assert val.equals("ddd");
        assert tree.get(100) == null;
        assert tree.isValidRBTree();
        System.out.println("------------------");
        tree.printTree();

        val = tree.remove(125);
        assert val.equals("jjj");
        assert tree.get(125) == null;
        assert tree.isValidRBTree();
        System.out.println("------------------");
        tree.printTree();

        val = tree.remove(50);
        assert val.equals("iii");
        assert tree.get(50) == null;
        System.out.println("------------------");
        tree.printTree();
        assert tree.isValidRBTree();


        val = tree.remove(200);
        assert tree.isValidRBTree();
        System.out.println("------------------");
        tree.printTree();

        val = tree.remove(250);
        assert tree.isValidRBTree();
        System.out.println("------------------");
        tree.printTree();


        val = tree.remove(220);
        assert tree.isValidRBTree();
        System.out.println("------------------");
        tree.printTree();

        val = tree.remove(270);
        assert tree.isValidRBTree();
        System.out.println("------------------");
        tree.printTree();

        val = tree.remove(350);
        assert tree.isValidRBTree();
        System.out.println("------------------");
        tree.printTree();

        val = tree.remove(400);
        assert tree.isValidRBTree();
        System.out.println("------------------");
        tree.printTree();

        val = tree.remove(450);
        assert tree.isValidRBTree();
        System.out.println("------------------");
        tree.printTree();

        val = tree.remove(550);
        assert tree.isValidRBTree();
        tree.add(624, "aaa");
        System.out.println("------------------");
        tree.printTree();

        val = tree.remove(500);
        assert tree.isValidRBTree();
        System.out.println("------------------");
        tree.printTree();

        val = tree.remove(600);
        assert tree.isValidRBTree();
        System.out.println("------------------");
        tree.printTree();
    }

    @Test
    public void test17() {
        RBTree<Integer, String> tree = new RBTree<>();
        tree.add(200, "aaa");
        tree.add(100, "aab");
        tree.add(300, "aac");
        tree.add(250, "aad");
        tree.add(450, "aae");
        tree.add(350, "aaf");

        tree.printTree();

        String val = tree.remove(350);
        assert val.equals("aaf");
        assert tree.isValidRBTree();
        System.out.println("--------------------");
        tree.printTree();

        val = tree.remove(450);
        assert val.equals("aae");
        assert tree.isValidRBTree();
        System.out.println("--------------------");
        tree.printTree();

        val = tree.remove(250);
        assert tree.isValidRBTree();
        System.out.println("--------------------");
        tree.printTree();

        val = tree.remove(300);
        assert tree.isValidRBTree();
        System.out.println("--------------------");
        tree.printTree();
    }

    @Test
    public void test18() {
        RBTree<Integer, String> tree = new RBTree<>();
        tree.add(300, "aaa");
        tree.add(200, "bbb");
        tree.add(500, "ccc");
        tree.add(100, "ddd");
        tree.add(250, "eee");
        tree.add(400, "fff");
        tree.add(600, "hhh");
        tree.add(50, "iii");
        tree.add(125, "jjj");
        tree.add(220, "kkk");
        tree.add(270, "lll");
        tree.add(350, "mmm");
        tree.add(450, "nnn");
        tree.add(550, "ooo");
        tree.add(650, "ppp");
        tree.add(25, "qqq");

        tree.printTree();

        tree.remove(50);
        System.out.println("------------");
        tree.printTree();

        tree.remove(650);
        System.out.println("------------");
        tree.printTree();

        tree.remove(600);
        System.out.println("------------");
        tree.printTree();
    }

    /**
     * 测试isValidRBTree方法，校验红黑树是否符合要求，需要配合debug，在运行中修改变量
     */
    @Test
    public void test19() {
        RBTree<Integer, String> tree = new RBTree<>();
        tree.add(30, "aaa");
        tree.add(20, "bbb");
        tree.add(50, "ccc");
        tree.add(10, "ddd");
        tree.add(25, "eee");

        tree.printTree();
        try {
            assert tree.isValidRBTree();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("----------");
        tree.printTree();
    }

    @Test
    public void test20() {
        RBTree<Integer, String> tree = new RBTree<>();
        HashSet<Integer> integers = new HashSet<>();

        Random random = new Random();
        for (int i = 0; i < 20000; i++) {
            integers.add(random.nextInt(1000000));
            if (integers.size() == 10000) {
                break;
            }
        }

        for (Integer integer : integers) {
            tree.add(integer, "aaa");
            assert tree.isValidRBTree();
        }

        System.out.println("tree.size() = " + tree.size());

        for (Integer integer : integers) {
            tree.remove(integer);
            assert tree.isValidRBTree();
        }
    }

    public static void main(String[] args) {
        RBTree<Integer, String> tree = new RBTree<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("========功能菜单=========");
            System.out.println("1.添加元素");
            System.out.println("2.移除元素");
            System.out.println("请输入：(q 退出)");
            String str = scanner.next();
            if ("1".equals(str)) {
                System.out.println("请输入要添加的键（int类型）：");
                String key = scanner.next();
                addEle(tree, Integer.parseInt(key), null);
            } else if ("2".equals(str)) {
                System.out.println("请输入要删除的键：");
                String key = scanner.next();
                delEle(tree, Integer.parseInt(key));
            } else if("q".equals(str)){
                scanner.close();
                System.exit(0);
            }
        }
    }

    // 删除树中的某个元素并且打印树
    public static <K extends Comparable<K>, V> void delEle(RBTree<K, V> tree, K key) {
        tree.remove(key);
        tree.printTree();
    }

    // 向树中添加元素并且打印树结构
    public static <K extends Comparable<K>, V> void addEle(RBTree<K, V> tree, K key, V value) {
        tree.add(key, value);
        tree.printTree();
    }

}
