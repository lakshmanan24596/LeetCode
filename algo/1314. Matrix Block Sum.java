/*
Given a m * n matrix mat and an integer K, return a matrix answer where each answer[i][j] is the sum of all elements mat[r][c] for i - K <= r <= i + K, j - K <= c <= j + K, and (r, c) is a valid position in the matrix.

Example 1:
Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
Output: [[12,21,16],[27,45,33],[24,39,28]]

Example 2:
Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2
Output: [[45,45,45],[45,45,45],[45,45,45]]

Constraints:
m == mat.length
n == mat[i].length
1 <= m, n, K <= 100
1 <= mat[i][j] <= 100
*/

class Solution 
{
    /* https://leetcode.com/problems/matrix-block-sum/discuss/477036/JavaPython-3-PrefixRange-sum-w-analysis-similar-to-LC-30478
       logic: rectangle sum in matrix
              cumulative sum is initially found and inclusion-exclusion idea is use
    */
    
    public int[][] matrixBlockSum(int[][] matrix, int K) 
    {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] DP = new int[row+1][col+1];     // contains sum of all cells in the rectangle from (0,0) to (i,j)
        // where first row and col will have values as 0 to handle out of boundary elements
        
        int k, l;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                k = i+1;
                l = j+1;
                DP[k][l] = DP[k][l-1] + DP[k-1][l] - DP[k-1][l-1] + matrix[i][j];   // using cumulative sum and inclusion-exclusion idea
            }
        }
        
        int r1, r2, c1, c2;
        for(int i = 0; i < row; i++) 
        { 
            for(int j = 0; j < col; j++) 
            {
                r1 = Math.max(0, i-K);
                c1 = Math.max(0, j-K);
                
                r2 = Math.min(row, i+K+1);
                c2 = Math.min(col, j+K+1);
                
                matrix[i][j] = DP[r2][c2] - DP[r2][c1] - DP[r1][c2] + DP[r1][c1];   // main logic
            }
        }
        return matrix;
    }
}
