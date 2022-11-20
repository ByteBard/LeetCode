package LeetCode.Hard.Group01.Set03;

import LeetCode.ListNode;

import java.util.PriorityQueue;

public class MergeKSortedLists_23 {

    public void run() {
        ListNode ld1 = new ListNode(1);
        ListNode ld2 = new ListNode(4);
        ListNode ld3 = new ListNode(5);
        ld1.next = ld2;
        ld2.next = ld3;

        ListNode ld4 = new ListNode(1);
        ListNode ld5 = new ListNode(3);
        ListNode ld6 = new ListNode(4);
        ld4.next = ld5;
        ld5.next = ld6;

        ListNode ld7 = new ListNode(2);
        ListNode ld8 = new ListNode(6);
        ld7.next = ld8;

        ListNode[] ls = new ListNode[]{ld1, ld4, ld7};
        ListNode res = mergeKLists(ls);
        while (res != null){
            System.out.println(res.val);
            res = res.next;
        }
    }

   class Status implements Comparable<Status>{

        int _val;
        ListNode _node;

        Status(int val, ListNode node){
            _val = val;
            _node = node;
        }

       @Override
       public int compareTo(Status o) {
           return (this._val - o._val);
       }
   }

   public ListNode mergeKLists(ListNode[] lists){
        PriorityQueue<Status> queue = new PriorityQueue<>();
        for (ListNode node: lists){
            if(node != null){
                queue.offer(new Status(node.val, node));
            }
        }

        ListNode head = new ListNode(0);
        ListNode tail = head;

        while (!queue.isEmpty()){
            Status top = queue.poll();
            tail.next = top._node;
            tail = tail.next;
            if(top._node.next != null){
                queue.offer(new Status(top._node.next.val, top._node.next));
            }
        }
        return head.next;
   }

//    作者：LeetCode-Solution
//    链接：https://leetcode.cn/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
