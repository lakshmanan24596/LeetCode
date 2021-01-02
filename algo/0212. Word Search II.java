/*
Given an m x n board of characters and a list of strings words, return all words on the board.
Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

Example 1:
Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]

Example 2:
Input: board = [["a","b"],["c","d"]], words = ["abcb"]
Output: []

Constraints:
m == board.length
n == board[i].length
1 <= m, n <= 12
board[i][j] is a lowercase English letter.
1 <= words.length <= 3 * 104
1 <= words[i].length <= 10
words[i] consists of lowercase English letters.
All the strings of words are unique.
*/


// this problem can be also solved using trie --> https://leetcode.com/problems/word-search-ii/discuss/59784/My-simple-and-clean-Java-code-using-DFS-and-Trie 
// Below solution is based on storing firstCharPositions index of the board
class Solution 
{
    int row, col;
    char[][] board;
    String word;
    int moveX[] = new int[]{-1, 0, 1, 0};
    int moveY[] = new int[]{0, 1, 0, -1};
    boolean[][] visited;
    
    public List<String> findWords(char[][] board, String[] words)   // Same as problem 79. Word Search
    {
        this.row = board.length;
        this.col = board[0].length;
        this.board = board;
        this.visited = new boolean[row][col];
        
        List<int[]>[] firstCharPositions = new ArrayList[26];
        for(int i = 0; i < 26; i++) {
            firstCharPositions[i] = new ArrayList<int[]>();
        }
        for(int i = 0; i < row; i++)    //  main logic: iterate the board only once and store the firstCharPositions
        {
            for(int j = 0; j < col; j++)
            {
                int index = board[i][j] - 'a';
                List<int[]> currCharList = firstCharPositions[index];
                currCharList.add(new int[] {i, j});
            }
        }
        
        List<String> output = new ArrayList<String>();   
        for(int i = 0; i < words.length; i++)
        {
            this.word = words[i];
            int index = word.charAt(0) - 'a';
            List<int[]> currCharList = firstCharPositions[index];
            for(int[] firstCharPosition : currCharList)
            {
                int x = firstCharPosition[0];
                int y = firstCharPosition[1];
                visited[x][y] = true;
                boolean currOutput = DFS(x, y, 1);  // count = 1, because 1 char is matched so far
                visited[x][y] = false;
                if(currOutput) {
                    output.add(word);
                    break;
                }
            }
        }
        return output;
    }
    
    public boolean DFS(int currX, int currY, int count)
    {
        if(count == word.length()) {
            return true;
        }     
        for(int side = 0; side < moveX.length; side++)
        {
            int nextX = currX + moveX[side];
            int nextY = currY + moveY[side];
                
            if((nextX >= 0 && nextX < row && nextY >= 0 && nextY < col) &&      // isSafe
                (!visited[nextX][nextY]) &&                                     // next cell not visited
                (board[nextX][nextY] == word.charAt(count)))                    // if char matches, then do DFS
            {
                visited[nextX][nextY] = true;
                boolean currOutput = DFS(nextX, nextY, count+1);
                visited[nextX][nextY] = false;
                if(currOutput) {
                    return true;
                }
            }
        }
        return false;
    }
}
