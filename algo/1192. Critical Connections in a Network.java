/*
There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b. 
Any server can reach any other server directly or indirectly through the network.
A critical connection is a connection that, if removed, will make some server unable to reach some other server.
Return all critical connections in the network in any order.

Example 1:
Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.
 
Constraints:
1 <= n <= 10^5
n-1 <= connections.length <= 10^5
connections[i][0] != connections[i][1]
There are no repeated connections.
*/


/*
    Find all bridges in a graph is the critical connections in a network
        1) O(V*(V+E)) or O(E*(V+E)) --> brute force
        2) O(V+E) --> tarjan algo --> https://www.geeksforgeeks.org/bridge-in-a-graph/
        
        The problem is to find the edges which are not involved in a cycle.
        A back edge cannot be a bridge. Only a forward edge may be a bridge.
*/
class Solution 
{
    List<List<Integer>> output = new ArrayList<List<Integer>>();
    int[] discoveryTime;
    int[] lowTime;
    int[] parent;
    boolean[] visited;
    Graph graph;
    int time;
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) 
    {
        graph = new Graph(n);
        for(List<Integer> conn : connections) {
            graph.addEdge(conn.get(0), conn.get(1));
        }
        
        discoveryTime = new int[n];
        lowTime = new int[n];
        visited = new boolean[n];
        parent = new int[n];
        
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfsUtil(i);
            }
        }
        return output;
    }
    
    public void dfsUtil(int curr)
    {
        visited[curr] = true;
        time++;
        discoveryTime[curr] = time;
        lowTime[curr] = time;
        
        List<Integer> currList = graph.adjList[curr];
        for(int next : currList)
        {
            if(!visited[next])
            {
                parent[next] = curr;
                dfsUtil(next);
                
                // main logic:
                lowTime[curr] = Math.min(lowTime[curr], lowTime[next]);
                if(lowTime[next] > discoveryTime[curr]) // lowTime should be lesser. If suppose it is greater, then bridge is there
                {
                    output.add(Arrays.asList(curr, next));
                }
            }
            else if(next != parent[curr])
            {
                lowTime[curr] = Math.min(lowTime[curr], discoveryTime[next]);
            }
        }
    }
}

class Graph
{
    ArrayList<Integer>[] adjList;   
    Graph(int v)
    { 
        adjList = new ArrayList[v];
        for(int i = 0; i < v; i++) {
            adjList[i] = new ArrayList<Integer>();
        }
    }
    public void addEdge(int v1, int v2)
    {
        adjList[v1].add(v2);
        adjList[v2].add(v1);
    }
}
