/* *****************************************************************************
 *  Name:    Jiatong Yu
 *  NetID:   NA
 *  Precept: P04
 *
 *  Description:  2019fall midterm ADT design
 * double-ended PQ with add key, delete max, delete min
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.RedBlackBST;

public class MinMaxPQ<Key extends Comparable<Key>> {
    private RedBlackBST<Key, Integer> bst = new RedBlackBST<>();
    private Key min;
    private Key max;

    // Θ(log n)
    public void insert(Key x) {
        if (bst.isEmpty()) {
            min = x;
            max = x;
        }
        else {
            if (x.compareTo(min) < 0) min = x;
            if (x.compareTo(max) > 0) max = x;
        }

        int val = 1;
        if (bst.contains(x)) val = bst.get(x) + 1;

        bst.put(x, val);
    }

    // Θ(log n)
    public Key delMin() {
        int freq = bst.get(min);
        if (freq > 1) {
            bst.put(min, freq - 1);
            return min;
        }
        else {
            bst.delete(min);
            Key smin = min;
            min = bst.min();
            return smin;
        }
    }
    // Θ(log n)
    public Key delMax() {
        int freq = bst.get(max);
        if (freq > 1) {
            bst.put(max, freq - 1);
            return max;
        }
        else {
            bst.delete(max);
            Key smax = max;
            max = bst.max();
            return max;
        }
    }

    // requirement: Θ(log n)
    // use: Θ(1)
    public Key min() {
        return min;
    }

    // requirement: Θ(log n)
    // use: Θ(1)
    public Key max() {
        return max;
    }


    public static void main(String[] args) {

    }
}
