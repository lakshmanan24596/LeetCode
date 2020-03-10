/*
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]

Example 2:
Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
*/

class Solution 
{
    public List<Integer> spiralOrder(int[][] arr) 
    {
        if(arr.length == 0)
            return new ArrayList<Integer>();
        
        int row = arr.length;       
        int col = arr[0].length;
        
        int x = 0, y = 0;   // currRow, currCol
        List<Integer> output = new ArrayList<Integer>();
        
        while(row > 0 && col > 0)
        {
            if(row == 1)
            {
                for(int i=0; i<col; i++)
                    output.add(arr[x][y++]);
                break;
            }
            else if(col == 1)
            {
                for(int i=0; i<row; i++)
                    output.add(arr[x++][y]);
                break;
            }
                
            // top
            for(int i = 0; i < col - 1; i++)
                output.add(arr[x][y++]);
            
            // right
            for(int i = 0; i < row - 1; i++)
                output.add(arr[x++][y]);
            
            // bottom
            for(int i = 0; i < col - 1; i++)
                output.add(arr[x][y--]);
            
            // left
            for(int i = 0; i < row - 1; i++)
                output.add(arr[x--][y]);
            
            // for next iteration
            x++;
            y++;
            row -= 2;
            col -= 2;           
        }
        
        return output;
    }
}