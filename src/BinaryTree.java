public class BinaryTree {
    TreeNode root;

    public void add(int value) {
        root = addRecursive(root, value, false);
    }

    public void add(int value, boolean highlighted) {
        root = addRecursive(root, value, highlighted);
    }

    private TreeNode addRecursive(TreeNode node, int value, boolean highlighted) {
        if (node == null) return new TreeNode(value);

        if (value < node.value) {
            node.left = addRecursive(node.left, value, highlighted);
        } else if (value > node.value) {
            node.right = addRecursive(node.right, value, highlighted);
        }

        return node;
    }

    public void clear() {
        root = null;
    }
}
