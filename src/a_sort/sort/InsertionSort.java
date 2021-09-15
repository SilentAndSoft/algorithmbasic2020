package a_sort.sort;

/**
 * 插入排序：
 * 时间复杂度 O(N^2)
 * 空间复杂度 O(1)
 * 有稳定性
 **/
public class InsertionSort {
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0~0，默认有序
        // 0~1有序，从1往前看，小就交换
        // 0~2有序，从2往前看，小就交换
        // 0 ~ i 做到有序，从i往前看，小就交换
        for (int i = 1; i < arr.length; i++) {
            // 从i往前看，j+1位置小于j位置就交换
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    // i和j是一个位置的话，会出错
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
