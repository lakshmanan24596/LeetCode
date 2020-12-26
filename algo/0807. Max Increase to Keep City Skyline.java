/*
    A city's skyline is the outer contour of the rectangles formed by all the buildings when viewed from a distance.
    Ques: Max value in all rows and all cols should stay the same in givenGrid and newGrid
*/
class Solution 
{
    public int maxIncreaseKeepingSkyline(int[][] grid)  // Time: 2 * r * c and Space: r + c
    {
        int row = grid.length;
        int col = grid[0].length;
        int[] rowMax = new int[row];
        int[] colMax = new int[col];
        
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                rowMax[i] = Math.max(rowMax[i], grid[i][j]);
                colMax[j] = Math.max(colMax[j], grid[i][j]);
            }
        }
        
        int output = 0, newVal;
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                newVal = Math.min(rowMax[i], colMax[j]);
                output += newVal - grid[i][j];
            }
        }
        return output;
    }
}
