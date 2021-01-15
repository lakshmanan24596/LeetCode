/*
Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized, and return the distance. 
If no land or water exists in the grid, return -1.
The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.

Example 1:
Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
Output: 2
Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.

Example 2:
Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
Output: 4
Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.

Constraints:
n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1
*/


/*
    Similar to https://leetcode.com/problems/01-matrix/
    We can use BFS or DP
    Time for BFS: O(n * 4) 
    Space for BFS: O(n)
    where n = r * c
*/
class Solution 
{
    public int maxDistance(int[][] grid) 
    {
        int n = grid.length;
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean[][] visited = new boolean[n][n];    // instead we can also alter in original array itself
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {           // initially add 1 to the queue
                    queue.add((i * n) + j);     // formula to convert 2D array index to 1D array index
                    visited[i][j] = true;
                }
            }
        }
        
        if(queue.size() == n * n) {
            return -1;
        }
        int queueSize, level = 0;
        int currIndex, currRow, currCol, nextIndex, nextRow, nextCol;
        int[] dir = new int[]{0, -1, 0, 1, 0};
        
        while(!queue.isEmpty())
        {
            queueSize = queue.size();
            while(queueSize-- > 0)
            {
                currIndex = queue.remove();
                currRow = currIndex / n;        // formula to convert 1D array index back to 2D array index
                currCol = currIndex % n;
                
                for(int i = 0; i < 4; i++) 
                {
                    nextRow = currRow + dir[i];
                    nextCol = currCol + dir[i + 1];
                    if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n && !visited[nextRow][nextCol]) 
                    {
                        visited[nextRow][nextCol] = true;
                        nextIndex = (nextRow * n) + nextCol;
                        queue.add(nextIndex);
                    }
                }
            }
            level++;
        }
        return level - 1;
    }
}
