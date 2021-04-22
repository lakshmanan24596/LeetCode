/*
You are given an m x n grid grid of values 0, 1, or 2, where:
each 0 marks an empty land that you can pass by freely,
each 1 marks a building that you cannot pass through, and
each 2 marks an obstacle that you cannot pass through.

You want to build a house on an empty land that reaches all buildings in the shortest total travel distance. 
You can only move up, down, left, and right.

Return the shortest travel distance for such a house. If it is not possible to build such a house according to the above rules, return -1.

The total travel distance is the sum of the distances between the houses of the friends and the meeting point.
The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.


Example 1:
Input: grid = [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
Output: 7
Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2).
The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal.
So return 7.

Example 2:
Input: grid = [[1,0]]
Output: 1

Example 3:
Input: grid = [[1]]
Output: -1

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 100
grid[i][j] is either 0, 1, or 2.
There will be at least one building in the grid.
*/



/*
    BFS
    Logic: from all buildings, do BFS one by one to reach all empty cells
        (instead of moving from empty cell to building, we move from building to all other empty cells)
        count the distance of all BFS iteration
        also we need to check whether reachable count == totalBuildings
        
    time: O(noOfBuildings * r*c)
    space: r*c
    
    Further optimizations:
    1) We can also do initial checking --> find whether all buildings are reachable or not, from any of the empty cell.
    if not reachable, then return -1 without doing the main processing.
    
    2) Additional pruning: No need visited array in BFS
    https://leetcode.com/problems/shortest-distance-from-all-buildings/discuss/76880/36-ms-C%2B%2B-solution
    "walk only onto the cells that were reachable from all previous buildings"
*/

class Solution {
    int[][] grid;
    int rows, cols;
    int[][] dist;
    int[][] reachable;
    int[] dir = new int[] {0, -1, 0, 1, 0};
    
    public int shortestDistance(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.dist = new int[rows][cols];
        this.reachable = new int[rows][cols];
        int totalBuildings = 0;
        int minDist = Integer.MAX_VALUE;
        
        for (int i = 0; i < rows; i++) {                                       
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    bfs(i, j);                                                  // do BFS from all building
                    totalBuildings++;
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0 && reachable[i][j] == totalBuildings) {     // main logic
                    minDist = Math.min(minDist, dist[i][j]);
                }
            }
        }
        return (minDist == Integer.MAX_VALUE) ? -1 : minDist;
    }
    
    public void bfs(int x, int y) {                                             // fill dist[] and reachable[]
        Queue<int[]> queue = new LinkedList<int[]>();
        boolean[][] visited = new boolean[rows][cols];
        queue.add(new int[] {x, y});
        int level = 0;
        int queueSize;
        int[] currPos, nextPos;
        int nextX, nextY;
        
        while (!queue.isEmpty()) {
            queueSize = queue.size();
            level++;
            while (queueSize-- > 0) {
                currPos = queue.remove();
                for (int i = 0; i < 4; i++) {
                    nextX = currPos[0] + dir[i];
                    nextY = currPos[1] + dir[i + 1];
                    if (nextX >= 0 && nextX < rows && nextY >= 0 && nextY < cols
                            && grid[nextX][nextY] == 0 
                            && !visited[nextX][nextY]) {
                        nextPos = new int[] {nextX, nextY};
                        visited[nextX][nextY] = true;
                        queue.add(nextPos);
                        dist[nextX][nextY] += level;                            // main logic
                        reachable[nextX][nextY]++;                              // main logic
                    }
                }
            }
        }
    }
}