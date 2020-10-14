/* *****************************************************************************
 *  Name:    Jiatong Yu
 *  NetID:   na
 *  Precept: P04
 *
 *
 *  Description: fall 2018 midterm design question.
 *
 * adding / deleting integers to two unordered lists, check
 * whether any integers appears in both list
 *
 * performance requirement: each operation takes constant time
 *
 * ADT design: Hash Table can assure o(0) for search and insertion
 * need to keep count of number of times an element appears in Hash Table,
 * (put multiple times corresponds only to change in value in Symbol Table,
 * which need to be manually modified to simulate an array).
 * Also need to keep track of number of duplicates.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.SeparateChainingHashST;

public class Duo {
    // keep track of number of duplicates
    private int duplicate;
    private SeparateChainingHashST<Integer, Integer> list1;
    private SeparateChainingHashST<Integer, Integer> list2;

    public Duo() {
        duplicate = 0;
        list1 = new SeparateChainingHashST<>();
        list2 = new SeparateChainingHashST<>();
    }

    // add integer to first list
    public void addToList1(int x) {
        int val;
        if (list1.contains(x)) val = list1.get(x) + 1;
        else val = 1;
        if (list2.contains(x)) duplicate++;
        list1.put(x, val);

    }

    // add integer to second list
    public void addToList2(int x) {
        int val;
        if (list2.contains(x)) val = list2.get(x) + 1;
        else val = 1;
        if (list1.contains(x)) duplicate++;
        list2.put(x, val);


    }

    // del integer form first list
    public void deleteFromList1(int x) {
        if (list2.contains(x)) duplicate--;
        if (list1.get(x) == 1) list1.delete(x);
        else list1.put(x, list1.get(x) - 1);
    }

    // del integer from second list
    public void deleteFromList2(int x) {
        if (list1.contains(x)) duplicate--;
        if (list2.get(x) == 1) list2.delete(x);
        else list2.put(x, list2.get(x) - 1);
    }

    // does any integer appears in both list?
    public boolean hasDuplicate() {
        return !(duplicate == 0);
    }

    public static void main(String[] args) {

    }
}
