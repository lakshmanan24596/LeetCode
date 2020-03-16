/*
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X

After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X

Explanation:
Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. 
Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. 
Two cells are connected if they are adjacent cells connected horizontally or vertically.
*/

class Solution 
{	
    int row, col;
    int[] xSide = new int[]{0, -1, 0, 1};
    int[] ySide = new int[]{-1, 0, 1, 0};
    
    char[][] board;
    boolean[][] visited;
    List<Temp> list;
    
    public void solve(char[][] board) 
    {
        if(board == null || board.length <= 2)
            return;
        
        row = board.length;
        col = board[0].length;
        
        this.board = board;
        visited = new boolean[row][col];
        
        for(int i = 1; i <= row - 2; i++)
        {
            for(int j = 1; j <= col - 2; j++)               // iterate by leaving all 4 corners
            {
            	if(board[i][j] == 'O' || !visited[i][j])
            	{
            		list = new ArrayList<Temp>();
            		if(!DFS(i, j))
	                {
                        // reset 'X' to 'O' when false is returned
                        for(Temp obj : list)
                        {
                            board[obj.i][obj.j] = 'O';
                        }		                
	                }
            	}	
            }
        }
    }
    
    public boolean DFS(int i, int j)
    {
        if(board[i][j] == 'X' || visited[i][j])
            return true;
        
        if(board[i][j] == 'O' && (i == 0 || i == row-1 || j == 0 || j == col-1))
            return false;         
        
        visited[i][j] = true;   
        list.add(new Temp(i,j));
        
        boolean s1 = DFS(i + xSide[0], j + ySide[0]);
        boolean s2 = DFS(i + xSide[1], j + ySide[1]);
        boolean s3 = DFS(i + xSide[2], j + ySide[2]);
        boolean s4 = DFS(i + xSide[3], j + ySide[3]);
        
        if(s1 && s2 && s3 && s4)    
        {
        	board[i][j] = 'X';      // it is a surrounder region
            return true;
        }    
        else
        {
            return false;
        }    
    }
}

class Temp
{
	int i, j;
	Temp(int i, int j)
	{
		this.i = i;
		this.j = j;
	}
}


// Time = (2 * board)

// class Solution 
// {       
//     public void solve(char[][] board) 
//     {
//         if (null == board || board.length == 0) 
//             return;
       
//         int m = board.length, 
//             n = board[0].length;
        
//         for (int i=0; i<m; i++)                                     // do DFS from all 4 sides corner
//             for (int j=0; j<n; j++)
//                 if (i==0 || i==m-1 || j==0 || j==n-1)
//                     dfs(board, i, j, m, n);
        
//         for (int i=0; i<m; i++)                                     // form the board with correct data
//         {
//             for (int j=0; j<n; j++) 
//             {
//                 if (board[i][j] == 'O') 
//                     board[i][j] = 'X';
//                 else if (board[i][j] == 'T') 
//                     board[i][j] = 'O';
//             }
//         }
//     }
    
//     private void dfs(char[][] board, int i, int j, int m, int n) 
//     {
//         if (board[i][j] == 'X' || board[i][j] == 'T') 
//             return;
        
//         board[i][j] = 'T';
        
//         if (i>0) 
//             dfs(board, i-1, j, m, n);
//         if (i<m-1) 
//             dfs(board, i+1, j, m, n);
//         if (j>0) 
//             dfs(board, i, j-1, m, n);
//         if (j<n-1) 
//             dfs(board, i, j+1, m, n);
//     }
// }