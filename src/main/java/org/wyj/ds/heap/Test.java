package org.wyj.ds.heap;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>(100);

/*        heap.add(1);
        heap.add(2);
        heap.add(3);
        heap.add(4);
        heap.add(5);
        heap.add(6);
        heap.add(7);
        heap.add(8);
        heap.add(9);
        heap.add(10);*/
        heap.add(11);
        heap.add(12);
        heap.add(13);
        heap.add(9);
        heap.add(7);
        heap.add(8);

 //       Integer[] sort = heap.sort(new Integer[]{1, 2, 3, 4, 5, 6, 7});
      Integer[] sort = heap.sort(new Integer[]{36, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 15, 25, 6, 12, -24});
        System.out.println(Arrays.toString(sort));


/*        System.out.println(heap);

        heap.remove(12);

        System.out.println(heap);

        System.out.println("heap.contains(6) = " + heap.contains(6));
        System.out.println("heap.getIndex(6) = " + heap.getIndex(6));
        System.out.println("heap.get(6) = " + heap.get(6));*/

/*      // 测试删除方法
        heap.remove(2);
        System.out.println(heap);
        heap.remove(13);
        System.out.println(heap);*/



    }
}
