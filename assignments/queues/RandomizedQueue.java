/* *****************************************************************************
 *  Name:    Jiatong Yu
 *  NetID:   NA
 *  Precept: P00
 *
 *  Description:  I used resizing array for RandomizedQueue. To ensure
 * randomness, the remove function picks a random index between(0,N-1),
 * print out the item, and then replace the item with the last item in the array
 *
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // count number of items in queue
    private int N;

    // resizing array
    private Item[] queue;

    // constructor, should take constant amortised time
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
        N = 0;
    }

    // check if empty
    public boolean isEmpty() {
        return N == 0;
    }

    // return number of items in array
    public int size() {
        return N;
    }

    // resize array
    private void resize(int size) {
        Item[] temp = (Item[]) new Object[size];
        for (int i = 0; i < N; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }

    // add item to queue at the end of array
    public void enqueue(Item item) {
        // corner case
        if (item == null) throw new IllegalArgumentException();
        // double size of array if full
        if (N == queue.length) {
            resize(2 * queue.length);
        }
        queue[N] = item;
        N++;
    }

    // remove and return a random item
    public Item dequeue() {
        // corner case
        if (isEmpty()) throw new NoSuchElementException();

        // select item from a random index.
        int index = StdRandom.uniform(0, N);
        Item item = queue[index];

        // remove the item by replacing it with the end-of-array item
        queue[index] = queue[N - 1];
        queue[N - 1] = null; // loitering
        N--;

        // resize array if size less than a quarter of of array
        if (N == queue.length / 4 && N != 0) {
            resize(queue.length / 2);
        }

        return item;
    }

    // return a random item (not removing it)
    public Item sample() {
        // corner case
        if (N == 0) throw new NoSuchElementException();

        int index = StdRandom.uniform(N);
        Item samp = queue[index];
        return samp;
    }

    // Iterator should take O(n) time
    public Iterator<Item> iterator() {
        return new RandIterator();
    }

    // constructing the iterator should take O(n) time
    private class RandIterator implements Iterator<Item> {

        // construct the iterator by creating a shuffled array
        private final int[] shuffle = new int[N];
        // point to the current item being iterated
        private int pointer = 0;

        // constructor
        public RandIterator() {

            // initialize the shuffle array
            for (int i = N - 1; i >= 0; i--) {
                shuffle[i] = i;
            }

            // shuffle array using StdRandom
            StdRandom.shuffle(shuffle);

        }


        public boolean hasNext() {
            return pointer <= N - 1;
        }

        // to access the element in shuffled array
        private int access(int i) {
            return shuffle[i];
        }


        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            int i = access(pointer);
            Item item = queue[i];
            pointer++;
            return item;
        }
    }

    public static void main(String[] args) {

    }


}
