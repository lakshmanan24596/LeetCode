/*
Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
The distance between two adjacent cells is 1.

Example 1:
Input:
[[0,0,0],
 [0,1,0],
 [0,0,0]]
Output:
[[0,0,0],
 [0,1,0],
 [0,0,0]]

Example 2:
Input:
[[0,0,0],
 [0,1,0],
 [1,1,1]]
Output:
[[0,0,0],
 [0,1,0],
 [1,2,1]]
 
Note:
The number of elements of the given matrix will not exceed 10,000.
There are at least one 0 in the given matrix.
The cells are adjacent in only four directions: up, down, left and right.
*/


/*
// BFS
class Solution 
{
    public int[][] updateMatrix(int[][] matrix)     // Time: r*c*4, Space: r*c*2
    {
        int row = matrix.length;
        int col = matrix[0].length;
        Queue<int[]> queue = new LinkedList<int[]>();
        boolean[][] visited = new boolean[row][col];

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(matrix[i][j] == 0) {
                    visited[i][j] = true;
                    queue.add(new int[]{i,j});      // initially add "0" to queue
                }
            }
        }
        
        int level = 0, queueSize, nextX, nextY;
        int[] curr, dir = new int[]{0, -1, 0, 1, 0};
        
        while(!queue.isEmpty())                     // Similar to rotten orange problem
        {
            level++;
            queueSize = queue.size();
            while(queueSize-- > 0) 
            {
                curr = queue.remove();
                for(int i = 0; i < 4; i++) 
                {
                    nextX = curr[0] + dir[i];
                    nextY = curr[1] + dir[i + 1];
                    if(nextX >= 0 && nextX < row && nextY >= 0 && nextY < col && !visited[nextX][nextY])
                    {
                        visited[nextX][nextY] = true;
                        queue.add(new int[]{nextX, nextY});
                        matrix[nextX][nextY] = level;
                    }
                }
            }
        }
        return matrix;
    }
}
*/


/*
    DP tabulation:
        1) Iterate from start to end and check left and top
        2) Iterate from end to start and check right and bottom
*/
class Solution
{
    public int[][] updateMatrix(int[][] matrix)     // Time: r*c*2, Space: 1
    {
        int row = matrix.length;
        int col = matrix[0].length;
        int maxVal = row + col;
        int leftVal, topVal, rightVal, bottomVal;
        
        for(int i = 0; i < row; i++)                // Iterate from start to end and check left and top
        {
            for(int j = 0; j < col; j++) 
            {
                if(matrix[i][j] != 0) 
                {
                    leftVal = (j-1 >= 0) ? matrix[i][j-1] : maxVal;
                    topVal = (i-1 >= 0) ? matrix[i-1][j] : maxVal;
                    matrix[i][j] = 1 + Math.min(leftVal, topVal);
                }
            }
        }
        
        for(int i = row - 1; i >= 0; i--)           // Iterate from end to start and check right and bottom
        {
            for(int j = col - 1; j >= 0; j--) 
            {
                if(matrix[i][j] != 0) 
                {
                    rightVal = (j+1 < col) ?  matrix[i][j+1] : maxVal;
                    bottomVal = (i+1 < row) ?  matrix[i+1][j] : maxVal;
                    matrix[i][j] = Math.min(matrix[i][j], 1 + Math.min(rightVal, bottomVal));   // main logic
                }
            }
        }
        return matrix;
    }
}
