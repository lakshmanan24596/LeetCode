/*
You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). 
You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). 
You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
Return the minimum effort required to travel from the top-left cell to the bottom-right cell.

Example 1:
Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
Output: 2
Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.

Example 2:
Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
Output: 1
Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].

Example 3:
Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
Output: 0
Explanation: This route does not require any effort.
 
Constraints:
rows == heights.length
columns == heights[i].length
1 <= rows, columns <= 100
1 <= heights[i][j] <= 106
*/


/*
    This solution will work only if the given sides are right and down.
    But the ques says, we can move it all 4 directions which reults in dfs/bfs recursion
    So it gives wrong output for example-3.
*/
/*
class Solution 
{
    public int minimumEffortPath(int[][] heights) 
    {
        int row = heights.length;
        int col = heights[0].length;
        int[][] DP = new int[row][col];
        DP[0][0] = 0;
        
        for(int i = 1; i < row; i++) {
            DP[0][i] = Math.max(DP[0][i-1], Math.abs(heights[0][i] - heights[0][i-1]));     // 1st row
        }
        for(int i = 1; i < col; i++) {
            DP[i][0] = Math.max(DP[i-1][0], Math.abs(heights[i][0] - heights[i-1][0]));     // 1st col
        }
        
        int left, top;
        for(int i = 1; i < row; i++) 
        {
            for(int j = 1; j < col; j++)
            {
                left = Math.max(DP[i][j-1], Math.abs(heights[i][j] - heights[i][j-1]));      // max absolute diff
                top = Math.max(DP[i-1][j], Math.abs(heights[i][j] - heights[i-1][j]));
                DP[i][j] = Math.min(left, top);                                              // minimum effort
            }
        }
        return DP[row-1][col-1];
    }
}
*/


/*
    Dijkstra Greedy Algo 
    https://www.geeksforgeeks.org/minimum-cost-path-left-right-bottom-moves-allowed/
    Time: E*log(V) where E = 4*r*c and V = r*c
    so time = 4rc*log(rc) and space = r*c
*/
class Solution
{
    int row, col;
    public int minimumEffortPath(int[][] heights) 
    {
        row = heights.length;
        col = heights[0].length;
        int[][] distArr = new int[row][col];
        for(int[] arr : distArr) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        PriorityQueue<int[]> pQueue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[2] - b[2];                          // min heap for distance
            }
        });
        pQueue.add(new int[]{0, 0, 0});
        int[] dir = new int[] {0, -1, 0, 1, 0};              // using i, i+1 we can fetch x, y direction in 1D array itself
        int currX, currY, currDist, nextX, nextY, nextDist, edgeCost;
        
        while(!pQueue.isEmpty())
        {
            int[] currNode = pQueue.remove();
            currX = currNode[0]; currY = currNode[1]; currDist = currNode[2];
            if(currX == row - 1 && currY == col -1) {       // target reached
                return currDist;
            }
            if(currDist > distArr[currX][currY]) { // because we didn't remove from pQueue. so same vertex can be present duplicately
                continue;
            }
            for(int i = 0; i < 4; i++)
            {
                nextX = currX + dir[i]; nextY = currY + dir[i + 1];
                if(nextX >= 0 && nextX < row && nextY >= 0 && nextY < col)  // inside the grid
                {
                    edgeCost = Math.abs(heights[currX][currY] - heights[nextX][nextY]);   // effort required to travel
                    nextDist = Math.max(currDist, edgeCost);
                    if(nextDist < distArr[nextX][nextY])   // we need min effort, so it should be lesser
                    {
                        // if this vertex is already been reached once, remove from pQueue 
                        // Even if we dont remove from pQueue, it will work
                        /* if(distArr[nextX][nextY] != Integer.MAX_VALUE) {
                            pQueue.remove(new int[]{nextX, nextY, distArr[nextX][nextY]});
                        } */
                        distArr[nextX][nextY] = nextDist;
                        pQueue.add(new int[]{nextX, nextY, nextDist});
                    }
                }
            }
        }
        return distArr[row-1][col-1];   // unreachable code
    }
}
