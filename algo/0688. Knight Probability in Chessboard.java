/*
On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. 
The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).
A chess knight has 8 possible moves it can make, as illustrated below. 
Each move is two squares in a cardinal direction, then one square in an orthogonal direction.

Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.
The knight continues moving until it has made exactly K moves or has moved off the chessboard. 
Return the probability that the knight remains on the board after it has stopped moving.

Example:
Input: 3, 2, 0, 0
Output: 0.0625

Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
From each of those positions, there are also two moves that will keep the knight on the board.
The total probability the knight stays on the board is 0.0625.

Note:
N will be between 1 and 25.
K will be between 0 and 100.
The knight always initially starts on the board.
*/


/*
    Input: 3, 2, 0, 0, it should be like:
        to go to (1,2) the probablity is 1/8 (one out of 8 moves from (0, 0)), then from (1,2) there are 2 valid moves out of 8, so it's 1/8 * 2/8;
        same for (2, 1);
        so the probabilty is (1/8 * 2/8) + (1/8 * 2/8) = 0.0625;
        
    1) Logic: DFS, Time: exponential
    2) Logic: DP, Time: n*n*k and space: n*n*k
*/

class Solution {
    int N;
    int[] dirX = new int[] {-1, -2, -2, -1, 1, 2, 2, 1};
    int[] dirY = new int[] {-2, -1, 1, 2, 2, 1, -1, -2};
    double probPerNode = 1 / (double) 8;
    double[][][] DP;
    
    public double knightProbability(int N, int K, int r, int c) {
        this.N = N;
        this.DP = new double[N][N][K+1];
        return dfs(K, r, c);
    }
    
    public double dfs(int K, int currX, int currY) {
        if (K == 0) {
            return 1;
        }
        if (DP[currX][currY][K] > 0) {
            return DP[currX][currY][K];
        }
        int nextX, nextY;
        double probability = 0;
        
        for (int i = 0; i < 8; i++) {
            nextX = currX + dirX[i];
            nextY = currY + dirY[i];
            if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) {
                probability += probPerNode * dfs(K - 1, nextX, nextY);      // main logic
            }
        }
        return DP[currX][currY][K] = probability;
    }
}