/*
Given a rows x cols matrix grid representing a field of cherries. 
Each cell in grid represents the number of cherries that you can collect.
You have two robots that can collect cherries for you, Robot #1 is located at the top-left corner (0,0) , and Robot #2 is located at the top-right corner (0, cols-1) of the grid.

Return the maximum number of cherries collection using both robots  by following the rules below:
From a cell (i,j), robots can move to cell (i+1, j-1) , (i+1, j) or (i+1, j+1).
When any robot is passing through a cell, It picks it up all cherries, and the cell becomes an empty cell (0).
When both robots stay on the same cell, only one of them takes the cherries.
Both robots cannot move outside of the grid at any moment.
Both robots should reach the bottom row in the grid.

Example 1:
Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
Output: 24
Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
Total of cherries: 12 + 12 = 24.

Example 2:
Input: grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
Output: 28
Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (1 + 9 + 5 + 2) = 17.
Cherries taken by Robot #2, (1 + 3 + 4 + 3) = 11.
Total of cherries: 17 + 11 = 28.

Example 3:
Input: grid = [[1,0,0,3],[0,0,0,3],[0,0,3,3],[9,0,3,3]]
Output: 22

Example 4:
Input: grid = [[1,1],[1,1]]
Output: 4

Constraints:
rows == grid.length
cols == grid[i].length
2 <= rows, cols <= 70
0 <= grid[i][j] <= 100 
*/



/*
    logic: 
        we should move both the robots synchronously
        both the robots should not cross. 
        ie; y1 < y2 is must
    
    DP memo
    DP states: m, n, n
    time: O(m * n * n * 3 * 3)
    space: O(m * n * n)
    
    curr row depends only on prev row
    so we can apply space optimization and reduce space to O(n * n)
*/

class Solution {
    int[][] grid;
    int m, n;
    Integer[][][] DP;
    
    public int cherryPickup(int[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        this.DP = new Integer[m][n][n];
        return dfs(0, 0, n-1);
    }
    
    public int dfs(int x, int y1, int y2) {
        if (x == m) {
            return 0;
        }
        if (DP[x][y1][y2] != null) {
            return DP[x][y1][y2];
        }
        int output = Integer.MIN_VALUE;
        int currOutput;
        int ny1, ny2;                                               // next y1, next y2
        
        for (int i = -1; i <= 1; i++) {                             // 3 directions (leftBottom, bottom, rightBottom)
            for (int j = -1; j <= 1; j++) { 
                ny1 = y1 + i;
                ny2 = y2 + j;
                if (ny1 >= 0 && ny1 < n && ny2 >= 0 && ny2 < n) {
                    if (ny1 < ny2) {                                // main condition: both the robots should not cross
                        currOutput = dfs(x + 1, ny1, ny2);
                        output = Math.max(output, currOutput); 
                    }
                }
            }
        }
        output += grid[x][y1] + grid[x][y2];
        DP[x][y1][y2] = output;
        return output;
    }
}