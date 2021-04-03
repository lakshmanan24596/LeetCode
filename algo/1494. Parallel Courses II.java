/*
Given the integer n representing the number of courses at some university labeled from 1 to n, and the array dependencies where dependencies[i] = [xi, yi] represents a prerequisite relationship, that is, the course xi must be taken before the course yi. Also, you are given the integer k.
In one semester you can take at most k courses as long as you have taken all the prerequisites for the courses you are taking.
Return the minimum number of semesters to take all courses. It is guaranteed that you can take all courses in some way.

Example 1:
Input: n = 4, dependencies = [[2,1],[3,1],[1,4]], k = 2
Output: 3 
Explanation: The figure above represents the given graph. In this case we can take courses 2 and 3 in the first semester, then take course 1 in the second semester and finally take course 4 in the third semester.

Example 2:
Input: n = 5, dependencies = [[2,1],[3,1],[4,1],[1,5]], k = 2
Output: 4 
Explanation: The figure above represents the given graph. In this case one optimal way to take all courses is: take courses 2 and 3 in the first semester and take course 4 in the second semester, then take course 1 in the third semester and finally take course 5 in the fourth semester.

Example 3:
Input: n = 11, dependencies = [], k = 2
Output: 6

Constraints:
1 <= n <= 15
1 <= k <= n
0 <= dependencies.length <= n * (n-1) / 2
dependencies[i].length == 2
1 <= xi, yi <= n
xi != yi
All prerequisite relationships are distinct, that is, dependencies[i] != dependencies[j].
The given graph is a directed acyclic graph.
*/


/*
    https://leetcode.com/problems/parallel-courses-ii/discuss/769196/Backtracking-Topological-Sort-with-Combinations  
    https://leetcode.com/problems/parallel-courses-ii/discuss/719159/DP-solution-with-memoization-and-bitmasks-With-C%2B%2B-code-20-ms-runtime
    
    Logic: DP + bitmask + backtracking
    (note: greedy wont work)
    
    time: O(3^n)
    space: O(2^n)
    
    DP state is coursesTakenMask, which is 2^n
    in each state, we need to check all combinations of length k
    ie; we need to generate all submask of mask --> https://cp-algorithms.com/algebra/all-submasks.html
*/

class Solution {
    int allCoursesMask;
    int k, n;
    List<Integer>[] adjList;
    Integer[] cacheMinSemCount;
    
    public int minNumberOfSemesters(int n, int[][] dependencies, int k) {
        this.k = k;
        this.n = n;
        this.allCoursesMask = (1 << n) - 1; 
        this.adjList = new ArrayList[n];
        this.cacheMinSemCount = new Integer[allCoursesMask + 1];
        
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<Integer>();
        }
        for (int[] dependency : dependencies) {
            adjList[dependency[0] - 1].add(dependency[1] - 1);
        }
        return findMinNumberOfSemesters(0);
    }
    
    public int findMinNumberOfSemesters(int coursesTakenMask) {
        if (coursesTakenMask == allCoursesMask) {
            return 0;
        }
        if (cacheMinSemCount[coursesTakenMask] != null) {
            return cacheMinSemCount[coursesTakenMask];
        }
        int minSemCount = Integer.MAX_VALUE, currSemCount;
        int availCoursesMask = 0, nextMask;
        int[] indegree = new int[n];
        
        for (int i = 0; i < n; i++) {                                        // calculate indegree for all nodes
            if ((coursesTakenMask & (1 << i)) == 0) {                        // course "i" is not taken
                List<Integer> nextCourses = adjList[i];
                for (int nextCourse : nextCourses) {
                    indegree[nextCourse]++;
                }
            }
        }
        for (int i = 0; i < n; i++) {                                        // find the available courses
            if (indegree[i] == 0 && ((coursesTakenMask & (1 << i)) == 0)) {  // indegree 0 and course "i" is not taken 
                availCoursesMask |= (1 << i);
            }
        }
        
        if (Integer.bitCount(availCoursesMask) <= k) {                       // main logic
            nextMask = coursesTakenMask | availCoursesMask;                  // take all available courses
            minSemCount = 1 + findMinNumberOfSemesters(nextMask);
        } else {
            for (int subMask = availCoursesMask; subMask > 0; subMask = (subMask - 1) & availCoursesMask) {
                if (Integer.bitCount(subMask) == k) {                        // take all combinations of subsets of length k
                    nextMask = coursesTakenMask | subMask;
                    currSemCount = 1 + findMinNumberOfSemesters(nextMask);
                    minSemCount = Math.min(minSemCount, currSemCount);
                }
            }
        }
        return cacheMinSemCount[coursesTakenMask] = minSemCount;
    }
}



/*
// GREEDY
// DOESNT WORK FOR ALL CASES

class Solution {
    public int minNumberOfSemesters(int n, int[][] dependencies, int k) {
        int[] indegree = new int[n];
        Graph graph = new Graph(n);
        int minSemesters = 0;
        Queue<Integer> coursesQueue = new LinkedList<Integer>();
        
        for (int[] dependency : dependencies) {
            graph.addEdge(dependency[0] - 1, dependency[1] - 1);
        }
        for (int i = 0; i < n; i++) {
            if (graph.indegree[i] == 0) {
                coursesQueue.add(i);
            }
        }
        
        while (!coursesQueue.isEmpty()) {
            int noOfCourses = coursesQueue.size();
            minSemesters += ((int) Math.ceil(((double) noOfCourses) / k));      // main logic
            while (noOfCourses-- > 0) {
                int course = coursesQueue.remove();
                List<Integer> nextCourses = graph.adjList[course];
                for (int nextCourse : nextCourses) {
                    graph.indegree[nextCourse]--;
                    if (graph.indegree[nextCourse] == 0) {
                        coursesQueue.add(nextCourse);
                    }
                }
            }
        }
        return minSemesters;
    }
    
    class Graph {
        List<Integer>[] adjList;
        int noOfNodes;
        int[] indegree;
        
        Graph(int noOfNodes) {
            this.noOfNodes = noOfNodes;
            this.adjList = new ArrayList[noOfNodes];
            indegree = new int[noOfNodes];
            for (int i = 0; i < noOfNodes; i++) {
                adjList[i] = new ArrayList<Integer>();
            }
        }
        
        public void addEdge(int a, int b) {
            adjList[a].add(b);
            indegree[b]++;
        }
    }
}
*/