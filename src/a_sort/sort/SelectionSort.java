package a_sort.sort;

/**
 * 选择排序：
 * 时间复杂度 O(n^2)
 * 空间复杂度 O(1)
 * 无稳定性
 **/
public class SelectionSort {
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int n = arr.length;
        // 0 ~ n-1  找到最小值，在哪，放到0位置上
        // 1 ~ n-1  找到最小值，在哪，放到1位置上
        // 2 ~ n-1  找到最小值，在哪，放到2位置上
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                // i ~ N-1 上找最小值的下标，放在i位置
                minIndex = arr[minIndex] > arr[j] ? j : minIndex;
            }
            swap(arr, minIndex, i);
        }
    }

    // 交换arr的i和j位置上的值
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
