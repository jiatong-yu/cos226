/* *****************************************************************************
 *  Name:    Jiatong Yu
 *  NetID:   alovelace
 *  Precept: P00
 *
 *  Description:  Binary Search Tree (for Symbol Table)
 *  runtime(worst): insert ~ n
 *                  search ~ n
 *  runtime(avg):   insert ~ log(n)
 *                  search ~ log(n)
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.Queue;

public class myBST<Key extends Comparable<Key>, Value> {
    private Node root;      // BST didn't have a constructor?

    private class Node {
        private Key key;
        private Value val;
        // # of accessible nodes from the give node (self-including)
        private int size;
        // left child always smaller, right child always larger
        private Node left, right;

        // initialize Node
        public Node(Key k, Value v) {
            key = k;
            val = v;
            size = 1;
            // default right/left node to be null
        }
    }

    public void put(Key k, Value v) {
        root = put(root, k, v);
    }

    // recursive helper function for put
    private Node put(Node x, Key k, Value v) {
        if (x == null) return new Node(k, v);
        int comp = x.key.compareTo(k);
        if (comp < 0) x.left = put(x.left, k, v);
        else if (comp > 0) x.right = put(x.right, k, v);
        else x.val = v;
        // using the helper method allows for handling null case
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Value get(Key k) {
        Node x = root;
        while (x != null) {
            int comp = k.compareTo(x.key);
            if (comp < 0) x = x.left;
            else if (comp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    public Iterable<Key> keys() {
        // FIFO order, use queue to store keys
        return null;

    }

    // private helper function for keys()
    // enqueue nodes in ascending key order
    private void inorder(Node x, Queue<Key> q) {
        if (x == null) return;
        inorder(x.left, q);
        q.enqueue(x.key);
        inorder(x.right, q);
        q.enqueue(x.key);
    }

    // TO DO
    public Key floor(Key key) {
        return null;
    }

    public Key ceiling(Key key) {
        return null;
    }


    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int comp = x.key.compareTo(key);
        if (comp < 0) return rank(key, x.left);
            // + 1 because we didn't include parent node in size(x.left)
        else if (comp > 0) return size(x.left) + rank(key, x.right) + 1;
        else return size(x.left);
    }


    private void delete(Key key) {

    }


    public static void main(String[] args) {

    }
}
