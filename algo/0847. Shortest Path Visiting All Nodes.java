/*
An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is given as graph.
graph.length = N, and j != i is in the list graph[i] exactly once, if and only if nodes i and j are connected.
Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.

Example 1:
Input: [[1,2,3],[0],[0],[0]]
Output: 4
Explanation: One possible path is [1,0,2,0,3]

Example 2:
Input: [[1],[0,2,4],[1,3,4],[2],[1,2]]
Output: 4
Explanation: One possible path is [0,1,4,2,3]

Note:
1 <= graph.length <= 12
0 <= graph[i].length < graph.length
*/


/*
    BFS
        states of bfs:  (main logic)
        1) node index
        2) visited array mask value
        
    time: O((2^n) * n)
    space: O((2^n) * n)
*/

class Solution {
    public int shortestPathLength(int[][] graph) {
        Queue<NodeState> nodesQueue = new LinkedList<NodeState>();
        int noOfNodes = graph.length;
        int allNodesMask = (1 << noOfNodes) - 1;
        int[][] distArr = new int[noOfNodes][allNodesMask + 1];
        
        for (int[] currDistArr : distArr) {
            Arrays.fill(currDistArr, Integer.MAX_VALUE);
        }
        for (int i = 0; i < noOfNodes; i++) {
            nodesQueue.add(new NodeState(i, 1 << i));
            distArr[i][1 << i] = 0;
        }
        while (!nodesQueue.isEmpty()) {
            NodeState curr = nodesQueue.remove();
            int dist = distArr[curr.nodeIndex][curr.visitedMask];
            if (curr.visitedMask == allNodesMask) {
                return dist;
            }
            for (int neighIndex : graph[curr.nodeIndex]) {
                int neighVisitedMask = curr.visitedMask | (1 << neighIndex);
                if (dist + 1 < distArr[neighIndex][neighVisitedMask]) {                 // avoid infinite loop
                    distArr[neighIndex][neighVisitedMask] = dist + 1;
                    nodesQueue.add(new NodeState(neighIndex, neighVisitedMask));
                }
            }
        }
        return -1;
    }
    
    class NodeState {
        int nodeIndex;
        int visitedMask;
        NodeState(int nodeIndex, int visitedMask) {
            this.nodeIndex = nodeIndex;
            this.visitedMask = visitedMask;
        }
    }
}