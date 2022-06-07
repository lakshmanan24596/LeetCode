/*
You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's (representing land). 
An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells outside of the grid are considered water cells.

An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in grid2.

Return the number of islands in grid2 that are considered sub-islands.

Example 1:
Input: grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
Output: 3
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.

Example 2:
Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
Output: 2 
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands.

Constraints:
m == grid1.length == grid2.length
n == grid1[i].length == grid2[i].length
1 <= m, n <= 500
grid1[i][j] and grid2[i][j] are either 0 or 1.
*/


/*
    logic: dfs
    time: r * c
    space: r * c
    main logic: grid2 = 1 and grid1 = 0, then return false
    
    visited[0] = not visited
    visited[1] = visited
    visited[2] = visited and it is false --> main logic 
*/

class Solution {
    int[][] grid1, grid2;
    int rows, cols;
    int[][] visited;
    int[] dir = new int[] {-1, 0, 1, 0, -1};
    
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        this.grid1 = grid1;
        this.grid2 = grid2;
        this.rows = grid2.length;
        this.cols = grid2[0].length;
        this.visited = new int[rows][cols];
        int subIsland = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid2[i][j] == 1 && visited[i][j] == 0) {
                    if (dfs(i, j)) {
                        subIsland++;
                    } else {
                    	visited[i][j] = 2;
                    }
                }
            }
        }
        return subIsland;
    }
    
    public boolean dfs(int i, int j) {
        if (grid2[i][j] == 0) {
            return true;
        }
        if (grid1[i][j] == 0) {     // main logic: grid2 = 1 and grid1 = 0, then return false
            return false;
        }
        visited[i][j] = 1;
        int nextI, nextJ;
        
        for (int d = 0; d < 4; d++) {
            nextI = i + dir[d];
            nextJ = j + dir[d + 1];
            if (nextI >= 0 && nextI < rows && nextJ >= 0 && nextJ < cols) {
                if (visited[nextI][nextJ] == 2) {       // already visited and processed as false
                    return false;
                }
                if (visited[nextI][nextJ] == 0 && !dfs(nextI, nextJ)) {
                    visited[nextI][nextJ] = 2;
                    return false;
                }
            }
        }
        return true;
    }
}
