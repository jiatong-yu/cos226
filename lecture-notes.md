## Soring
**proposition: campare-based sorting algs has Ω(NlogN) compares** 
def: A sorting algorithm is *in-place* if it uses ≤ c log n extra space  
[best, average, worst]  
  
**Selection Sort**     
independent of given array order.  
~ Compares: [1/2 n^2, 1/2 n^2, 1/2 n^2]  
~ Exch: n
Stable? No (*swapping kth element with smallest*)  
In-Place? Yes  

**Insertion Sort**    
dependent of given array order  
~ Compares: [n, 1/4n^2, 1/2n^2]  
~ Exch: *avg* N^2 / 4; *worst* N^2 / 2  
Stable? Yes  
In-Place? Yes  

**Mergesort (top-down)**   
recursively divide and sort subarrays, *merge* for order  
**asymptotically optimal** *(worst case & any array ~ n log n)*  
Compares(n) <= Compares(n/2) + Compares(n/2) + n *(draw a tree)*  
~ Compares: [1/2n log n,n log n, n log n]  
~ Array Access: 6n log n *(2N for copy array, 2N for move back, <= 2N for compare)*  
Stable? Yes  
In-Place? No  *o(n) extra space*  
**Timesort**  
use natural subarray sets (with pre-existing order) to merge  
~ Compares: [n, n log n, n log n]  

**Quicksort (2-way partitioning)**   
shuffle, and recursively {use a[0] as base, divide into two subarray through returned value of partition}  
*good for many **duplicate keys** (3-way partitioning) *  
~ Compares: [n log n, 2n ln n, n^2 / 2]  
~ Exch: *avg* 1/3n ln n  
Stable? No 
In-Place? Yes  
**quick select**  
given: for every partition, a[j] (base) is in the right index    
query: find the kth value (given sorted index, find item)    
solution: after every partition, prune out the impossible half  
performance: state-of-art o(n) *different from BinarySearch in that 1.BS assumes ordered array 2.BS given key find index*  
**3-way partitioning**    
~Compares: [n, n log n, n log n] *for duplicated keys array*  

**Heapsort**  
method: construct maxPQ in bottom-up order  
~ construction: 2n compare, n exchange *starting with n/2, n--*  
~ sorting: [(n log n), 2n log n , 2n log n]  
Stable? No  
In-Place? Yes  
only in-place algs with omega(n log n)  







