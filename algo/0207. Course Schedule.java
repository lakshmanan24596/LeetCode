/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:
Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.

Example 2:
Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
 
Constraints:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
1 <= numCourses <= 10^5
*/

class Solution 
{
    Stack<Integer> stack = new Stack<Integer>();
    boolean[] visited;
    Graph graph;
    int numCourses;
    
    // logic: Do topological sorting and if cycle exist, then return false. Else return true.
    
    public boolean canFinish(int numCourses, int[][] prerequisites) 
    {
        if(numCourses <= 1)
            return true;
        
        this.numCourses = numCourses;
        visited = new boolean[numCourses];
        graph = new Graph(numCourses);                                      // create graph
        
        for(int i = 0; i < prerequisites.length; i++)
            graph.addEdge(prerequisites[i][1], prerequisites[i][0]);        // add edges
        
        return topologicalBFS();
    }
    
    public boolean topologicalBFS()
    {
        Queue<Integer> queue = new LinkedList<Integer>();
        int[] inDegreeArr = new int[numCourses];        
        List<Integer> currAdjList;
        
        for(int i = 0; i < numCourses; i++)                                 
        {
            currAdjList = graph.adjList[i];                       
            for(int adjElement : currAdjList)
                inDegreeArr[adjElement]++;                                  // form inDegree array
        }
        
        for(int i = 0; i < numCourses; i++)
            if(inDegreeArr[i] == 0) 
                queue.add(i);                                               // add inDegree = 0 elements in queue initially
        
        int count = 0;
        while(!queue.isEmpty())
        {
            int size = queue.size();
            for(int i = 0; i < size; i++)
            {
                int curr = queue.remove();
                count++;                                                    
                
                currAdjList = graph.adjList[curr];
                for(int next : currAdjList)
                {
                    inDegreeArr[next]--;
                    if(inDegreeArr[next] == 0)
                        queue.add(next);
                }
            }
        }
        
        // now count will be number of elements added/removed in the queue
        return count == numCourses;
    }
    
    class Graph
    {
        int V;
        List<Integer> adjList[];
        
        Graph(int V)
        {
            this.V = V;
            adjList = new ArrayList[V];
            
            for(int i = 0; i < V; i++)
                adjList[i] = new ArrayList<Integer>();    
        }
        
        public void addEdge(int n1, int n2)
        {
            adjList[n1].add(n2);
        }
    }
}