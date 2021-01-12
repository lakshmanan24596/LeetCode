/*
There are n cities numbered from 0 to n-1. 
Given the array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.
Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold, If there are multiple such cities, return the city with the greatest number.
Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.

Example 1:
Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
Output: 3
Explanation: The figure above describes the graph. 
The neighboring cities at a distanceThreshold = 4 for each city are:
City 0 -> [City 1, City 2] 
City 1 -> [City 0, City 2, City 3] 
City 2 -> [City 0, City 1, City 3] 
City 3 -> [City 1, City 2] 
Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3 since it has the greatest number.

Example 2:
Input: n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
Output: 0
Explanation: The figure above describes the graph. 
The neighboring cities at a distanceThreshold = 2 for each city are:
City 0 -> [City 1] 
City 1 -> [City 0, City 4] 
City 2 -> [City 3, City 4] 
City 3 -> [City 2, City 4]
City 4 -> [City 1, City 2, City 3] 
The city 0 has 1 neighboring city at a distanceThreshold = 2.
 
Constraints:
2 <= n <= 100
1 <= edges.length <= n * (n - 1) / 2
edges[i].length == 3
0 <= fromi < toi < n
1 <= weighti, distanceThreshold <= 10^4
All pairs (fromi, toi) are distinct.
*/


/*
    Single source shortest path algo is done for all nodes using Dijkstras algo
    Time: V*(E*logV)
    Space: E
*/
/*
class Solution 
{
    ArrayList<int[]>[] graph;
    int n, distanceThreshold;
    int[][] edges;
    
    public int findTheCity(int n, int[][] edges, int distanceThreshold) 
    {
        this.n = n; this.edges = edges; this.distanceThreshold = distanceThreshold;
        createGraph();
        
        int outputNode = -1, minNeighCount = Integer.MAX_VALUE, currCityNeighCount;
        for(int i = 0; i < n; i++)
        {
            currCityNeighCount = dijkstras(i);  // main logic: do Dijkstras algo for all nodes and take min
            if(currCityNeighCount <= minNeighCount) 
            {
                minNeighCount = currCityNeighCount;
                outputNode = i;
            }
        }
        return outputNode;
    }
    
    public void createGraph()
    {
        this.graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<int[]>();
        }
        for(int i = 0; i < edges.length; i++)
        {
            int u = edges[i][0], v = edges[i][1], cost = edges[i][2];
            graph[u].add(new int[] {v, cost});
            graph[v].add(new int[] {u, cost});
        }
    }
    
    public int dijkstras(int startCity)
    {
        boolean[] visited = new boolean[n];
        PriorityQueue<int[]> pQueue = new PriorityQueue<int[]>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];             // min heap for distance
            }
        });
        
        pQueue.add(new int[] {startCity, 0});   // sourceCity, distance
        int output = 0;
        
        while(!pQueue.isEmpty())
        {
            int[] curr = pQueue.remove();
            int currCity = curr[0], currDist = curr[1], neighCity, neighDist;
            if(visited[currCity]) {             // because we didnt remove from pQueue 
                continue;
            }
            visited[currCity] = true;
            output++;
            
            ArrayList<int[]> adjList = graph[currCity];
            for(int[] neigh : adjList)
            {
                neighCity = neigh[0];
                neighDist = neigh[1];
                
                if(!visited[neighCity] && currDist + neighDist <= distanceThreshold)  // main logic
                {
                    pQueue.add(new int[] {neighCity, currDist + neighDist});
                }
            }
        }
        return output - 1;
    }
}
*/


/*
    All pair shortest path algo using floydWarshall DP
    Time: V^3
    Space: V^2
    
    d[i][j][k] = shortest path from i -> j and intermediates from: [0 - k]
    init: d[i][j][-1] = graph[i][j], direct edges
    
    recursion relation:
		d[i][j][k] = min( d[i][j][k-1], d[i][k][k-1] + d[k][j][k-1] )
			       = min( not pick k , pick k )
        because d[i][j][k] only depends on d[i][j][k-1] -> can use 2-d array and "k must be outermost loop"
*/
class Solution 
{
    public int findTheCity(int n, int[][] edges, int distanceThreshold) 
    {
        // step 1: Initialize DP with 0, inf, direct distance
        int[][] DP = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                DP[i][j] = (i == j) ? 0 : Integer.MAX_VALUE;
            }
        }
        
        int u , v, cost;
        for(int i = 0; i < edges.length; i++)
        {
            u = edges[i][0]; v = edges[i][1]; cost = edges[i][2];
            DP[u][v] = cost;
            DP[v][u] = cost;
        }
        
        // step 2: find all pair shortest path
        floydWarshall(n, DP);
        
        // step 3: find the output node
        int outputNode = -1, minNeighCount = Integer.MAX_VALUE, currCityNeighCount;
        for(int i = 0; i < n; i++) 
        {
            currCityNeighCount = 0;
            for(int j = 0; j < n; j++) 
            {
                if(i != j && DP[i][j] <= distanceThreshold) 
                {
                    currCityNeighCount++;
                }
            }
            if(currCityNeighCount <= minNeighCount) 
            {
                minNeighCount = currCityNeighCount;
                outputNode = i;
            }
        }
        return outputNode;
    }
    
    public void floydWarshall(int n, int[][] DP)
    {
        for(int k = 0; k < n; k++)              // check all intermediate nodes ("k must be outermost loop")
        {
            for(int i = 0; i < n; i++)          // start node
            {
                for(int j = 0; j < n; j++)      // end node
                {
                    if(DP[i][k] == Integer.MAX_VALUE || DP[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    DP[i][j] = Math.min(DP[i][j], DP[i][k] + DP[k][j]);     // main logic
                }
            }
        }
    }
}
