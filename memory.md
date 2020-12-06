## Memory Analysis  
  
**primative datatype**  
datatype | memory usage  
-------- | ---------  
boolean | 1  
byte | 1  
char | 2  
int | 4  
float | 4  
double | 8  
long | 8  
  
**Arrays**  
*Array of Objects:*  
(1) find object size  
(2) + 8N for references inside array  
(3) overhead = 24  

*Common mem for Object[]:*  
- created, not initialzied: 8n + 32  
- created, initialized: each object take x, (8+x) n + 32  
  
  
datatype | memory usage  
-------- | --------  
bool[] | 1n + 24 (array overhead)  
int[] | 4n + 24  
double[] | 8n + 24 
bool[][] | ~ 1 n^2  
int[][] | ~ 4 N^2  
double[][] | ~ 8 n^2  
  
**objects**  
  
*object overhead = 16*  
*object reference = 8*  
*padding: round up to multiples of 8*  
  
  examples: (https://us.edstem.org/courses/638/lessons/5007/slides/23114)  
  
**Notes**  
if there is nested class inside:  
private class | private static class  
------- | --------  
object overhead: 16 | object overhead: 16  
need to keep track | static, no need to keep track  
tracking: 8 | tracking: 0  

## Notations  
notation | provides | example | represents  
-- | -- | -- | --  
tilda (~) | leading term | ~ 2 n^2 | 2 n^2 + n + 1  
theta (Θ) | order of growth | Θ(n^2) | 2 n^2 + n + 1  
big O (O) | upper bound | O(n^2) | **Θ(n^2) or smaller**: n^2, n, log n ...  
omega (Ω) | lower bound | Ω(n^2) | **Θ(n^2) or larger**: n^2, n^3, n^2logn ...  

