/*
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

Example 1:
Input: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
Output: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]

Example 2:
Input: 
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
Output: 
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]

Follow up:
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
*/

class Solution 
{
    public void setZeroes(int[][] matrix) 
    {
        // space = mn (use extra 2d array)
        // space = m+n (use 2 set for row and col)
        // space = 1 (use first row and first col in given array to track zero)
        
        // we can set first row and first col with differnet integer value.. but there can be -infinity to +infinity inside array
        // so use boolean flag to track first row and first col
               
        // Time: 2mn and Space: 1
        int row = matrix.length;
        int col = matrix[0].length;
        boolean firstRowFlag = false, firstColFlag = false;
        
        // track zero
        for(int i = 0; i < row; i++)
        {
            if(matrix[i][0] == 0)
            {
                firstColFlag = true;
                break;
            }
        }      
        for(int i = 0; i < col; i++)
        {
            if(matrix[0][i] == 0)
            {
                firstRowFlag = true;
                break;
            }
        }        
        for(int i = 1; i < row; i++)
        {
            for(int j = 1; j < col; j++)
            {
                if(matrix[i][j] == 0)
                {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }    
            }
        }
                
        // set zero
        for(int i = 1; i < row; i++)
        {
            for(int j = 1; j < col; j++)
            {
                if(matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }
        if(firstRowFlag)
        {
            for(int i = 0; i < col; i++)
                matrix[0][i] = 0;
        }
        if(firstColFlag)
        {
            for(int i = 0; i < row; i++)
                matrix[i][0] = 0;
        }
    }
}