/* *****************************************************************************
 *  Name:    Jiatong Yu
 *  NetID:   NA
 *  Precept: P00
 *
 *  Description:  java realization of mergesort 
 *
 **************************************************************************** */

public class Mergesort {
    private static Comparable[] aux;        // aux array for merges

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];     // allocate space
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo <= hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    // crux: merge to put in order
    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        for (int k = 0; k < hi; k++) aux[k] = a[k];     // copy to aux for processing
        for (int k = 0; k < hi; k++) {
            if (i > mid) a[k] = aux[j++];   // corner case: left is exhausted
            else if (j > hi) a[k] = aux[i++];    // corner case: right is exhausted
                // core: copy the smaller element back
            else if (aux[i].compareTo(aux[j]) < 0) a[k] = aux[i];
            else a[k] = aux[j];
        }

    }

    public static void main(String[] args) {

    }
}
