/* *****************************************************************************
 *  Name:    Jiatong Yu
 *  NetID:   na
 *  Precept: P04
 *
 *  Description: spring 2020 midterm algs design
 *
 *
         * given a[n] and a pair of inversion (p, q), find an adjacent
         * pair inversion in sub-array a[p .. q] (n distinct keys)
         *
         * requirement: theta (log n)
         *
         * idea: modified version of binary search.
         * compare mid with lo. since lo > hi, mid is at least an inversion with either
         * lo or hi.
         *
         *
         **************************************************************************** */

public class Inversions {
    // return index of the first of the two inversions
    // (so the other one is index + 1)
    public static int partition(int[] a, int lo, int hi) {
        if (a.length < 2) throw new IllegalArgumentException();
        while (hi > lo + 1) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] < a[lo]) hi = mid;
            else lo = mid;
        }
        return lo;
    }

    public static void main(String[] args) {
        int[] a = { 20, 75, 78, 80, 83, 65, 68, 70, 73, 90 };
        int i = partition(a, 1, 8);
        int j = i + 1;
        System.out.println(i + ", " + j);

    }
}
