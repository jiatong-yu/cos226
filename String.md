### Key Indexed Counting  
runtime | space | stability  
-- | -- | --  
n + R | n + R | stable  
* n is input size  
* R is aphabet size  

```java
int n = a.length;
int[] count = new int[R+1];
for (int i = 0; i < n; i++)
 count[a[i]+1]++;
for (int r = 0; r < R; r++)
 count[r+1] += count[r];
for (int i = 0; i < n; i++)
 aux[count[a[i]]++] = a[i];
for (int i = 0; i < n; i++)
 a[i] = aux[i];
 ```
 
 ### String Sort  
 `| LSD | MSD | 3-way Radix Sort  
 -- | -- | --  | --  
 __comment__ | right-to-left index sorting | partition array into __R pieces__, recursively sort, need special charAt() that return -1, # of examination can be __sublinear__  | MSD use too much overhead and space --> 3-way partition 
 __runtime__ | 2wn | Worst (all equal string) 2wn, random n logR n| worst 1.39 wn log 2 R, random 1.39 n log2 R  
 __space__ | n + R|  n + DR | log n + w  
 __stability__ | Stable | Stable | Not Stable | 
 
 ### Tries  
 `| R-way Trie | Ternary Search Trie (TST)  
 --|--|--
 __comment__ | each node has children[R] | each node three children, __斜线标红不连接char__  
 __search(hit)__ | L | L + log n  
 __search(miss)__ | log r N | log N  
 __insert__ | R + L | L + log N  
 
 
 
