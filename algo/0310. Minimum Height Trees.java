/*
For an undirected graph with tree characteristics, we can choose any node as the root. 
The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). 
Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1 :
Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3 

Output: [1]

Example 2 :
Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5 

Output: [3, 4]

Note:
According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
*/

class Solution 
{
    public List<Integer> findMinHeightTrees(int n, int[][] edges) 
    {
        /*
            How many MHTs can a graph have at most? --> answer = atmost 2
        
            1) Brute --> Time = O(n^2) --> pick each node as root and visit all other nodes.
            2) Optimal --> Time = O(n), where V = n and E = n-1
        
            The idea is to eat up all the leaves at the same time, until one/two leaves are left. (Bottom up)
            Explanation: 
                The question is to find the root node, such that we need to find all MHT
                We know that "leaf nodes cannot be root". So in example:2, the leaf nodes are 0,1,2,5.
                Imagine the graph with leaf nodes removed. Now the remaining nodes are 3,4.
                Keep on removing 2nd level leaf, 3rd level leaf, etc.. until 1 or 2 nodes are left which is the answer.
                (Similar to Khans BFS topological sorting)
        */
                
        if(n == 1) {
            return Collections.singletonList(0);
        }
               
        List<HashSet<Integer>> adjList = new ArrayList<HashSet<Integer>>();
        for(int i = 0; i < n; i++) {                        // n nodes
            adjList.add(new HashSet<Integer>());
        }
        for(int i = 0; i < n-1; i++) {                      // n-1 edges
            adjList.get(edges[i][0]).add(edges[i][1]);
            adjList.get(edges[i][1]).add(edges[i][0]);
        }
        
        List<Integer> leaves = new ArrayList<Integer>();
        for(int i = 0; i < n; i++) {                        // 1st level leaves
            if(adjList.get(i).size() == 1) {
                leaves.add(i);
            }
        }
          
        while(n > 2) {
            List<Integer> newLeaves = new ArrayList<Integer>();
            n -= leaves.size();
            
            for(int e1 : leaves) {
                int e2 = adjList.get(e1).iterator().next(); // because only 1 element will be present. So next() is used directly 
                
                //adjList.get(e1).remove(e2);
                adjList.get(e2).remove(e1);                 // set.remove() is O(1). So set is used instead of list.
                
                if(adjList.get(e2).size() == 1) {
                    newLeaves.add(e2);                      // inner level leaves    
                }
            }
            leaves = newLeaves;
        }
        
        return leaves;
    }
}