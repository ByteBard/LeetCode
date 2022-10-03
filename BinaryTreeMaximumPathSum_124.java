package LeetCode;

public class BinaryTreeMaximumPathSum_124 {
    /* Definition for a binary tree node. */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int maxNum = Integer.MIN_VALUE;

    public int get_max(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int maxLeft = Math.max(get_max(node.left), 0);
        int maxRight = Math.max(get_max(node.right), 0);
        int maxPathVal = maxLeft + maxRight + node.val;
        maxNum = Math.max(maxNum, maxPathVal);
        return node.val + Math.max(maxLeft, maxRight);
    }

    public int maxPathSum(TreeNode root) {
        get_max(root);
        return maxNum;
    }
}
