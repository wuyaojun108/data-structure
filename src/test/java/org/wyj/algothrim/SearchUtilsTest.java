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
}
