public class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;
    boolean highlighted;

    public TreeNode(int value) {
        this.value = value;
    }

    public TreeNode(int value, boolean highlighted) {
        this.value = value;
        this.highlighted = highlighted;
    }

    public void setHighlighted(boolean highlighted){
        this.highlighted = highlighted;
    }
}
