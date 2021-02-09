/* *****************************************************************************
 *  Name:    Jiatong Yu
 *  NetID:   jiatongy
 *  Precept: P04
 *
 *  Partner Name:    NA
 *  Partner NetID:   NA
 *  Partner Precept: NA
 *
 *  Description:  Using re-implemented BFS to search for shortest common ancestor
 * have nested class cycleDetection and BFS.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.HashMap;

public class ShortestCommonAncestor {
    // rooted DAG
    private final Digraph G;


    // constructor: check if input is not rooted Dag
    public ShortestCommonAncestor(Digraph graph) {
        // check if G is a rooted DAG
        if (graph == null) throw new IllegalArgumentException();
        this.G = new Digraph(graph);
        DirectedCycle cycle = new DirectedCycle(G);
        if (cycle.hasCycle()) throw new IllegalArgumentException();
        if (!rooted()) throw new IllegalArgumentException();
    }

    // private helper function: root detection
    private boolean rooted() {
        // number of vertex with out-degree = 0
        // rooted DAG: exactly one vertex with 0 out-degree
        int n = 0;
        for (int i = 0; i < G.V(); i++) {
            if (G.outdegree(i) == 0) n++;
        }
        return n == 1;
    }

    /* @citation Adapted from: https://algs4.cs.princeton.edu/
    code/javadoc/edu/princeton/cs/algs4/BreadthFirstDirectedPaths.html
     */

    // extra credit method: length of shortest ancestral path
    public int length(int v, int w) {
        validate(v);
        validate(w);
        int champD = G.V() + 1;

        // run bfs on v
        Queue<Integer> q = new Queue<>();
        HashMap<Integer, Integer> mapv = new HashMap<>();
        q.enqueue(v);
        mapv.put(v, 0);
        while (!q.isEmpty()) {
            int node = q.dequeue();
            for (int x : G.adj(node)) {
                if (!mapv.containsKey(x)) {
                    mapv.put(x, mapv.get(node) + 1);
                    q.enqueue(x);
                }
            }
        }

        Queue<Integer> q2 = new Queue<>();
        HashMap<Integer, Integer> mapw = new HashMap<>();
        q2.enqueue(w);
        mapw.put(w, 0);
        while (!q2.isEmpty()) {
            int node = q2.dequeue();
            if (mapv.containsKey(node)) {
                if (mapv.get(node) + mapw.get(node) < champD) {
                    champD = mapv.get(node) + mapw.get(node);
                }

            }
            for (int x : G.adj(node)) {
                if (!mapw.containsKey(x)) {
                    mapw.put(x, mapw.get(node) + 1);
                    q2.enqueue(x);
                }
            }
        }
        return champD;
    }

    // extra credit method: a shortest common ancestor
    public int ancestor(int v, int w) {
        validate(v);
        validate(w);
        int champD = G.V() + 1;
        int champV = -1;

        // run bfs on v
        Queue<Integer> q = new Queue<>();
        HashMap<Integer, Integer> mapv = new HashMap<>();
        q.enqueue(v);
        mapv.put(v, 0);
        while (!q.isEmpty()) {
            int node = q.dequeue();
            for (int x : G.adj(node)) {
                if (!mapv.containsKey(x)) {
                    mapv.put(x, mapv.get(node) + 1);
                    q.enqueue(x);
                }
            }
        }

        Queue<Integer> q2 = new Queue<>();
        HashMap<Integer, Integer> mapw = new HashMap<>();
        q2.enqueue(w);
        mapw.put(w, 0);
        while (!q2.isEmpty()) {
            int node = q2.dequeue();
            if (mapv.containsKey(node)) {
                if (mapv.get(node) + mapw.get(node) < champD) {
                    champD = mapv.get(node) + mapw.get(node);
                    champV = node;
                }

            }
            for (int x : G.adj(node)) {
                if (!mapw.containsKey(x)) {
                    mapw.put(x, mapw.get(node) + 1);
                    q2.enqueue(x);
                }
            }
        }
        return champV;
    }

    // extra credit method: length of shortest ancestral path
    public int lengthSubset(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
        if (subsetA == null || subsetB == null) throw new IllegalArgumentException();

        int champD = G.V() + 1;
        Queue<Integer> q1 = new Queue<>();
        Queue<Integer> q2 = new Queue<>();
        HashMap<Integer, Integer> mapA = new HashMap<>();
        HashMap<Integer, Integer> mapB = new HashMap<>();
        int count = 0;
        for (Integer node : subsetA) {
            if (node == null) throw new IllegalArgumentException();
            validate(node);
            q1.enqueue(node);
            mapA.put(node, 0);
            count++;
        }
        if (count == 0) throw new IllegalArgumentException();

        while (!q1.isEmpty()) {
            int node = q1.dequeue();
            for (int x : G.adj(node)) {
                if (!mapA.containsKey(x)) {
                    mapA.put(x, mapA.get(node) + 1);
                    q1.enqueue(x);
                }
            }
        }

        int count2 = 0;
        for (Integer node : subsetB) {
            if (node == null) throw new IllegalArgumentException();
            validate(node);
            q2.enqueue(node);
            mapB.put(node, 0);
            count2++;
        }
        if (count2 == 0) throw new IllegalArgumentException();

        while (!q2.isEmpty()) {
            int node = q2.dequeue();
            if (mapA.containsKey(node)) {
                if (mapA.get(node) + mapB.get(node) < champD) {
                    champD = mapA.get(node) + mapB.get(node);
                }
            }
            for (int x : G.adj(node)) {
                if (!mapB.containsKey(x)) {
                    mapB.put(x, mapB.get(node) + 1);
                    q2.enqueue(x);
                }

            }
        }
        return champD;
    }

    // extra credit method: a shortest common ancestor
    public int ancestorSubset(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
        if (subsetA == null || subsetB == null) throw new IllegalArgumentException();

        int champD = G.V() + 1;
        int champV = -1;
        Queue<Integer> q1 = new Queue<>();
        Queue<Integer> q2 = new Queue<>();
        HashMap<Integer, Integer> mapA = new HashMap<>();
        HashMap<Integer, Integer> mapB = new HashMap<>();
        int count = 0;
        for (Integer node : subsetA) {
            if (node == null) throw new IllegalArgumentException();
            validate(node);
            q1.enqueue(node);
            mapA.put(node, 0);
            count++;
        }
        if (count == 0) throw new IllegalArgumentException();

        while (!q1.isEmpty()) {
            int node = q1.dequeue();
            for (int x : G.adj(node)) {
                if (!mapA.containsKey(x)) {
                    mapA.put(x, mapA.get(node) + 1);
                    q1.enqueue(x);
                }
            }
        }

        int count2 = 0;
        for (Integer node : subsetB) {
            if (node == null) throw new IllegalArgumentException();
            validate(node);
            q2.enqueue(node);
            mapB.put(node, 0);
            count2++;
        }
        if (count2 == 0) throw new IllegalArgumentException();

        while (!q2.isEmpty()) {
            int node = q2.dequeue();
            if (mapA.containsKey(node)) {
                if (mapA.get(node) + mapB.get(node) < champD) {
                    champD = mapA.get(node) + mapB.get(node);
                    champV = node;
                }
            }
            for (int x : G.adj(node)) {
                if (!mapB.containsKey(x)) {
                    mapB.put(x, mapB.get(node) + 1);
                    q2.enqueue(x);
                }

            }
        }
        return champV;
    }

    // private helper method: check vertex v in range
    private void validate(int v) {
        int n = G.V();
        if (v < 0 || v > n) throw new IllegalArgumentException();
    }


    public static void main(String[] args) {
        Bag<Integer> bagA = new Bag<>();
        Bag<Integer> bagB = new Bag<>();
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        for (int i = 0; i < 3; i++) {
            bagA.add(StdRandom.uniform(G.V()));
            bagB.add(StdRandom.uniform(G.V()));
        }

        ShortestCommonAncestor sca = new ShortestCommonAncestor(G);
        StdOut.println("set sca:" + sca.ancestorSubset(bagA, bagB));
        StdOut.println("set sca length" + sca.lengthSubset(bagA, bagB));
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sca.length(v, w);
            int ancestor = sca.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }

}
