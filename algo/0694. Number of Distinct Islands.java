/*
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
Count the number of distinct islands. 
An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

Example 1:
11000
11000
00011
00011
Given the above grid map, return 1.

Example 2:
11011
10000
00001
11011
Given the above grid map, return 3.

Notice that:
11
1
and
 1
11
are considered different island shapes, because we do not consider reflection / rotation.

Note: The length of each dimension in the given grid does not exceed 50.
*/


/*
    logic: BFS + hashing
    time: rows * cols
    space: rows * cols
*/
class Solution {
    StringBuilder path;
    int[] dir = new int[] {0, -1, 0, 1, 0};
    int[][] grid;
    boolean[][] visited;
    int rows, cols;
    
    public int numDistinctIslands(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.visited = new boolean[rows][cols];
        Set<String> islandPaths = new HashSet<String>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    path = new StringBuilder();
                    dfs(i, j, '0');
                    islandPaths.add(path.toString());            // main logic
                }
            }
        }
        return islandPaths.size();
    }
    
    public void dfs(int x, int y, char dir) {
        if (x >= 0 && x < rows && y >= 0 && y < cols && !visited[x][y] && grid[x][y] == 1) {
            visited[x][y] = true;
            path.append(dir);           // main logic
            dfs(x, y - 1, 'L');
            dfs(x - 1, y, 'T');
            dfs(x, y + 1, 'R');
            dfs(x + 1, y, 'D');
            path.append('b');          // main logic: backtrack
        }
    }
}