package a_sort.sort;

/**
 * 冒泡排序：
 * 时间复杂度 O(N^2)
 * 空间复杂度 O(1)
 * 有稳定性
 **/
public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0 ~ length-1之间找到最大值
        // 0 ~ length-2之间找到最大值
        // 0 ~ length-3之间找到最大值
        for (int i = arr.length - 1; i > 0; i--) {
            // 0 1位置谁大谁往后
            // 1 2位置谁大谁往后
            // j-1 j位置谁大谁往后
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    // 交换arr的i和j位置上的值
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
