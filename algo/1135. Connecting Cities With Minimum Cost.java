/*
There are N cities numbered from 1 to N.
You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.  
(A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)

Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together.  
The cost is the sum of the connection costs used. If the task is impossible, return -1.


Example 1:
Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
Output: 6
Explanation: 
Choosing any 2 edges will connect all cities so we choose the minimum 2.

Example 2:
Input: N = 4, connections = [[1,2,3],[3,4,4]]
Output: -1
Explanation: 
There is no way to connect all cities even if all edges are used.

Note:
1 <= N <= 10000
1 <= connections.length <= 10000
1 <= connections[i][0], connections[i][1] <= N
0 <= connections[i][2] <= 10^5
connections[i][0] != connections[i][1]
*/



/*
    Logic: MST, DSU, Kruskal algo
    time: no of connections * (amortized time of DSU, which is 1)
    space: N
*/

class Solution {
    public int minimumCost(int N, int[][] connections) {
        Arrays.sort(connections, (a, b) -> (a[2] - b[2]));
        DSU dsu = new DSU(N);
        int u, v, cost;
        int minCost = 0;
        
        for (int[] connection : connections) {
            u = dsu.find(connection[0] - 1);
            v = dsu.find(connection[1] - 1);
            if (u != v) {
                dsu.union(u, v);
                minCost += connection[2];                           // main logic
                if (dsu.connectedComponents == 1) {
                    return minCost;
                }
            }
        }
        return -1;
    }
}

class DSU {
    int[] parent;
    int[] rank;
    int connectedComponents;
    
    DSU(int n) {
        this.parent = new int[n];
        this.rank = new int[n];
        this.connectedComponents = n;
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }
    
    public int find(int i) {
        if (i == parent[i]) {
            return i;
        }
        return parent[i] = find(parent[i]);
    }
    
    public void union(int u, int v) {
        if (rank[u] > rank[v]) {
            rank[u] += rank[v];
            parent[v] = u;
        } else {
            rank[v] += rank[u];
            parent[u] = v;
        }
        connectedComponents--;
    }
}