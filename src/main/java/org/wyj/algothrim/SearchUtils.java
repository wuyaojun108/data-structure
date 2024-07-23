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

    /**
     * 串的暴力匹配：又称朴素模式匹配，在字符串中查找子字符串的位置
     */
    public static int naivePatternMatching(String s, String pattern) {
        int n = s.length();
        int m = pattern.length();

        for (int i = 0; i < n - m + 1; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (pattern.charAt(j) != s.charAt(i + j)) {
                    break;
                }
            }
            if (j == m) {
                return i;
            }
        }
        return -1;
    }

    /**
     * KMP算法
     */
    public static int kmpSearch(String s, String pattern, int pos) {
        int sLen = s.length();
        int pLen = pattern.length();

        int[] next = getNext(pattern);

        int i = pos, j = 0;
        while (i < sLen && j < pLen) {
            if (j == -1 || s.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == pLen) {
            return i - j;
        }
        return -1;
    }

    public static int kmpSearch(String s, String pattern) {
        return kmpSearch(s, pattern, 0);
    }

    private static int[] getNext(String p) {
        int[] next = new int[p.length()];
        next[0] = -1;

        int j = 0, k = -1;
        while (j < p.length() - 1) {
            if (k == -1 || p.charAt(j) == p.charAt(k)) {
                j++;
                k++;
                if (p.charAt(j) == p.charAt(k)) {
                    next[j] = next[k];
                } else {
                    next[j] = k;
                }
            } else {
                k = next[k];
            }
        }

        return next;
    }

}
