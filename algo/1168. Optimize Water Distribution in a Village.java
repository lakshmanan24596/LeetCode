/*
There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.

For each house i, we can either build a well inside it directly with cost wells[i - 1] (note the -1 due to 0-indexing), or pipe in water from another well to it. 
The costs to lay pipes between houses are given by the array pipes where each pipes[j] = [house1j, house2j, costj] represents the cost to connect house1j and house2j together using a pipe. 
Connections are bidirectional, and there could be multiple valid connections between the same two houses with different costs.

Return the minimum total cost to supply water to all houses.

Example 1:
Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
Output: 3
Explanation: The image shows the costs of connecting houses using pipes.
The best strategy is to build a well in the first house with cost 1 and connect the other houses to it with cost 2 so the total cost is 3.

Example 2:
Input: n = 2, wells = [1,1], pipes = [[1,2,1],[1,2,2]]
Output: 2
Explanation: We can supply water with cost two using one of the three options:
Option 1:
  - Build a well inside house 1 with cost 1.
  - Build a well inside house 2 with cost 1.
The total cost will be 2.
Option 2:
  - Build a well inside house 1 with cost 1.
  - Connect house 2 with house 1 with cost 1.
The total cost will be 2.
Option 3:
  - Build a well inside house 2 with cost 1.
  - Connect house 1 with house 2 with cost 1.
The total cost will be 2.
Note that we can connect houses 1 and 2 with cost 1 or with cost 2 but we will always choose the cheapest option. 


Constraints:
2 <= n <= 104
wells.length == n
0 <= wells[i] <= 105
1 <= pipes.length <= 104
pipes[j].length == 3
1 <= house1j, house2j <= n
0 <= costj <= 105
house1j != house2j
*/



/*
    logic: assume well as node 0
           find MST (Prims) in the graph
           refer screenshot image in finder
           
    time: (V+E)*logE
    space: V+E
    
    where, V = n + 1
           E = wells + pipes
*/

class Solution {
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        n += 1;                 // well is also a node, so n+1
        
        // create graph
        Graph graph = new Graph(n);     
        for (int i = 0; i < wells.length; i++) {
            graph.addEdge(0, i + 1, wells[i]);
        }
        for (int[] pipe : pipes) {
            graph.addEdge(pipe[0], pipe[1], pipe[2]);
        }
        
        // MST (Prims)
        PriorityQueue<Element> pQueue = new PriorityQueue<Element>((a, b) -> (a.cost - b.cost));
        pQueue.add(new Element(0, 0));
        
        boolean[] visited = new boolean[n + 1];
        
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 1;
        
        Element curr;
        List<Element> neighbours;
        int minCost = 0;
        
        while(!pQueue.isEmpty()) {
            curr = pQueue.remove();
            if (visited[curr.node]) {
                continue;
            }
            visited[curr.node] = true;
            minCost += curr.cost;                                               // main logic
            neighbours = graph.adjList[curr.node];
            
            for (Element neigh : neighbours) {
                if (!visited[neigh.node] && neigh.cost < dist[neigh.node]) {    // main logic
                    pQueue.add(new Element(neigh.node, neigh.cost));
                    dist[neigh.node] = neigh.cost;
                }
            }
        }
        return minCost;
    }
}

class Element {
    int node;
    int cost;
    Element(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
}

class Graph {
    int n;
    List<Element>[] adjList;
    
    Graph(int n) {
        this.n = n;
        this.adjList = new ArrayList[n];
        
        for (int i = 0; i < n; i++) {
            this.adjList[i] = new ArrayList<Element>();
        }
    }
    
    public void addEdge(int u, int v, int cost) {
        adjList[u].add(new Element(v, cost));
        adjList[v].add(new Element(u, cost));
    }
}