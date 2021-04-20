/*
You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.
Return the number of connected components in the graph.

Example 1:
Input: n = 5, edges = [[0,1],[1,2],[3,4]]
Output: 2

Example 2:
Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
Output: 1
 
Constraints:
1 <= n <= 2000
1 <= edges.length <= 5000
edges[i].length == 2
0 <= ai <= bi < n
ai != bi
There are no repeated edges.
*/



/*
    Logic: DSU or DFS
    time: E+V
    space: V
*/

class Solution {
    public int countComponents(int n, int[][] edges) {
        DSU dsu = new DSU(n);
        int parent1, parent2;
        int componentsCount = n;
        
        for (int[] edge : edges) {
            parent1 = dsu.find(edge[0]);
            parent2 = dsu.find(edge[1]);
            if (parent1 != parent2) {
                dsu.union(parent1, parent2);
                componentsCount--;
            }
        }
        return componentsCount;
    }
    
    class DSU {
        int[] parent;
        int[] rank;
        
        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }
        
        public int find(int node) {
            if (parent[node] == node) {
                return node;
            }
            return parent[node] = find(parent[node]);
        }
        
        public void union(int node1, int node2) {
            if (rank[node1] > rank[node2]) {
                parent[node2] = node1;
                rank[node1] += rank[node2];
            } else {
                parent[node1] = node2;
                rank[node2] += rank[node1];
            }
        }
    }
}