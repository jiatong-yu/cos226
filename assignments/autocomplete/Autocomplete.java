/* *****************************************************************************
 *  Name:    Jiatong Yu
 *  NetID:   jiatongy
 *  Precept: P04
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description: implement ADT autocomplete. Sort the terms in lexi order,
 * use binary search to find all query strings that start with a given prefix,
 * sort the matching terms in descending order by weights.
 *
 *
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Autocomplete {
    // instance variable: term
    private final Term[] term;


    // Initialize the data structure from given array of terms
    // constructor take O(nlogn) compares
    public Autocomplete(Term[] terms) {
        if (terms == null) {
            throw new IllegalArgumentException();
        }
        // check if user gave null element in array
        for (int i = 0; i < terms.length; i++) {
            if (terms[i] == null) throw new IllegalArgumentException();
        }
        // I used copyOf() due to the pwd warning of ArrayIsStoredDirectly
        term = Arrays.copyOf(terms, terms.length);
        Arrays.sort(term);

    }


    // Returns all terms that start with the given prefix,
    // in descending order of weight
    public Term[] allMatches(String prefix) {
        if (prefix == null) throw new IllegalArgumentException();
        // first find first index of matched item
        int r = prefix.length();
        Term pref = new Term(prefix, 0);
        int firstIdx = BinarySearchDeluxe.firstIndexOf(
                term, pref, Term.byPrefixOrder(r));
        int lastIdx = BinarySearchDeluxe.lastIndexOf(
                term, pref, Term.byPrefixOrder(r));


        // corner case: if no matches were found
        if (firstIdx == -1) {
            Term[] nul = new Term[0];
            return nul;
        }
        else {
            int n = lastIdx - firstIdx + 1;

            Term[] allM = new Term[n];
            for (int i = 0; i < n; i++) {
                allM[i] = term[firstIdx++];
            }

            Arrays.sort(allM, Term.byReverseWeightOrder());
            return allM;
        }

    }

    // Returns the number of terms that start with the given prefix
    public int numberOfMatches(String prefix) {
        if (prefix == null) throw new IllegalArgumentException();
        Term pref = new Term(prefix, 0);
        int r = prefix.length();
        int firstIdx = BinarySearchDeluxe.firstIndexOf(
                term, pref, Term.byPrefixOrder(r));
        int lastIdx = BinarySearchDeluxe.lastIndexOf(
                term, pref, Term.byPrefixOrder(r));
        if (firstIdx == -1 || lastIdx == -1) return 0;
        return lastIdx - firstIdx + 1;
    }

    public static void main(String[] args) {
        // using sample client from assignment web

        // read in terms from a file
        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        Term[] terms = new Term[n];
        for (int i = 0; i < n; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query, weight);
        }

        // read in queries from stdIn and print the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            StdOut.printf("%d matches\n", autocomplete.numberOfMatches(prefix));
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }


}

