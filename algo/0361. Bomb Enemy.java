/*
Given an m x n matrix grid where each cell is either a wall 'W', an enemy 'E' or empty '0', return the maximum enemies you can kill using one bomb. 
You can only place the bomb in an empty cell.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since it is too strong to be destroyed.

Example 1:
Input: grid = [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
Output: 3

Example 2:
Input: grid = [["W","W","W"],["0","0","0"],["E","E","E"]]
Output: 1
 
Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 500
grid[i][j] is either 'W', 'E', or '0'.
*/


/*
    1) brute:
        time: O(r * c * (r + c))
        space: 1
        
    2) Store enemyCount:
       iterate row-wise and iterate col-wise, "till we find a wall or move out of grid"
       once we have found a wall or move out of grid, iterate from startPos to currPos and fill enemies count
       output = row-wise count + col-wise count in each enemy cell
       
       time: 2*r*c + 2*r*c (because each cell is visited twice in each iteration)
       space: r*c
       
    3) same logic as above
        implementation varies: https://leetcode.com/problems/bomb-enemy/solution/
*/

class Solution {
    public int maxKilledEnemies(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] killedEnemies = new int[rows][cols];
        int enemyCount = 0;
        int startPos;
        int maxKill = 0;
        
        for (int i = 0; i < rows; i++) {                                // iterate row-wise
            startPos = 0;
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 'E') {
                    enemyCount++;
                }
                if (grid[i][j] == 'W' || j == cols - 1) {               // found a wall or move out of grid 
                    for (int k = startPos; k <= j; k++) {
                        if (grid[i][k] == '0') {
                            killedEnemies[i][k] = enemyCount;
                        }
                    }
                    startPos = j + 1;
                    enemyCount = 0;
                }
            }
        }
        
        for (int j = 0; j < cols; j++) {                                // iterate col-wise
            startPos = 0;
            for (int i = 0; i < rows; i++) {
                if (grid[i][j] == 'E') {
                    enemyCount++;
                }
                if (grid[i][j] == 'W' || i == rows - 1) {               // found a wall or move out of grid 
                    for (int k = startPos; k <= i; k++) {
                        if (grid[k][j] == '0') {
                            killedEnemies[k][j] += enemyCount;          // row-wise count + col-wise count
                            maxKill = Math.max(maxKill, killedEnemies[k][j]);
                        }
                    }
                    startPos = i + 1;
                    enemyCount = 0;
                }
            }
        }
        return maxKill;
    }
}