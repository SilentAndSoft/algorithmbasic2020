package a_sort.sort;

/**
 * 桶排序-计数排序
 * 时间复杂度 O(N)
 * 空间复杂度 O(M)
 * 有稳定性
 **/
public class CountSort {
    /**
     * 一般要求样本是整数，且范围较小
     **/
    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int[] bucket = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }
        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                arr[i++] = j;
            }
        }
    }
}
