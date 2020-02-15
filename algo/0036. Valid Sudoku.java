// Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

// Each row must contain the digits 1-9 without repetition.
// Each column must contain the digits 1-9 without repetition.
// Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

// A partially filled sudoku which is valid.

// The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


class Solution 
{
    // here n = 9
    // brute force --> O(n^3)       --> pick a "element"        and     check is valid in row, col, box
    // efficient   --> O(3 * n^2)   --> pick a "row, col, box"  and     check is valid 
    
    public boolean isValidSudoku(char[][] board) 
    {
        int n = 9;
        boolean[] visited;
        int val;
        
        // check row
        for(int i=0; i<n; i++)
        {
            visited = new boolean[n];
            for(int j=0; j<n; j++)
            {
                if(board[i][j] != '.')
                {
                    val = board[i][j] - '0';
                    if(visited[val-1])
                        return false;
                    visited[val-1] = true;
                }
            }
        }
        
        // check column
        for(int i=0; i<n; i++)
        {
            visited = new boolean[n];
            for(int j=0; j<n; j++)
            {
                if(board[j][i] != '.')
                {
                    val = board[j][i] - '0';
                    if(visited[val-1])
                        return false;
                    visited[val-1] = true;
                }
            }
        }
        
        // check box
        for(int i=0; i<n; i+=3)
        {
            for(int j=0; j<n; j+=3)
            {               
                visited = new boolean[n];
                for(int k=i; k<(i+3); k++)
                {
                    for(int l=j; l<(j+3); l++)
                    {
                        if(board[k][l] != '.')
                        {
                            val = board[k][l] - '0';
                            if(visited[val-1])
                                return false;
                            visited[val-1] = true;
                        }
                    }
                }
            }
        }
        
        return true;
    }
}