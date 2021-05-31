/*
    1) Brute force:
        time: O(t^n), which is exponential
        space: O(t)
    
    2) DP memo:
        states: t, n
        time: O((t*n)*e), where e is edges, in worst case: e = n
        space: O(t*n)

    3) DP tabulation space optimization:
        time: O((t*n)*e)
        space: O(n)
        
    4) Dijstras:
        time: O((t*n)*(log(t*e)))
        space: O(t*n)
        
    Follow up ques:
        1) add/edit/delete allowed
            https://leetcode.com/discuss/interview-question/751787/google-on-site-smallest-edit-distance-to-convert-path
        2) find similar path
            https://leetcode.com/discuss/interview-question/691300/Google-or-Onsite-or-Find-Most-Similar-Path-In-Graph
        3) If each node can be visited only once in the path
*/


/*
// DP memo
class Solution {
    Graph graph;
    String[] names, targetPath;
    Path[][] DP;
    
    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        this.names = names;
        this.targetPath = targetPath;
        this.graph = new Graph(n);
        this.DP = new Path[targetPath.length][n];
        Path currPath;
        Path minEditPath = new Path(Integer.MAX_VALUE, new ArrayList<Integer>());
        
        for (int[] road : roads) {
            graph.addEdge(road[0], road[1]);
        }
        for (int i = 0; i < n; i++) {
            currPath = dfs(0, i);                                           // do dfs from all nodes
            if (currPath.dist < minEditPath.dist) {
                minEditPath = currPath;
            }
        }
        return minEditPath.pathInfo;
    }
    
    public Path dfs(int level, int index) {                                 // level is "t" and index is "n"
        if (level == targetPath.length) {
            return new Path(0, new ArrayList<Integer>());
        }
        if (DP[level][index] != null) {
            return DP[level][index];
        }
        Path currPath;
        Path minEditPath = new Path(Integer.MAX_VALUE, new ArrayList<Integer>());
        
        for (int neighIndex : graph.adjList[index]) {                       // find best minEditPath
            currPath = dfs(level + 1, neighIndex);
            if (currPath.dist < minEditPath.dist) {
                minEditPath = currPath;
            }
        }
        int minEditDist = minEditPath.dist;
        minEditDist += (!targetPath[level].equals(names[index])) ? 1 : 0;   // main logic: calculate edit distance 
        List<Integer> outputPath = new ArrayList<Integer>();
        outputPath.add(index);
        outputPath.addAll(minEditPath.pathInfo);
        return DP[level][index] = new Path(minEditDist, outputPath);
    }
}

class Graph {
    int n;
    List<Integer>[] adjList;
    
    Graph(int n) {
        this.n = n;
        this.adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<Integer>();
        }
    }
    
    public void addEdge(int u, int v) {
        adjList[u].add(v);
        adjList[v].add(u);
    }
}

class Path {
    int dist;
    List<Integer> pathInfo;
    
    Path(int dist, List<Integer> pathInfo) {
        this.dist = dist;
        this.pathInfo = pathInfo;
    }
}
*/


// Dijkstra's
class Solution {
    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        int t = targetPath.length;
        int[][] distance = new int[t][n];       // store distance, used for priorityQueue
        int[][] path = new int[t][n];           // store path index, used to fetch output list
        Graph graph = new Graph(n);
        LinkedList<Integer> output = new LinkedList<Integer>();
        PriorityQueue<Node> pQueue = new PriorityQueue<Node>((a, b) -> {
            int d1 = distance[a.targetIndex][a.nodeIndex];
            int d2 = distance[b.targetIndex][b.nodeIndex];
            return d1 - d2;
        });
        int i, j, currDist, nextDist;
        Node currNode;
        int pathIndex = -1;
        
        for (int[] road : roads) {
            graph.addEdge(road[0], road[1]);
        }
        for (i = 0, j = 0; j < n; j++) {
            pQueue.add(new Node(i, j));                                                 // add 1st row elements into pQueue
            distance[i][j] = (!targetPath[i].equals(names[j])) ? 1 : 0;
        }
        for (i = 1; i < t; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
        
        while (!pQueue.isEmpty()) {
            currNode = pQueue.remove();
            i = currNode.targetIndex;
            j = currNode.nodeIndex;
            currDist = distance[i][j];
            if (i == t - 1) {
                pathIndex = j;
                break;
            }
            
            int ii = i + 1;
            for (int jj : graph.adjList[j]) {
                nextDist = currDist + ((!targetPath[ii].equals(names[jj])) ? 1 : 0);    // main logic: edit distance
                if (nextDist < distance[ii][jj]) {                                      // main logic: dijktra's condition
                    distance[ii][jj] = nextDist;
                    path[ii][jj] = j;
                    pQueue.add(new Node(ii, jj));
                }
            }
        }
        for (i = t - 1; i >= 0; i--) {
            output.addFirst(pathIndex);
            pathIndex = path[i][pathIndex];
        }
        return output;
    }
}

class Graph {
    int n;
    List<Integer>[] adjList;
    
    Graph(int n) {
        this.n = n;
        this.adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<Integer>();
        }
    }
    
    public void addEdge(int u, int v) {
        adjList[u].add(v);
        adjList[v].add(u);
    }
}

class Node {
    int targetIndex;
    int nodeIndex;
    Node(int targetIndex, int nodeIndex) {
        this.targetIndex = targetIndex;
        this.nodeIndex = nodeIndex;
    }
}