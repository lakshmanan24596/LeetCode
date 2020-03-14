/*
Given a 2D board and a word, find if the word exists in the grid.
The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
 
Constraints:
board and word consists only of lowercase and uppercase English letters.
1 <= board.length <= 200
1 <= board[i].length <= 200
1 <= word.length <= 10^3
*/

class Solution 
{
    int row, col;
    char[][] board;
    String word;
    int moveX[] = new int[]{-1, 0, 1, 0};
    int moveY[] = new int[]{0, 1, 0, -1};
    boolean[][] visited;
    
    // https://www.geeksforgeeks.org/search-a-word-in-a-2d-grid-of-characters/
    // this is slightly different question.. no DFS or BFS.. time for this above ques = (board * 4 * wordLength)    
    // difference:
    // 1) geeks    : output will be always in straight line. So no need DFS or BFS
    // 2) leetcode : output can be curved also.
    
    public boolean exist(char[][] board, String word) 
    {
        // BFS (queue) or DFS (backtracking)::: time ==> (board * 4 * input.length)
        
        this.row = board.length;
        this.col = board[0].length;
        this.board = board;
        this.word = word;
        this.visited = new boolean[row][col];
        
        for(int i=0; i<row; i++)
        {
            for(int j=0; j<col; j++)
            {
                if(board[i][j] == word.charAt(0))   // if first char matches then do DFS
                {                  
                    visited[i][j] = true;
                    if(DFS(i, j, 1))                // count = 1, because 1 char is matched so far
                        return true;
                    visited[i][j] = false;
                }
            }
        }        
        return false;
    }
    
    public boolean DFS(int currX, int currY, int count)
    {
        if(count == word.length())
            return true;
                
        for(int side = 0; side < moveX.length; side++)
        {
            int nextX = currX + moveX[side];
            int nextY = currY + moveY[side];
                
            if((nextX >= 0 && nextX < row && nextY >= 0 && nextY < col) &&      // isSafe
                (!visited[nextX][nextY]) &&                                     // next cell not visited
                (board[nextX][nextY] == word.charAt(count)))                    // if char matches, then do DFS
            {
                visited[nextX][nextY] = true;
                if(DFS(nextX, nextY, count+1))
                    return true;
                visited[nextX][nextY] = false;
            }
        }
        return false;
    }
}