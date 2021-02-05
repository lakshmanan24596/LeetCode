/*
Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices. Y
ou spend 1 second to walk over one edge of the tree. 
Return the minimum time in seconds you have to spend to collect all apples in the tree, starting at vertex 0 and coming back to this vertex.
The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi. 
Additionally, there is a boolean array hasApple, where hasApple[i] = true means that vertex i has an apple; otherwise, it does not have any apple.

Example 1:
Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
Output: 8 
Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.  

Example 2:
Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
Output: 6
Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.  

Example 3:
Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
Output: 0

Constraints:
1 <= n <= 10^5
edges.length == n - 1
edges[i].length == 2
0 <= ai < bi <= n - 1
fromi < toi
hasApple.length == n
*/


/*
    logic: 
        if an apple is present in currNode or in anyChildNode, then include this edge
        otherwise dont include this edge
        For each edge included, time = 2 (collect apple and come back) 
        In ex:1, 4 edges are included. So output = 4 * 2 = 8
    Time: n
    Space: h
*/
class Solution {
    List<Boolean> hasApple;
    ArrayList<Integer>[] adjList;
    boolean[] visited;
    
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        this.hasApple = hasApple;
        this.adjList = new ArrayList[n];
        this.visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {      // store undirected n-ary tree either in adjList or hashMap or n-ary treeNode format
            adjList[edge[0]].add(edge[1]);
            adjList[edge[1]].add(edge[0]);
        }
        return dfs(0);
    }
    
    public int dfs(int curr) {          // post order tree traversal
        visited[curr] = true; 
        int output = 0;
        List<Integer> children = adjList[curr];
        for (int child : children) {    // n-ary tree
            if(!visited[child]) {
                output += dfs(child);
            }
        }
        
        boolean currHasApple = hasApple.get(curr);
        boolean anyChildHasApple = output > 0;
        if ((currHasApple || anyChildHasApple) && (curr != 0)) {   // main logic
            return output + 2;
        } else {
            return output;
        }
    }
}