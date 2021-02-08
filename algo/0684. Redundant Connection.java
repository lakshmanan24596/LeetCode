/*
In this problem, a tree is an undirected graph that is connected and has no cycles.
The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. 
The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. 
Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.
Return an edge that can be removed so that the resulting graph is a tree of N nodes. 
If there are multiple answers, return the answer that occurs last in the given 2D-array. 
The answer edge [u, v] should be in the same format, with u < v.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given undirected graph will be like this:
  1
 / \
2 - 3

Example 2:
Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
Output: [1,4]
Explanation: The given undirected graph will be like this:
5 - 1 - 2
    |   |
    4 - 3

Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.

Update (2017-09-26):
We have overhauled the problem description + test cases and specified clearly the graph is an undirected graph. 
For the directed graph follow up please see Redundant Connection II). We apologize for any inconvenience caused.
*/


/*
    1) n^2 solution:
        a) create a graph
        b) for each edge(u, v), try to remove it and check if there is any other path from u to v using DFS
    
    2) n solution:
        we can simply find "which edge is causing an cycle"
        this can be easily done using DSU in n time
        
        becuase if there is a cycle, then even if we remove it, we can reach from u to v in other path
        also the question is gauranteed to have a cycle
*/

class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        DSU dsu = new DSU(n);
        int u, v, setA, setB;
        
        for (int[] edge : edges) {
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
        throw new AssertionError();
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