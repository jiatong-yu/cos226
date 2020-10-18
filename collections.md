# Collections  
  
## Priority Queue
  
  **Binary Heap**  
  heap-order (1) key stored in *array*, (2) parent key is larger than its two children keys (3) last row filled from left to right  
  heap array order = level order  
  **crux**: swim(x) takes at most 1 + log N compare   
            sink(x) takes at most log2 N compare  
  insertion: omega(log N + 1)  
  deletion: omega( 2 * log N)
  
  

# Symbol Table  
  
  *key-value pair abstraction*  
  insert: a value with a given key  
  search: given key, search value  
  delete: delete value and key  
  
(guarantee, average)  
algs | search | insertion | deletion | ordered operation? 
--- | --- | --- | --- | ---  
sequential search | (n, n) | (n, n) | (n, n) | 0  
binary search | (log n, log n) | (n, n) | (n, n) | 1  
standard BST | (n, log n) | (n, log n) | (n, sqrt(n) | 1  
**red-black BST** | (log n, log n) | (log n, log n) | (log n, log n) | 1  
**hashing** | (n, 1) | (n, 1) | (n, 1) | 0  



## LL Red-Black BST  
**2-3 tree**: (1)Symmetric order (2) perfect balance (*every path **from null** to root has same length*)  
*note: perfect balance could be tricky: a 3node must link to three children or no children*  
min height: log 3 n  
max height: log 2 n  
search: [log n, log n]  
insert: [log n, log n]  
delete: [log n, log n]
  
  **left-leaning red black tree**  
  red link documented by child node  
  **requirements:** (1) no node has two red link connected to it (2) only left red link (3) perfect balance for black links  
  *level order traversal uniquely reconstruct LLRBT*  
  
## Hash Table  
**hash fuction**  
*load balancing*: when n >> m, each bin (m) has around n / m balls  
*birthday problem*: collision inevitable  
  ``` java
  private int hash(Key x)  
  {return (x.hashCode() & 0x7fffffff) % M; } // M is the number of bins  
  ```

**seperate chaining**  
array of m linked lists  
expected number of probes: **O(n/m)**  
sweetspot: m ~ 1/4 n, constant time O(1)  

**linear probing**  
parallel, resizing arrays  
disadvantage: big clusters easily forming  
expected number of probes: **1/2 (1 + [1 / (1 - k)])** where n = k*m   
sweetspot: k = 1/2 (m = 2n)  

  







