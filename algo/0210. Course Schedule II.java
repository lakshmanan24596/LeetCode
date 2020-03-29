/*
There are a total of n courses you have to take, labeled from 0 to n-1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:
Input: 2, [[1,0]] 
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
             course 0. So the correct course order is [0,1] .

Example 2:
Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
*/

class Solution 
{
    Stack<Integer> stack = new Stack<Integer>();
    boolean[] visited;
    Graph graph;
    int numCourses;
    
    // logic: Do topological sorting
    
    public int[] findOrder(int numCourses, int[][] prerequisites)  
    {
        if(numCourses <= 1)
            return new int[]{0};
        
        this.numCourses = numCourses;
        visited = new boolean[numCourses];
        
        createGraph(prerequisites);
        return topologicalBFS();
    }
    
    public void createGraph(int[][] prerequisites)
    {
        graph = new Graph(numCourses);                                      // create graph        
        for(int i = 0; i < prerequisites.length; i++)
            graph.addEdge(prerequisites[i][1], prerequisites[i][0]);        // add edges
    }
    
    public int[] topologicalBFS()
    {
        Queue<Integer> queue = new LinkedList<Integer>();
        int[] inDegreeArr = new int[numCourses];        
        int[] output = new int[numCourses];
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
                output[count] = curr;
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
        return (count == numCourses) ? output : new int[0];                // if cycle exist in graph, then return empty array
    }
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