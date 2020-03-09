/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
Given an integer n, return all distinct solutions to the n-queens puzzle.
Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:
Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
*/

class Solution 
{
    List<List<String>> output = new ArrayList<List<String>>();
    
    public List<List<String>> solveNQueens(int n) 
    {
        boolean[][] board = new boolean[n][n];  // this prob can also be solved in O(n) space.. there is going to be exactly 1 queen in 1 column.. so board = 1,3,0,2
        nQueenHelper(board, n, 0);
        return output;
    }
    
    public boolean nQueenHelper(boolean[][] board, int n, int col)
    {
        if(col == n)
        {
            output.add(convertBoardToList(board, n));
            return false;   // to generate all output return false... (to generate 1 output return true)
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
    
    public boolean isSafe(boolean[][] board, int row, int col, int n) // time: O(3n)... but if we use hashset then time is O(3).
    {
        // no need to check::: 1)current column, 2)top right diagnol, 3)bottom right diagnol
        // we can reduce time taken for below 3 steps with HASHSET.. put availables rows, diagnols in hashset and check from it in O(1).
        
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
    
    public List<String> convertBoardToList(boolean[][] board, int n)
    {
        List<String> currOutput = new ArrayList<String>();
        for(int i=0; i<n; i++)
        {
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<n; j++)
            {
                if(board[i][j])
                    sb.append("Q");
                else
                    sb.append(".");
            }
            currOutput.add(sb.toString());
        }
        return currOutput;
    }
}