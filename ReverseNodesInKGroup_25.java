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
0 <= TreeNode.val <= 1000
1 <= k <= sz
 */
public class ReverseNodesInKGroup_25 {

    public void run() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);

        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);

        node1.next = node2;
        node2.next = node3;

        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        ListNode result = reverseKGroup(node1, 3);
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
                ListNode secondNext = second.next;
                second.next = first;
                first = second;
                second = secondNext;
            }

            count -= k;
            temp.next = first;
            tempNext.next = second;
            temp = tempNext;

        }
        return dummy.next;
    }

    //https://leetcode.com/problems/reverse-nodes-in-k-group/solutions/540563/reverse-nodes-in-k-group/
    //https://leetcode.com/problems/reverse-nodes-in-k-group/description/
    public ListNode reverseLinkedList(ListNode head, int k) {

        // Reverse k nodes of the given linked list.
        // This function assumes that the list contains
        // atleast k nodes.
        ListNode new_head = null;
        ListNode ptr = head;

        while (k > 0) {

            // Keep track of the next node to process in the
            // original list
            ListNode next_node = ptr.next;

            // Insert the node pointed to by "ptr"
            // at the beginning of the reversed list
            ptr.next = new_head;
            new_head = ptr;

            // Move on to the next node
            ptr = next_node;

            // Decrement the count of nodes to be reversed by 1
            k--;
        }


        // Return the head of the reversed list
        return new_head;

    }

    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode ptr = head;
        ListNode ktail = null;

        // Head of the final, moified linked list
        ListNode new_head = null;

        // Keep going until there are nodes in the list
        while (ptr != null) {

            int count = 0;

            // Start counting nodes from the head
            ptr = head;

            // Find the head of the next k nodes
            while (count < k && ptr != null) {
                ptr = ptr.next;
                count += 1;
            }

            // If we counted k nodes, reverse them
            if (count == k) {

                // Reverse k nodes and get the new head
                ListNode revHead = this.reverseLinkedList(head, k);

                // new_head is the head of the final linked list
                if (new_head == null)
                    new_head = revHead;

                // ktail is the tail of the previous block of
                // reversed k nodes
                if (ktail != null)
                    ktail.next = revHead;

                ktail = head;
                head = ptr;
            }
        }

        // attach the final, possibly un-reversed portion
        if (ktail != null)
            ktail.next = head;

        return new_head == null ? head : new_head;
    }
}
