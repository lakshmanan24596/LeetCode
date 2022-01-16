/*
    logic: DFS
    time: n
    space: n
    
    condition for cycle:
        1) currChar == nextChar
        2) visited[nextI][nextJ] == true
        3) count >= 4
        4) nextI != prevI || nextJ != prevJ     (this is to avoid invalid cycle)
*/
class Solution {
    char[][] grid;
    int rows, cols;
    boolean[][] visited;
    int[] dir = new int[] {0, -1, 0, 1, 0};
    
    public boolean containsCycle(char[][] grid) {
        if (grid.length == 0) {
            return false;
        }
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.visited = new boolean[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    if (containsCycleUtil(i, j, -1, -1, 1)) {       // do dfs from all cells
                        return true;
                    } 
                }
            }
        }
        return false;
    }
    
    public boolean containsCycleUtil(int currI, int currJ, int prevI, int prevJ, int count) {
        int nextI, nextJ;
        char currCh = grid[currI][currJ];
        
        for (int d = 0; d < dir.length - 1; d++) {
            nextI = currI + dir[d];
            nextJ = currJ + dir[d + 1];
            
            if (nextI >= 0 && nextI < rows && nextJ >= 0 && nextJ < cols && currCh == grid[nextI][nextJ]) {
                if (visited[nextI][nextJ]) {
                    if (count >= dir.length - 1 && (nextI != prevI || nextJ != prevJ)) {    // main logic
                        return true;
                    }
                } else {
                    visited[nextI][nextJ] = true;
                    if (containsCycleUtil(nextI, nextJ, currI, currJ, count + 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
