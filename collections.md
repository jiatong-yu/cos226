# Collections  
  
## Priority Queue
  
  **Binary Heap**  
  heap-order (1) key stored in *array*, (2) parent key is larger than its two children keys (3) last row filled from left to right  
  heap array order = level order  
  insertion: omega(log N + 1)  
  deletion: omega( 2 * log N)
  
  

# Symbol Table  

## LL Red-Black BST  
**2-3 tree**: (1)Symmetric order (2) perfect balance (*every path has same length*)  
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
  
