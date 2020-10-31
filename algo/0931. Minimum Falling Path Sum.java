/*
Given a square array of integers A, we want the minimum sum of a falling path through A.
A falling path starts at any element in the first row, and chooses one element from each row.  
The next row's choice must be in a column that is different from the previous row's column by at most one.

Example 1:
Input: [[1,2,3],[4,5,6],[7,8,9]]
Output: 12

Explanation: 
The possible falling paths are:
[1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
[2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
[3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
The falling path with the smallest sum is [1,4,7], so the answer is 12.

Constraints:
1 <= A.length == A[0].length <= 100
-100 <= A[i][j] <= 100
*/


/*
// DFS solution: Time: O(row * (3 ^ row)), because the number of level in state space tree is "row" and in each level work done is "exponential with power 3".

class Solution 
{
    int[][] A;
    int size;
    public int minFallingPathSum(int[][] A) 
    {
        this.A = A;
        this.size = A.length;
        
        int minSum = Integer.MAX_VALUE;
        for(int col = 0; col < size; col++) {
            minSum = Math.min(minSum, dfs(0, col));
        }
        return minSum;
    }
    
    public int dfs(int row, int col) 
    {
        if(row == size-1) {
            return A[row][col];
        }
        
        int minSum = Integer.MAX_VALUE;
        int currSum = A[row][col];
        int nextRow = row + 1;
        
        for(int nextCol = col - 1; nextCol <= col + 1; nextCol++) 
        {
            if(nextCol >= 0 && nextCol < size) 
            {
                minSum = Math.min(minSum, currSum + dfs(nextRow, nextCol));
            }
        }
        return minSum;
    }
}
*/


/*  DP solution: 
    Time: O(3*row*col)
    Space: 
        1) O(row*col) for extra DP matrix. 
        2) O(2*row) if we use DP arr for currRow and prevRow (Space optimized)
        3) O(1) if we change use given matrix as DP */

class Solution 
{
    public int minFallingPathSum(int[][] A) 
    {
        int size = A.length;
        int minSum, currSum, nextRow; 
        
        for(int row = 1; row < size; row++)     // start from row = 1 because (DP of firstrow = A of firstRow) and no changes in it
        {
            for(int col = 0; col < size; col++) 
            {
                minSum = Integer.MAX_VALUE;
                currSum = A[row][col];
                nextRow = row - 1;  // fetch from prevRow
                
                for(int nextCol = col - 1; nextCol <= col + 1; nextCol++) 
                {
                    if(nextCol >= 0 && nextCol < size) // out of boundary condition
                    {
                        minSum = Math.min(minSum, currSum + A[nextRow][nextCol]);
                    }
                }
                A[row][col] = minSum;
            }
        }
        
        minSum = Integer.MAX_VALUE;
        for(int i = 0; i < size; i++) {
            minSum = Math.min(minSum, A[size-1][i]);    // output is minValue present in last row
        }
        return minSum;
    }
}
