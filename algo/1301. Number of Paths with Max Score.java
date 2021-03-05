/*
You are given a square board of characters. 
You can move on the board starting at the bottom right square marked with the character 'S'.
You need to reach the top left square marked with the character 'E'. 
The rest of the squares are labeled either with a numeric character 1, 2, ..., 9 or with an obstacle 'X'. 
In one move you can go up, left or up-left (diagonally) only if there is no obstacle there.
Return a list of two integers: the first integer is the maximum sum of numeric characters you can collect, and the second is the number of such paths that you can take to get that maximum sum, taken modulo 10^9 + 7.
In case there is no path, return [0, 0].

Example 1:
Input: board = ["E23","2X2","12S"]
Output: [7,1]

Example 2:
Input: board = ["E12","1X1","21S"]
Output: [4,2]

Example 3:
Input: board = ["E11","XXX","11S"]
Output: [0,0]
 
Constraints:
2 <= board.length == board[i].length <= 100
*/



/*
    DP memo
    states: x, y
    
    time: rows * cols
    space: rows * cols * 2
*/

class Solution {
    char[][] arr;
    int[][] dir = new int[][] {{0, -1},{-1, 0}, {-1, -1}};
    int n;
    int mod = 1_000_000_007;
    int[][][] DP;
    
    public int[] pathsWithMaxScore(List<String> board) {
        n = board.size();
        arr = new char[n][n];
        DP = new int[n][n][2];      // dp[x][y][0] is the maxSum and dp[x][y][1] is the count (number of paths with maxSum)
        String currStr;
        
        for (int i = 0; i < n; i++) {
            currStr = board.get(i);
            for (int j = 0; j < n; j++) {
                arr[i][j] = currStr.charAt(j);
            }
        }
        arr[n-1][n-1] = '0';
        int[] output = dfs(n - 1, n - 1);
        if (output[0] == -1) {
            output[0] = 0;
        }
        return output;
    }
    
    public int[] dfs(int x, int y) {
        if (x == 0 && y == 0) {
            return new int[] {0, 1};
        }
        if (DP[x][y][0] != 0) {
            return DP[x][y];
        }
        int[] output = new int[] {-1, 0};
        int[] currOutput;
        int nx, ny;
        int currVal = arr[x][y] - '0';
        int currSum, currCount;
        
        for (int i = 0; i < 3; i++) {
            nx = x + dir[i][0];
            ny = y + dir[i][1];
            if (nx >= 0 && ny >= 0 && arr[nx][ny] != 'X') {
                currOutput = dfs(nx, ny);
                
                if (currOutput[0] != -1) {
                    currSum = currOutput[0] + currVal;
                    currCount = currOutput[1];
                    
                    if (currSum > output[0]) {
                        output[0] = currSum;
                        output[1] = currCount;
                    } else if (currSum == output[0]) {
                        output[1] += currCount;
                    }
                }
            }
        }
        output[0] %= mod;
        output[1] %= mod;
        return DP[x][y] = output;
    }
}