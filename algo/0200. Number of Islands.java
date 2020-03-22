/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:
Input:
11110
11010
11000
00000
Output: 1

Example 2:
Input:
11000
11000
00100
00011
Output: 3
*/

class Solution 
{
    int row, col;
    boolean[][] visited;  // instead of using visited array, we can also change it in original array itself
    char[][] grid;
    
    int[] moveX = new int[]{0, -1, 0, 1};
    int[] moveY = new int[]{-1, 0, 1, 0};
    int sides = 4;
    int nextX, nextY;
    
    public int numIslands(char[][] grid) 
    {
        int output = 0;
        if(grid == null || grid.length == 0)
            return output;
        
        this.grid = grid;
        this.row = grid.length;
        this.col = grid[0].length;
        this.visited = new boolean[row][col];       
        
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(grid[i][j] == '1' && !visited[i][j])
                {
                    output++;
                    DFS(i, j);
                }
            }
        }
        return output;
    }
    
    public void DFS(int currX, int currY)
    {
        visited[currX][currY] = true;
        
        for(int i = 0; i < sides; i++)
        {
            nextX = currX + moveX[i];
            nextY = currY + moveY[i];
            
            if(nextX >= 0 && nextX < row && nextY >= 0 && nextY < col && 
               grid[nextX][nextY] == '1' && 
               !visited[nextX][nextY])
            {
                DFS(nextX, nextY);
            }
        }
    }
}