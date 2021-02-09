/*
Consider a directed graph, with nodes labelled 0, 1, ..., n-1.  
In this graph, each edge is either red or blue, and there could be self-edges or parallel edges.
Each [i, j] in red_edges denotes a red directed edge from node i to node j.  
Similarly, each [i, j] in blue_edges denotes a blue directed edge from node i to node j.
Return an array answer of length n, where each answer[X] is the length of the shortest path from node 0 to node X such that the edge colors alternate along the path (or -1 if such a path doesn't exist).

Example 1:
Input: n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
Output: [0,1,-1]

Example 2:
Input: n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
Output: [0,1,-1]

Example 3:
Input: n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
Output: [0,-1,-1]

Example 4:
Input: n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
Output: [0,1,2]

Example 5:
Input: n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
Output: [0,1,1]

Constraints:
1 <= n <= 100
red_edges.length <= 400
blue_edges.length <= 400
red_edges[i].length == blue_edges[i].length == 2
0 <= red_edges[i][j], blue_edges[i][j] < n
*/


/*
    Logic: plain BFS
    for each node, we need to track 2 distance arrays for red and blue colors
    instead of visited boolean array, we can make use of distance array itself
    
    Time: V+E = 2n
    Space: 2n
*/

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        int[] redDist = new int[n];
        int[] blueDist = new int[n];
        Arrays.fill(redDist, Integer.MAX_VALUE);
        Arrays.fill(blueDist, Integer.MAX_VALUE);
        redDist[0] = 0;
        blueDist[0] = 0;
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(new Node(0, -1));     // color code: (-1, 0, 1) = (no color, red, blue)
        int neighIndex, neighColor;
        int queueSize, level = 0;
        Node currNode;
        
        Graph graph = new Graph(n);    // create graph
        for (int[] redEdge : red_edges) {
            graph.addEdge(redEdge[0], redEdge[1], 0);
        }
        for (int[] blueEdge : blue_edges) {
            graph.addEdge(blueEdge[0], blueEdge[1], 1);
        }

        while (!queue.isEmpty()) {      // BFS
            queueSize = queue.size();
            level++;
            while (queueSize-- > 0) {
                currNode = queue.remove();
                ArrayList<int[]> neighbours = graph.adjList[currNode.index];
                for (int[] neigh : neighbours) {
                    neighIndex = neigh[0];
                    neighColor = neigh[1];
                    if (neighColor != currNode.color) {                                 // main condition
                        if (neighColor == 0 && level < redDist[neighIndex]) {           // red
                            redDist[neighIndex] = level;
                            queue.add(new Node(neighIndex, neighColor));
                        } else if (neighColor == 1 && level < blueDist[neighIndex]) {   // blue
                            blueDist[neighIndex] = level;
                            queue.add(new Node(neighIndex, neighColor));
                        }
                    }
                }
            }
        }
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = Math.min(redDist[i] , blueDist[i]);     // main logic
            if (answer[i] == Integer.MAX_VALUE) {
                answer[i] = -1;
            }
        }
        return answer;
    }
    
    class Node {
        int index, color;
        
        Node(int index, int color) {
            this.index = index;
            this.color = color;
        }
    }
    
    class Graph {
        ArrayList<int[]>[] adjList;
        
        Graph(int n) {
            adjList = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adjList[i] = new ArrayList<int[]>();
            }
        }
        
        public void addEdge(int u, int v, int color) {
            adjList[u].add(new int[] {v, color});
        }
    }
}