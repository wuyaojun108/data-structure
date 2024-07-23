package org.wyj.algothrim;

/**
 * @auther 武耀君
 * @date 2024/7/22
 */
public class AlgorithmTest {
    /**
     * 求：字符串中的每一个元素之前的子串中的最大公共前后缀，结果是前缀的下一个位置，将结果写到数组中。
     * 这是kmp算法中需要用到的计算
     */
    public static int[] getNext(String s) {
        int[] next = new int[s.length()];
        next[0] = -1;
        next[1] = 0;

        int k = 0, j = 2;
        while (j < s.length()) {
            if (k == -1 || s.charAt(k) == s.charAt(j - 1)) {
                next[j] = k + 1;
                j++;
                k++;
            } else {
                k = next[k];
            }
        }
        return next;
    }

    /**
     * kmp算法中的next数组
     */
    public static int[] getNext2(String s) {
        int[] next = new int[s.length()];
        next[0] = -1;

        int j = 1, k = 0;
        while (j < s.length() - 1) {
            if (k == -1 || s.charAt(j) == s.charAt(k)) {
                k++;
                j++;
                next[j] = k;
            } else {
                k = next[k];
            }
        }
        return next;
    }

    /**
     * kmp算法中的next数组，逻辑优化
     */
    public static int[] getNext3(String s) {
        int[] next = new int[s.length()];
        next[0] = -1;

        int j = 0, k = -1;
        while (j < s.length() - 1) {
            if (k == -1 || s.charAt(j) == s.charAt(k)) {
                k++;
                j++;
                // 当两个字符相等时，要跳过，原理是，当j处指向的元素和k处指向的元素相同时，j处指向的元素已经不匹配了，
                // 没有必要再匹配k处的元素了，所以直接移动到k之前的最大公共前后缀处。
                if (s.charAt(j) == s.charAt(k)) {
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

    public static int[] getNext4(String s) {
        int[] next = new int[s.length()];
        next[0] = -1;
        next[1] = s.charAt(1) == s.charAt(0) ? -1 : 0;

        int j = 2, k = 0;
        while (j < s.length()) {
            if (k == -1 || s.charAt(j - 1) == s.charAt(k)) {
                k++;
                if (s.charAt(j) == s.charAt(k)) {
                    next[j] = next[k];
                } else {
                    next[j] = k;
                }
                j++;
            } else {
                k = next[k];
            }
        }
        return next;
    }


}
