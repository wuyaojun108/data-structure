package org.wyj.algothrim;

/**
 * @auther 武耀君
 * @date 2024/7/18
 */
public class SortUtils {

    /**
     * 冒泡排序：两两交换，大数下沉
     * 第一趟循环，确定数组的最后一位元素，第二趟循环，确定数组的倒数第二位元素，
     * 以此类推，直到数组的第一位元素，因为当其它元素都有序时，数组的第一位元素
     * 就是有序的，所以共需要进行n-1次循环，n-1次循环是外层循环，在外层循环中，
     * 还需要进行n-1-i次内层循环，用于实现两两交换的比较
     */
    public static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }


    /**
     * 选择排序：第一个数和后面的所有数进行比较，找出最小的，和第一个数进行交换，第二个数再次进行这样的比较，
     * 直到倒数第一个数，因为当前面所有数有序之后，倒数第一个数一定是有序的
     */
    public static <T extends Comparable<T>> void selectSort(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min].compareTo(arr[j]) > 0) {
                    min = j;
                }
            }
            if (min != i) {
                T tmp = arr[min];
                arr[min] = arr[i];
                arr[i] = tmp;
            }
        }
    }

    /**
     * 插入排序：类似于整理手中的扑克牌。把数组的第一个元素看成是一个有序数组，第二个元素和第一个元素进行比较，
     * 如果比它小，则第一个元素后移一位，第二个元素放到一个元素的位置，否则，第二个元素放到原来的位置，此时，第
     * 一个元素和第二个元素组成了一个包含两个元素的有序数组，第三个、第四个元素依次类推，直到所有元素有序
     */
    public static <T extends Comparable<T>> void insertSort(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            T current = arr[i];
            int preIndex = i - 1;
            while (preIndex >= 0 && current.compareTo(arr[preIndex]) < 0) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
    }

    /**
     * 希尔排序：插入排序的优化版，又称缩小增量排序。插入排序会按部就班地将每个元素和它前面的所有元素进行比较，直到
     * 找到比自己小的，插入到它的后面，而希尔排序，是在插入排序的基础上增加了一个增量的概念，例如，在插入排序中，
     * 第5个元素中会和它前面的所有元素进行比较，但是在希尔排序中，加入增量是4，第5个元素会直接和第1个元素进行比较，
     * 然后第6个和第3个比较，第7个和第4个比较，这种比较方式减少了元素的交换次数，原先需要交换多次才能实现目的，现在
     * 交换一次即可。在希尔排序的实现上，增量通常是 数组的长度 / 2，在比较完一次之后，增量再次除以2，直到增量为0
     */
    public static void hillSort(int[] arr) {
        int gap = arr.length / 2;
        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                int current = arr[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && current < arr[preIndex]) {
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex = preIndex - gap;
                }
                arr[preIndex + gap] = current;
            }
            gap = gap / 2;
        }
    }

    /**
     * 归并排序：将序列从左往右，进行对半拆分，拆分出来的一半再次进行对半拆分，一直拆分到每个子序列都只有两个元素，
     * 将这两个子元素视为两个有序序列，使用一个辅助数组，把两个序列合并，合并时，序列中的第一个元素相互比较，小的
     * 元素先放进辅助数组中，然后再和另外一个数组中的下一个元素进行比较，直到其中一个数组为空，另一个数组的剩余元
     * 素也放进辅助数组中。再把合并后的序列进行两两合并，直到最终将所有的子序列全部合并
     */
    public static <T extends Comparable<T>> void mergeSort(T[] arr) {
        ms(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void ms(T[] arr,
                                                     int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        ms(arr, start, mid);
        ms(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }

    // start到mid是第一个数组的元素，mid+1到end是第二个数组的元素，对这两个数组进行归并操作
    private static <T extends Comparable<T>> void merge(T[] arr, int start,
                                                        int mid, int end) {
        Object[] tmpArr = new Object[end - start + 1];
        int k = 0;
        int i = start;
        int j = mid + 1;
        while (i <= mid && j <= end) {
            if (arr[i].compareTo(arr[j]) < 0) {
                tmpArr[k++] = arr[i++];
            } else {
                tmpArr[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            tmpArr[k++] = arr[i++];
        }
        while (j <= end) {
            tmpArr[k++] = arr[j++];
        }
        System.arraycopy(tmpArr, 0, arr, start, tmpArr.length);
    }


    /**
     * 快速排序：
     * 第一步：选择数组最左侧的元素作为基准数。初始化两个指针，一个指向数组的左侧基准数之后的元素，
     * 叫做i，一个指向数组的最右侧，叫做j，
     * 第二步：在i小于j的情况下，从后向前找，找到比基准数小的数，也就是说，如果基准数小于等于当前数，则继续向前
     * 第三步：在i小于j的情况下，从前向后找，找到比基准数大的数，也就是说，如果基准数大于等于当前数，则继续向后
     * 第三步：在两次寻找结束后，如果i<j，则交换i和j位置上的变量
     * 第四步：重复第二步的动作，直到i=j，此时i指向的元素一定比基准数小，或者就是基准数本身。
     * 因为先从后向前找比基准数小的数字，然后再从前向后找比基准数大的数字
     * 如果i=j，此时j一定停留在一个比基准数小的数字上，或者停留在基准数上，所以i指向的数字一定小于等于基准数。
     * 然后i指向的元素和基准数交换位置
     * 第五步：此时i指针指向的元素就是基准数，如果i指针左边的元素的个数大于1，递归地执行第1到3步，如果i指针右边
     * 的元素个数大于1，也递归地执行第1到3步。
     * <p>
     * 基准数
     * 一趟排序将数组分为两个部分
     */
    public static <T extends Comparable<T>> void quickSort(T[] arr) {
        qs(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void qs(T[] arr, int left, int right) {
        T base = arr[left];
        int i = left, j = right;
        while (i < j) {
            while (arr[j].compareTo(base) >= 0 && i < j) {
                j--;
            }
            while (arr[i].compareTo(base) <= 0 && i < j) {
                i++;
            }
            if (i < j) {
                T tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        arr[left] = arr[i];
        arr[i] = base;
        if (left < i - 1)
            qs(arr, left, i - 1);
        if (i + 1 < right)
            qs(arr, i + 1, right);
    }

    /**
     * 堆排序。堆是基于数组的完全二叉树，在堆中，父节点的值总是大于子节点的值，但是堆两个子节点之间的
     * 大小不做要求。使用堆来进行排序，首先是把数组中的元素组织成一个堆，具体方法是从数组的中间开始，
     * 每个元素和它的子元素进行比较，比子元素大，下沉，然后子元素再和它的子元素比较，直到数组开头。
     * 把数组组织成一个堆后，此时，堆中的第一个元素就是数组中最大的元素，然后把它和最后一位进行交换，
     * 数组的最后一位就是有序的，然后再把第一位的元素下沉，找到当前堆中除了最后一位以外最大的元素，然后
     * 在把它和倒数第二位交换，依次类推。
     */
    public static <T extends Comparable<T>> void heapSort(T[] arr) {
        // 堆基于数组，并且数组的第一个位置不存储元素，为了方便计算
        T[] tmpArr = (T[]) new Comparable[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            tmpArr[i + 1] = arr[i];
        }

        // 创建堆
        int mid = tmpArr.length / 2;
        while (mid > 0) {
            sink(tmpArr, mid, tmpArr.length);  // 元素下沉
            mid--;
        }

        // 排序，堆中的第一个元素就是最大值
        int tmpSize = tmpArr.length;
        while (tmpSize > 1) {
            T tmp = tmpArr[1];
            tmpArr[1] = tmpArr[tmpSize - 1];
            tmpArr[tmpSize - 1] = tmp;

            tmpSize--;
            sink(tmpArr, 1, tmpSize);
        }
        for (int i = 1; i < tmpArr.length; i++) {
            arr[i - 1] = tmpArr[i];
        }
    }

    private static <T extends Comparable<T>> void sink(T[] arr, int k, int size) {
        while (k * 2 < size) {
            int max = k * 2;
            if (k * 2 + 1 < size) {
                if (arr[k * 2].compareTo(arr[k * 2 + 1]) < 0) {
                    max = k * 2 + 1;
                }
            }
            if (arr[k].compareTo(arr[max]) < 0) {
                T tmp = arr[k];
                arr[k] = arr[max];
                arr[max] = tmp;
            }
            k = max;
        }
    }

    /**
     * 检测数组是否是升序
     */
    public static <T extends Comparable<T>> boolean checkSortProp(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

}
