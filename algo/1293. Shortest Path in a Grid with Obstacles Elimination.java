/*
Given a m * n grid, where each cell is either 0 (empty) or 1 (obstacle). 
In one step, you can move up, down, left or right from and to an empty cell.
Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m-1, n-1) given that you can eliminate at most k obstacles. 
If it is not possible to find such walk return -1.

Example 1:
Input: 
grid = 
[[0,0,0],
 [1,1,0],
 [0,0,0],
 [0,1,1],
 [0,0,0]], 
k = 1
Output: 6
Explanation: 
The shortest path without eliminating any obstacle is 10. 
The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).

Example 2:
Input: 
grid = 
[[0,1,1],
 [1,1,1],
 [1,0,0]], 
k = 1
Output: -1
Explanation: 
We need to eliminate at least two obstacles to find such a walk.

Constraints:
grid.length == m
grid[0].length == n
1 <= m, n <= 40
1 <= k <= m*n
grid[i][j] == 0 or 1
grid[0][0] == grid[m-1][n-1] == 0
*/


/*
    Logic: Normal BFS with each index can be visited k+1 times
    All the cells would be visited more than once (k + 1 times) as we could reach same cell with more distance but less obstacles which could be helpful later in traversal.
    
    Consider ex-1, for index (2,2):
        output = 3 for k = 1 
        output = 5 for k = 0
        so we need to check from 0 to k value
        
    Time: r*c*k
    Space: r*c*k
*/
class Solution {
    public int shortestPath(int[][] grid, int k) {
        int row = grid.length;
        int col = grid[0].length;
        if (k >= ((row + col) - 3)) {   // extra condition if k is larger
            return ((row + col) - 2);
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(new Node(0, 0, 0));
        boolean[][][] visited = new boolean[row][col][k + 1];                       // main logic
        int[] dir = new int[] {0, -1, 0, 1, 0};
        Node curr;
        int nextX, nextY, nextK;
        int level = 0, queueSize;
        
        while (!queue.isEmpty()) {
            queueSize = queue.size();
            while (queueSize-- > 0) {
                curr = queue.remove();
                if (curr.x == row - 1 && curr.y == col - 1) {
                    return level;
                }
                for (int i = 0; i < 4; i++) {
                    nextX = curr.x + dir[i];
                    nextY = curr.y + dir[i + 1];
                    if (nextX >= 0 && nextX < row && nextY >= 0 && nextY < col) {
                        nextK = curr.k + grid[nextX][nextY];                        // calculate obstracles
                        if (nextK <= k && !visited[nextX][nextY][nextK]) {          // main logic
                            visited[nextX][nextY][nextK] = true;
                            queue.add(new Node(nextX, nextY, nextK));
                        }
                    }
                }
            }
            level++;
        }
        return -1;
    }
    
    class Node {
        int x, y, k;
        Node(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
        }
    }
}


/*
    Same as above logic
    but instead of boolean visited arr (r, c, k), we are using integer minObstacles arr (r, c)
    Time: r*c*k
    Space: r*c
*/
/*
class Solution {
    public int shortestPath(int[][] grid, int k) {
        int row = grid.length;
        int col = grid[0].length;
        if (k >= ((row + col) - 3)) {   // extra condition if k is larger
            return ((row + col) - 2);
        }
        int[][] minObstacles = new int[row][col]; 
        for (int[] currObstacleArr : minObstacles) {
            Arrays.fill(currObstacleArr, Integer.MAX_VALUE);
        }
        minObstacles[0][0] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 0});
        int[] dir = new int[] {0, -1, 0, 1, 0};
        int[] curr;
        int nextX, nextY, nextK;
        int level = 0, queueSize;
        
        while (!queue.isEmpty()) {
            queueSize = queue.size();
            while (queueSize-- > 0) {
                curr = queue.remove();
                if (curr[0] == row - 1 && curr[1] == col - 1) {
                    return level;
                }
                for (int i = 0; i < 4; i++) {
                    nextX = curr[0] + dir[i];
                    nextY = curr[1] + dir[i + 1];
                    if (nextX >= 0 && nextX < row && nextY >= 0 && nextY < col) {
                        nextK = curr[2] + grid[nextX][nextY];                       // calculate obstracles
                        if (nextK <= k && nextK < minObstacles[nextX][nextY]) {     // main logic
                            minObstacles[nextX][nextY] = nextK;
                            queue.add(new int[]{nextX, nextY, nextK});
                        }
                    }
                }
            }
            level++;
        }
        return -1;  
    }
}
*/