/* *****************************************************************************
 *  Name:    Jiatong Yu
 *  NetID:   jiatongy
 *  Precept: P04
 *
 *  Partner Name:    NA
 *  Partner NetID:   NA
 *  Partner Precept: NA
 *
 *  Description: I maintained a red-black BST<int, char> as well as a sequence[]
 * array. The BST key corresponds to the current position of char, while
 * the value is the char itself. the sequence[] index are the characters,
 * while the values are the current position.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    // apply move-to-front encoding, reading from stdin and writing to stdout
    public static void encode() {
        int[] charIndex = buildAlphabet(); // index -> char; value -> position
        int[] positionIndex = buildAlphabet(); // index --> pos; value --> char
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            BinaryStdOut.write(charIndex[(int) c], 8);
            moveToFront(charIndex, positionIndex, c);

        }
        BinaryStdOut.close();
        BinaryStdIn.close();
    }

    // helper method: move to front of a int[] array
    private static void moveToFront(int[] charIndex, int[] posIndex, int c) {
        int pos = charIndex[c];
        charIndex[c] = 0;

        for (int i = pos; i > 0; i--) {
            char chr = (char) posIndex[i - 1];
            posIndex[i] = chr;
            charIndex[chr] = i;
        }
        posIndex[0] = c;
    }

    // apply move-to-front decoding, reading from stdin and writing to stdout
    public static void decode() {
        int[] charIndex = buildAlphabet();
        int[] positionIndex = buildAlphabet();
        while (!BinaryStdIn.isEmpty()) {
            int pos = BinaryStdIn.readChar();
            BinaryStdOut.write(positionIndex[pos], 8);
            moveToFront(charIndex, positionIndex, positionIndex[pos]);
        }
        BinaryStdIn.close();
        BinaryStdOut.close();
    }


    // helper method: initialize the alphabet sequence
    private static int[] buildAlphabet() {
        int EXTENDED_ASCII = 256;
        int[] sequence = new int[EXTENDED_ASCII];
        for (int i = 0; i < EXTENDED_ASCII; i++) {
            sequence[i] = i;
        }
        return sequence;
    }

    public static void main(String[] args) {
        if (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("not - or +");

    }


}
