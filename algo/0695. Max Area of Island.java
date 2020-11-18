/*
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) 
You may assume all four edges of the grid are surrounded by water.
Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6.
Note the answer is not 11, because the island must be connected 4-directionally.

Example 2:
[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.

Note: The length of each dimension in the given grid does not exceed 50.
*/

class Solution 
{
    boolean[][] visited;
    int[] moveX = new int[] {-1, 0, 1, 0};
    int[] moveY = new int[] {0, -1, 0, 1};
    int[][] grid;
    int row, col;
    
    public int maxAreaOfIsland(int[][] grid)    // Time: O((n^2) * 4) and Space: O(n^2)
    {
        int maxArea = 0, currArea;
        if(grid == null || grid.length == 0) {
            return maxArea;
        }
        
        this.grid = grid;
        this.row = grid.length;
        this.col = grid[0].length;
        this.visited = new boolean[row][col];   // instead of visited array, we can change 1 to 0 in given array itself
        
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(grid[i][j] == 1 && !visited[i][j])
                {
                    currArea = dfs(i, j);
                    maxArea = Math.max(maxArea, currArea);
                }
            }
        }
        return maxArea;
    }
    
    public int dfs(int currX, int currY)
    {
        visited[currX][currY] = true;
        int nextX, nextY;
        int output = 1;
        
        for(int i = 0; i < moveX.length; i++)
        {
            nextX = currX + moveX[i];
            nextY = currY + moveY[i];
            
            if(nextX >= 0 && nextX < row && nextY >= 0 && nextY < col
              && !visited[nextX][nextY]
              && grid[nextX][nextY] == 1)
            {
                output += dfs(nextX, nextY);
            }
        }
        return output;
    }
}