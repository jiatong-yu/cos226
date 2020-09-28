/* *****************************************************************************
 *  Name:    Jiatong Yu
 *  NetID:   jiatongy
 *  Precept: P04
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:  immutable data type Term with three different comparator scheme
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Term implements Comparable<Term> {
    // query
    private final String query;
    // weight
    private final long weight;

    // initialize a term with given query string and weights
    public Term(String q, long w) {
        if (q == null) throw new IllegalArgumentException("query is null");
        if (w < 0) throw new IllegalArgumentException("weight is negative");
        query = q;
        weight = w;
    }

    // private helper method for ReverseWeightOrder
    // Citation: I used elementary sort lecture notes for this method
    private static class ReverseWeightOrder implements Comparator<Term> {
        public int compare(Term v, Term w) {
            // reverse order: larger weights are ranked smaller
            if (v.weight > w.weight) return -1;
            if (v.weight < w.weight) return 1;
            else return 0;

        }
    }

    // compare the two terms in descending order by weights
    public static Comparator<Term> byReverseWeightOrder() {
        return new ReverseWeightOrder();
    }

    // Compares the two terms in lexicographic order by query
    public int compareTo(Term that) {
        int res = this.query.compareTo(that.query);
        if (res < 0) return -1;
        if (res > 0) return 1;
        else return 0;
    }

    // private helper method for byPrefixOrder
    private static class PrefixOrder implements Comparator<Term> {
        // int r
        private final int r;

        // pass int r to comparator
        private PrefixOrder(int pref) {
            r = pref;
        }

        public int compare(Term v, Term w) {
            // idea: compare the queries by char
            int i = 0;
            // case 1: if length of v less than r
            if (v.query.length() < r && w.query.length() >= r) {
                while (i < v.query.length()) {
                    if (v.query.charAt(i) < w.query.charAt(i)) return -1;
                    if (v.query.charAt(i) > w.query.charAt(i)) return 1;
                    i++;
                }
                return -1;
            }

            // case 2: if length of w less than r
            if (w.query.length() < r && v.query.length() >= r) {
                while (i < w.query.length()) {
                    if (v.query.charAt(i) < w.query.charAt(i)) return -1;
                    if (v.query.charAt(i) > w.query.charAt(i)) return 1;
                    i++;
                }
                return 1;
            }

            // case 3: if both w and v shorter than r
            // if length equal
            if (w.query.length() < r && v.query.length() < r) {
                if (w.query.length() == v.query.length()) {

                    while (i < w.query.length()) {
                        if (v.query.charAt(i) < w.query.charAt(i)) return -1;
                        if (v.query.charAt(i) > w.query.charAt(i)) return 1;
                        i++;
                    }
                    return 0;
                }
                // if length not equal
                if (v.query.length() < w.query.length()) {
                    while (i < v.query.length()) {
                        if (v.query.charAt(i) < w.query.charAt(i)) return -1;
                        if (v.query.charAt(i) > w.query.charAt(i)) return 1;
                        i++;
                    }
                    return -1;
                }
                // if length not equal
                else {
                    while (i < w.query.length()) {
                        if (v.query.charAt(i) < w.query.charAt(i)) return -1;
                        if (v.query.charAt(i) > w.query.charAt(i)) return 1;
                        i++;
                    }
                    return 1;
                }
            }

            // normal case: both length greater than r
            else {
                while (i < r) {
                    if (v.query.charAt(i) < w.query.charAt(i)) return -1;
                    if (v.query.charAt(i) > w.query.charAt(i)) return 1;
                    i++;
                }
                return 0;
            }
        }
    }

    // Compares the two terms in lexicographic order,
    // but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0) throw new IllegalArgumentException("prefix length is negative");
        else return new PrefixOrder(r);
    }

    // return a string representation of Term: weight+tab+query
    public String toString() {
        return "" + this.weight + "\t" + this.query;
    }

    public static void main(String[] args) {
        Term t1 = new Term("doc", 10);
        Term t2 = new Term("document", 6);
        Term t3 = new Term("zombie", 1);
        StdOut.println(t1);
        StdOut.println(t2);
        StdOut.println(t3);
        int res = t1.compareTo(t2);
        StdOut.println(
                "lexi order by query, t1 should be smaller, so should be -1: " + res);
        Comparator<Term> prefcomp = byPrefixOrder(3);
        res = prefcomp.compare(t1, t2);
        StdOut.println(
                "lexi order but r = 3, so t1 = t2 so should be 0: " + res);
        Comparator<Term> reversecomp = byReverseWeightOrder();
        res = reversecomp.compare(t2, t3);
        StdOut.println(
                "reverse weight order, t2 is smaller, so should be -1: " + res);


    }
}


