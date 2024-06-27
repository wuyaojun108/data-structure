package org.wyj.ds.stack;

public class MyStackTest {
    public static void main(String[] args) {
        MyStack<String> stack = new MyStack<>(5);
        stack.push("aaa");
        stack.push("bbb");
        stack.push("ccc");
        System.out.println("stack.top() = " + stack.top());
        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.pop() = " + stack.pop());
    }
}
