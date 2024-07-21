package org.wyj.algothrim;

/**
 * @auther 武耀君
 * @date 2024/7/21
 */
public class SearchUtils {
    /**
     * 二分查找法
     */
    public static <T extends Comparable<T>> int binarySearch(T[] arr, T key) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = (start + end) >> 1;
            int cmp = key.compareTo(arr[mid]);
            if (cmp < 0) {
                end = mid - 1;
            } else if (cmp > 0) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
