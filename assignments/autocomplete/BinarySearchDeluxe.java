/* *****************************************************************************
 *  Name:    Jiatong Yu
 *  NetID:   jiatongy
 *  Precept: P04
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: P04
 *
 *  Description:  Used in Autocomplete to find all query strings that
 * start with the given prefix. When binary searching a sorted array that
 * contains more than one key equal to the search key, the client may want
 * to know the index of either the first or the last such key
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class BinarySearchDeluxe {

    // return index of first matching key or -1 if such key doesn't exist
    // 1 + log2n compares in worst case
    public static <Key> int firstIndexOf(
            Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException();
        }
        // Citation: I modified the code from Elementary Sort lecture slides
        int lo = 0, hi = a.length - 1;
        int current = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            // throw illegal argument if element is null
            if (a[mid] == null) throw new IllegalArgumentException();
            int comp = comparator.compare(key, a[mid]);
            // comp < 0, key is smaller than a[mid]
            if (comp < 0) hi = mid - 1;
            // Modification: comp = 0, do not stop
            if (comp == 0) {
                hi = mid - 1;
                current = mid;
            }
            if (comp > 0) lo = mid + 1;

        }
        return current;


    }


    // return index of last matching key or -1
    public static <Key> int lastIndexOf(
            Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException();
        }
        int lo = 0, hi = a.length - 1;
        int current = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] == null) throw new IllegalArgumentException();
            int comp = comparator.compare(key, a[mid]);
            if (comp < 0) hi = mid - 1;
            if (comp == 0) {
                current = mid;
                lo = mid + 1;

            }
            if (comp > 0) lo = mid + 1;

        }
        return current;

    }

    public static void main(String[] args) {
        Integer[] a = {
                0, 1, 7, 41, 79
        };

        int key = 1;
        int firstIndex = firstIndexOf(a, key, Comparator.naturalOrder());
        StdOut.println("first index: " + firstIndex);
        int lastIndex = lastIndexOf(a, key, Comparator.naturalOrder());
        StdOut.println("last index: " + lastIndex);


    }
}
