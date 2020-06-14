/*
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:
Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
*/

class Solution 
{
    public int maximalSquare(char[][] matrix) 
    {
        // 1) O(n^4), O(1) --> BFS --> pick each element and do BFS
        // 2) O(n^2), O(n^2) --> DP --> 1 + Min(left, topLeft, top);
        // 3) O(n^2), O(2n) --> Space optimized DP
        
        if(matrix.length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[] DP1 = new int[col];   // top row
        int[] DP2 = new int[col];   // bottom row (currRow)
        int output = 0;
        
        for(int i = 0; i < row; i++)
        {         
            for(int j = 0; j < col; j++) 
            {          
                if(matrix[i][j] != '0')
                {
                    if(i > 0 && j > 0) {
                        DP2[j] = 1 + Math.min(DP2[j-1], Math.min(DP1[j-1], DP1[j]));  // main logic --> 1 + Min(left, topLeft, top);  
                    } else {
                        DP2[j] = 1;                                                   // 1st row or 1st col
                    }
                    output = Math.max(output, DP2[j]);
                }
            }
            DP1 = DP2;
            DP2 = new int[col];                                                       // space optimized
        }
        return output * output;
    }
}

