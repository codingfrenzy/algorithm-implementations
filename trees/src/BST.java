import java.util.ArrayList;
import java.util.LinkedList;

public class BST {

    private static TreeNode root;

    // insert
    public static void insertRecursive(int value, TreeNode node) {
        if (node == null) {
            node = new TreeNode(value);
            if (root == null) {
                root = node;
            }
            return;
        }

        if (value == node.getValue()) {
            return;
        } else if (value < node.getValue()) {
            insertRecursive(value, node.getLeftChild());
        } else if (value > node.getValue()) {
            insertRecursive(value, node.getRightChild());
        }
    }

    public static void insertIterative(int value) {
        if (root == null) {
            root = new TreeNode(value);
            return;
        }

        TreeNode currentNode = root;
        TreeNode parentNode = currentNode;
        while (currentNode != null) {
            if (value < currentNode.getValue()) {
                parentNode = currentNode;
                currentNode = currentNode.getLeftChild();
            } else if (currentNode.getValue() < value) {
                parentNode = currentNode;
                currentNode = currentNode.getRightChild();
            } else {
                // value already exists
                return;
            }
        }

        TreeNode newNode = new TreeNode(value);
        if (value < parentNode.getValue()) {
            parentNode.setLeftChild(newNode);
        } else {
            parentNode.setRightChild(newNode);
        }
    }

    // search
    // remove
    // balance using two in-order traversals
    // balance using Day–Stout–Warren_algorithm

    // inorder print
    public static void printInorderRecursive(TreeNode node) {
        if (node == null) {
            return;
        }

        printInorderRecursive(node.getLeftChild());
        System.out.print(node.getValue() + ", ");
        printInorderRecursive(node.getRightChild());
    }

    // preorder print
    public static void printPreorderRecursive(TreeNode node) {
        if (node == null) {
            return;
        }

        System.out.print(node.getValue() + ", ");
        printPreorderRecursive(node.getLeftChild());
        printPreorderRecursive(node.getRightChild());
    }

    // postorder print
    public static void printPostorderRecursive(TreeNode node) {
        if (node == null) {
            return;
        }

        printPostorderRecursive(node.getLeftChild());
        printPostorderRecursive(node.getRightChild());
        System.out.print(node.getValue() + ", ");
    }

    /**
     * Method to get a list of nodes at each level. K levels will have K lists.
     *
     * @param root
     * @return
     */
    public static ArrayList<ArrayList<TreeNode>> listOfNodesPerLevel(TreeNode root) {
        ArrayList<ArrayList<TreeNode>> levelList = new ArrayList<>();
        listOfNodesPerLevel(root, 0, levelList);
        return levelList;
    }

    /*
        At each level, add the current node into the list and then recurse for the child levels.
     */
    private static void listOfNodesPerLevel(TreeNode node, int level, ArrayList<ArrayList<TreeNode>> levels) {
        if (node == null) {
            return;
        }

        ArrayList<TreeNode> listAtLevel = null;
        try {
            // get the list at this level
            listAtLevel = levels.get(level);

        } catch (IndexOutOfBoundsException e) {
            // if the current level does not exist, the assignment above is going to throw an exception
        } finally {

            // if the list at this level does not exist, create a new one.
            // each creation will happen only one time, when the first left child of the subtree is encountered
            if (listAtLevel == null) {
                listAtLevel = new ArrayList<>();
                levels.add(listAtLevel);
            }
        }

        // add current node to the list at this level
        // due to the overall list containing the object references of the list at each level, adding to the underlying node list per level is suffcient
        listAtLevel.add(node);

        // traverse through its children
        listOfNodesPerLevel(node.getLeftChild(), level + 1, levels);
        listOfNodesPerLevel(node.getRightChild(), level + 1, levels);
    }

    /*
    Use BFS in the tree.
    A queue stores all the children of the current level.
    Remove from queue as elements are printed (visited)
     */
    public static void printLevelOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        class TreeLevels {
            TreeNode node;
            int level;

            public TreeLevels(TreeNode node, int level) {
                this.node = node;
                this.level = level;
            }

            public String toString() {
                return "Level: " + level + " Node:" + node;
            }
        }

        LinkedList<TreeLevels> queue = new LinkedList<>();
        TreeLevels rootNode = new TreeLevels(root, 0);
        queue.addLast(rootNode);
        // root node is in queue
        TreeLevels currentNode = queue.peekFirst();

        while (!queue.isEmpty()) {
            System.out.println();
            // this is for keeping track of levels.
            // after all nodes in a level are traversed, break the loop
            int level = currentNode.level;

            while (currentNode != null && level == currentNode.level) {
                // remove first node from queue after printing
                System.out.print(currentNode.node + ", ");
                queue.removeFirst();

                // if children exists, add them to queue with the level + 1
                TreeNode node;
                node = currentNode.node.getLeftChild();
                if (node != null) {
                    queue.addLast(new TreeLevels(node, level + 1));
                }
                node = currentNode.node.getRightChild();
                if (node != null) {
                    queue.addLast(new TreeLevels(node, level + 1));
                }

                // If queue is empty after having traversed through all children, break out
                if (queue.isEmpty()) {
                    currentNode = null;
                } else {
                    currentNode = queue.peekFirst();
                }
            }
        }

    }

    public static void main(String[] args) {
        insertIterative(50);
        insertIterative(30);
        insertIterative(20);
        insertIterative(40);
        insertIterative(70);
        insertIterative(40);
        insertIterative(80);
        insertIterative(35);
        insertIterative(33);

//        printInorderRecursive(root);
//        System.out.println();
//        printPreorderRecursive(root);
//        System.out.println();
//        printPostorderRecursive(root);
//        System.out.println();
//        System.out.println(listOfNodesPerLevel(root));
        printLevelOrderTraversal(root);
    }
}