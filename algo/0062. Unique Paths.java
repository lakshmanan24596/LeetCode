/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
How many possible unique paths are there?

Example 1:
Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right

Example 2:
Input: m = 7, n = 3
Output: 28

Constraints:
1 <= m, n <= 100
It's guaranteed that the answer will be less than or equal to 2 * 10 ^ 9.
*/

class Solution 
{
    // 1) recursion
    // 2) DP tabulation
    // 3) DP space optimized
    // 4) combination formula 
    
    public int uniquePaths(int row, int col) 
    {
        int[][] DP = new int[row][col];   
        DP[0][0] = 1;
        
        for(int i=1; i<row; i++)
            DP[i][0] = 1;
        
        for(int i=1; i<col; i++)
            DP[0][i] = 1;
        
        for(int i=1; i<row; i++)
        {
            for(int j=1; j<col; j++)
            {
                DP[i][j] = DP[i-1][j] + DP[i][j-1];
            }
        }
        
        return DP[row-1][col-1];
        
        // https://www.geeksforgeeks.org/count-possible-paths-top-left-bottom-right-nxm-matrix/
        // this ques forms a series:::  {1,1,1,1}
        //                              {1,2,3,4}
        //                              {1,3,6,10}
        //                              {1,4,10,20}
        // so we can solve this using 
        //      1) O(N) space
        //      2) combination formula  --> (m+n-2) C (n-1)
        
    }
}