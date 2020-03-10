/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
Now consider if some obstacles are added to the grids. How many unique paths would there be?
An obstacle and empty space is marked as 1 and 0 respectively in the grid.

Note: m and n will be at most 100.

Example 1:
Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2

Explanation:
There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
*/

class Solution 
{
    public static int uniquePathsWithObstacles(int[][] arr) 
    {
        // change in original array itself.. so space = O(1)
        // time = n^2
        
        int row = arr.length;
        int col = arr[0].length;
         
        boolean initialObstracleFound;
        if(arr[0][0] == 1)
        {
        	initialObstracleFound = true;
        	arr[0][0] = 0;
        }
        else
        {
        	initialObstracleFound = false;
        	arr[0][0] = 1;
        }

        // first row
        boolean obstracleFound = initialObstracleFound;
        for(int i=1; i<row; i++)
        {
            if(arr[i][0] == 1) 
                obstracleFound = true;
            arr[i][0] = (obstracleFound) ? 0 : 1;
        }

        // first col
        obstracleFound = initialObstracleFound;
        for(int i=1; i<col; i++)
        {  
            if(arr[0][i] == 1)
                obstracleFound = true;
            arr[0][i] = (obstracleFound) ? 0 : 1;
        }
        
        // main logic
        for(int i=1; i<row; i++)
        {
            for(int j=1; j<col; j++)
            {
                if(arr[i][j] == 1)  // obstracle found
                    arr[i][j] = 0;
                else
                    arr[i][j] = arr[i-1][j] + arr[i][j-1];
            }
        }
        
        return arr[row-1][col-1];
    }
}