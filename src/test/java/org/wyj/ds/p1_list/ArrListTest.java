package org.wyj.ds.p1_list;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * @auther 武耀君
 * @date 2024/7/5
 */
public class ArrListTest {
    // 测试向列表中添加元素
    @Test
    public void test1() {
        ArrList<String> arrList = new ArrList<>();
        arrList.add("aaa");
        arrList.add("aab");
        arrList.add("aac");
        arrList.add("aad");
        arrList.add("aae");
        arrList.add("aaf");
        arrList.add("aag");
        arrList.add("aah");
        arrList.add("aai");

        for (int i = 0; i < arrList.size(); i++) {
            System.out.println(arrList.get(i));
        }
        assert !arrList.isEmpty();
        assert arrList.size() == 9;
    }

    // 测试根据下标来移除元素
    @Test
    public void test2() {
        ArrList<String> arrList = new ArrList<>();
        arrList.add("aaa");
        arrList.add("aab");
        arrList.add("aac");
        arrList.add("aad");
        arrList.add("aae");
        arrList.add("aaf");
        arrList.add("aag");
        arrList.add("aah");

        String oldValue = arrList.remove(7);
        for (int i = 0; i < arrList.size(); i++) {
            System.out.println(arrList.get(i));
        }

        assert oldValue.equals("aah");
        assert arrList.size() == 7;
    }

    // 测试移除指定元素
    @Test
    public void test3() {
        ArrList<String> arrList = new ArrList<>();
        arrList.add(null);
        arrList.add("aaa");
        arrList.add("aab");
        arrList.add("aac");
        arrList.add("aad");
        arrList.add("aae");
        arrList.add("aaa");

        arrList.remove("aaa");
        arrList.remove(null);
        for (int i = 0; i < arrList.size(); i++) {
            System.out.println(arrList.get(i));
        }
        assert !arrList.contains("aaa");
        assert !arrList.contains(null);
        assert arrList.size() == 4;
    }

    // 测试isEmpty函数
    @Test
    public void test4() {
        ArrList<String> arrList = new ArrList<>();
        System.out.println("arrList.isEmpty() = " + arrList.isEmpty());  // true
        arrList.add(null);
        System.out.println("arrList.isEmpty() = " + arrList.isEmpty());  // false
        assert !arrList.isEmpty();
    }

    // 测试clear方法
    @Test
    public void test5() {
        ArrList<String> arrList = new ArrList<>();
        arrList.add(null);
        arrList.add("aaa");
        arrList.add("aab");
        arrList.add("aac");
        arrList.add("aad");
        arrList.add("aae");
        arrList.add("aaf");
        arrList.add("aag");
        arrList.add("aah");
        arrList.add("aai");

        arrList.clear();
        assert arrList.isEmpty();
    }

    // 测试contains方法
    @Test
    public void test6() {
        ArrList<String> arrList = new ArrList<>();
        arrList.add("aaa");
        arrList.add(null);
        arrList.add("aab");
        arrList.add("aac");
        arrList.add("aad");
        arrList.add("aae");
        arrList.add("aaf");
        arrList.add("aag");
        arrList.add("aah");
        arrList.add("aai");

        assert arrList.contains("aah");
        assert arrList.contains(null);
    }

    // 测试迭代器
    @Test
    public void test7() {
        ArrList<String> arrList = new ArrList<>();
        arrList.add("aaa");
        arrList.add(null);
        arrList.add("aab");
        arrList.add("aac");
        arrList.add("aad");
        arrList.add("aae");
        arrList.add("aaf");
        arrList.add("aag");
        arrList.add("aah");
        arrList.add("aai");

        assert arrList.iterator() != null;

        Iterator<String> iterator = arrList.iterator();
        while (iterator.hasNext()) {
            System.out.println("iterator.next() = " + iterator.next());
        }
    }

    // 测试addAll方法
    @Test
    public void test8() {
        ArrList<String> arrList = new ArrList<>();
        arrList.add("aaa");
        arrList.add(null);
        arrList.add("aab");
        arrList.add("aac");
        arrList.add("aad");
        arrList.add("aae");
        arrList.add("aaf");
        arrList.add("aag");
        arrList.add("aah");
        arrList.add("aai");

        ArrList<String> arr2List = new ArrList<>();
        arr2List.addAll(arrList);
        for (int i = 0; i < arr2List.size(); i++) {
            System.out.println(arrList.get(i));
        }

        assert arr2List.size() == 10;
    }

    // 测试当前类是否可以被遍历
    @Test
    public void test10() {
        ArrList<String> arrList = new ArrList<>();
        arrList.add("aaa");
        arrList.add(null);
        arrList.add("aab");
        arrList.add("aac");
        arrList.add("aad");
        arrList.add("aae");
        arrList.add("aaf");
        arrList.add("aag");
        arrList.add("aah");
        arrList.add("aai");

        for (String s : arrList) {
            System.out.println("s = " + s);
        }
    }

    // 测试在指定位置添加元素
    @Test
    public void test11() {
        ArrList<String> arrList = new ArrList<>();
        arrList.add("aaa");
        arrList.add("aab");
        arrList.add("aac");
        arrList.add("aad");
        arrList.add("aae");
        arrList.add("aaf");
        arrList.add("aag");
        arrList.add("aah");

        String oldValue = arrList.set(7, "fff");
        System.out.println("oldValue = " + oldValue);
        for (String s : arrList) {
            System.out.println("s = " + s);
        }

        assert "fff".equals(arrList.get(7));
    }

    // 测试indexOf方法
    @Test
    public void test12() {
        ArrList<String> arrList = new ArrList<>();
        arrList.add("aaa");
        arrList.add(null);
        arrList.add("aab");
        arrList.add("aac");
        arrList.add("aad");
        arrList.add("aae");
        arrList.add("aaf");
        arrList.add("aag");
        arrList.add("aah");
        arrList.add("aai");

        assert arrList.indexOf("aab") == 2;
    }

    // 测试lastIndexOf方法
    @Test
    public void test13() {
        ArrList<String> arrList = new ArrList<>();
        arrList.add("aaa");
        arrList.add(null);
        arrList.add("aab");
        arrList.add("aac");
        arrList.add("aad");
        arrList.add("aae");
        arrList.add("aaf");
        arrList.add("aag");
        arrList.add("aab");
        arrList.add("aai");

        assert arrList.lastIndexOf("aab") == 8;
        assert arrList.lastIndexOf(null) == 1;
        assert arrList.lastIndexOf("www") == -1;

        ArrList<String> arr2List = new ArrList<>();
        assert arrList.lastIndexOf("aa") == -1;
    }

    // 测试toArray方法
    @Test
    public void test14() {
        ArrList<String> arrList = new ArrList<>();
        arrList.add("aaa");
        arrList.add(null);
        arrList.add("aab");
        arrList.add("aac");
        arrList.add("aad");
        arrList.add("aae");
        arrList.add("aaf");
        arrList.add("aag");
        arrList.add("aab");
        arrList.add("aai");

        assert arrList.toArray() != null;

        Object[] array = arrList.toArray();
        for (Object o : array) {
            System.out.println("o = " + o);
        }
    }

    // 测试在指定位置添加元素
    @Test
    public void test15() {
        ArrList<String> arrList = new ArrList<>();
        arrList.add("aaa");
        arrList.add("aab");
        arrList.add("aac");
        arrList.add("aad");
        arrList.add("aae");
        arrList.add("aaf");
        arrList.add("aag");
        arrList.add("aab");

        arrList.add(7, "fff");

        for (String s : arrList) {
            System.out.println("s = " + s);
        }

        assert Objects.equals(arrList.get(7), "fff");
        assert arrList.size() == 9;
    }

    // 测试将集合转换为数组的方法
    @Test
    public void test16() {
        ArrList<String> arrList = new ArrList<>();
        arrList.add("aaa");
        arrList.add("aab");
        arrList.add("aac");
        arrList.add("aad");
        arrList.add("aae");
        arrList.add("aaf");
        arrList.add("aag");
        arrList.add("aab");

        Object[] array = arrList.toArray();
        assert array != null;
        assert array.length == 8;
    }

    // 测试将集合转换为数组的方法，指定数组的类型
    @Test
    public void test17() {
        ArrList<String> arrList = new ArrList<>();
        arrList.add("aaa");
        arrList.add("aab");
        arrList.add("aac");
        arrList.add("aad");
        arrList.add("aae");
        arrList.add("aaf");
        arrList.add("aag");
        arrList.add("aab");

        String[] arr = arrList.toArray(new String[0]);
        assert arr != null;
        assert arr.length == 8;
    }

    // 使用默认的序列化策略进行序列化和反序列化
    @Test
    public void test18() throws IOException, ClassNotFoundException {
        // 序列化
        ArrList<Object> arrList = new ArrList<>();
        arrList.add("aaa");
        arrList.add("aab");
        arrList.add(null);
        arrList.add("aac");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(arrList);

        byte[] byteArray = byteArrayOutputStream.toByteArray();
        // [-84, -19, 0, 5, 115, 114, 0, 31, 111, 114, 103, 46, 119, 121, 106, 46, 112, 49, 95, 100, 115, 46, 112, 49, 95, 108, 105, 115, 116, 46, 77, 121, 65, 114, 114, 76, 105, 115, 116, 0, 0, 0, 0, 0, 0, 4, -46, 2, 0, 3, 73, 0, 8, 99, 97, 112, 97, 99, 105, 116, 121, 73, 0, 4, 115, 105, 122, 101, 91, 0, 3, 97, 114, 114, 116, 0, 19, 91, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 59, 120, 112, 0, 0, 0, 8, 0, 0, 0, 4, 117, 114, 0, 19, 91, 76, 106, 97, 118, 97, 46, 108, 97, 110, 103, 46, 79, 98, 106, 101, 99, 116, 59, -112, -50, 88, -97, 16, 115, 41, 108, 2, 0, 0, 120, 112, 0, 0, 0, 8, 116, 0, 3, 97, 97, 97, 116, 0, 3, 97, 97, 98, 112, 116, 0, 3, 97, 97, 99, 112, 112, 112, 112]
        System.out.println("byteArray = " + Arrays.toString(byteArray));

        // 反序列化
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        ArrList<String> arr2List = (ArrList<String>) objectInputStream.readObject();

        for (String o : arr2List) {
            System.out.println("o = " + o);
        }
        assert arr2List.get(3).equals("aac");
    }

    // 使用定制的序列化和反序列化策略
    @Test
    public void test19() throws IOException, ClassNotFoundException {
        // 序列化
        ArrList<Object> arrList = new ArrList<>();
        arrList.add("aaa");
        arrList.add("aab");
        arrList.add(null);
        arrList.add("aac");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        arrList.writeObject(objectOutputStream);

        byte[] byteArray = byteArrayOutputStream.toByteArray();
        // [-84, -19, 0, 5, 119, 8, 0, 0, 0, 4, 0, 0, 0, 8, 117, 114, 0, 19, 91, 76, 106, 97, 118, 97, 46, 108, 97, 110, 103, 46, 79, 98, 106, 101, 99, 116, 59, -112, -50, 88, -97, 16, 115, 41, 108, 2, 0, 0, 120, 112, 0, 0, 0, 8, 116, 0, 3, 97, 97, 97, 116, 0, 3, 97, 97, 98, 112, 116, 0, 3, 97, 97, 99, 112, 112, 112, 112]
        System.out.println("byteArray = " + Arrays.toString(byteArray));

        // 反序列化
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        ArrList<String> arr2List = new ArrList<>();
        arr2List.readObject(objectInputStream);
        for (String o : arr2List) {
            System.out.println("o = " + o);
        }

        System.out.println("------------------");
        arr2List.add("aaa");
        arr2List.add("aab");
        arr2List.add("aac");
        arr2List.add("aad");
        arr2List.add("aae");
        arr2List.add("aaf");
        arr2List.add("aag");
        arr2List.add("aab");
        for (String o : arr2List) {
            System.out.println("o = " + o);
        }

        assert arr2List.get(11).equals("aab");
    }

    // 测试removeAll方法
    @Test
    public void test20() {
        ArrList<String> arrList = new ArrList<>();
        arrList.add("aaa");
        arrList.add("aab");
        arrList.add("aac");
        arrList.add("aad");

        ArrayList<String> list2 = new ArrayList<>();
        list2.add("aab");
        list2.add("aad");
        list2.add("aaf");
        arrList.removeAll(list2);

        for (String s : arrList) {
            System.out.println("s = " + s);
        }
        assert arrList.get(1).equals("aac");
    }

    // 测试retainAll方法
    @Test
    public void test21() {
        ArrList<String> arrList = new ArrList<>();
        arrList.add("aaa");
        arrList.add("aab");
        arrList.add("aac");
        arrList.add("aad");

        ArrayList<String> list2 = new ArrayList<>();
        list2.add("aab");
        list2.add("aad");
        list2.add("aaf");
        arrList.retainAll(list2);

        for (String s : arrList) {
            System.out.println("s = " + s);
        }
        assert arrList.get(1).equals("aad");
    }

    // 测试containsAll方法
    @Test
    public void test22() {
        ArrList<String> arrList = new ArrList<>();
        arrList.add("aaa");
        arrList.add("aab");
        arrList.add("aac");
        arrList.add("aad");

        ArrayList<String> list2 = new ArrayList<>();
        list2.add("aab");
        list2.add("aad");
        list2.add("aaf");
        assert !arrList.containsAll(list2);

        ArrayList<String> list3 = new ArrayList<>();
        list3.add("aab");
        list3.add("aad");
        assert arrList.containsAll(list3);
    }

    // 测试clone方法
    @Test
    public void test23() {
        ArrList<String> arrList = new ArrList<>();
        arrList.add("aaa");
        arrList.add("aab");
        arrList.add("aac");
        arrList.add("aad");

        ArrList<String> arr2List = arrList.clone();
        arr2List.set(2, "fff");

        // 拷贝出的数组和原先的数组互不影响
        assert arrList.get(2).equals("aac");
    }

    // 测试addAll方法，在指定下标处添加元素
    @Test
    public void test24() {
        ArrList<String> arrList = new ArrList<>();
        arrList.add("aaa");
        arrList.add("aab");
        arrList.add("aac");
        arrList.add("aad");

        ArrList<String> arr2List = new ArrList<>();
        arr2List.add("fff");
        arr2List.add("eee");
        arr2List.addAll(1, arrList);
        for (int i = 0; i < arr2List.size(); i++) {
            System.out.println(arr2List.get(i));
        }

        assert arr2List.size() == 6;
        assert arr2List.get(5).equals("eee");
        assert arr2List.get(4).equals("aad");
    }

    // 测试get方法
    @Test
    public void test25() {
        ArrList<String> arrList = new ArrList<>();
        arrList.add("aaa");
        arrList.add("aab");
        arrList.add("aac");
        arrList.add("aad");

        String msg = null;
        try {
            String s = arrList.get(-1);
        } catch (Exception e) {
            msg = e.getMessage();
        }
        assert msg.equals("下标越界：-1");
        try {
            String s = arrList.get(10);
        } catch (Exception e) {
            msg = e.getMessage();
        }
        assert msg.equals("下标越界：10");
    }
}
