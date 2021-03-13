/*
Given a weighted undirected connected graph with n vertices numbered from 0 to n - 1, and an array edges where edges[i] = [ai, bi, weighti] represents a bidirectional and weighted edge between nodes ai and bi. A minimum spanning tree (MST) is a subset of the graph's edges that connects all vertices without cycles and with the minimum possible total edge weight.
Find all the critical and pseudo-critical edges in the given graph's minimum spanning tree (MST). An MST edge whose deletion from the graph would cause the MST weight to increase is called a critical edge. On the other hand, a pseudo-critical edge is that which can appear in some MSTs but not all.
Note that you can return the indices of the edges in any order.

Example 1:
Input: n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
Output: [[0,1],[2,3,4,5]]
Explanation: The figure above describes the graph.
The following figure shows all the possible MSTs:
Notice that the two edges 0 and 1 appear in all MSTs, therefore they are critical edges, so we return them in the first list of the output.
The edges 2, 3, 4, and 5 are only part of some MSTs, therefore they are considered pseudo-critical edges. We add them to the second list of the output.

Example 2:
Input: n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
Output: [[],[0,1,2,3]]
Explanation: We can observe that since all 4 edges have equal weight, choosing any 3 edges from the given 4 will yield an MST. Therefore all 4 edges are pseudo-critical.

Constraints:
2 <= n <= 100
1 <= edges.length <= min(200, n * (n - 1) / 2)
edges[i].length == 3
0 <= ai < bi < n
1 <= weighti <= 1000
All pairs (ai, bi) are distinct.
*/



/*
    Find MST using Kruskals algo using DSU
    Time: O(E * 2E), where DSU methods are amortized O(1)
    Space: E
    
    Logic for critical edge:
        after removing this edge --> the weight will increase or cannot form a mst
    
    Logic for pseuso critical edge:
        after compulsorily accepting this edge --> the weight should be same as original weight
*/

class Solution {
    int n;
    int[][] sortedEdges;
    
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        this.n = n;
        this.sortedEdges = edges.clone();
        Arrays.sort(sortedEdges, (a, b) -> (a[2] - b[2]));
        int originalWeight = getMstWeight(null, null);
        List<Integer> criticalEdge = new ArrayList<Integer>();
        List<Integer> pseudoCriticalEdge = new ArrayList<Integer>();
        int[] edge;
        
        for (int i = 0; i < edges.length; i++) {
            edge = edges[i];
            if (getMstWeight(edge, null) > originalWeight) {                // main logic
                criticalEdge.add(i);
            } else if (getMstWeight(null, edge) == originalWeight) {        // main logic
                pseudoCriticalEdge.add(i);
            }
        }
        
        List<List<Integer>> outputEdgeList = new ArrayList<List<Integer>>();
        outputEdgeList.add(criticalEdge);
        outputEdgeList.add(pseudoCriticalEdge);
        return outputEdgeList;
    }
    
    public int getMstWeight(int[] removalEdge, int[] acceptanceEdge) {
        DSU dsu = new DSU(n);                           // Kruskals algo using DSU
        int mstWeight = 0;
        int[] edge;
        int setU, setV;
        int mstEdgeCount = 0;
        
        if (acceptanceEdge != null) {
            setU = dsu.find(acceptanceEdge[0]);
            setV = dsu.find(acceptanceEdge[1]);
            dsu.union(setU, setV);
            mstWeight += acceptanceEdge[2];
            mstEdgeCount++;
        }
        for (int i = 0; i < sortedEdges.length; i++) {
            edge = sortedEdges[i];
            if (edge != removalEdge) {
                setU = dsu.find(edge[0]);
                setV = dsu.find(edge[1]);
                if (setU != setV) {                     // no cycle
                    dsu.union(setU, setV);
                    mstWeight += edge[2];
                    mstEdgeCount++;
                    if (mstEdgeCount == n - 1) {        // MST has V-1 edges in total
                        return mstWeight;
                    }
                }
            }
        }
        return Integer.MAX_VALUE;                       // cannot form MST
    }
    
    class DSU {
        int[] parent;
        int[] rank;
        
        DSU(int n) {
            this.rank = new int[n];
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            Arrays.fill(rank, 1);
        }
        
        public int find(int curr) {
            if (parent[curr] == curr) {
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