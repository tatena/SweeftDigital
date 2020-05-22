import javax.swing.plaf.SpinnerUI;
import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * List of ints, elements are always sorted
 * time complexity:
 *      add - O(log(n))
 *      remove - O(log(n))
 *      size - O(1)
 *      empty - O(1)
 *  Uses BST as internal representation to store data.
 */
public class SortedList implements List {
    private int size;
    private Node root;

    /**
     * node element of a BST, stores value, left and right children
     */
    private class Node {
        private int value;
        private Node right;
        private Node left;

        public Node(int value) {
            this.value = value;
            this.right = null;
            this.left = null;
        }
    }

    public SortedList() {
        root = null;
        size = 0;
    }

    @Override
    public void add(int element) {
        root = recAdd(root, element);
    }

    /**
     * recursively searches place for the element in BST
     * makes new node and adds it in the tree, if not there yet
     * @param curr
     * @param element
     * @return
     */
    private Node recAdd(Node curr, int element) {
        if (curr == null) {
            curr = new Node(element);
            size++;
        } else if (curr.value > element) {
            curr.left = recAdd(curr.left, element);
        } else if (curr.value < element){
            curr.right = recAdd(curr.right, element);
        }
        return curr;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(int element) {
        int oldSize = size;
        root = recRemove(root, element);
        return oldSize != size;
    }

    /**
     * recursively search for the node with element's value
     * deletes the node if found (comments below about the algorithm)
     * @param curr - root of the current subtree
     * @param element
     * @return
     */
    private Node recRemove(Node curr, int element) {
        if (curr == null) {
            return null;
        } else if (curr.value > element) {
            curr.left = recRemove(curr.left, element);
        } else if (curr.value < element) {
            curr.right = recRemove(curr.right, element);
        } else {
            // node to delete
            size--;
            if (curr.left == null && curr.right == null) {  // case LEAF -> delete the node
                curr = null;
            } else if (curr.left == null) {   // case RIGHT_ONLY -> replace with left child
                curr = curr.right;
            } else if (curr.right == null) {  // case LEFT_ONlY -> replace with right child
                curr = curr.left;
            } else {
                size++;  // case BOTH -> replace with minimum node of right subtree
                int newValue = getMin(curr.right);
                curr.value = newValue;
                curr.right = recRemove(curr.right, newValue);
            }
        }
        return curr;
    }

    /**
     * @param node - root of the subtree
     * @return - minimum value node of the subtree
     */
    private int getMin(Node node) {
        if (node.left == null)
            return node.value;
        return getMin(node.left);
    }

    @Override
    public String toString() {
        String res = recToString(root);
        return (res.isEmpty() ? "" : res.substring(1));
    }

    /**
     * recursive helper function - inorder traversal of BST
     * @param curr
     * @return
     */
    private String recToString(Node curr) {
        if (curr == null)
            return "";
        return recToString(curr.left) + "," + curr.value + recToString(curr.right);
    }

    // simple main method to manually test the class
    public static void main(String[] args) {
        SortedList testLs = new SortedList();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Your sorted list is empty now. Type: \n" +
                "1  -> add element \n" +
                "2  -> remove element \n" +
                "3  -> get size of the list \n" +
                "4  -> check if list is empty \n" +
                "5  -> get the string representation of the list \n"+
                "-1 -> quit \n");
        while (true) {
            System.out.println("Type number: ");
            int input = scanner.nextInt();

            switch (input) {
                case -1:
                    return;
                case 1:
                    System.out.println("Enter the integer to add");
                    int elem = scanner.nextInt();
                    testLs.add(elem);
                    System.out.println("Here is the list after add: " + testLs.toString());
                    break;
                case 2:
                    System.out.println("Enter the integer to remove");
                    elem = scanner.nextInt();
                    boolean res = testLs.remove(elem);
                    System.out.println("Remove operation returned " + String.valueOf(res));
                    System.out.println("Here is the list after remove: " + testLs.toString());
                    break;
                case 3:
                    System.out.println("Size of the list is " + testLs.size());
                    break;
                case 4:
                    System.out.println("isEmpty() returned " + String.valueOf(testLs.isEmpty()));
                    break;
                case 5:
                    System.out.println("Here is the current list \n" + testLs.toString());
                    break;
                default:
                    System.out.println("Please type the valid number");
            }
        }
    }
}
