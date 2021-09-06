package class02;

public class Code02_EvenTimesOddTimes {

    // arr中，只有一种数，出现奇数次，找到这个数
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            //0 ^ N = N，N ^ N = 0
            //异或运算满足交换率和结合律
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    // arr中，有两个不同的数，出现奇数次，其他数出现了偶数次，找到这两种数
    public static void printOddTimesNum2(int[] arr) {
        // a 和 b是个不同的数
        // eor != 0，说明eor在某位一定有1
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            //eor = a ^ b
            eor ^= arr[i];
        }
        // eor最右侧的1，提取出来，假设最右侧的1是在第X位
        // eor :     00110010110111000
        // rightOne :00000000000001000
        // int rightOne = eor & (~eor + 1);
        int rightOne = eor & (-eor);
        // eor'
        int onlyOne = 0;
        for (int i = 0; i < arr.length; i++) {
            //  arr[1] =  111100011110000
            // rightOne=  000000000010000
            if ((arr[i] & rightOne) != 0) {
                //把X是1的所有数异或，得到的结果肯定是a或b
                onlyOne ^= arr[i];
            }
        }
        // a^eor=b 或 b^eor=a
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }


    public static int bit1counts(int N) {
        int count = 0;

        //   011011010000
        //   000000010000     1

        //   011011000000
        //


        while (N != 0) {
            int rightOne = N & ((~N) + 1);
            count++;
            //抹掉最右边的1
            N ^= rightOne;
            // N如果是负数会有问题
            // N -= rightOne
        }


        return count;

    }


    public static void main(String[] args) {
        int a = 5;
        int b = 7;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println(a);
        System.out.println(b);

        int[] arr1 = {3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1};
        printOddTimesNum1(arr1);

        int[] arr2 = {4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2};
        printOddTimesNum2(arr2);

    }

}
