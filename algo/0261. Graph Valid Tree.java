/*
You have a graph of n nodes labeled from 0 to n - 1. 
You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.
Return true if the edges of the given graph make up a valid tree, and false otherwise.

Example 1:
Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
Output: true

Example 2:
Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
Output: false

Constraints:
1 <= 2000 <= n
0 <= edges.length <= 5000
edges[i].length == 2
0 <= ai, bi < n
ai != bi
There are no self-loops or repeated edges.
*/



/*
    Condition for a undirected graph to be a tree:
        1) single connected component
        2) no cycles should be formed
        
    Logic: DSU
    Time: E + V
    space: V
*/

class Solution {
    public boolean validTree(int n, int[][] edges) {
        DSU dsu = new DSU(n);
        int parent1, parent2;
        
        for (int[] edge : edges) {
            parent1 = dsu.findParent(edge[0]);
            parent2 = dsu.findParent(edge[1]);
            if (parent1 == parent2) {                       // no cycles should be formed
                return false;
            } else {
                dsu.union(parent1, parent2);
            }
        }
        return dsu.connectedComponentsCount == 1;           // single connected component
    }
    
    class DSU {
        int[] parent;
        int[] rank;
        int connectedComponentsCount;
        
        DSU(int n) {
            this.connectedComponentsCount = n;
            this.parent = new int[n];
            this.rank = new int[n];
            
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }
        
        public int findParent(int node) {
            if (parent[node] == node) {
                return node;
            }
            return parent[node] = findParent(parent[node]);
        }
        
        public void union(int n1, int n2) {
            if (rank[n1] > rank[n2]) {
                parent[n2] = n1;
                rank[n1] += rank[n2];
            } else {
                parent[n1] = n2;
                rank[n2] += rank[n1];
            }
            connectedComponentsCount--;
        }
    }
}