import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StopwatchCPU;

import java.util.LinkedList;

public class KdTreeST<Value> {

    // creates a root node
    private Node root;
    // creates a size variable that keeps track of the size of the tree
    private int size;

    // Node private class that comprises of a key value pairing, the rectHV that
    // corresponds with it and the level or depth of the node.
    private class Node {
        // keeps track of the x coordinate of the point
        private final double x;
        // keeps track of the y coordinate of the point
        private final double y;
        // keeps track of the Point2D that is the key of the node.
        private final Point2D key;
        // keeps track of the value of the Node
        private Value val;
        // keeps track of the level or depth of the node
        private final int level;
        // left and right children of a node
        private Node left, right;
        // corresponding bounding box of the node
        private RectHV rect;


        // method for level

        // constructor
        public Node(Point2D key, Value val, int level, RectHV rect) {
            this.x = key.x();
            this.y = key.y();
            this.level = level;
            this.key = key;
            this.val = val;
            this.rect = rect;
        }

        // returns the x coordinate of the node
        public final double x() {
            return this.x;
        }

        // returns the y coordinate of the node
        public final double y() {
            return this.y;
        }

        // public int level() {
        //     return size;
        // }

        // public int compareTo(Point2D that) {
        // double one = this.key.distanceSquaredTo();
        // Node comp = new Node(this.val, this.size, this.rect, that);
        // double two = comp.rect.distanceSquaredTo(that);
        //
        // if (one > two) {
        //     return 1;
        // }
        // else if (one < two) {
        //     return -1;
        // }
        // else {
        //     return 0;
        // }
        // double compare = this.key.distanceSquaredTo(that);
        //
        // if (compare > 0) {
        //     return 1;
        // }
        // else if (compare < 0) {
        //     return -1;
        // }
        // else {
        //     return 0;
        // }

        // if (this.size % 2 == 0) {
        //     if (this.x > that.x()) {
        //         return 1;
        //     }
        //     else if (this.x < that.x()) {
        //         return -1;
        //     }
        //     else {
        //         return 0;
        //     }
        //
        // }
        // else {
        //     if (this.y > that.y()) {
        //         return 1;
        //     }
        //     else if (this.y < that.y()) {
        //         return -1;
        //     }
        //     else {
        //         return 0;
        //     }
        // }


    }

    // compareTo level that compares a node with a Point2D based on the level
    private int compareTo(Node x, Point2D that, int lev) {
        // if the lev or depth was even
        if (lev % 2 == 0) {
            if (x.x() < that.x()) {
                return 1;
            }
            else if (x.x() > that.x()) {
                return -1;
            }
            else {
                return 0;
            }

        }
        // if the lev or depth was odd
        else {
            if (x.y() < that.y()) {
                return 1;
            }
            else if (x.y() > that.y()) {
                return -1;
            }
            else {
                return 0;
            }
        }


    }

    // construct an empty symbol table of points
    public KdTreeST() {
        // empty symbol table whih has a null root and a size of 0
        root = null;
        size = 0;

    }

    // is the symbol table empty?
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;

    }

    // number of points
    public int size() {
        return size;

    }

    // private boundingboxes that returns the bounding boxes for a specific node
    private RectHV boundingbox(Point2D key, Node x, int lev) {
        double max = Double.MAX_VALUE;
        if (x == null) {
            return new RectHV(-max, -max, max, max);
        }
        // System.out.println(lev);
        double ymin = x.rect.ymin();
        double ymax = x.rect.ymax();
        double xmin = x.rect.xmin();
        double xmax = x.rect.xmax();

        // System.out.println(x.key + "previous key");
        // System.out.println(key + " Point2D");
        // System.out.println(x.rect + " bounding box previous");
        // keeps track of the previous level and see's if its even or odd.
        int previous_level = lev - 1;
        // lev--;
        if (previous_level % 2 == 0) {
            if (key.x() < x.x()) {
                xmax = x.x();
            }
            else {
                xmin = x.x();
            }

        }
        else {
            if (key.y() < x.y()) {
                ymax = x.y();

            }
            else {
                ymin = x.y();
            }
        }
        RectHV bounding = new RectHV(xmin, ymin, xmax, ymax);

        // System.out.println(bounding);
        return bounding;
    }

    // associate the value val with point p
    public void put(Point2D p, Value val) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if (val == null) {
            throw new IllegalArgumentException();
        }


        root = put(null, root, 0, p, val);


    }

    // private put method
    private Node put(Node parent, Node x, int lev, Point2D p, Value val) {
        // boolean temp = false;
        // double max = Double.MAX_VALUE;
        if (x == null) {
            this.size++;
            return new Node(p, val, lev, boundingbox(p, parent, lev));
        }
        int cmp = compareTo(x, p, lev);
        if (cmp < 0) {
            // if (x.left == null) {
            //     temp = true;
            // }
            // lev++;

            x.left = put(x, x.left, lev + 1, p, val);
            // System.out.println(lev);
            // if (temp) {
            //     x.left.rect = boundingbox(p, parent, lev);
            // }
        }
        else if (cmp > 0) {
            // if (x.right == null) {
            //     temp = true;
            // }
            // lev++;

            x.right = put(x, x.right, lev + 1, p, val);
            // if (temp) {

            // x.right.rect = boundingbox(p, parent, lev);
            // }

        }
        else if (x.key.equals(p)) {
            x.val = val;
        }
        else {
            // lev++;
            // if (x.right == null) {
            //     temp = true;
            // }

            x.right = put(x, x.right, lev + 1, p, val);
            // if (temp) {
            //     x.right.rect = boundingbox(p, parent, lev);
            // }
        }
        return x;
    }

    // value associated with point p
    public Value get(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        return get(root, p, 0);
    }

    // private get method
    private Value get(Node x, Point2D p, int lev) {
        if (x == null) {
            return null;
        }

        int cmp = compareTo(x, p, lev);
        if (cmp < 0) {
            // lev++;
            return get(x.left, p, lev + 1);
        }
        else if (cmp > 0) {
            // lev++;
            return get(x.right, p, lev + 1);
        }
        else if (x.key.equals(p)) {
            return x.val;
        }
        else {
            // lev++;
            return get(x.right, p, lev + 1);
        }

    }

    // does the symbol table contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        return get(p) != null;

    }

    // all points in the symbol table
    public Iterable<Point2D> points() {
        Queue<Node> allnodes = new Queue<Node>();
        Queue<Point2D> allpoints = new Queue<Point2D>();

        allnodes.enqueue(root);

        while (!allnodes.isEmpty()) {
            Node x = allnodes.dequeue();
            allpoints.enqueue(x.key);
            if (x.left != null) {
                allnodes.enqueue(x.left);
            }
            if (x.right != null) {
                allnodes.enqueue(x.right);
            }


        }


        return allpoints;

    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        // LinkedList<Node> allnodes = new LinkedList<Node>();
        // LinkedList<Point2D> allpoints = new LinkedList<Point2D>();
        //
        // allnodes.add(root);
        //
        // while (!allnodes.isEmpty()) {
        //     Node x = allnodes.remove();
        //     if (x.left != null) {
        //         allnodes.add(x.left);
        //     }
        //     if (x.right != null) {
        //         allnodes.add(x.right);
        //     }
        //     if (rect.contains(x.key)) {
        //         allpoints.add(x.key);
        //     }
        //
        // }
        //
        //
        // return allpoints;
        LinkedList<Point2D> rangepoints = new LinkedList<Point2D>();
        range(root, rangepoints, rect);
        return rangepoints;


    }

    // private range function to prune out the subtrees that don't intersect
    // made it void because this function doesn't neccesarily need to return a iterable
    // just needs to update it.
    private void range(Node x, LinkedList<Point2D> rangepoints,
                       RectHV rect) {
        if (x == null) {
            return;
        }
        if (!rect.intersects(x.rect)) {
            return;
        }
        if (rect.contains(x.key)) {
            rangepoints.add(x.key);
        }
        range(x.left, rangepoints, rect);
        range(x.right, rangepoints, rect);

    }

    // a nearest neighbor of point p; null if the symbol table is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }


        return nearest(p, root, root.key, 0);

    }

    // private nearest function to prune out things not needed
    private Point2D nearest(Point2D p, Node x, Point2D near, int lev) {
        // if p is equal to x or if the distanceSquareTo is 0 then your done.
        // if (this.size() == 1) {
        //     return near;
        // }
        if (x == null) {
            return near;
        }
        if (p.equals(near)) {
            return near;
        }
        // if the closest point discovered so far is closer than the distance
        // between the query point
        // and the rectangle corresponding to a node, there is no need to
        // explore that node (or its subtrees)
        if (x.rect.distanceSquaredTo(p) > near.distanceSquaredTo(p)) {

            return near;
        }

        if (p.distanceSquaredTo(x.key) < p.distanceSquaredTo(near)) {
            near = x.key;
        }


        int cmp = compareTo(x, p, lev);

        if (cmp < 0) {

            // x.left.key.distanceSquaredTo(p) < near.distanceSquaredTo(p)
            // System.out.println("cmp < 0");

            near = nearest(p, x.left, near, lev + 1);
            near = nearest(p, x.right, near, lev + 1);
        }
        else if (cmp > 0) {


            near = nearest(p, x.right, near, lev + 1);
            near = nearest(p, x.left, near, lev + 1);

            // else if (x.left.rect.contains(p)) {
            //     near = nearest(p, x.left, near, lev);
            // }
        }
        else {
            near = nearest(p, x.right, near, lev + 1);
            near = nearest(p, x.left, near, lev + 1);
        }
        return near;


        // double distance_left = p.distanceSquaredTo(x.left.key);
        // double distance_right = p.distanceSquaredTo(x.right.key);
        //
        // if distance from query point to left node bounding box is already big
        // if (x.left.rect.contains(p) && distance_left < distance_right) {
        //     near = nearest(p, x.left, near);
        // }
        // else if (x.right.rect.contains(p) && distance_left > distance_right) {
        //     near = nearest(p, x.right, near);
        // }

        // int cmp = x.compareTo(p);
        // if (cmp < 0 && x.rect.contains(p)) {
        //     near = nearest(p, x.left, x.left.key);
        // }
        // else if (cmp > 0 && x.rect.contains(p)) {
        //     near = nearest(p, x.right, x.right.key);
        // }
        // else {
        //     return near;
        // }
    }

    // unit testing (required)
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        //
        // // set up the StdDraw environment
        //
        //
        KdTreeST<Integer> kdtree = new KdTreeST<Integer>();
        //
        // // create k-d tree
        for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            //     //
            //     //
            Point2D p = new Point2D(x, y);
            kdtree.put(p, i);
        }
        // //
        // // obtain level-order traversal and store in points[]
        // Point2D[] points = new Point2D[kdtree.size()];
        // // System.out.println(kdtree.size());
        // int size = 0;
        // for (Point2D p : kdtree.points()) {
        //     System.out.println(p);
        //     points[size] = p;
        //     size++;
        // }
        //
        // Point2D point = new Point2D(2, 3);
        // Point2D point2 = new Point2D(4, 2);
        // Point2D point3 = new Point2D(4, 5);
        // Point2D point4 = new Point2D(3, 3);
        // Point2D point5 = new Point2D(1, 5);
        // Point2D point6 = new Point2D(4, 4);
        // kdtree.put(point, 0);
        // kdtree.put(point2, 1);
        // kdtree.put(point3, 2);
        // kdtree.put(point4, 3);
        // kdtree.put(point5, 4);
        // kdtree.put(point6, 5);
        // StdOut.println(kdtree.get(point4) + " get method");
        // StdOut.println(kdtree.get(point6) + " get method");
        // StdOut.println(kdtree.get(point) + " get method");
        // StdOut.println("size: " + kdtree.size());
        // Point2D point7 = new Point2D(1, 1);
        // kdtree.nearest(point7);

        // KdTreeST<Integer> kst = new KdTreeST<Integer>();

        StopwatchCPU stopwatch = new StopwatchCPU();

        int m = 1000000;

        for (int i = 0; i < m; i++) {
            double x = StdRandom.uniform(0.0, 1.0);
            double y = StdRandom.uniform(0.0, 1.0);
            Point2D p = new Point2D(x, y);
            kdtree.nearest(p);
        }

        double time = stopwatch.elapsedTime();

        double callpersecond = m / time;

        System.out.println("time: " + time);
        System.out.println("calls per second: " + callpersecond);


        // queue of parents


    }

}
