## Soring
**proposition: campare-based sorting algs has Ω(NlogN) compares**  
  
  
**Selection Sort** \   
independent of given array order.  
~ N^2 / 2 compares  
~ N exch  
Stable? No (*swapping kth element with smallest*)  
In-Place? Yes  

**Insertion Sort** \    
dependent of given array order  
~ Compares: *avg* N^2 / 4; *worst* N^2 / 2  
~ Exch: *avg* N^2 / 4; *worst* N^2 / 2  
Stable? Yes  
In-Place? Yes  

**Mergesort (top-down)** \  
recursively divide and sort subarrays, *merge* for order  
Compares(n) <= Compares(n/2) + Compares(n/2) + n *(draw a tree)*  
~ Compares: n log n
~ Array Access: 6n log n *(2N for copy array, 2N for move back, <= 2N for compare)*  
Stable? Yes  
In-Place? No  

**Quicksort** 
