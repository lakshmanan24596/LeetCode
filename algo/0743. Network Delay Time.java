/*
There are N network nodes, labelled 1 to N.
Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.
Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

Example 1:
Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
Output: 2

Note:
N will be in the range [1, 100].
K will be in the range [1, N].
The length of times will be in the range [1, 6000].
All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
*/


/*
    1) Brute Force DFS --> each edge will be visited exponential times
    2) Bellman-Ford algorithm. Time: VE, Space: V
    3) Dijkstra's algorithm. Time: V*log(V) + E, Space: V+E --> https://www.youtube.com/watch?v=pVfj6mxhdMw
    
    https://leetcode.com/problems/network-delay-time/discuss/183873/Java-solutions-using-Dijkstra-FloydWarshall-and-Bellman-Ford-algorithm
*/


/*
// Brute force DFS
class Solution 
{
    boolean[] recursionStack;
    Integer[] output;
    Graph graph;
    
    public int networkDelayTime(int[][] times, int n, int k) 
    {
        graph = new Graph(n);
        this.output = new Integer[n];
        recursionStack = new boolean[n];
        
        for(int[] time : times) {
            graph.addEdge(time[0] - 1, time[1] - 1, time[2]); // u, v, cost
        }
        
        Arrays.fill(output, Integer.MAX_VALUE);
        output[k-1] = 0;
        dfs(k-1);
        
        int minTime = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++)
        {
            if(output[i] == Integer.MAX_VALUE) {
                return -1;
            }
            minTime = Math.max(minTime, output[i]);
        }
        return minTime;
    }
    
    public void dfs(int currNode)
    {
        recursionStack[currNode] = true;                // to avoid processing cycle formed nodes
        List<int[]> list = graph.adjList[currNode];
        
        for(int[] next : list)
        {
            int nextNode = next[0];
            int nextDist = next[1];
            
            if(!recursionStack[nextNode])
            {
                output[nextNode] = Math.min(output[nextNode], output[currNode] + nextDist);     // main logic
                dfs(nextNode);
            }
        }
        recursionStack[currNode] = false;
    }
}
class Graph
{
    int n;
    ArrayList<int[]>[] adjList;
    Graph(int n) 
    {
        this.n = n;
        adjList = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<int[]>();
        }
    }
    public void addEdge(int u, int v, int cost) {
        adjList[u].add(new int[]{v, cost});
    }
}
*/


/*
// Bellman-Ford algorithm. Time: VE, Space: V
class Solution
{
    public int networkDelayTime(int[][] times, int N, int K) 
    {
        double[] disTo = new double[N];
        Arrays.fill(disTo, Double.POSITIVE_INFINITY);
        disTo[K - 1] = 0;
        
        while(--N > 0)  // n-1 times
        {
            for (int[] edge : times) 
            {
                int u = edge[0] - 1, v = edge[1] - 1, w = edge[2];
                disTo[v] = Math.min(disTo[v], disTo[u] + w);
            }
        }
        
        double res = Double.MIN_VALUE;
        for (double i: disTo) {
            res = Math.max(i, res);
        }
        return res == Double.POSITIVE_INFINITY ? -1 : (int) res;
    }
}
*/


// Dijkstra's algorithm. Time: V*log(V) + E, Space: V+E 
class Solution 
{
    public int networkDelayTime(int[][] times, int n, int k) 
    {
        Graph graph = new Graph(n);
        boolean[] visited = new boolean[n];
        int[] output = new int[n];                              // store shortest distance

        PriorityQueue<int[]> pQueue = new PriorityQueue<int[]>(new Comparator<int[]>(){     // node, distance
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];                             // min heap for shortest distance
            } 
        });

        for(int[] time : times) {
            graph.addEdge(time[0] - 1, time[1] - 1, time[2]);   // u, v, cost
        }

        Arrays.fill(output, Integer.MAX_VALUE);
        output[k-1] = 0;
        pQueue.add(new int[]{k-1, 0});
        int minDistance = 0;
       
        while(!pQueue.isEmpty()) 
        {
            int[] curr = pQueue.remove();       // main logic: always shortest distance node will be processed first
            int currNode = curr[0];
            int currDist = curr[1];
            
            if(visited[currNode]) {
                continue;
            }
            visited[currNode] = true;

            n--;
            minDistance = currDist;

            List<int[]> list = graph.adjList[currNode];
            for(int[] next : list)
            {
                int nextNode = next[0];
                int nextDist = next[1];

                if(!visited[nextNode] && output[currNode] + nextDist < output[nextNode])
                {
                    output[nextNode] = output[currNode] + nextDist;         // main logic: greedy
                    pQueue.add(new int[] {nextNode, output[nextNode]});
                }
            }
        }
        return n == 0 ? minDistance : -1;
    }
}
class Graph
{
    int n;
    ArrayList<int[]>[] adjList;
    Graph(int n) 
    {
        this.n = n;
        adjList = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<int[]>();
        }
    }
    public void addEdge(int u, int v, int cost) {
        adjList[u].add(new int[]{v, cost});
    }
}
