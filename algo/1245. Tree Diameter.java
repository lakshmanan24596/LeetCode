/*
Given an undirected tree, return its diameter: the number of edges in a longest path in that tree.
The tree is given as an array of edges where edges[i] = [u, v] is a bidirectional edge between nodes u and v.  
Each node has labels in the set {0, 1, ..., edges.length}.

Example 1:
Input: edges = [[0,1],[0,2]]
Output: 2
Explanation: A longest path of the tree is the path 1 - 0 - 2.

Example 2:
Input: edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
Output: 4
Explanation: A longest path of the tree is the path 3 - 2 - 1 - 4 - 5.

Constraints:
0 <= edges.length < 10^4
edges[i][0] != edges[i][1]
0 <= edges[i][j] <= edges.length
The given edges form an undirected tree.
*/



/*
    1) Logic: post order traversal
        Same as finding diameter in binary tree
        time: n, space: n

    2) two BFS
        first bfs, start from any node --> from this, we can find one extreme peripheral node
        second bfs, start from output of prev bfs --> it gives another extreme peripheral node
        note: bfs will return extreme peripharal node and also distance from start node.
        time: 2n, space: n  
*/

class Solution {
    List<Integer>[] adjList;
    int n;
    int diameter = 0;
    boolean[] visited;
    
    public int treeDiameter(int[][] edges) {
        this.n = edges.length + 1;
        this.adjList = createAdjList(edges, n);
        this.visited = new boolean[n];
        treeDiameterUtil(0);                        // start from any node, because given graph is a connected graph
        return diameter - 1;
    }
    
    public List<Integer>[] createAdjList(int[][] edges, int n) {
        List<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList[u].add(v);
            adjList[v].add(u);
        }
        return adjList;
    }
    
    public int treeDiameterUtil(int curr) {
        visited[curr] = true;
        int firstMax = 0, secondMax = 0, currOutput;
        
        for (int neigh : adjList[curr]) {
            if (!visited[neigh]) {                  // instead of visited array, we can also use parent variable
                currOutput = treeDiameterUtil(neigh);
                if (currOutput > firstMax) {
                    secondMax = firstMax;
                    firstMax = currOutput;
                } else if (currOutput > secondMax) {
                    secondMax = currOutput;
                }
            }
        }
        diameter = Math.max(diameter, (firstMax + secondMax + 1));      // main logic
        return firstMax + 1;                                            // main logic
    }
}