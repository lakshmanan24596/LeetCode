/*
    logic: DP
    for filling each cell in curr row (r), we need to check all elements in top row (r-1)
    
    time: r * c * c
    space: r * c 
    tabulation space optimization: c 
*/

class Solution {
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int rows = grid.length;
        int cols = grid[0].length;
        int currCost, minCost;
        int moveCostRow;
        int[][] DP = new int[rows][cols];
        DP[0] = Arrays.copyOf(grid[0], cols);
        
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                minCost = Integer.MAX_VALUE;
                
                for (int k = 0; k < cols; k++) {
                    moveCostRow = grid[i - 1][k];
                    currCost = DP[i - 1][k] + moveCost[moveCostRow][j] + grid[i][j];    // main logic
                    minCost = Math.min(minCost, currCost);
                }
                DP[i][j] = minCost;
            }
        }
        
        minCost = Integer.MAX_VALUE;
        for (int j = 0; j < cols; j++) {
            minCost = Math.min(minCost, DP[rows - 1][j]);
        }
        return minCost;
    }
}
