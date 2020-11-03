## Graph Search  
type | procedure | implementation 
-- | -- | -- | 
DFS preorder | in order of calls to dfs(G, v) | stack  
DFS postorder (topo) | in order of returns from dfs(G, v) | stack  
BFS | in increasing order of distance from s | queue

### Depth First Search (DFS)  
objective: find all vertices **reachable** from s in Digraph  
procedure: (1) mark v as visited (2) recursively visit all unmarked w adjacent to v  
runtime: omega(E + V)  

### Breadth First Search (BFS)  
objective: find directed path from s to each vertice in **shortest distance**  
prodedure: (1) v = dequeue (2) for unmarked w: mark w, enqueue w  

### Topological Sort  
objective: Redraw DAG so all edges point upwards (from prerequestie to end)  
prodecdure: **DFS postorder**, maintain a FIFO queue  

**CycleDetection**  
objective: detect cycle in Digraph  
procedure: activate when called to dfs(G,v), de-activate when returned. if *marked AND active** cycle found  

## Minimum Spannning Tree (MST) 
tree that connect all vertices in **undirected graph** with minimal total weight  

  
  **cut property**  
  for any cut, the min-weight crossing edge is in MST  
  
  ### Kruskal's algorithm  
    implementation: 
    (0) iterate G.V() times:
    (1) sort in ascending weight order, 
    (2) add edge to MST unless create cycle  
  runtime: **omega(E log E)**  
    
  **crust: detect if adding v-w create cycle**  
    (0) use **Union Find** data structure  
    (1) maintain a UF for each connected area in graph  
    (2) if w-v in same set --> create cycle  
    (3) if w-v not in same set --> add to MST, union two sets 
   
  ### Prim's algorithm  
    implementation: 
    (0) start with v, 
    (1) repeat until V-1 edges: 
    (2) add min-weight crossing edge between T and unmarked area  
  runtime:  
  lazyPrim: **omega(E log E) with omega(E) extra space**  
  EagerPrim: **omega(E log V) with omega(V) extra space**  
    
  **crust: find min-weight crossing edge between v and unmarked area**  
    (0) use **MinPQ** data structure  
    (1) delete-min v-w edge  
    (2) if v && w are marked, disregard
    (3) else add v-w to tree, add all adj(w) to MinPQ  
      
  eager: for every v, only min-edge to v is in MST  
      
  ## Shortest Path  
  find shortest path from s to t in Digraph  
  
  ###S hortest Path Tree (SPT)
  objective: find shortest paths from s to **every other vertice**  
    
  **edge relaxation v->w**  
    
    distTo(v): shortest known distance from source to v  
    distTo(w): shortest known distance from source to w  
    edgeTo(w): last edge to w in shortest known path  
    if distTo(v) + v->w.weight < distTo(w): update distTo(w) and edgeTo(w)  
    
  ### Bellman Ford algorithm  
  **runtime: omega(EV)** for best and worst case  
    
    initialize distTo(v) = Double.INIFINITY  
    repeat V-1 times:  
      repeat V-1 times:  
        relax all edge of adj(i)  
        
  improvement: if distTo(v) is not changed in pass i, no need to relax any edge incident from v in pass i+1  
  
  ### Dijkstra's algorithm  
  **runtime: omega(E log V)**  
    
    use IndexMinPQ data type 
    while(!pq.isEmpty()):  
      pick non-tree v with min distTo(source)  
      add v to tree  
      relax all edge adj(v)
    
  
    
    
    
  
  
    
  
  
  
