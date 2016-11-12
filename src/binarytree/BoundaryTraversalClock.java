package binarytree;

/**
 * Created by xuanwang on 11/2/16.
 */
public class BoundaryTraversalClock {


    static class Node
    {
        int data;
        Node left, right;

        Node(int item)
        {
            data = item;
            left = right = null;
        }
    }

    Node root;

    // A simple function to print leaf nodes of a binary tree
    void printLeaves(Node node)
    {
        if (node != null)
        {
            printLeaves(node.right);

            // Print it if it is a leaf node
            if (node.left == null && node.right == null)
                System.out.print(node.data + " ");
            printLeaves(node.left);
        }
    }

    // A function to print all left boundry nodes, except a leaf node.
    // Print the nodes in TOP DOWN manner
    void printBoundaryLeft(Node node)
    {
        if (node != null)
        {
            if (node.left != null)
            {

                // to ensure top down order, print the node
                // before calling itself for left subtree
                printBoundaryLeft(node.left);
                System.out.print(node.data + " ");

            }
            else if (node.right != null)
            {
                printBoundaryLeft(node.right);
                System.out.print(node.data + " ");
            }

            // do nothing if it is a leaf node, this way we avoid
            // duplicates in output
        }
    }

    // A function to print all right boundry nodes, except a leaf node
    // Print the nodes in BOTTOM UP manner
    void printBoundaryRight(Node node)
    {
        if (node != null)
        {
            if (node.right != null)
            {
                // to ensure bottom up order, first call for right
                //  subtree, then print this node
                System.out.print(node.data + " ");
                printBoundaryRight(node.right);

            }
            else if (node.left != null)
            {
                System.out.print(node.data + " ");
                printBoundaryRight(node.left);
            }
            // do nothing if it is a leaf node, this way we avoid
            // duplicates in output
        }
    }

    // A function to do boundary traversal of a given binary tree
    void printBoundary(Node node)
    {
        if (node != null)
        {
            System.out.print(node.data + " ");

            // Print the right boundary in top-downp manner
            printBoundaryRight(node.right);

            // Print all leaf nodes
            printLeaves(node.right);
            printLeaves(node.left);

            // Print the left boundary in bottom-up manner.
            printBoundaryLeft(node.left);

        }
    }

    // Driver program to test above functions
    public static void main(String args[])
    {
        BoundaryTraversalClock tree = new BoundaryTraversalClock();
        tree.root = new Node(20);
        tree.root.left = new Node(8);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(12);
        tree.root.left.right.left = new Node(10);
        tree.root.left.right.right = new Node(14);
        tree.root.right = new Node(22);
        tree.root.right.right = new Node(25);
        tree.printBoundary(tree.root);
        // 20 8 4 10 14 25 22
        /*
        20

      8    22

    4  12     25

      10  14
         */


    }
}
