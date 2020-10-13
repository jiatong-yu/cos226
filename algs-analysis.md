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
  
**arrays**  
*array overhead = 24*  
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
  
