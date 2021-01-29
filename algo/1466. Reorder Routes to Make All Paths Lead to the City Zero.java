/*
There are n cities numbered from 0 to n-1 and n-1 roads such that there is only one way to travel between two different cities (this network form a tree). 
Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.
Roads are represented by connections where connections[i] = [a, b] represents a road from city a to b.
This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
Your task consists of reorienting some roads such that each city can visit the city 0. 
Return the minimum number of edges changed.
It's guaranteed that each city can reach the city 0 after reorder.

Example 1:
Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
Output: 3
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).

Example 2:
Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
Output: 2
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).

Example 3:
Input: n = 3, connections = [[1,0],[2,0]]
Output: 0
 
Constraints:
2 <= n <= 5 * 10^4
connections.length == n-1
connections[i].length == 2
0 <= connections[i][0], connections[i][1] <= n-1
connections[i][0] != connections[i][1]
*/


/*
    Time: 2n, Space: n
    Logic: 
        created an undirected graph
        start from 0 and do BFS
        Use hashset to check whether child to parent path is present or not
        if not present, then do output++
*/
/*
class Solution {
    public int minReorder(int n, int[][] connections) {
        Graph graph = new Graph(n);
        Set<Connection> connectionSet = new HashSet<Connection>();
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(0);
        boolean[] visited = new boolean[n];
        visited[0] = true;
        
        for (int[] connection : connections) {
            connectionSet.add(new Connection(connection[0], connection[1]));
            graph.addEdge(connection[0], connection[1]);
        }
        
        int output = 0;
        while (!queue.isEmpty()) {
            int curr = queue.remove();
            List<Integer> neighbours = graph.adjList[curr];
            for(int neigh : graph.adjList[curr]) {
                if(!visited[neigh]) {
                    queue.add(neigh);
                    visited[neigh] = true;
                    if(!connectionSet.contains(new Connection(neigh, curr))) {    // main logic
                        output++;
                    }
                }
            }
        }
        return output;
    }
    
    class Graph {
        ArrayList<Integer>[] adjList;
        
        Graph(int n) {
            this.adjList = new ArrayList[n];
            for(int i = 0; i < n; i++) {
                adjList[i] = new ArrayList<Integer>();
            }
        }
        
        public void addEdge(int a, int b) {
            adjList[a].add(b);
            adjList[b].add(a);
        }
    }
    
    class Connection {
        int x, y;
        
        Connection(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(this.x, this.y);
        }
        
        @Override
        public boolean equals(Object obj) {
            Connection curr = (Connection)obj;
            return curr.x == this.x && curr.y == this.y;
        }
    }
}
*/



/*
    Time: 2n, Space: n
    Logic: 
        Exactly same as above
        But instead of hashset, we can use negated index to represent the direction
            a) parent to a child is positive
            b) child to its parent is negative
        While doing dfs, if we come across a positive then do output++
*/
class Solution {
    Graph graph;
    boolean[] visited;
    
    public int minReorder(int n, int[][] connections) {
        graph = new Graph(n);
        visited = new boolean[n];
        
        for (int[] connection: connections) {
            graph.addEdge(connection[0], connection[1]);
        }
        return dfs(0);
    }
    
    private int dfs(int curr) {
        int cost = 0;
        visited[curr] = true;
        List<Integer> neighbours = graph.adjList.get(curr);
        
        for (int next: neighbours) {
            if (!visited[Math.abs(next)]) {
                cost += (next > 0 ? 1 : 0) + dfs(Math.abs(next));   // main logic
            }
        }
        return cost;
    }
    
    class Graph {
        List<List<Integer>> adjList;
        
        Graph(int n) {
            this.adjList = new ArrayList<List<Integer>>();
            for (int i = 0; i < n; i++) {
                adjList.add(new ArrayList<Integer>());
            }
        }
        
        public void addEdge(int a, int b) {
            adjList.get(a).add(b);
            adjList.get(b).add(-a);     // negated index to represent opposite direction
        }
    }
}