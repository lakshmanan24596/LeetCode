/*
According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0). 
Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population.
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously. 
Given the current state of the m x n grid board, return the next state.

Example 1:
Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]

Example 2:
Input: board = [[1,1],[1,0]]
Output: [[1,1],[1,1]]

Constraints:
m == board.length
n == board[i].length
1 <= m, n <= 25
board[i][j] is 0 or 1.

Follow up:
Could you solve it in-place? Remember that the board needs to be updated simultaneously: You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches upon the border of the array (i.e., live cells reach the border). How would you address these problems?

*/


/*
    Ques: board needs to be updated simultaneously
    You cannot update some cells first and then use their updated values to update other cells.
    
    1) time: r*c, space: r*c
    2) time: r*c, space: 1
    
    Follow-up: What is board is of infinite length?
    Solution:
        1) if it is a sparse matrix, store the position of live cells alone
        2) if it doesn't fit into memory, then fetch 3 rows alone from the disk, because we need to check only 8 positions.
*/

// in-place algo
class Solution {
    public void gameOfLife(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        int[][] dir = new int[][] {{-1, -1}, {-1, 0}, {-1, 1},
                                   {0, -1}, {0, 1},
                                   {1, -1}, {1, 0}, {1, 1}};
        int nextI, nextJ;
        int neighCount;
        int dead = 0, live = 1, newDead = -1, newLive = 2;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                neighCount = 0;
                
                for (int d = 0; d < dir.length; d++) {
                    nextI = i + dir[d][0];
                    nextJ = j + dir[d][1];
                    if (nextI >= 0 && nextI < rows && nextJ >= 0 && nextJ < cols) {
                        if (board[nextI][nextJ] == live || board[nextI][nextJ] == newDead) {
                            neighCount++;
                            if (neighCount > 3) {
                                break;
                            }
                        }
                    }
                }
                if (board[i][j] == dead) { 
                    if (neighCount == 3) {
                        board[i][j] = newLive;                // dead to newLive (0 to 1)
                    }
                } else if (board[i][j] == live) {
                    if (neighCount < 2 || neighCount > 3) {
                        board[i][j] = newDead;                // live to newDead (1 to 0)
                    }
                }
            }
        }
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == newLive) {                 // newLive to live
                    board[i][j] = live;
                } else if (board[i][j] == newDead) {          // newDead to dead
                    board[i][j] = dead;
                }
            }
        }
    }
}