package class06;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code02_Heap {
    public static class MyMaxHeap {
        private int[] heap;
        private final int limit;
        private int heapSize;

        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        //向大根堆中新增一个数，保证加完之后还是大根堆
        public void push(int value) {
            if (heapSize == limit) {
                throw new RuntimeException("heap is full");
            }
            heap[heapSize] = value;
            // value  heapSize
            heapInsert(heap, heapSize++);
        }

        // 用户此时，让你返回最大值，并且在大根堆中把最大值删掉，剩下的数仍然是大根堆
        public int pop() {
            int ans = heap[0];
            // 用最后一个元素tail 顶替第一个元素，heapSize减一，tail向下看和较大的孩子交换，继续找较大的孩子
            swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return ans;
        }

        //向大根堆中新增一个数，保证加完之后还是大根堆
        // 复杂度 log(N)
        private void heapInsert(int[] arr, int index) {
            // [index]    [index-1]/2
            // index == 0
            while (arr[index] > arr[(index - 1) / 2]) {
                //新来的数向上看，如果比父亲大，交换，直到不比父亲大，停！
                swap(arr, index, (index - 1) / 2);
                //下标来到父亲的位置
                index = (index - 1) / 2;
            }
        }

        // arr从index位置，往下看，不断的下沉，达到长度heapSize为止
        // 孩子都不再比index位置的数大 或者 已经没孩子了，停！
        // 复杂度 log(N)
        private void heapify(int[] arr, int index, int heapSize) {
            int left = index * 2 + 1;
            // 左孩子没越界，说明有左孩子，但未必有右孩子；没有左孩子肯定也没有右孩子
            while (left < heapSize) {
                // 把两个孩子中较大孩子的下标，给largest
                // 右孩子大的情况：有右孩子 && 右孩子大于左孩子
                // 左孩子大的情况：else
                int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
                // 拿到自己和孩子中最大值的下标
                largest = arr[largest] > arr[index] ? largest : index;
                // 孩子没有比自己大的，跳出
                if (largest == index) {
                    break;
                }
                // index和较大孩子，要互换
                swap(arr, largest, index);
                //index来到大孩子的位置
                index = largest;
                //找下次的左孩子
                left = index * 2 + 1;
            }
        }

        private void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

    }

    public static class RightMaxHeap {
        private int[] arr;
        private final int limit;
        private int size;

        public RightMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("heap is full");
            }
            arr[size++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = arr[maxIndex];
            arr[maxIndex] = arr[--size];
            return ans;
        }

    }


    public static class MyComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }

    }

    public static void main(String[] args) {
        // 小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComparator());
        heap.add(5);
        heap.add(5);
        heap.add(5);
        heap.add(3);
        //  5 , 3
        System.out.println(heap.peek());
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        System.out.println(heap.peek());
        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }


        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyMaxHeap my = new MyMaxHeap(curLimit);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops!");
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.push(curValue);
                } else if (my.isFull()) {
                    if (my.pop() != test.pop()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else {
                        if (my.pop() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");

    }

}
