package LeetCode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SerializeAndDeserializeBinaryTree_297 {

    /* Definition for a binary tree node. */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return rserialize(root, "");
    }

    public String rserialize(TreeNode root, String val) {
        if (root == null) {
            val += "null,";
        } else {
            val += root.val + ",";
            val += rserialize(root.left, "");
            val += rserialize(root.right, "");
        }
        return val;
    }

    public TreeNode rdeserialize(List<String> ls) {
        if (ls.get(0).equals("null")) {
            ls.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(ls.get(0)));
        ls.remove(0);
        root.left = rdeserialize(ls);
        root.right = rdeserialize(ls);
        return root;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        List<String> ls = new LinkedList<>(Arrays.asList(arr));
        return rdeserialize(ls);
    }
}
