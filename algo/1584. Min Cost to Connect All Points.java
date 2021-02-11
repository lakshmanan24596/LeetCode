/*
You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
Return the minimum cost to make all points connected. 
All points are connected if there is exactly one simple path between any two points. 

Example 1:
Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
Output: 20
Explanation:
We can connect the points as shown above to get the minimum cost of 20.
Notice that there is a unique path between every pair of points.

Example 2:
Input: points = [[3,12],[-2,5],[-4,1]]
Output: 18

Example 3:
Input: points = [[0,0],[1,1],[1,0],[-1,1]]
Output: 4

Example 4:
Input: points = [[-1000000,-1000000],[1000000,1000000]]
Output: 4000000

Example 5:
Input: points = [[0,0]]
Output: 0

Constraints:
1 <= points.length <= 1000
-106 <= xi, yi <= 106
All pairs (xi, yi) are distinct.
*/


/*
    The problem is about finding the MST
    This can be solved greedily using Prims or Kruskals algo
        Prims: exactly similar to Dijsktras
        Krushkal: check cycle formed or not
        
    Below solution is Kruskals algo using DSU
        Time: ElogE + VlogE
        Space: E + V
        where, E = V^2 for this problem
*/

class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int dist, count = 0, cost = 0;
        int setU, setV;
        Node curr;
        PriorityQueue<Node> pQueue = new PriorityQueue<Node>((a, b) -> (a.dist - b.dist));
        DSU dsu = new DSU(n);
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                pQueue.add(new Node(i, j , dist));
            }
        }
        while (!pQueue.isEmpty() && count < n - 1) {
            curr = pQueue.remove();         // take min cost edge
            setU = dsu.find(curr.u);
            setV = dsu.find(curr.v);
            if (setU != setV) {             // cycle should not be formed
                cost += curr.dist;
                dsu.union(setU, setV);
                count++;
            }
        }
        return cost;
    }
    
    class Node {
        int u, v, dist;
        Node(int u, int v, int dist) {
            this.u = u;
            this.v = v;
            this.dist = dist;
        }
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