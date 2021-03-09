/*
Storekeeper is a game in which the player pushes boxes around in a warehouse trying to get them to target locations.
The game is represented by a grid of size m x n, where each element is a wall, floor, or a box.
Your task is move the box 'B' to the target position 'T' under the following rules:

Player is represented by character 'S' and can move up, down, left, right in the grid if it is a floor (empy cell).
Floor is represented by character '.' that means free cell to walk.
Wall is represented by character '#' that means obstacle  (impossible to walk there). 
There is only one box 'B' and one target cell 'T' in the grid.
The box can be moved to an adjacent free cell by standing next to the box and then moving in the direction of the box. This is a push.
The player cannot walk through the box.

Return the minimum number of pushes to move the box to the target. 
If there is no way to reach the target, return -1.

Example 1:
Input: grid = [["#","#","#","#","#","#"],
               ["#","T","#","#","#","#"],
               ["#",".",".","B",".","#"],
               ["#",".","#","#",".","#"],
               ["#",".",".",".","S","#"],
               ["#","#","#","#","#","#"]]
Output: 3
Explanation: We return only the number of times the box is pushed.

Example 2:
Input: grid = [["#","#","#","#","#","#"],
               ["#","T","#","#","#","#"],
               ["#",".",".","B",".","#"],
               ["#","#","#","#",".","#"],
               ["#",".",".",".","S","#"],
               ["#","#","#","#","#","#"]]
Output: -1

Example 3:
Input: grid = [["#","#","#","#","#","#"],
               ["#","T",".",".","#","#"],
               ["#",".","#","B",".","#"],
               ["#",".",".",".",".","#"],
               ["#",".",".",".","S","#"],
               ["#","#","#","#","#","#"]]
Output: 5
Explanation:  push the box down, left, left, up and up.

Example 4:
Input: grid = [["#","#","#","#","#","#","#"],
               ["#","S","#",".","B","T","#"],
               ["#","#","#","#","#","#","#"]]
Output: -1

Constraints:
m == grid.length
n == grid[i].length
1 <= m <= 20
1 <= n <= 20
grid contains only characters '.', '#',  'S' , 'T', or 'B'.
There is only one character 'S', 'B' and 'T' in the grid.
*/



/*
    Logic:
        double BFS
        we need to do bfs inside a bfs
        
    States of bfs: (main logic)
        1) boxX
        2) boxY
        3) playerX
        4) playerY
        
    Time: O(rows * cols * 4) * (rows * cols) ===> n^4
    Space: O(rows * cols * 4)                ===> n^2
    
    https://leetcode.com/problems/minimum-moves-to-move-a-box-to-their-target-location/discuss/709355/Java-use-2-level-BFS-beat-99
    
    Note: The player cannot walk through the box  
    Corner case: (For this case, we need direction also as a param in visited array)
        [["#",".",".","#","T","#","#","#","#"],
         ["#",".",".","#",".","#",".",".","#"],
         ["#",".",".","#",".","#","B",".","#"],
         ["#",".",".",".",".",".",".",".","#"],
         ["#",".",".",".",".","#",".","S","#"],
         ["#",".",".","#",".","#","#","#","#"]]
*/

class Solution {
    final int[] dir = new int[] {0, -1, 0, 1, 0};
    char[][] grid;
    int rows, cols;
    
    public int minPushBox(char[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        Queue<int[]> boxQueue = new LinkedList<int[]>();
        int boxQueueSize;
        boolean[][][] boxVisited = new boolean[rows][cols][4];          // direction is also a param
        int boxX = -1, boxY = -1, playerX = -1, playerY = -1;
        int[] currPos;
        int noOfPush = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 'B') {
                    boxX = i;
                    boxY = j;
                } else if (grid[i][j] == 'S') {
                    playerX = i;
                    playerY = j;
                }
            }
        }
        boxQueue.add(new int[] {boxX, boxY, playerX, playerY});
            
        while (!boxQueue.isEmpty()) {                                   // BFS
            boxQueueSize = boxQueue.size();
            while (boxQueueSize-- > 0) {
                currPos = boxQueue.remove();
                if (grid[currPos[0]][currPos[1]] == 'T') {
                    return noOfPush;
                }
                for (int i = 0; i < 4; i++) {
                    boxX = currPos[0] + dir[i];
                    boxY = currPos[1] + dir[i + 1];
                    playerX = currPos[0] - dir[i];                      // minus direction (opposite direction of the box)
                    playerY = currPos[1] - dir[i + 1];
                    
                    if (boxX >= 0 && boxX < rows && boxY >= 0 && boxY < cols 
                            && !boxVisited[boxX][boxY][i]
                            && grid[boxX][boxY] != '#') {
                        
                        boolean[][] playerVisited = new boolean[rows][cols];
                        playerVisited[currPos[0]][currPos[1]] = true;   // The player cannot walk through the box
                        
                        if (canPlayerMove(currPos[2], currPos[3], playerX, playerY, playerVisited)) {  // BFS
                            boxVisited[boxX][boxY][i] = true;
                            boxQueue.add(new int[] {boxX, boxY, playerX, playerY});
                        }
                    }
                }
            }
            noOfPush++;
        }
        return -1;
    }
    
    public boolean canPlayerMove(int currX, int currY, int targetX, int targetY, boolean[][] playerVisited) {
        Queue<int[]> playerQueue = new LinkedList<int[]>();
        playerQueue.add(new int[] {currX, currY});
        playerVisited[currX][currY] = true;
        int nextX, nextY;
        int playerQueueSize;
        int[] curr;
        
        while (!playerQueue.isEmpty()) {
            playerQueueSize = playerQueue.size();
            while (playerQueueSize-- > 0) {
                curr = playerQueue.remove();
                currX = curr[0];
                currY = curr[1];
                if (currX == targetX && currY == targetY) {
                    return true;
                }
                for (int i = 0; i < 4; i++) {
                    nextX = currX + dir[i];
                    nextY = currY + dir[i + 1];
                    if (nextX >= 0 && nextX < rows && nextY >= 0 && nextY < cols
                            && !playerVisited[nextX][nextY]
                            && grid[nextX][nextY] != '#') {
                        playerVisited[nextX][nextY] = true;
                        playerQueue.add(new int[] {nextX, nextY});
                    }
                }
            }
        }
        return false;
    }
}