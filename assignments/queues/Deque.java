/* *****************************************************************************
 *  Name:    Jiatong Yu
 *  NetID:   NA
 *  Precept: P00
 *
 *  Description:  I'm using doubly-linked list for this assignment, since
 * singly-linked list doesn't support popping the last node, yet resizing array
 *  cannot satisfy the constant run time requirement. also, i keep track of
 * the beginning and end of the linked list via two nodes (first/last)
 *
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    // pointer of the first node
    private Node first;
    // pointer of the last node
    private Node last;
    // count the number of items
    private int N;

    private class Node {
        // item of node
        private Item item;
        // next of node
        private Node next;
        // previous of node (for doubly linked list)
        private Node prev;
    }

    // construct empty queue through doubly linked list
    public Deque() {
        first = null;
        last = null;
        N = 0;
    }

    // return if no item is in list
    public boolean isEmpty() {
        return N == 0;
    }

    // return number of items
    public int size() {
        return N;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        // corner case: no node in deque
        if (isEmpty()) {
            first = new Node();
            first.item = item;
            last = first;
        }
        else {
            Node oldF = first;
            first = new Node();
            first.item = item;
            first.next = oldF;
            oldF.prev = first;
        }
        N++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        // corner case: no node in deque
        if (isEmpty()) {
            last = new Node();
            last.item = item;
            first = last;
        }
        else {
            Node oldL = last;
            last = new Node();
            last.item = item;
            last.prev = oldL;
            oldL.next = last;
        }
        N++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();

        // removing the first node, prev/next are null
        if (N == 1) {
            Item item = first.item;
            first = null;
            last = null;
            N--;
            return item;
        }

        else {
            Item item = first.item;
            first = first.next;
            first.prev = null;
            N--;
            return item;

        }
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (N == 0) throw new NoSuchElementException();

        // removing the last node, prev/next are null
        if (N == 1) {
            Item item = last.item;
            last = null;
            first = null;
            N--;
            return item;
        }

        else {
            Item item = last.item;
            last = last.prev;
            last.next = null;
            N--;
            return item;
        }

    }

    // Iterator iterator() is required for the Iterable interface
    public Iterator<Item> iterator() {
        return new MyIterator();
    }

    // Iterator interface
    private class MyIterator implements Iterator<Item> {

        // starting from the first node
        private Node current = first;

        public boolean hasNext() {
            return (current != null);
        }

        public Item next() {
            if (current == null) throw new NoSuchElementException();
            else {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }

    }


    public static void main(String[] args) {

    }

}
