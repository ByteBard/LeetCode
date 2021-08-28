public class ListNode {
    public int val;
    public ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        next = next;
    }

    public String toString() {
        String result = val + " ";
        if (next != null) {
            result += next.toString();
        }
        return result;
    }

}
