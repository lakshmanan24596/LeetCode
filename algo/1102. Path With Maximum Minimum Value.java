/*
Given a matrix of integers grid with m rows and n columns, find the maximum score of a path starting at [0,0] and ending at [m-1,n-1].
The score of a path is the minimum value in that path.  For example, the value of the path 8 →  4 →  5 →  9 is 4.
A path moves some number of times from one visited cell to any neighbouring unvisited cell in one of the 4 cardinal directions (north, east, west, south).

Example 1:
Input: [[5,4,5],[1,2,6],[7,4,6]]
Output: 4
Explanation: 
The path with the maximum score is highlighted in yellow. 

Example 2:
Input: [[2,2,1,2,2,2],[1,2,2,2,1,2]]
Output: 2

Example 3:
Input: [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
Output: 3

Note:
1 <= m, n <= 100
0 <= grid[i][j] <= 109
*/



/*
    1) max heap and track minVal
        time: O(r * c * 4 * log(r*c)) = n * logn
        space: O(r * c)               = n
        where n = r * c, which is the number of elements in the grid
        
    2) sort + DSU
        sort matrix in descending order, based on the grid value and store it in a separate array
        connect/merge nodes using DSU union()
        if start and end cell are in single component, then return the minVal in that component
        for each component, we need to track: parent[], rank[], minVal
        
    3) binary search answer range
        possible answer range is, minVal in grid to maxVal in grid
        ex:1 --> answer range = 1 to 7
        since 0 <= grid[i][j] <= 10^9 --> log_2(10^9) = 30
        time: O(r * c * 4 * 30)) = n
        space: O(r * c)          = n
        https://leetcode.com/problems/path-with-maximum-minimum-value/discuss/457525/JAVA-A-Summery-of-All-Current-Solutions
*/


/*
// approach-1
class Solution {
    public int maximumMinimumPath(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        PriorityQueue<int[]> gridQueue = new PriorityQueue<int[]>((a, b) -> (grid[b[0]][b[1]] - grid[a[0]][a[1]]));
        gridQueue.add(new int[] {0, 0});
        boolean[][] visited = new boolean[rows][cols];
        int[] direction = new int[] {0, -1, 0, 1, 0};
        int[] currPos;
        int nextX, nextY;
        int minVal = grid[0][0];
        
        while (!gridQueue.isEmpty()) {
            currPos = gridQueue.remove();
            minVal = Math.min(minVal, grid[currPos[0]][currPos[1]]);        // main logic
            if (currPos[0] == rows - 1 && currPos[1] == cols - 1) {
                break;
            }
            for (int dir = 0; dir < 4; dir++) {
                nextX = currPos[0] + direction[dir];
                nextY = currPos[1] + direction[dir + 1];
                if (nextX >= 0 && nextX < rows && nextY >= 0 && nextY < cols && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    gridQueue.add(new int[] {nextX, nextY});
                }
            }
        }
        return minVal;
    }
}
*/


// approach-3
class Solution {
    int[][] grid;
    int rows, cols;
    final int[] direction = new int[] {0, -1, 0, 1, 0};
    
    public int maximumMinimumPath(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        int[] minMax = getMinMaxValues();
        int left = minMax[0];
        int right = minMax[1];
        int mid;
        int output = left;
        
        while (left <= right) {
            mid = left + ((right - left) / 2);
            if (hasPath(mid)) {
                output = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return output;
    }
    
    public int[] getMinMaxValues() {
        int[] minMax = new int[2];
        minMax[0] = grid[0][0];
        minMax[1] = grid[0][0];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                minMax[0] = Math.min(minMax[0], grid[i][j]);
                minMax[1] = Math.max(minMax[1], grid[i][j]);
            }
        }
        return minMax;
    }
    
    public boolean hasPath(int answer) {
        if (grid[0][0] < answer || grid[rows - 1][cols - 1] < answer) {
            return false;
        }
        boolean[][] visited = new boolean[rows][cols];
        visited[0][0] = true;
        return hasPathUtil(answer, visited, 0, 0);
    }
    
    public boolean hasPathUtil(int answer, boolean[][] visited, int currX, int currY) {
        if (currX == rows - 1 && currY == cols - 1) {
            return true;
        }
        int nextX, nextY;
        
        for (int dir = 0; dir < 4; dir++) {
            nextX = currX + direction[dir];
            nextY = currY + direction[dir + 1];
            if (nextX >= 0 && nextX < rows && nextY >= 0 && nextY < cols 
                    && !visited[nextX][nextY] 
                    && grid[nextX][nextY] >= answer) {
                
                visited[nextX][nextY] = true;
                if (hasPathUtil(answer, visited, nextX, nextY)) {
                    return true;
                }
            }
        }
        return false;
    }
}