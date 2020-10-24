/*
Given an integer matrix, find the length of the longest increasing path.
From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:
Input: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
Output: 4 
Explanation: The longest increasing path is [1, 2, 6, 9].

Example 2:
Input: nums = 
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
] 
Output: 4 
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
*/

class Solution 
{
    /*
    1) DFS + memorization: (Time = O(row*col), Space = O(row*col)) // DFS + memorization. (Time = O(row*col), Space = O(row*col))
    2) Topological sorting: https://leetcode.com/problems/longest-increasing-path-in-a-matrix/discuss/144558/Java-BFS-Topological-Sort
    */
    
    int[][] matrix, DP;
    int[] sideX = new int[] {0, 1, 0, -1};
    int[] sideY = new int[] {1, 0, -1, 0};
    int row, col;
    
    public int longestIncreasingPath(int[][] matrix) 
    {
        int output = 0;
        if(matrix == null || matrix.length == 0) {
            return output;
        }
        this.matrix = matrix;
        this.row =  matrix.length;
        this.col = matrix[0].length;
        this.DP = new int[row][col];
        
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                output = Math.max(output, recur(i, j));
            }
        }
        return output;
    }
    
    public int recur(int currX, int currY) 
    {
        if(DP[currX][currY] != 0) {
            return DP[currX][currY];
        }
        int nextX, nextY, output = 1, currOutput = 1;
        
        for(int i = 0; i < sideX.length; i++) 
        {
            nextX = currX + sideX[i];
            nextY = currY + sideY[i];
            
            if(nextX >= 0 && nextX < row && nextY >= 0 && nextY < col 
               && matrix[nextX][nextY] > matrix[currX][currY])
            {
                currOutput = recur(nextX, nextY) + 1;
                if(currOutput > output) {
                    output = currOutput;
                }
            }
        }
        return DP[currX][currY] = output;
    }
}
