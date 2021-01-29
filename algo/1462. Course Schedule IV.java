/*
There are a total of n courses you have to take, labeled from 0 to n-1.
Some courses may have direct prerequisites, for example, to take course 0 you have first to take course 1, which is expressed as a pair: [1,0]
Given the total number of courses n, a list of direct prerequisite pairs and a list of queries pairs.
You should answer for each queries[i] whether the course queries[i][0] is a prerequisite of the course queries[i][1] or not.
Return a list of boolean, the answers to the given queries.
Please note that if course a is a prerequisite of course b and course b is a prerequisite of course c, then, course a is a prerequisite of course c.

Example 1:
Input: n = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
Output: [false,true]
Explanation: course 0 is not a prerequisite of course 1 but the opposite is true.

Example 2:
Input: n = 2, prerequisites = [], queries = [[1,0],[0,1]]
Output: [false,false]
Explanation: There are no prerequisites and each course is independent.

Example 3:
Input: n = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
Output: [true,true]

Example 4:
Input: n = 3, prerequisites = [[1,0],[2,0]], queries = [[0,1],[2,0]]
Output: [false,true]

Example 5:
Input: n = 5, prerequisites = [[0,1],[1,2],[2,3],[3,4]], queries = [[0,4],[4,0],[1,3],[3,0]]
Output: [true,false,true,false]

Constraints:
2 <= n <= 100
0 <= prerequisite.length <= (n * (n - 1) / 2)
0 <= prerequisite[i][0], prerequisite[i][1] < n
prerequisite[i][0] != prerequisite[i][1]
The prerequisites graph has no cycles.
The prerequisites graph has no repeated edges.
1 <= queries.length <= 10^4
queries[i][0] != queries[i][1]
*/


/*
    1) Do a dfs for each query (without DP):
        Time: n * query
        do a dfs for each query
        when there are more number of queries, then it wont scale
        
    2) FloydWarshall-Algorithm
        Time: (n^3) + query
        Space: n^2
        https://leetcode.com/problems/course-schedule-iv/discuss/660509/JavaPython-FloydWarshall-Algorithm-Clean-code-O(n3)
    
    3) DFS + DP
        Preprocess: Do dfs for all nodes initially and fill up the DP array
        a->b means then --> fill a->b true and a->(all niegh of b) as true
        For each query, fetch the answer from the DP array in O(1)
        Time: (n^2) + query
        Space: n^2
*/


/*
// Time: n * query (without DP)
class Solution {
    Graph graph;
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        graph = new Graph(n);
        for (int[] preReq : prerequisites) {
            graph.addEdge(preReq[0], preReq[1]);
        }
        
        List<Boolean> outputList = new ArrayList<Boolean>();
        for(int[] query : queries) {
            outputList.add(dfs(query[0], query[1], new boolean[n]));
        }
        return outputList;
    }
    
    public Boolean dfs(int curr, int req, boolean[] visited) {
        if(curr == req) {
            return true;
        }
        visited[curr] = true;
        List<Integer> neighbours = graph.adjList[curr];
        for(int neigh : neighbours) {
            if(!visited[neigh]) {
                if(dfs(neigh, req, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    class Graph {
        ArrayList<Integer>[] adjList;
        Graph(int V) {
            adjList = new ArrayList[V];
            for(int i = 0; i < V; i++) {
                adjList[i] = new ArrayList<Integer>();
            }
        }
        public void addEdge(int a, int b) {
            adjList[a].add(b);
        }
    }
}
*/


// DFS + DP, Time: (n^2) + query, Space: n^2
class Solution {
    boolean[][] DP;
    Graph graph;
    boolean[] visited;
    
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        graph = new Graph(n);
        for (int[] preReq : prerequisites) {
            graph.addEdge(preReq[0], preReq[1]);
        }
        
        DP = new boolean[n][n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
        
        List<Boolean> outputList = new ArrayList<Boolean>();
        for (int[] query : queries) {
            outputList.add(DP[query[0]][query[1]]);
        }
        return outputList;
    }
    
    public void dfs(int curr) {
        visited[curr] = true;
        List<Integer> neighbours = graph.adjList[curr];
        for (int neigh : neighbours) {
            if (!visited[neigh]) {
                dfs(neigh); 
            }
            
            DP[curr][neigh] = true; // main logic: a->b means then fill a->b true and a->(all niegh of b) as true
            for (int i = 0; i < graph.V; i++) {
                DP[curr][i] = DP[curr][i] || DP[neigh][i];
            }
        }
    }
    
    class Graph {
        int V;
        ArrayList<Integer>[] adjList;
        Graph(int V) {
            this.V = V;
            this.adjList = new ArrayList[V];
            for(int i = 0; i < V; i++) {
                adjList[i] = new ArrayList<Integer>();
            }
        }
        public void addEdge(int a, int b) {
            adjList[a].add(b);
        }
    }
}