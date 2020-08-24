/*
Given a directed acyclic graph of N nodes. Find all possible paths from node 0 to node N-1, and return them in any order.
The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.

Example:
Input: [[1,2],[3],[3],[]]
Output: [[0,1,3],[0,2,3]]
Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3
There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 
Constraints:
The number of nodes in the graph will be in the range [2, 15].
You can print different paths in any order, but you should keep the order of nodes inside one path.
*/

class Solution 
{
    List<List<Integer>> outputList;
    List<Integer> currList;
    int n;
    int[][] graph;
    
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) 
    {
        outputList = new ArrayList<List<Integer>>();
        currList = new ArrayList<Integer>();
        this.graph = graph;
        this.n = graph.length;
        
        currList.add(0);  
        dfs(0);
        return outputList;
    }
    
    public void dfs(int currPos)
    {
        if(currPos == n-1) {
            outputList.add(new ArrayList<>(currList));
            return;
        }

        int adjSize = graph[currPos].length;
        int nextPos; 
        
        for(int i = 0; i < adjSize; i++)
        {
            nextPos = graph[currPos][i];
            
            currList.add(nextPos);
            dfs(nextPos);
            currList.remove(currList.size()-1);
        }
    }
}