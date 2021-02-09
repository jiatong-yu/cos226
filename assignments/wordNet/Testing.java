/* *****************************************************************************
 *  Name:    Ada Lovelace
 *  NetID:   alovelace
 *  Precept: P00
 *
 *  Partner Name:    Dorothy Johnson Vaughan
 *  Partner NetID:   djvaughan
 *  Partner Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Testing {

    public static void main(String[] args) {
        In ref = new In(args[0]);
        // while (StdIn.hasNextLine() && ref.hasNextLine()) {
        //     String wordnet = StdIn.readLine();
        //     String referenceall = ref.readLine();
        //     String[] list = referenceall.split("\\,");
        //     String reference = list[1];
        //     if (!reference.equals(wordnet))
        //         StdOut.println("wordnet word: " + wordnet + "; reference: " + reference);
        int i = 0;
        while (ref.hasNextLine()) {
            String referenceall = ref.readLine();
            String[] list = referenceall.split("\\,");
            String reference = list[1];
            String target = args[1];
            if (reference.equals(target)) StdOut.println("found " + target);
            StdOut.print(i++ + " ");
        }

    }

}

