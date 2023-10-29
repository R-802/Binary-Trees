import ecs100.UI;

import java.awt.*;

public class TreeVisualizer {

    private final BinaryTree tree;
    private final int[] values = {10, 5, 15, 3, 7, 13, 12, 14, 18, 1, 4};

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
        UI.addButton("Binary Search", this::binarySearch);
        UI.addButton("Quit", UI::quit);
        init();
    }

    private void init() {
        for (int value : values) {
            tree.add(value, false);
        }
        drawTree();
    }

    private void sumTree() {
        int sum = sumTree(tree.root);
        UI.println(sum);
    }

    private int sumTree(TreeNode node) {
        if (node == null) return 0;
        return node.value + sumTree(node.left) + sumTree(node.right);
    }

    public void binarySearch() {
        UI.clearText();
        int target = UI.askInt("Target: ");
        TreeNode node = binarySearch(tree.root, target);
        if (node == null) {
            UI.println("Target " + target + " not found!");
        } else {
            node.setHighlighted(true);
            UI.println(node.value);
        }
        drawTree();
    }

    private TreeNode binarySearch(TreeNode node, int target) {
        if (node == null) return null;
        if (node.value == target) return node;
        else if (target < node.value) {
            return binarySearch(node.left, target);
        } else {
            return binarySearch(node.right, target);
        }
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

        // Draw nodes
        if (node.highlighted) {
            UI.setColor(Color.green);
            UI.eraseOval(x - 15, y - 15, 30, 30);
            UI.fillOval(x - 15, y - 15, 30, 30);
            UI.setColor(Color.black);
            UI.drawOval(x - 15, y - 15, 30, 30);
            UI.drawString(Integer.toString(node.value), x - 5, y + 5);
        } else {
            UI.eraseOval(x - 15, y - 15, 30, 30);
            UI.drawOval(x - 15, y - 15, 30, 30);
            UI.drawString(Integer.toString(node.value), x - 5, y + 5);
        }

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
