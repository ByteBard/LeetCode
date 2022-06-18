package LeetCode;

public class BinaryTreeCameras_968 {

    public void run() {

        BinaryTree tree = new BinaryTree();
        int parent[] = new int[]{-1, 0, 0, 1, 1, 3, 5};
        int n = parent.length;

        TreeNode root = tree.createTree(parent, n);
        System.out.println(minCameraCover(root));


    }

        public  class BinaryTree{
        TreeNode root;

        // Creates a node with key as 'i'.  If i is root, then it changes
        // root.  If parent of i is not created, then it creates parent first
        void createNode(int parent[], int i, TreeNode created[])
        {
            // If this node is already created
            if (created[i] != null)
                return;

            // Create a new node and set created[i]
            created[i] = new TreeNode(i);

            // If 'i' is root, change root pointer and return
            if (parent[i] == -1)
            {
                root = created[i];
                return;
            }

            // If parent is not created, then create parent first
            if (created[parent[i]] == null)
                createNode(parent, parent[i], created);

            // Find parent pointer
            TreeNode p = created[parent[i]];

            // If this is first child of parent
            if (p.left == null)
                p.left = created[i];
            else // If second child

                p.right = created[i];
        }

        /* Creates tree from parent[0..n-1] and returns root of
           the created tree */
        TreeNode createTree(int parent[], int n)
        {
            // Create an array created[] to keep track
            // of created nodes, initialize all entries
            // as NULL
            TreeNode[] created = new TreeNode[n];
            for (int i = 0; i < n; i++)
                created[i] = null;

            for (int i = 0; i < n; i++)
                createNode(parent, i, created);

            return root;
        }
    }

    //Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    int camera = 0;

    public int minCameraCover(TreeNode root) {
        return dfs(root) == 0 ? ++camera : camera;
    }

    public int dfs(TreeNode root) {
        if (root == null) return 1;
        int left = dfs(root.left);
        int right = dfs(root.right);
        if (left == 0 || right == 0) {
            camera++;
            return 2;
        } else if (left == 2 || right == 2) {
            return 1;
        } else {
            return 0;
        }

    }

}
