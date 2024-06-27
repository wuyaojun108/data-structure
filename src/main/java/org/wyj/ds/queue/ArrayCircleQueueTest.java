package org.wyj.ds.queue;

import java.util.Scanner;

public class ArrayCircleQueueTest {

    static final Scanner sc = new Scanner(System.in);
    static ArrayCircleQueue<Object> queue = null;

    // 测试循环队列
    public static void main(String[] args) {
        while (true){
            start();
        }
    }

    public static void start(){
        menu();
        final int instruction = sc.nextInt();
        switch (instruction){
            case 1:
                initialQueue();
                break;
            case 2:
                addElement();
                break;
            case 3:
                getElement();
                break;
            case 4:
                traverseQueue();
                break;
            case 5:
                checkQueue();
                break;
            case 6:
                exit();
                break;
            default:
                System.out.println("请重新输入正确的选项");
                break;
        }
    }

    public static void traverseQueue(){
        queue.traverseQueue();
    }

    public static void getElement(){
        final Object element = queue.exQueue();
        System.out.println("从队列中取出的元素是：" + element);
    }

    public static void addElement(){
        System.out.println("请输入要添加的元素");
        final String str = sc.next();
        queue.enQueue(str);
    }

    public static void checkQueue(){
        System.out.println("查看队列中的参数值：");
        System.out.println("queue.getCapacity() = " + queue.getCapacity());
        System.out.println("queue.getRear() = " + queue.getRear());
        System.out.println("queue.getFront() = " + queue.getFront());
        System.out.println("queue.getCount() = " + queue.getCount());
    }

    public static void initialQueue(){
        System.out.println("初始化数组：");
        System.out.println("请输入队列的大小:");
        final int capacity = sc.nextInt();
        queue = new ArrayCircleQueue<>(capacity);
    }

    public static void exit(){
        System.exit(0);
    }

    // 打印菜单
    public static void menu(){
        System.out.println("=============菜单=============");
        System.out.println("1. 初始化队列");
        System.out.println("2. 向队列中添加元素");
        System.out.println("3. 从队列中取出元素");
        System.out.println("4. 遍历队列");
        System.out.println("5. 查看队列中的参数值");
        System.out.println("6. 退出");
        System.out.println("请输入选项：");
    }
}
