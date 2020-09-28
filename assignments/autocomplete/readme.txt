/* *****************************************************************************
 *  Name:  Jiatong Yu
 *  NetID:    jiatongy
 *  Precept:  P04
 *
 *  Partner Name:   I didn't have a partner
 *  Partner NetID:
 *  Partner Precept:
 *
 *  Hours to complete assignment (optional):
 *
 **************************************************************************** */

Programming Assignment 3: Autocomplete


/* *****************************************************************************
 *  Describe how your firstIndexOf() method in BinarySearchDeluxe.java
 *  finds the first index of a key that is equal to the search key.
 **************************************************************************** */
I modified the original version of BinarySearch:
the original one stops when found compareTo(key, a[mid]) == 0.
But since we want the very first item, the search process shouldn't stop there
so I modified it so that it continues to the very end (when hi <= lo).
each time when it finds a match (compare == 0), it goes to the smaller half
of the array to keep searching, while keeping record of the last match.
when process finished it return the most recent match.

/* *****************************************************************************
 *  Identify which sorting algorithm (if any) that your program uses in the
 *  Autocomplete constructor and instance methods. Choose from the following
 *  options:
 *
 *    none, selection sort, insertion sort, mergesort, quicksort, heapsort
 *
 *  If you are using an optimized implementation, such as Arrays.sort(),
 *  select the principal algorithm.
 **************************************************************************** */

Autocomplete() : mergesort (Timsort)

allMatches() : mergesort

numberOfMatches() : I didn't use sorting for this method.

/* *****************************************************************************
 *  How many compares (in the worst case) does each of the operations in the
 *  Autocomplete data type make, as a function of both the number of terms n
 *  and the number of matching terms m? Use Big Theta notation to simplify
 *  your answers.
 *
 *  Recall that with Big Theta notation, you should discard both the
 *  leading coefficients and lower-order terms, e.g., Theta(m^2 + m log n).
 **************************************************************************** */

Autocomplete():     Theta(n log n)

allMatches():       Theta(m log m + log n)

numberOfMatches():  Theta(log n)




/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */


/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *
 *  Also include any resources (including the web) that you may
 *  may have used in creating your design.
 **************************************************************************** */
resources:
I used website assignment page, the checklist.
I read the java comparator and Arrays.copyOf() documentation.
I discussed with my P04 classmate Christian Venturella for BinarySearchDeluxe
ideas before implementation.
I asked question on Ed about BinarySearch and a pmd warning.

/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */
I had a difficulty running the BinarySearchDeluxe, as I first designed
to find a match through the original BinarySearch algorithms and
go forward/backward.
I realized that it doesn't match the required performance,
so I first tried to pipeline the original binary search with a second
searching algorithm, but failed on that.
So I discussed this problem with Christian Venturella and I learned that
modifying the original binary search into the two methods might be a way out.

/* *****************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **************************************************************************** */

/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */

