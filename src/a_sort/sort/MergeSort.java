package a_sort.sort;

/**
 * 归并排序：
 * 时间复杂度 O(N*logN)
 * 空间复杂度 O(N)
 * 有稳定性
 **/
public class MergeSort {
    /**
     * 递归方法实现
     */
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    // L~R范围上排序，左半部分有序，右半部分有序，然后两个指针谁小拷贝谁
    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            //说明只有一个元素
            return;
        }
        int mid = L + ((R - L) >> 1);
        // 先搞左边
        process(arr, L, mid);
        // 再搞右边
        process(arr, mid + 1, R);
        // 最后汇总一下
        merge(arr, L, mid, R);
    }

    //归并过程
    public static void merge(int[] arr, int L, int M, int R) {
        //准备辅助数组，大小为L~R范围上的元素个数
        int[] help = new int[R - L + 1];
        //help的下标
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        //p1和p2都没越界，谁小拷贝进help
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 要么p1越界了，要么p2越界了
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        //help 刷回arr
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    /**
     * 非递归方法实现
     **/
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        // 步长，每次保证2*mergeSize个元素有序
        int mergeSize = 1;
        while (mergeSize < N) { // log N
            // 当前左组的，第一个位置
            int L = 0;
            while (L < N) {
                //左组凑不齐
                if (mergeSize >= N - L) {
                    break;
                }
                //L~M左组大小
                int M = L + mergeSize - 1;
                //右组可能凑不齐
                int R = M + Math.min(mergeSize, N - M - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            // 防止溢出（逼近int最大值时）
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }
}
