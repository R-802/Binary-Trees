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
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.getWidth() * 0.5);
        int height = (int) (screenSize.getHeight() * 0.5);
        UI.setWindowSize(width, height);
        int x = (int) ((screenSize.getWidth() - width) / 2);
        int y = (int) ((screenSize.getHeight() - height) / 2);
        UI.getFrame().setLocation(x, y);
        UI.addButton("Add Node", this::addNode);
        UI.addButton("Sum Tree", this::sumTree);
        UI.addButton("Binary Search", this::binarySearch);
        UI.addButton("Print Pre-Order", this::printPreOrder);
        UI.addButton("Print Post-Order", this::printPostOrder);
        UI.addButton("Print In-Order", this::printInOrder);
        UI.addButton("Quit", UI::quit);
        UI.getFrame().setTitle("Binary Tree Visualizer");
        UI.setDivider(0.225);
        init();
        print(" Binary Tree Visualizer v1.0 ");
        UI.sleep(500);
        print("  Select an option to start ");
    }

    /**
     * Preorder starts at the root and traverses
     * to the leaf nodes
     */
    private void printPreOrder() {
        UI.clearText();
        printPreOrder(tree.root, "");
    }

    private void printPreOrder(TreeNode node, String prefix) {
        if (node == null) return;
        UI.sleep(15);
        UI.println(prefix + node.value);
        printPreOrder(node.left, prefix + " ");
        printPreOrder(node.right, prefix + " ");
    }

    /**
     * Post order traversal starts at the bottom of the tree and moves to the top
     */
    public void printPostOrder() {
        UI.clearText();
        printPostOrder(tree.root, "");
    }

    private void printPostOrder(TreeNode node, String prefix) {
        if (node == null) return;
        UI.sleep(15);
        printPostOrder(node.left, prefix + " ");
        printPostOrder(node.right, prefix + " ");
        UI.println(prefix + node.value);
    }

    /**
     * Traverses the tree from left to right
     */
    public void printInOrder() {
        UI.clearText();
        printInOrder(tree.root, "");
    }

    private void printInOrder(TreeNode node, String prefix) {
        if (node == null) return;
        UI.sleep(15);
        printInOrder(node.left, prefix + " ");
        UI.println(prefix + node.value);
        printInOrder(node.right, prefix + " ");
    }

    public void print(String s) {
        UI.clearText();
        for (char str : s.toCharArray()) {
            UI.print(str);
            UI.sleep(15);
        }
    }

    private void init() {
        for (int value : values) {
            tree.add(value, false);
        }
        drawTree();
    }

    public void sumTree() {
        int sum = sumTree(tree.root);
        print("The sum of the tree is: " + sum);
    }

    private int sumTree(TreeNode node) {
        if (node == null) return 0;
        return node.value + sumTree(node.left) + sumTree(node.right);
    }

    public void binarySearch() {
        print("Target");
        int target = UI.askInt(": ");
        TreeNode node = binarySearch(tree.root, target);
        if (node == null) {
            print("Target " + target + " not found!");
        } else {
            node.setHighlighted(true);
            print("Found " + node.value);
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
        print("Enter a number");
        int val = UI.askInt(": ");
        for (int value : values) {
            if (value == val) {
                print("Tree contains " + val);
            }
        }
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
        } else {
            UI.eraseOval(x - 15, y - 15, 30, 30);
        }
        UI.setColor(Color.black);
        UI.drawString(Integer.toString(node.value), x - 5, y + 5);
        UI.drawOval(x - 15, y - 15, 30, 30);

        if (node.left != null) { // Draw left
            UI.drawLine(x - 5, y + 15, x - offset, y + 50);  // Adjusted coordinates
            drawNode(node.left, x - offset, y + 50, offset / 2);
        }
        if (node.right != null) { // Draw right
            UI.drawLine(x + 5, y + 15, x + offset, y + 50);  // Adjusted coordinates
            drawNode(node.right, x + offset, y + 50, offset / 2);
        }
    }
}
