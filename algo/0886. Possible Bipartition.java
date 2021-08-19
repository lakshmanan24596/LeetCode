/*
Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
Each person may dislike some other people, and they should not go into the same group. 
Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
Return true if and only if it is possible to split everyone into two groups in this way.

Example 1:
Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: group1 [1,4], group2 [2,3]

Example 2:
Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false

Example 3:
Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
Output: false
 
Constraints:
1 <= N <= 2000
0 <= dislikes.length <= 10000
dislikes[i].length == 2
1 <= dislikes[i][j] <= N
dislikes[i][0] < dislikes[i][1]
There does not exist i != j for which dislikes[i] == dislikes[j].
*/


/*
    Graph coloring with 2 colors
    same as 785. Is Graph Bipartite?
*/
class Solution 
{
    ArrayList<Integer>[] graph;
    int[] color;    // -1 blue, 0 not visited, 1 red
    int n;
    
    public boolean possibleBipartition(int n, int[][] dislikes) 
    {
        this.n = n;
        createGraph(dislikes);
        return isBipartite();
    }
    
    public void createGraph(int[][] dislikes)
    {
        graph =  new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        
        int person1, person2;
        for(int[] dislike : dislikes) 
        {
            person1 = dislike[0] - 1;
            person2 = dislike[1] - 1;
            graph[person1].add(person2);
            graph[person2].add(person1);
        }
    }
    
    public boolean isBipartite() 
    {
        color = new int[n];
        for(int i = 0; i < n; i++) {
           if(color[i] == 0) {
               if(!dfs(i, 1)) {
                   return false;
               }
           }
        }
        return true;
    }
    
    public boolean dfs(int curr, int currColor)
    {
        color[curr] = currColor;       
        ArrayList<Integer> edges = graph[curr];
        
        for(int next : edges)
        {
            if(color[next] != 0 && color[next] == color[curr]) { // if already visited and same color, then "odd length cycle" formed
                return false;
            } 
            if(color[next] == 0) {
                if(!dfs(next, -currColor)) {    // reverse the color
                    return false;
                }
            }
        }
        return true;
    }
}
