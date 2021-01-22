/*
You are given an m x n grid where each cell can have one of three values:
0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.

Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

Example 1:
Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4

Example 2:
Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

Example 3:
Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 10
grid[i][j] is 0, 1, or 2.
*/

// BFS, Time: V+E ==> (r * c) + (4 * r * c) ==> r * c
class Solution {
    public int orangesRotting(int[][] grid) {
        Queue<Integer> queue = new LinkedList<Integer>();
        int m = grid.length;
        int n = grid[0].length;
        int oneCount = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.add((i * n) + j);     // formula to convert 2D to 1D --> (i * colSize) + j
                } else if (grid[i][j] == 1) {
                    oneCount++;
                }
            }
        }

        int level = 0, queueSize, curr, currX, currY, nextX, nextY;
        int[] dir = new int[] {0, -1, 0, 1, 0};
        while (!queue.isEmpty()) {
            queueSize = queue.size();
            while (queueSize-- > 0) {
                curr = queue.remove();
                currX = curr / n;               // formula to convert 1D to 2D --> [x / colSize][y % colSize]
                currY = curr % n;
                for (int i = 0; i < 4; i++) {
                    nextX = currX + dir[i];
                    nextY = currY + dir[i + 1];
                    if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && grid[nextX][nextY] == 1) {
                        grid[nextX][nextY] = 2;
                        oneCount--;
                        queue.add((nextX * n) + nextY);
                    }
                }
            } 
            if (queue.size() > 0) {
                level++;
            }
        }
        return (oneCount != 0) ? -1 : level;
    }
}
