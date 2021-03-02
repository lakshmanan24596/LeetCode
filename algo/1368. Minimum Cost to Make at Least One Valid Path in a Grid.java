/*
Given a m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell. The sign of grid[i][j] can be:
1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
Notice that there could be some invalid signs on the cells of the grid which points outside the grid.

You will initially start at the upper left cell (0,0). A valid path in the grid is a path which starts from the upper left cell (0,0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid path doesn't have to be the shortest.
You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.
Return the minimum cost to make the grid have at least one valid path.

Example 1:
Input: grid = [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]
Output: 3
Explanation: You will start at point (0, 0).
The path to (3, 3) is as follows. (0, 0) --> (0, 1) --> (0, 2) --> (0, 3) change the arrow to down with cost = 1 --> (1, 3) --> (1, 2) --> (1, 1) --> (1, 0) change the arrow to down with cost = 1 --> (2, 0) --> (2, 1) --> (2, 2) --> (2, 3) change the arrow to down with cost = 1 --> (3, 3)
The total cost = 3.

Example 2:
Input: grid = [[1,1,3],[3,2,2],[1,1,4]]
Output: 0
Explanation: You can follow the path from (0, 0) to (2, 2).

Example 3:
Input: grid = [[1,2],[4,3]]
Output: 1

Example 4:
Input: grid = [[2,2,2],[2,2,2]]
Output: 3

Example 5:
Input: grid = [[4]]
Output: 0

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 100
*/



/*
    Ques: we need to find shortest path
    4 directions allowed (so normal 2 dir DP is not sufficient)
    
    Logic 1: Dijkstras
        time: m*n * log(m*n)
        space: m*n
    
    Logic 2: "BFS + DFS"
        time: m*n
        space: m*n
        
        https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/discuss/524820/Java-2-different-solutions-Clean-code
        
        for each node in bfs, we need to do dfs
        Inside bfs:
            initially add all nodes (by doing dfs) into the queue with output = 0
            then in next level, add all nodes (by doing dfs) into the queue with output = 1
            and so on..
        output is the minCost, which in the level in bfs traversal
*/


class Solution {
    int[][] grid;
    int rows, cols;
    Queue<int[]> queue;
    boolean[][] visited;
    int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public int minCost(int[][] grid) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;
        queue = new LinkedList<int[]>();
        queue.add(new int[] {0, 0});
        visited = new boolean[rows][cols];
        int output = 0, queueSize;
        int x, y, nx, ny;
        
        dfs(0, 0);
        while (!queue.isEmpty()) {              // BFS + DFS (both combined)
            queueSize = queue.size();
            while (queueSize-- > 0) {
                int[] curr = queue.remove();
                x = curr[0];
                y = curr[1];
                if (x == rows - 1 && y == cols - 1) {
                    return output;
                }
                for (int i = 0; i < 4; i++) {
                    nx = x + dir[i][0];
                    ny = y + dir[i][1];
                    if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && !visited[nx][ny]) {
                        dfs(nx, ny);            // main logic
                    }
                }
            }
            output++;
        }
        return output;
    }
    
    public void dfs(int x, int y) {             // do dfs without adding up any cost (ie; without changing the dir sign)
        visited[x][y] = true;
        queue.add(new int[]{x, y});
        
        int gridDir = grid[x][y] - 1;
        int nx = x + dir[gridDir][0];
        int ny = y + dir[gridDir][1];
        
        if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && !visited[nx][ny]) {
            dfs(nx, ny);
        }
    }
}