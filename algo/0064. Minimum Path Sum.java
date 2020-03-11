/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
Note: You can only move either down or right at any point in time.

Example:
Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
*/

class Solution 
{
    int row;
    int col;
    
    public int minPathSum(int[][] grid) 
    {
        row = grid.length;
        col = grid[0].length;
        
        return minPathSumUtil(grid);
//      return minPathSumUtil(grid, 0, 0);
    }
  
    
//     RECURSION  
//     public int minPathSumUtil(int[][] grid, int x, int y)
//     {
//         if(x >= row || y >= col)
//             return Integer.MAX_VALUE;
        
//         if(x == row-1 && y == col-1)
//             return grid[x][y];
            
//         int right = minPathSumUtil(grid, x, y+1);
//         int down = minPathSumUtil(grid, x+1, y);    
        
//         return grid[x][y] + Math.min(right, down);
//     }
        
//  DP    
    public int minPathSumUtil(int[][] grid)
    {
        int[][] DP = new int[row][col];
        DP[0][0] = grid[0][0];        
        
        for(int i = 1; i < col; i++)
        {   
           DP[0][i] = grid[0][i] + DP[0][i-1];
        }       
        for(int i = 1; i < row; i++)
        {    
            DP[i][0] = grid[i][0] + DP[i-1][0];
        }
               
        for(int i = 1; i < row; i++)
        {
            for(int j = 1; j < col; j++)
            {
                DP[i][j] = grid[i][j] + Math.min(DP[i-1][j] , DP[i][j-1]);
            }
        }       
        return DP[row-1][col-1];
    }
}