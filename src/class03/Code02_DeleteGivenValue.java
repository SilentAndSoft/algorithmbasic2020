package class03;

/**
 * 删除链表上等于num的节点
 **/
public class Code02_DeleteGivenValue {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    // head = removeValue(head, 2);
    public static Node removeValue(Node head, int num) {
        // head来到第一个不需要删的位置
        // 遍历链表，找到第一个不等于num的节点，作为头部返回
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        // 1 ) head == null
        // 2 ) head != null
        //pre记录上一个不等于num的位置
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                //跳过给定的num，pre指向当前的next
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

}
