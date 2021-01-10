/*
Given a 2D grid consists of 0s (land) and 1s (water).  
An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
Return the number of closed islands.

Example 1:
Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
Output: 2
Explanation: 
Islands in gray are closed because they are completely surrounded by water (group of 1s).

Example 2:
Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
Output: 1

Example 3:
Input: grid = [[1,1,1,1,1,1,1],
               [1,0,0,0,0,0,1],
               [1,0,1,1,1,0,1],
               [1,0,1,0,1,0,1],
               [1,0,1,1,1,0,1],
               [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]
Output: 2

Constraints:
1 <= grid.length, grid[0].length <= 100
0 <= grid[i][j] <=1
*/


// Time: V + E ==> n + 4n ==> O(n), where n = r * c = no of elements in grid
class Solution 
{
    int[] dir;
    int row, col;
    int[][] grid;
    boolean[][] visited;
    
    public int closedIsland(int[][] grid)   // similar to 200. Number of Islands
    {
        this.grid = grid;
        row = grid.length;
        col = grid[0].length;
        visited = new boolean[row][col];
        dir = new int[] {0, -1, 0, 1, 0};
        int output = 0;
        
        for(int i = 1; i < row - 1; i++)
        {
            for(int j = 1; j < col - 1; j++)
            {
                if(grid[i][j] == 0 && !visited[i][j]) 
                {
                    if(dfs(i, j)) {
                        output++;
                    }
                }
            }
        }
        return output;
    }
    
    public boolean dfs(int currX, int currY)
    {
        if(grid[currX][currY] == 1) {
            return true;
        }
        if(currX == 0 || currX == row - 1 || currY == 0 || currY == col - 1) {
            return false;
        }
        
        visited[currX][currY] = true;
        int nextX, nextY;
        boolean output = true;
        
        for(int i = 0; i < 4; i++)
        {
            nextX = currX + dir[i];
            nextY = currY + dir[i + 1];
            
            if(!visited[nextX][nextY]) {    // no need to check boundary condition
                if(!dfs(nextX, nextY)) {
                    output = false;         // main logic: Do not return false. Try to visit all nodes and finally return false
                }
            }
        }
        return output;
    }
}
/*
    Another approach:
        Flood Fill: https://leetcode.com/problems/number-of-closed-islands/discuss/425150/JavaC%2B%2B-with-picture-Number-of-Enclaves
        a) Iterate all boundary elements first
        b) Then iterate all inner elements (This step is similar to 200. Number of Islands)
*/
