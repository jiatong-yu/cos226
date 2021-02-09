/* *****************************************************************************
 *  Name:     Jiatong Yu
 *  NetID:    jiatongy
 *  Precept:  P04
 *
 *  Partner Name:     NA
 *  Partner NetID:    NA
 *  Partner Precept:  NA
 *
 *  Hours to complete assignment (optional):
 *
 **************************************************************************** */

Programming Assignment 6: WordNet


/* *****************************************************************************
 *  Describe concisely the data structure(s) you used to store the
 *  information in synsets.txt. Why did you make this choice?
 **************************************************************************** */
I used HashMap under the uniform hashing assumption. I choose this data
structure because, under the uniform hashing assumption, the HashMap takes
constant time to retrieve values associated with keys.

I used a HashMap<Integer, String> to store the id (key) and synset (value) pairs
read from input.

I used a HashMap<String, Bag<Integer>> to map words with all the synsets id that
contains it.

I used Bag<String> to store the synset id because the order of input does not
matter in this case.


/* *****************************************************************************
 *  Describe concisely the data structure(s) you used to store the
 *  information in hypernyms.txt. Why did you make this choice?
 **************************************************************************** */
I did not store the entire hypernyms document. As the program read hypernyms
from the StdIn, it just process the information by adding links to the Digraph.


/* *****************************************************************************
 *  Describe concisely the algorithm you use in the constructor of
 *  ShortestCommonAncestor to check if the digraph is a rooted DAG.
 *  What is the order of growth of the worst-case running times of
 *  your algorithm? Express your answer as a function of the
 *  number of vertices V and the number of edges E in the digraph.
 *  (Do not use other parameters.) Use Big Theta notation to simplify
 *  your answer.
 **************************************************************************** */

Description: There are two steps to check if a digraph is a rooted DAG.

First, I check if the digraph is acylic by using the data structure
DirectedCycle.

The DirectedCycle class use depth-first search and the active[] array
to check if the given digraph is cycled. This takes theta(E + V).

Then I run a private function to detect if the digraph is rooted.
A rooted digraph has and has exactly one vertex with 0 out-degree. Therefore
I go through the adjacency list and document the number of vertices with 0
out-degree. This takes theta(V).

The dominating worst-case runtime is thus theta(E + V).


Order of growth of running time: theta(E + V)


/* *****************************************************************************
 *  Describe concisely your algorithm to compute the shortest common ancestor
 *  in ShortestCommonAncestor. For each method, give the order of growth of
 *  the best- and worst-case running times. Express your answers as functions
 *  of the number of vertices V and the number of edges E in the digraph.
 *  (Do not use other parameters.) Use Big Theta notation to simplify your
 *  answers.
 *
 *  If you use hashing, assume the uniform hashing assumption so that put()
 *  and get() take constant time per operation.
 *
 *  Be careful! If you use a BreadthFirstDirectedPaths object, don't forget
 *  to count the time needed to initialize the marked[], edgeTo[], and
 *  distTo[] arrays.
 **************************************************************************** */

Description:
for given (vertex v, vertex w), I run two re-implemented breadth-first search.

for v and w, instead of initializing the three vertex-indexed arrays, I use
a HashMap<Integer, Integer> to store the marked vertex and its distance. When
I go over the breadth-first search for w, I also compare each node pushed by
the queue to the HashMap of v, to see if it's a common ancestor, and if so, check
if it's the champion so far.





                                 running time
method                  best case            worst case
--------------------------------------------------------
length()                theta(1)            theta(E+V)

ancestor()              theta(1)            theta(E+V)

lengthSubset()          theta(1)            theta(E+V)

ancestorSubset()        theta(1)            theta(E+V)



/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */


/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **************************************************************************** */
no help from others.



/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */
I didn't read the assignment page closely enough, and I didn't realize until
two TigerFile checks that each synsets have many words separated by spaces.
The debugging process was quite torturing.


/* *****************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **************************************************************************** */
No partner


/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
