/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.

Example:
Consider the following matrix:
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.
Given target = 20, return false.
*/

class Solution 
{
    public boolean searchMatrix(int[][] matrix, int target) 
    {
        // linear search  : n*m
        // divide conquer : log(nm) base 2
        // greedy         : n+m
        
        if(matrix == null || matrix.length == 0)
            return false;
        
        int row = matrix.length;        
        int col = matrix[0].length;       
        int i = 0, j = col-1;               // start from right top corner or (from bottom left corner)
        
        if(target < matrix[0][0] || target > matrix[row-1][col-1])
            return false;   
        
        while(i < row && j >= 0)
        {
           if(target == matrix[i][j])
               return true;
            else if(target < matrix[i][j])
                j--;
            else
                i++;
        }  
        return false;
    }
}