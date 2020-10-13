/* *****************************************************************************
 *  Name:    Jiatong Yu
 *  NetID:   NA
 *  Precept: P00
 *
 *  Description:  seperate chaining implementation of hash table
 *
 **************************************************************************** */

public class SepChainHashST<Key extends Comparable<Key>, Value> {

    private int N;
    private int M;
    private SequentialSearchST<Key, Value>[] table;

    private class SequentialSearchST<Key, Value> {
        private Node first;

        private class Node {
            private Key key;
            private Value val;
            private Node next;

            public Node(Key k, Value v, Node n) {
                this.key = k;
                this.val = v;
                this.next = n;
            }
        }

        // put into ST
        private void put(Key k, Value v) {
            for (Node x = first; x != null; x = x.next) {
                if (k.equals(x.key)) {
                    x.val = v;
                    return;
                }
            }
            first = new Node(k, v, first);
        }

        // search for key, return associated value
        private Value get(Key k) {
            for (Node x = first; x != null; x = x.next) {
                if (k.equals(x.key)) return x.val;
            }
            return null;
        }
    }

    public SepChainHashST() {
        this(997);
    }

    public SepChainHashST(int M) {
        this.M = M;
        table = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) table[i] = new SequentialSearchST<>();
    }

    private int hash(Key x) {
        return (x.hashCode() & 0x7fffffff) % M;
    }

    public void put(Key key, Value val) {
        table[hash(key)].put(key, val);
    }

    public Value get(Key key) {
        return table[hash(key)].get(key);
    }


    public static void main(String[] args) {

    }
}
