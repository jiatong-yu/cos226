import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class KdTreeST<Value> {
    // root node of tree
    private Node root;
    // temporary var that keep tracks of level when traversing the tree
    private int level;
    // helper var for nearest neighbor method
    private Point2D candidate;


    private class Node {
        // children nodes
        private Node left, right;
        // key
        private final Point2D key;
        // value
        private Value val;

        // number of nodes accessible from the node
        private int size;

        // bounding box of node
        private RectHV rect;

        // constructor
        public Node(Point2D p, Value value) {
            key = p;
            val = value;
        }
    }

    // constructor
    public KdTreeST() {
    }

    // private helper method for size
    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    // is empty?
    public boolean isEmpty() {
        return size(root) == 0;
    }

    // size
    public int size() {
        return size(root);
    }

    // put value into 2d tree
    public void put(Point2D p, Value val) {
        if (p == null || val == null) throw new IllegalArgumentException();
        // corner case: initialize the space to be infinite
        if (isEmpty()) {
            root = new Node(p, val);
            root.rect = new RectHV(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY,
                                   Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        }
        // reset level to traverse from root
        level = 0;
        root = put(root, p, val, root, true);
    }

    // private helper method for initializing the rectangle
    private void setRect(Node x, Node parent, boolean min) {
        boolean even;
        if (level % 2 == 0) even = true;
        else even = false;
        RectHV rect = parent.rect;

        // min when child node on the left on parent node

        if (!even && min) x.rect = new RectHV(rect.xmin(), rect.ymin(),
                                              parent.key.x(), rect.ymax());
        if (!even && !min) x.rect = new RectHV(parent.key.x(), rect.ymin(),
                                               rect.xmax(), rect.ymax());
        if (even && min) x.rect = new RectHV(rect.xmin(), rect.ymin(),
                                             rect.xmax(), parent.key.y());
        if (even && !min) x.rect = new RectHV(rect.xmin(), parent.key.y(),
                                              rect.xmax(), rect.ymax());

    }

    // private helper method
    /* @citation Adapted from: https://algs4.cs.princeton.edu/32bst/BST.java.html */
    private Node put(Node x, Point2D p, Value val, Node parent, boolean min) {

        if (x == null) {
            Node n = new Node(p, val);
            setRect(n, parent, min);
            n.size = 1;
            return n;
        }

        int comp;
        // if level is even --> compare x
        if (level % 2 == 0) {
            comp = Double.compare(p.x(), x.key.x());
        }
        // if level is odd --> compare y
        else {
            comp = Double.compare(p.y(), x.key.y());
        }

        // if node to put is to the left of node now
        if (comp < 0) {
            // since going down a level, level++
            level++;
            x.left = put(x.left, p, val, x, true);
        }
        else if (comp > 0) {
            level++;
            x.right = put(x.right, p, val, x, false);
        }
        else {
            if (x.key.equals(p)) x.val = val;
            else {
                level++;
                x.right = put(x.right, p, val, x, false);
            }
        }

        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    // value associated with p
    /* @citation Adapted from: https://algs4.cs.princeton.edu/32bst/BST.java.html */
    public Value get(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        level = 0;
        return get(root, p);
    }

    // helper method for get
    private Value get(Node x, Point2D p) {
        if (x == null) return null;
        int comp;
        if (level % 2 == 0) comp = Double.compare(p.x(), x.key.x());
        else comp = Double.compare(p.y(), x.key.y());
        if (comp < 0) {
            level++;
            return get(x.left, p);
        }
        if (comp > 0) {
            level++;
            return get(x.right, p);
        }
        else {
            if (x.key.equals(p)) return x.val;
            else {
                level++;
                return get(x.right, p);
            }
        }
    }

    // does the symbol table contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return get(p) != null;
    }

    // all points in the symbol table
    /* @citation Adapted from: https://www.cs.princeton.edu/courses/archive/
    fall20/cos226/lectures/32BinarySearchTrees.pdf */
    public Iterable<Point2D> points() {
        Queue<Node> nodeq = new Queue<Node>();
        Queue<Point2D> pointq = new Queue<Point2D>();
        nodeq.enqueue(root);
        while (!nodeq.isEmpty()) {
            Node x = nodeq.dequeue();
            if (x != null) {
                pointq.enqueue(x.key);
                nodeq.enqueue(x.left);
                nodeq.enqueue(x.right);
            }
        }
        return pointq;
    }

    // return collection of points in the given range
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        Queue<Point2D> queue = new Queue<Point2D>();
        range(queue, root, rect);
        return queue;
    }

    // recursive private helper method
    // citation: I referred to the precept lesson about range search
    private void range(Queue<Point2D> queue, Node x, RectHV rect) {
        if (x == null) return;
        if (!rect.intersects(x.rect)) return;
        if (rect.contains(x.key)) queue.enqueue(x.key);
        range(queue, x.left, rect);
        range(queue, x.right, rect);

    }

    // a nearest neighbor of point p; null if the symbol table is empty
    // citation: I referred to the precept lesson about NN search
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (size() == 0) return null;
        level = 0;
        candidate = root.key;
        nearest(root, p, 0);
        return candidate;

    }

    // recursive helper method
    private void nearest(Node x, Point2D p, int lev) {
        if (x == null) return;
        if (x.rect.distanceSquaredTo(p) > candidate.distanceSquaredTo(p)) return;
        if (x.key.distanceSquaredTo(p) < candidate.distanceSquaredTo(p)) {
            candidate = x.key;
        }

        int comp;
        if (lev % 2 == 0) comp = Double.compare(p.x(), x.key.x());
        else comp = Double.compare(p.y(), x.key.y());

        if (comp < 0) {
            nearest(x.left, p, ++lev);
            nearest(x.right, p, lev);
        }
        else {
            nearest(x.right, p, ++lev);
            nearest(x.left, p, lev);
        }


    }


    public static void main(String[] args) {
        KdTreeST<Integer> tree = new KdTreeST<Integer>();
        StdOut.println("Should be empty: " + tree.isEmpty());
        int i = 0;
        
        while (!StdIn.isEmpty()) {
            Point2D p = new Point2D(StdIn.readDouble(), StdIn.readDouble());
            tree.put(p, i);
            i++;
        }
        StdOut.println("size " + tree.size());
        StdOut.println("all points:");
        for (Point2D p : tree.points()) StdOut.println(p);

        Point2D p = new Point2D(0.372, 0.491);
        if (tree.contains(p)) {
            StdOut.println(tree.get(p));
        }

        RectHV rect = new RectHV(0, 0, 0.4, 0.6);
        for (Point2D k : tree.range(rect)) StdOut.println(k);

        Point2D q = new Point2D(0.054, 0.617);
        StdOut.println("NN: " + tree.nearest(q));

    }
}
