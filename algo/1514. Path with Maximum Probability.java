/*
You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].
Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.
If there is no path from start to end, return 0. 
Your answer will be accepted if it differs from the correct answer by at most 1e-5.

Example 1:
Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
Output: 0.25000
Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.

Example 2:
Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
Output: 0.30000

Example 3:
Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
Output: 0.00000
Explanation: There is no path between 0 and 2.

Constraints:
2 <= n <= 10^4
0 <= start, end < n
start != end
0 <= a, b < n
a != b
0 <= succProb.length == edges.length <= 2*10^4
0 <= succProb[i] <= 1
There is at most one edge between every two nodes.
*/


/*
    Generic question: Is it possible to apply dijsktras algo for longest path? 
    No, because:
        https://stackoverflow.com/questions/8027180/dijkstra-for-longest-path-in-a-dag
        https://cs.stackexchange.com/questions/17980/is-it-possible-to-modify-dijkstra-algorithm-in-order-to-get-the-longest-path
    
    This problem is about finding "max" probability
    Eventhough it is max, it can be solved using dijstras becuase "adding up more edges will only decrease the cost" (as it involves multiplication of values <= 1)
    
    Example-1:
        0 to 1, the max probability is 0.5
        0 to x to 1, we cannot achieve the probability more than 0.5
        so "MaxHeap + dijstras" works here
    
    Time: O(V*logE) = n*logn
    Space: n
    Logic: MaxHeap + dijstras
*/

class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        double[] prob = new double[n];
        prob[start] = 1.0;
        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>((a, b) -> (prob[b] > prob[a] ? 1 : -1));  // maxHeap
        pQueue.add(start);
        boolean[] visited = new boolean[n];
        int curr, next;
        double cost;
        List<Object[]> neighbours;
        
        Graph graph = new Graph(n);
        for (int i = 0; i < edges.length; i++) {
            graph.addEdge(edges[i][0], edges[i][1], succProb[i]);
        }
        
        while (!pQueue.isEmpty()) {
            curr = pQueue.remove();
            if (curr == end) {
                return prob[curr];
            }
            if (visited[curr]) {
                continue;
            }
            visited[curr] = true;
            neighbours = graph.adjList[curr];
            for (Object[] neigh : neighbours) {
                next = (int) neigh[0];
                cost = (double) neigh[1];
                if (!visited[next] && cost * prob[curr] > prob[next]) {     // main condition
                    prob[next] = cost * prob[curr];
                    pQueue.add(next);
                }
            }
        }
        return 0.0;
    }
    
    class Graph {
        ArrayList[] adjList;
        
        Graph(int n) {
            adjList = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adjList[i] = new ArrayList<Object[]>();
            }
        }
        
        public void addEdge(int u, int v, double cost) {
            adjList[u].add(new Object[] {v, cost});
            adjList[v].add(new Object[] {u, cost});
        }
    }
}