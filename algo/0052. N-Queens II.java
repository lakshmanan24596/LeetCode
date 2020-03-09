/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Example:
Input: 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
*/

class Solution 
{
    int output = 0;
    
    public int totalNQueens(int n)
    {
        boolean[][] board = new boolean[n][n];        
        nQueenHelper(board, n, 0);
        return output;
    }
    
    public boolean nQueenHelper(boolean[][] board, int n, int col)
    {
        if(col == n)
        {
            output++;
            return false;                              // because we need to generate all posible solutions
        }
        
        for(int row=0; row<n; row++)
        {
            if(isSafe(board, row, col, n))
            {   
                board[row][col] = true;
                if(nQueenHelper(board, n, col+1))
                    return true;    
                board[row][col] = false;
            } 
        }
        return false;
    }
    
    public boolean isSafe(boolean[][] board, int row, int col, int n)
    {       
        // no need to check::: 1)current column, 2)top right diagnol, 3)bottom right diagnol
        
        for(int i=0; i<col; i++)                       // check current row left side
            if(board[row][i])
                return false;
        
        for(int i=row, j=col; i>=0 && j>=0; i--,j--)   // check top left diagnol
            if(board[i][j])
                return false;   
        
        for(int i=row, j=col; i<n && j>=0; i++,j--)    // check bottom left diagnol
            if(board[i][j])
                return false; 
        
        return true;
    }
}