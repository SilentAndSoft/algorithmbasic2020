package class03;

/**
 * 数组实现不能超过固定大小size的队列
 **/
public class Code04_RingArray {
    public static class MyQueue {
        private int[] arr;
        private int pushIndex;// end
        private int pollIndex;// begin
        private int size;
        private final int limit;

        public MyQueue(int limit) {
            arr = new int[limit];
            pushIndex = 0;
            pollIndex = 0;
            //通过size变量将pushIndex、pollIndex两个下标解耦
            size = 0;
            this.limit = limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("队列满了，不能再加了");
            }
            size++;
            arr[pushIndex] = value;
            pushIndex = nextIndex(pushIndex);
        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("队列空了，不能再拿了");
            }
            size--;
            int ans = arr[pollIndex];
            pollIndex = nextIndex(pollIndex);
            return ans;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 如果现在的下标是i，返回下一个位置，如果下标到了limit-1，就回到0位置，环形
        private int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }
    }
}
