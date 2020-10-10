import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class PointST<Value> {
    // red black BST
    private final RedBlackBST<Point2D, Value> BST;

    // construct an empty symbol table of points
    public PointST() {
        BST = new RedBlackBST<>();
    }

    // is the ST empty?
    public boolean isEmpty() {
        return BST.isEmpty();
    }

    // return number of points
    public int size() {
        return BST.size();
    }

    // associate value val with point p
    public void put(Point2D p, Value val) {
        if (p == null || val == null) throw new IllegalArgumentException();
        BST.put(p, val);
    }

    // value associated with point p
    public Value get(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        Value v = BST.get(p);
        return v;
    }

    // does the symbol table contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return BST.contains(p);
    }

    // all points in the symbol table
    public Iterable<Point2D> points() {
        return BST.keys();
    }

    // all points that are inside the rectangle (or on the boundary)
    // performance: o(n)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        if (BST.size() == 0) return null;
        Stack<Point2D> stack = new Stack<Point2D>();
        for (Point2D p : BST.keys()) {
            if (rect.contains(p)) stack.push(p);
        }
        return stack;
    }

    // a nearest neighbor of point p; null if the symbol table is empty
    // performance: o(n)
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (BST.size() == 0) return null;
        double min = Double.POSITIVE_INFINITY;
        Point2D champ = null;
        for (Point2D pt : BST.keys()) {
            if (p.distanceSquaredTo(pt) < min) {
                min = p.distanceSquaredTo(pt);
                champ = pt;
            }
        }
        return champ;
    }

    public static void main(String[] args) {
        PointST<Integer> pst = new PointST<Integer>();
        StdOut.println("should be empty: " + pst.isEmpty());
        Point2D p = new Point2D(5, 3);
        pst.put(p, 15);
        p = new Point2D(3, 6);
        pst.put(p, 18);
        StdOut.println("size should be 2: " + pst.size());
        StdOut.println("should be 18:" + pst.get(p));
        Iterable<Point2D> points = pst.points();
        for (Point2D point : points) StdOut.println(point);

        RectHV rect = new RectHV(0, 0, 3, 9);
        StdOut.println("should be false: " + pst.contains(new Point2D(4, 4)));
        Iterable<Point2D> stack = pst.range(rect);
        for (Point2D point : stack) StdOut.println(point);
        StdOut.println("prev line should be only one point: (3, 6)");

        Point2D origin = new Point2D(0, 0);

        StdOut.println("nearest to origin (5,3): " + pst.nearest(origin));


    }
}
