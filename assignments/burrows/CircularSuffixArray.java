/* *****************************************************************************
 *  Name:    Jiatong Yu
 *  NetID:   jiatongy
 *  Precept: P04
 *
 *  Partner Name:    NA
 *  Partner NetID:   NA
 *  Partner Precept: NA
 *
 *  Description:  create circular suffix array. crux: 1) use StringBuilder
 * 2) append original index into suffix array
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class CircularSuffixArray {
    // array of chars in original order
    private final String s;

    // length of string
    private final int n;

    // suffix array
    private CircularSuffix[] circular;

    // constructor
    public CircularSuffixArray(String s) {
        // initialize instance variables
        if (s == null) throw new IllegalArgumentException();
        n = s.length();
        circular = new CircularSuffix[n];
        this.s = s;

        buildCircular();
        Arrays.sort(circular);
    }

    // length of s
    public int length() {
        return n;
    }

    // returns original index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i > n - 1) throw new IllegalArgumentException();
        CircularSuffix cs = circular[i];
        return cs.start;
    }

    // helper method: fill in the circular suffix array
    private void buildCircular() {
        for (int i = 0; i < n; i++) {
            CircularSuffix cs = new CircularSuffix(i, s);
            circular[i] = cs;
        }
    }

    // nested class
    private static class CircularSuffix implements Comparable<CircularSuffix> {
        // denote the starting/ending index
        private final int start;

        // reference to input string
        private final String string;

        // constructor: index
        public CircularSuffix(int index, String input) {
            this.start = index;
            this.string = input;

        }

        // compareTo method
        public int compareTo(CircularSuffix that) {
            int i = this.start;
            int j = that.start;

            while (true) {
                int res = Character.compare(this.string.charAt(i),
                                            that.string.charAt(j));

                if (res != 0) return res;

                if (i == this.string.length() - 1) i = 0;
                else i++;
                if (j == that.string.length() - 1) j = 0;
                else j++;

                if (i == this.start || j == that.start) return 0;
            }
        }


    }


    public static void main(String[] args) {
        String s = args[0];
        CircularSuffixArray csa = new CircularSuffixArray(s);
        StdOut.println("length of string: " + csa.length());
        StdOut.println("original index of given search: " + csa.index(
                Integer.parseInt(args[1])
        ));

    }
}
