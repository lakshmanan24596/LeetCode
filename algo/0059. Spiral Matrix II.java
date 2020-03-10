/*
Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

Example:
Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
*/

class Solution 
{
    public int[][] generateMatrix(int n) 
    {
        int[][] output = new int[n][n];
        
        int start = -1, end = n, count = 0;
        int i = start, j = start;
        
        while(count < n * n)
        {
        	if(i == start && j == start) // cycle reached.. so go to next inner circle
        	{
        		start++;
        		end--;
        		i = j = start;
        	}
            
            output[i][j] = ++count;   
            
            if(i == start)	
            {
            	if(j != end) j++;       // right
            	else i++;
            }
            else if(j == end)	
            {
            	if(i != end) i++;       // down
            	else j--;
            }
            else if(i == end)	
            {
            	if(j != start) j--;     // left
            	else i--;
            }
            else if(j == start)	
            {
            	if(i != start) i--;     // top
            }
        }  
        return output;
    }
}