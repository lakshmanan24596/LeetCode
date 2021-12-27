/*
You are given an integer n, which indicates that there are n courses labeled from 1 to n. 
You are also given an array relations where relations[i] = [prevCoursei, nextCoursei], representing a prerequisite relationship between course prevCoursei and course nextCoursei: course prevCoursei has to be taken before course nextCoursei.

In one semester, you can take any number of courses as long as you have taken all the prerequisites in the previous semester for the courses you are taking.
Return the minimum number of semesters needed to take all courses. If there is no way to take all the courses, return -1.

Example 1:
Input: n = 3, relations = [[1,3],[2,3]]
Output: 2
Explanation: The figure above represents the given graph.
In the first semester, you can take courses 1 and 2.
In the second semester, you can take course 3.

Example 2:
Input: n = 3, relations = [[1,2],[2,3],[3,1]]
Output: -1
Explanation: No course can be studied because they are prerequisites of each other.

Constraints:
1 <= n <= 5000
1 <= relations.length <= 5000
relations[i].length == 2
1 <= prevCoursei, nextCoursei <= n
prevCoursei != nextCoursei
All the pairs [prevCoursei, nextCoursei] are unique.
*/


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