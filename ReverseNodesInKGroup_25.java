package LeetCode;

import java.util.List;

/*
25. K 个一组翻转链表
给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。

k 是一个正整数，它的值小于或等于链表的长度。

如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

进阶：

你可以设计一个只使用常数额外空间的算法来解决此问题吗？
你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。


示例 1：


输入：head = [1,2,3,4,5], k = 2
输出：[2,1,4,3,5]
示例 2：


输入：head = [1,2,3,4,5], k = 3
输出：[3,2,1,4,5]
示例 3：

输入：head = [1,2,3,4,5], k = 1
输出：[1,2,3,4,5]
示例 4：

输入：head = [1], k = 1
输出：[1]
提示：

列表中节点的数量在范围 sz 内
1 <= sz <= 5000
0 <= Node.val <= 1000
1 <= k <= sz
 */
public class ReverseNodesInKGroup_25 {

    public void run() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);

        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;

        node3.next = node4;
        node4.next = node5;

        ListNode result = reverseKGroup01(node1, 2);
        System.out.println(result.toString());

    }

    //https://www.youtube.com/watch?v=FYsYAELWyRs
    //https://www.youtube.com/watch?v=pLx1VP-FnuY
    public ListNode reverseKGroup01(ListNode head, int k) {
        int count = 0;
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode temp = dummy;

        while (temp.next != null) {
            temp = temp.next;
            count++;
        }

        temp = dummy;

        while (temp.next != null) {
            if (count < k) break;
            int nodes = k - 1;
            ListNode tempNext = temp.next;
            ListNode first = temp.next;
            ListNode second = first.next;

            while (nodes-- > 0) {
                ListNode next = second.next;
                second.next = first;
                first = second;
                second = next;
            }

            count -= k;
            temp.next = first;
            tempNext.next = second;
            temp = tempNext;

        }
        return dummy.next;
    }
}
