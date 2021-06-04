/*
    1) khans topological sorting
    2) dfs and find longest path
    
    time: V+E for both algo
    space: V+E for both algo
*/

class Solution {
    public int minimumSemesters(int n, int[][] relations) {
        Graph graph = new Graph(n);
        int[] indegree = new int[n];
        int u, v;
        Queue<Integer> courses = new LinkedList<Integer>();
        int noOfCourses, currCourse;
        int coursesTaken = 0;
        int semesters = 0;
        
        for (int[] relation : relations) {
            u = relation[0] - 1;
            v = relation[1] - 1;
            graph.addEdge(u, v);
            indegree[v]++;
        }
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                courses.add(i);
            }
        }
        while (!courses.isEmpty()) {
            semesters++;
            noOfCourses = courses.size();
            while (noOfCourses-- > 0) {
                currCourse = courses.remove();
                coursesTaken++;
                for (int nextCourse : graph.adjList[currCourse]) {
                    if (--indegree[nextCourse] == 0) {
                        courses.add(nextCourse);    
                    }
                }
            }
        }
        return (coursesTaken < n) ? -1 : semesters;
    }
}

class Graph {
    int n;
    List<Integer>[] adjList;
    
    Graph(int n) {
        this.n = n;
        this.adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<Integer>();
        }
    }
    
    public void addEdge(int u, int v) {
        adjList[u].add(v);
    }
}