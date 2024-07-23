package org.wyj.algothrim;

import org.junit.Test;

import java.util.HashSet;
import java.util.Objects;

/**
 * @auther 武耀君
 * @date 2024/7/21
 */
public class SearchUtilsTest {
    // 测试二分查找法
    @Test
    public void test1() {
        // 创建一个有序数组
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < 100000; i++) {
            set.add((int) (Math.random() * 1000000));
            if (set.size() >= 10000) {
                break;
            }
        }

        Integer[] arr = set.toArray(new Integer[0]);
        Integer key = arr[1000];
        SortUtils.mergeSort(arr);
        assert SortUtils.checkSortProp(arr);

        int idx = SearchUtils.binarySearch(arr, key);
        assert Objects.equals(arr[idx], key);

        key = -100;
        idx = SearchUtils.binarySearch(arr, key);
        assert idx == -1;
    }

    // 测试串的朴素模式匹配
    @Test
    public void test2() {
        String s = "ABCAABD";

        String pattern = "BD";
        int i = SearchUtils.naivePatternMatching(s, pattern);
        assert i == 5;

        pattern = "CABBB";
        i = SearchUtils.naivePatternMatching(s, pattern);
        assert i == -1;
    }

    // 测试getNext方法
    @Test
    public void testGetNext() {
        String s = "ababc";
        int[] next = AlgorithmTest.getNext(s);

        assert next[0] == -1;
        assert next[1] == 0;
        assert next[2] == 0;
        assert next[3] == 1;
        assert next[4] == 2;

        s = "ABACDABABC";
        next = AlgorithmTest.getNext(s);

        assert next[0] == -1;
        assert next[1] == 0;
        assert next[2] == 0;
        assert next[3] == 1;
        assert next[4] == 0;
        assert next[5] == 0;
        assert next[6] == 1;
        assert next[7] == 2;
        assert next[8] == 3;
        assert next[9] == 2;

        s = "aa";
        next = AlgorithmTest.getNext(s);
        assert next[0] == -1;
        assert next[1] == 0;

        s = "aaa";
        next = AlgorithmTest.getNext(s);
        assert next[0] == -1;
        assert next[1] == 0;
        assert next[2] == 1;
    }

    @Test
    public void testGetNext2() {
        String s = "ababc";
        int[] next = AlgorithmTest.getNext2(s);

        assert next[0] == -1;
        assert next[1] == 0;
        assert next[2] == 0;
        assert next[3] == 1;
        assert next[4] == 2;

        s = "ABACDABABC";
        next = AlgorithmTest.getNext2(s);

        assert next[0] == -1;
        assert next[1] == 0;
        assert next[2] == 0;
        assert next[3] == 1;
        assert next[4] == 0;
        assert next[5] == 0;
        assert next[6] == 1;
        assert next[7] == 2;
        assert next[8] == 3;
        assert next[9] == 2;

        s = "aa";
        next = AlgorithmTest.getNext2(s);
        assert next[0] == -1;
        assert next[1] == 0;

        s = "aaa";
        next = AlgorithmTest.getNext2(s);
        assert next[0] == -1;
        assert next[1] == 0;
        assert next[2] == 1;
    }

    @Test
    public void testGetNext3() {
        String s = "ababc";
        int[] next = AlgorithmTest.getNext3(s);

        assert next[0] == -1;
        assert next[1] == 0;
        assert next[2] == -1;
        assert next[3] == 0;
        assert next[4] == 2;

        s = "ABABDABABC";
        next = AlgorithmTest.getNext3(s);

        assert next[0] == -1;
        assert next[1] == 0;
        assert next[2] == -1;
        assert next[3] == 0;
        assert next[4] == 2;
        assert next[5] == -1;
        assert next[6] == 0;
        assert next[7] == -1;
        assert next[8] == 0;
        assert next[9] == 4;

        s = "aa";
        next = AlgorithmTest.getNext3(s);
        assert next[0] == -1;
        assert next[1] == -1;

        s = "aaa";
        next = AlgorithmTest.getNext3(s);
        assert next[0] == -1;
        assert next[1] == -1;
        assert next[2] == -1;
    }

    @Test
    public void testGetNext4() {
        String s = "ababc";
        int[] next = AlgorithmTest.getNext4(s);

        assert next[0] == -1;
        assert next[1] == 0;
        assert next[2] == -1;
        assert next[3] == 0;
        assert next[4] == 2;

        s = "ABABDABABC";
        next = AlgorithmTest.getNext4(s);

        assert next[0] == -1;
        assert next[1] == 0;
        assert next[2] == -1;
        assert next[3] == 0;
        assert next[4] == 2;
        assert next[5] == -1;
        assert next[6] == 0;
        assert next[7] == -1;
        assert next[8] == 0;
        assert next[9] == 4;

        s = "aa";
        next = AlgorithmTest.getNext4(s);
        assert next[0] == -1;
        assert next[1] == -1;

        s = "aaa";
        next = AlgorithmTest.getNext4(s);
        assert next[0] == -1;
        assert next[1] == -1;
        assert next[2] == -1;
    }

    // 测试kmp算法
    @Test
    public void testKmp() {
        String s = "ababc";
        String p = "bc";

        int idx = SearchUtils.kmpSearch(s, p);
        assert idx == 3;

        p = "dd";
        idx = SearchUtils.kmpSearch(s, p);
        assert idx == -1;
    }

    // 测试kmp算法
    @Test
    public void testKmp2() {
        String s = "ababcfafdafafkajhfddakfahkjfhdaeiwquredddaaa";

        String p = "faf";
        int idx = SearchUtils.kmpSearch(s, p);
        assert idx == 5;

        p = "daa";
        idx = SearchUtils.kmpSearch(s, p);
        assert idx == 40;

        p = "www";
        idx = SearchUtils.kmpSearch(s, p);
        assert idx == -1;

        p = "dda";
        idx = SearchUtils.kmpSearch(s, p, 30);
        assert idx == 39;
    }
}
