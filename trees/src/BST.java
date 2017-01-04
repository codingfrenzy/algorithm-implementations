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
            if (value < currentNode.getValue()){
                parentNode = currentNode;
                currentNode = currentNode.getLeftChild();
            } else if (currentNode.getValue() < value){
                parentNode = currentNode;
                currentNode = currentNode.getRightChild();
            } else {
                // value already exists
                return;
            }
        }

        TreeNode newNode = new TreeNode(value);
        if(value < parentNode.getValue()){
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
    // postorder print

    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            int rand = (int) (100 * Math.random());
            System.out.print(rand + ", ");
            insertIterative(rand);
        }

        System.out.println();
        printInorderRecursive(root);
    }
}