package class01;

/**
 * 无序数组，任意两个相邻的数不等，找到一个局部最小值
 **/
public class Code06_BSAwesome {

    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1; // no exist
        }
        //先看0和length-1位置是不是局部最小
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int left = 1;
        int right = arr.length - 2;
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            //mid大于mid-1去左侧二分
            if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            }
            //mid大于mid+1去右侧二分
            else if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

}
