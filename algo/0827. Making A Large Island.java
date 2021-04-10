/*
You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
Return the size of the largest island in grid after applying this operation.
An island is a 4-directionally connected group of 1s.

Example 1:
Input: grid = [[1,0],[0,1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.

Example 2:
Input: grid = [[1,1],[1,0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.

Example 3:
Input: grid = [[1,1],[1,1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.

Constraints:
n == grid.length
n == grid[i].length
1 <= n <= 500
grid[i][j] is either 0 or 1.
*/



/*
    1) brute force DFS:
        time: n^4
        space: n^2
    
    2) DFS with groupId for island
        time: n^2
        space: n^2
    
    example: 0 0 1 0 0          0 0 2 0 0       where 2, 3, 4 are the groupId of island
             0 0 1 0 0   -->    0 0 2 0 0
             1 0 0 1 1          4 0 0 3 3
             1 1 0 0 0          4 4 0 0 0
             
    In first iteration, we need to find size of each island separately and also assign a groupId for each island
    why groupId is needed?
    
    2
    2          --> if we set this 0 to 1 then the answer --> 1 + 2 + 2  --> because groupId is different
    0 3 3 
    
    4 0        --> If we set this 0 to 1, then the answer --> 1 + 3   (not 1 + 3 + 3) --> because groupId is same
    4 4            since the left neighbor and the bottom neighbor of the 0 belong to the same group
*/

class Solution {
    int[][] grid;
    int rows, cols;
    int[] dir = new int[] {0, -1, 0, 1, 0};
    Map<Integer, Integer> groupidVsSize = new HashMap<Integer, Integer>();
    
    public int largestIsland(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        int largestIsland = 0, size;
        int groupId = 2;
        Set<Integer> group;
        int nextX, nextY;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    size = getIslandSize(i, j, groupId);
                    largestIsland = Math.max(largestIsland, size);
                    groupidVsSize.put(groupId, size);                       // main logic
                    groupId++;
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    group = new HashSet<Integer>();
                    size = 1;
                    for (int d = 0; d < 4; d++) {
                        nextX = i + dir[d];
                        nextY = j + dir[d + 1];
                        if (nextX >= 0 && nextX < rows && nextY >= 0 && nextY < cols) {
                            groupId = grid[nextX][nextY];
                            if (groupId >= 2 && !group.contains(groupId)) {
                                group.add(groupId);
                                size += groupidVsSize.get(groupId);         // add size of adjacent groups of different groupId
                            }
                        }
                    }
                    largestIsland = Math.max(largestIsland, size);
                }
            }
        }
        return largestIsland;
    }
    
    public int getIslandSize(int x, int y, int groupId) {
        int nextX, nextY;
        int islandSize = 1;
        grid[x][y] = groupId;                                               // main logic
        
        for (int i = 0; i < 4; i++) {
            nextX = x + dir[i];
            nextY = y + dir[i + 1];
            if (nextX >= 0 && nextX < rows && nextY >= 0 && nextY < cols && grid[nextX][nextY] == 1) {
                islandSize += getIslandSize(nextX, nextY, groupId);
            }
        }
        return islandSize;
    }
}