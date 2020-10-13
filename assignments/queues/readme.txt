/* *****************************************************************************
 *  Name: Jiatong Yu
 *  NetID: NA
 *  Precept: P00
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Hours to complete assignment (optional):
 *
 **************************************************************************** */

Programming Assignment 2: Deques and Randomized Queues


/* *****************************************************************************
 *  Explain briefly how you implemented the randomized queue and deque.
 *  Which data structure did you choose (array, linked list, etc.)
 *  and why?
 **************************************************************************** */
For deque, I chose doubly linked list. Since the required runtime for deque is
constant, amortized-runtime resizing array is not an option. And since we want
to add/drop on both ends, we need two pointers and doubly linked list.

For randomized queue, I chose resizing array over deque, because the runtime
requirement for amortized-constant time, but using linked list requires going
through a random_index amount of nodes for each operation, which does not
satisfy the requirement. To ensure randomness, after picking each remove index,
I will replace the removed item with the last-in-row item.

/* *****************************************************************************
 *  How much memory (in bytes) do your data types use to store n items
 *  in the worst case? Use the 64-bit memory cost model from Section
 *  1.4 of the textbook and use tilde notation to simplify your answer.
 *  Briefly justify your answers and show your work.
 *
 *  Do not include the memory for the items themselves (as this
 *  memory is allocated by the client and depends on the item type)
 *  or for any iterators, but do include the memory for the references
 *  to the items (in the underlying array or linked list).
 **************************************************************************** */

Randomized Queue:   ~  40n bytes

Deque:              ~  48n  bytes



