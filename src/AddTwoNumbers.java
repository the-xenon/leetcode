
public class AddTwoNumbers {

    public static void main(String[] args) {
        new AddTwoNumbers().run();
    }

    public void run() {
        int i1 = 9999999;
        int i2 = 9999;

        Stats stats = Stats.start();
        for (int i = 0; i < 300000000; i++) {
            addTwoNumbers(
                intToNode(i),
                intToNode(i + 16439)
            );
        }
        stats.show();

        System.out.println(nodeToInt(addTwoNumbers(
                intToNode(i1),
                intToNode(i2)
        )));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode node = result;
        int t1, t2, transfer = 0;
        do {
            if (l1 == null) {
                t1 = 0;
            } else {
                t1 = l1.val;
                l1 = l1.next;
            }
            if (l2 == null) {
                t2 = 0;
            } else {
                t2 = l2.val;
                l2 = l2.next;
            }
            node.val = t1 + t2 + transfer;
            if (node.val < 10) {
                transfer = 0;
            } else {
                node.val -= 10;
                transfer = 1;
            }
        } while ((l1 != null || l2 != null) && null != (node = (node.next = new ListNode())));
        if (transfer != 0) {
            (node.next = new ListNode()).val = transfer;
        }
        return result;
    }

    private static final ListNode empty = new ListNode();

    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode node = result;
        int transfer = 0;
        do {
            node.val = l1.val + l2.val + transfer;
            if (node.val < 10) {
                transfer = 0;
            } else {
                node.val -= 10;
                transfer = 1;
            }
            l1 = l1.next == null ? empty : l1.next;
            l2 = l2.next == null ? empty : l2.next;
        }  while ((l1 != empty || l2 != empty) && null != (node = (node.next = new ListNode())));
        if (transfer != 0) {
            (node.next = new ListNode()).val = transfer;
        }
        return result;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode node = result;
        int t1, t2, sum, transfer = 0;
        while (l1 != null || l2 != null) {
            node = (node.next = new ListNode());
            t1 = l1 == null ? 0 : l1.val;
            t2 = l2 == null ? 0 : l2.val;
            sum = t1 + t2 + transfer;
            if (sum < 10) {
                transfer = 0;
            } else {
                sum -= 10;
                transfer = 1;
            }
            node.val = sum;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (transfer != 0) {
            (node.next = new ListNode()).val = transfer;
        }

        return result.next;
    }

    private ListNode intToNode(int value) {
        ListNode res = new ListNode();
        ListNode node = res;
        do {
            node = (node.next = new ListNode());
            node.val = value % 10;
            value = value / 10;
        } while (value > 0);
        return res.next;
    }

    private int nodeToInt(ListNode node) {
        int result = 0;
        int pos = 1;
        do {
            result += node.val * pos;
            pos *= 10;
        } while((node = node.next) != null);
        return result;
    }


    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
