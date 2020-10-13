/* *****************************************************************************
 *  Name:    Jiatong Yu
 *  NetID:  NA
 *  Precept: P00
 *
 *
 *  Description:  java realization of quicksort (2-way)
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

public class Quicksort {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    // recursive helper method for sort()
    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j);
        sort(a, j + 1, hi);
    }

    private static void exch(Comparable[] a, int v, int w) {
        Comparable temp = a[v];
        a[v] = a[w];
        a[w] = temp;
    }

    // crux: partition an array
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        // j = hi + 1 since we always put --j and ++i
        int j = hi + 1;
        while (true) {
            while (a[lo].compareTo(a[++i]) < 0) {
                if (i == hi) break;
            }
            while (a[lo].compareTo(a[--j]) > 0) {
                if (j == lo) break;
            }
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) {


    }
}
