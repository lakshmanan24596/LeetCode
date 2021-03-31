/*
An undirected, connected tree with N nodes labelled 0...N-1 and N-1 edges are given.
The ith edge connects nodes edges[i][0] and edges[i][1] together.
Return a list ans, where ans[i] is the sum of the distances between node i and all other nodes.

Example 1:
Input: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
Output: [8,12,6,10,10,10]
Explanation: 
Here is a diagram of the given tree:
  0
 / \
1   2
   /|\
  3 4 5
We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
equals 1 + 1 + 2 + 2 + 2 = 8.  Hence, answer[0] = 8, and so on.
Note: 1 <= N <= 10000
*/



/*
    1) brute force
        do dfs from all nodes
        time: n * n
        
    2) postOrder + preOrder
        time: n + n
        
        for example-1:
            countArr = 6, 1, 4, 1, 1, 1
            distance arr after postOrder = 8,  0, 3,  0,  0,  0
            distance arr after preOrder  = 8, 12, 6, 10, 10, 10
            
            Pre-order explanation:
            answer for node1 = (8 - 1) + (6 - 1) = 12
            
    https://leetcode.com/problems/sum-of-distances-in-tree/discuss/130583/C%2B%2BJavaPython-Pre-order-and-Post-order-DFS-O(N)
    When we move our root from one node to its connected node, one part of nodes get closer, one the other part get further.
*/

class Solution {
    List<List<Integer>> adjList;
    int[] countArr, distance;
    int N;
    
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        this.N = N;
        adjList = new ArrayList<List<Integer>>();
        countArr = new int[N];
        distance = new int[N];
        
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<Integer>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        getCount(0, -1);
        postOrder(0, -1);
        preOrder(0, -1);
        return distance;
    }
    
    public int getCount(int root, int parent) {                 // 1 + count of children
        int count = 1;
        for (int neigh : adjList.get(root)) {
            if (neigh != parent) {
                count += getCount(neigh, root);
            }
        }
        countArr[root] = count;
        return count;
    }
    
    public int postOrder(int root, int parent) {
        int currDist = 0;
        int neighDist;
        
        for (int neigh : adjList.get(root)) {
            if (neigh != parent) {
                neighDist = postOrder(neigh, root);
                currDist += neighDist + countArr[neigh];        // main logic
            }
        }
        distance[root] = currDist;
        return currDist;
    }
    
    public void preOrder(int root, int parent) {
        for (int neigh : adjList.get(root)) {
            if (neigh != parent) {
                int d1 = distance[root] - countArr[neigh];
                int d2 = N - countArr[neigh];
                distance[neigh] = d1 + d2;                      // main logic
                preOrder(neigh, root);
            }
        }
    }
}