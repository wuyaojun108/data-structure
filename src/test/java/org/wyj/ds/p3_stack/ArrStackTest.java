package org.wyj.ds.p3_stack;

import org.junit.Test;

/**
 * @auther 武耀君
 * @date 2024/7/10
 */
public class ArrStackTest {
    // 测试栈的基本功能
    @Test
    public void test1() {
        ArrStack<String> stack = new ArrStack<>();
        stack.push("aaa");
        stack.push("bbb");
        stack.push("ccc");

        assert !stack.isEmpty();
        assert stack.size() == 3;

        assert stack.peek().equals("ccc");
        assert stack.pop().equals("ccc");
        assert stack.pop().equals("bbb");
        assert stack.pop().equals("aaa");
        assert stack.isEmpty();
        assert stack.size() == 0;
    }

    // 测试栈的扩容功能
    @Test
    public void test2() {
        ArrStack<String> stack = new ArrStack<>();

        int len = 1024;
        for (int i = 0; i < len; i++) {
            stack.push(i + "");
        }

        for (int i = 0; i < len; i++) {
            assert stack.pop().equals((len - i - 1) + "");
        }
    }

    // 测试栈的扩容功能
    @Test
    public void test3() {
        ArrStack<String> stack = new ArrStack<>();

        int len = 1025;
        String msg = null;
        try {
            for (int i = 0; i < len; i++) {
                stack.push(i + "");
            }
        } catch (RuntimeException e) {
            msg = e.getMessage();
        }
        assert msg.equals("栈溢出：size == 1024");
    }

    @Test
    public void test4() {
        ArrStack<String> stack = new ArrStack<>();

        String msg = null;
        try {
            stack.pop();
        } catch (Exception e) {
            msg = e.getMessage();
        }
        assert msg.equals("栈中没有元素");
    }

    @Test
    public void test5() {
        ArrStack<String> stack = new ArrStack<>();

        String msg = null;
        try {
            stack.peek();
        } catch (Exception e) {
            msg = e.getMessage();
        }
        assert msg.equals("栈中没有元素");
    }


}
