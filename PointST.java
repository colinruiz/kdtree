import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StopwatchCPU;

import java.util.LinkedList;

public class PointST<Value> {

    // instance variable that declares a redBlackBST
    private RedBlackBST<Point2D, Value> redBlackBST;


    // construct an empty symbol table of points
    public PointST() {
        redBlackBST = new RedBlackBST<Point2D, Value>();

    }

    // is the symbol table empty?
    public boolean isEmpty() {
        if (redBlackBST.isEmpty()) {
            return true;
        }
        return false;
    }

    // number of points
    public int size() {
        return redBlackBST.size();
    }

    // associate the value val with point p
    public void put(Point2D p, Value val) {
        if (p == null || val == null) {
            throw new IllegalArgumentException();
        }
        redBlackBST.put(p, val);
    }

    // value associated with point p
    public Value get(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return redBlackBST.get(p);
    }

    // does the symbol table contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if (redBlackBST.contains(p)) {
            return true;
        }
        return false;
    }

    // all points in the symbol table
    public Iterable<Point2D> points() {
        return redBlackBST.keys();
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        LinkedList<Point2D> keys_in_rect = new LinkedList<Point2D>();
        for (Point2D point : points()) {
            if (rect.contains(point)) {
                keys_in_rect.add(point);
            }
        }
        return keys_in_rect;

    }

    // a nearest neighbor of point p; null if the symbol table is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        Point2D near = null;
        if (redBlackBST.isEmpty()) {
            return null;
        }


        double distance;
        double minimum = Double.POSITIVE_INFINITY;
        for (Point2D point : points()) {
            distance = p.distanceSquaredTo(point);
            if (distance < minimum) {
                minimum = distance;
                near = point;
            }
        }
        return near;


    }

    // unit testing (required)
    public static void main(String[] args) {
        // Point2D root = new Point2D(1, 1);
        // Point2D right = new Point2D(2, 2);
        // Point2D left = new Point2D(0, 0);
        // Point2D not_contains = new Point2D(3, 3);
        //
        //
        // PointST pst = new PointST();
        //
        // System.out.println(bst.isEmpty());
        // System.out.println(bst.size());
        // bst.put(root, 1);
        // bst.put(right, 1);
        // bst.put(left, 1);
        //
        // System.out.println(bst.contains(right));
        // System.out.println(bst.contains(not_contains));
        // System.out.println(bst.points());

        PointST<Integer> pst = new PointST<Integer>();
        String filename = args[0];
        In in = new In(filename);
        //
        // // set up the StdDraw environment
        //
        //

        //
        // // create k-d tree
        for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            //     //
            //     //
            Point2D p = new Point2D(x, y);
            pst.put(p, i);
        }

        StopwatchCPU stopwatch = new StopwatchCPU();

        int m = 50;

        for (int i = 0; i < m; i++) {
            double x = StdRandom.uniform(0.0, 1.0);
            double y = StdRandom.uniform(0.0, 1.0);
            Point2D p = new Point2D(x, y);
            pst.nearest(p);
        }

        double time = stopwatch.elapsedTime();

        double callpersecond = m / time;

        System.out.println("time: " + time);
        System.out.println("calls per second: " + callpersecond);


    }

}
