/* *****************************************************************************
 *  Name:    Jiatong Yu
 *  NetID:   jiatongy
 *  Precept: P04
 *
 *  Partner Name:    NA
 *  Partner NetID:   NA
 *  Partner Precept: NA
 *
 *  Description: Burrows Wheeler compression. For inverse method, I modified
 * the key-index counting method from lecture to find the next[] array.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {

        String input = BinaryStdIn.readString();
        BinaryStdIn.close();
        CircularSuffixArray csa = new CircularSuffixArray(input);
        int n = input.length();
        int first = -1;

        // find position of original suffix
        for (int i = 0; i < n; i++) {
            if (csa.index(i) == 0) {
                first = i;
                break;
            }
        }
        BinaryStdOut.write(first);  // 4 byte representation

        // collect the last column
        for (int i = 0; i < n; i++) {
            int index = csa.index(i);
            if (index == 0) BinaryStdOut.write(input.charAt(n - 1), 8);
            else BinaryStdOut.write(input.charAt(index - 1), 8);
        }

        BinaryStdOut.close();
    }


    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        int EXTENDED_ASCII = 256;

        // read input
        int first = BinaryStdIn.readInt();
        String input = BinaryStdIn.readString();
        BinaryStdIn.close();
        int n = input.length();
        int[] t = new int[n];
        for (int i = 0; i < n; i++) t[i] = input.charAt(i);



        /* @citation Adapted from lecture: https://www.cs.princeton.edu/courses/
        archive/fall20/cos226/lectures/51StringSorts.pdf
     */
        // find the next[] array
        int[] count = new int[EXTENDED_ASCII + 1]; // alphabet size + 1
        int[] next = new int[n];    // next[] array
        char[] firstCol = new char[n];

        for (int i = 0; i < n; i++) {
            count[t[i] + 1]++;
        }

        for (int i = 0; i < EXTENDED_ASCII; i++) {
            count[i + 1] += count[i];
        }


        for (int i = 0; i < n; i++) {
            // KEY MODIFICATION of key-indexed counting
            next[count[t[i]]] = i;
            firstCol[count[t[i]]++] = (char) t[i];
        }


        // reconstruct string
        for (int i = 0; i < n; i++) {
            BinaryStdOut.write(firstCol[first]);
            first = next[first];
        }

        BinaryStdOut.close();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-")) transform();
        else if (args[0].equals("+")) inverseTransform();
        else throw new IllegalArgumentException("not - or +");

    }
}
