package a_sort.sort;

/**
 * 随机快排
 * 时间复杂度 O(N*logN)
 * 空间复杂度 O(logN)
 * 无稳定性
 **/
public class QuickSort {
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        //L~R随机选一个数交换到R位置，划分值越靠中间性能越好，越靠两边越差
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process(arr, L, equalArea[0] - 1);
        process(arr, equalArea[1] + 1, R);
    }

    // arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值，返回等于区域的左右边界
    // <arr[R] | ==arr[R] | > arr[R]
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) { // L...R L>R
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1; // 小于区 右边界
        int more = R + 1; // 大于区 左边界
        int cur = L;
        int num = arr[R];
        // 当前位置，不能和 >区的左边界撞上
        while (cur < more) {
            //当前数等于划分值
            if (arr[cur] == num) {
                //当前数直接跳下一个
                cur++;
            }
            //当前数<划分值
            else if (arr[cur] < num) {
                //当前数和小于区的下一个位置交换，小于区右扩1位，当前下标跳下一个
                swap(arr, cur++, ++less);
            }
            //当前数>划分值
            else {
                // 当前数和大于区的前一个数交换，大于区左扩1位，当前下标不变
                swap(arr, cur, --more);
            }
        }
        return new int[]{less + 1, more - 1};
    }


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
