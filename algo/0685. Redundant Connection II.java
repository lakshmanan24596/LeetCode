/*
In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.
The given input is a directed graph that started as a rooted tree with n nodes (with distinct values from 1 to n), with one additional directed edge added. 
The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed.
The resulting graph is given as a 2D-array of edges. 
Each element of edges is a pair [ui, vi] that represents a directed edge connecting nodes ui and vi, where ui is a parent of child vi.
Return an edge that can be removed so that the resulting graph is a rooted tree of n nodes. 
If there are multiple answers, return the answer that occurs last in the given 2D-array.

Example 1:
Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]

Example 2:
Input: edges = [[1,2],[2,3],[3,4],[4,1],[1,5]]
Output: [4,1]

Constraints:
n == edges.length
3 <= n <= 1000
edges[i].length == 2
1 <= ui, vi <= n
*/



/*
    http://zxi.mytechroad.com/blog/wp-content/uploads/2017/10/685-ep83.png
    https://leetcode.com/problems/redundant-connection-ii/discuss/108070/Python-O(N)-concise-solution-with-detailed-explanation-passed-updated-testcases
    
    Violation cases:
        1) multiple parent
        2) cycle
    
    case 1: There is no multiple parent node. So we can just check 684. Redundant Connection
    case 2: Two parents are present for a single node. So check both the parents one by one.
    
    Time: n + nlogn + nlogn ==> n*logn, where log parameter comes for DSU
    Space: n
*/


class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        boolean[] visited = new boolean[edges.length + 1];
        Integer multipleParentNode = null;
        int[] edge, redundantEdge;
        
        for (int i = 0; i < edges.length; i++) {
            edge = edges[i];
            if (visited[edge[1]]) {
                multipleParentNode = edge[1];
                break;
            }
            visited[edge[1]] = true;
        }
        if (multipleParentNode == null) {
            return findRedundantConnection(edges, null);                    // case 1
        }
        
        for (int i = edges.length - 1; i >= 0; i--) {                       // iterate from last
            edge = edges[i];
            if (edge[1] == multipleParentNode) {
                redundantEdge = findRedundantConnection(edges, edge);       // case 2
                if (redundantEdge == null) {
                    return edge;
                }
            }
        }
        throw new AssertionError();
    }
    
    public int[] findRedundantConnection(int[][] edges, int[] skipEdge) {    // same as 684. Redundant Connection
        int n = edges.length;
        DSU dsu = new DSU(n);
        int u, v, setA, setB;
        
        for (int[] edge : edges) {
            if (edge == skipEdge) {     // extra condition for this problem
                continue;
            }
            u = edge[0] - 1;
            v = edge[1] - 1;
            setA = dsu.find(u);
            setB = dsu.find(v);
            if (setA == setB) {         // main logic: condition for loop
                return edge;
            } else {
                dsu.union(setA, setB);
            }
        }
        return null;
    }
    
    class DSU {
        int[] parent;
        int[] rank;
        
        DSU(int n) {
            rank = new int[n];
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int curr) {
            if (curr == parent[curr]) {
                return curr;
            }
            return parent[curr] = find(parent[curr]);
        }
        
        public void union(int a, int b) {
            if (rank[a] > rank[b]) {
                parent[b] = a;
                rank[a] += rank[b];
            } else {
                parent[a] = b;
                rank[b] += rank[a];
            }
        }
    }
}