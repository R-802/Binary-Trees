import ecs100.UI;

public class TreeVisualizer {

    private final BinaryTree tree;

    public TreeVisualizer() {
        tree = new BinaryTree();
        setupGUI();
    }

    public static void main(String[] args) {
        new TreeVisualizer();
    }

    private void setupGUI() {
        UI.initialise();
        UI.getFrame().setTitle("Binary Tree Visualizer");
        UI.setWindowSize(1080, 720);
        UI.addButton("Add Node", this::addNode);
        UI.addButton("Draw Tree", this::drawTree);
        UI.addButton("Sum Tree", this::sumTree);
        UI.addButton("Quit", UI::quit);
        init();
    }

    private void init() {
        int[] values = {10, 5, 15, 3, 7, 12, 18, 1};
        for (int value : values) {
            tree.add(value);
        }
        drawTree();
    }

    private void sumTree(){
        int sum = sumTree(tree.root);
        UI.println(sum);
    }

    private int sumTree(TreeNode node) {
        if (node == null) return 0;
        return node.value + sumTree(node.left) + sumTree(node.right);
    }
    private void addNode() {
        int val = UI.askInt("Enter a number: ");
        tree.add(val);
        drawTree();
    }

    private void drawTree() {
        UI.clearGraphics();
        drawNode(tree.root, (double) UI.getCanvasWidth() / 2, 50, (double) UI.getCanvasWidth() / 4);
    }

    private void drawNode(TreeNode node, double x, double y, double offset) {
        if (node == null) { // Base case
            return;
        }

        // Draw node
        UI.eraseOval(x - 15, y - 15, 30, 30);
        UI.drawOval(x - 15, y - 15, 30, 30);
        UI.drawString(Integer.toString(node.value), x - 5, y + 5);

        if (node.left != null) { // Draw left
            UI.drawLine(x, y, x - offset, y + 50);
            drawNode(node.left, x - offset, y + 50, offset / 2);
        }
        if (node.right != null) { // Draw right
            UI.drawLine(x, y, x + offset, y + 50);
            drawNode(node.right, x + offset, y + 50, offset / 2);
        }
    }
}
