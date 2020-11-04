/*
Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.
Example:
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output:  [1,2,4,7,5,3,6,8,9]
Explanation:
Note:
The total number of elements of the given matrix will not exceed 10,000.
*/

class Solution 
{
    public int[] findDiagonalOrder(int[][] matrix) 
    {
        if(matrix == null) {
            return null;
        }    
        int row = matrix.length;
        if(row == 0) {
            return new int[0];
        }
        int col = matrix[0].length;
        int size = row * col;        
        int[] outputArr = new int[size];
        int i = 0, j = 0;
        
        for(int outputIndex = 0; outputIndex < size; outputIndex++)
        {
            outputArr[outputIndex] = matrix[i][j];
            if ((i + j) % 2 == 0)  // up
            {
                if(j == col - 1) {
                    i++;   
                }
                else if(i == 0) { 
                    j++; 
                }
                else { 
                    i--; 
                    j++; 
                }
            } 
            else  // down 
            {
                if(i == row - 1) { 
                    j++; 
                }
                else if(j == 0) { 
                    i++; 
                }
                else { 
                    i++; 
                    j--;
                }
            }   
        }   
        return outputArr;
    }
}
