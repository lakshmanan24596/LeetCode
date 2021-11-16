/*
The chess knight has a unique movement, it may move two squares vertically and one square horizontally, or two squares horizontally and one square vertically (with both forming the shape of an L). 
The possible movements of chess knight are shown in this diagaram:
A chess knight can move as indicated in the chess diagram below:
We have a chess knight and a phone pad as shown below, the knight can only stand on a numeric cell (i.e. blue cell).
Given an integer n, return how many distinct phone numbers of length n we can dial.
You are allowed to place the knight on any numeric cell initially and then you should perform n - 1 jumps to dial a number of length n. All jumps should be valid knight jumps.
As the answer may be very large, return the answer modulo 109 + 7.

Example 1:
Input: n = 1
Output: 10
Explanation: We need to dial a number of length 1, so placing the knight over any numeric cell of the 10 cells is sufficient.

Example 2:
Input: n = 2
Output: 20
Explanation: All the valid number we can dial are [04, 06, 16, 18, 27, 29, 34, 38, 40, 43, 49, 60, 61, 67, 72, 76, 81, 83, 92, 94]

Example 3:
Input: n = 3
Output: 46

Example 4:
Input: n = 4
Output: 104

Example 5:
Input: n = 3131
Output: 136006598
Explanation: Please take care of the mod.

Constraints:
1 <= n <= 5000
*/



/*
    DP memo
    time: n*10
    space: n*10
*/
/*
class Solution {
    int[][] moves;
    int mod = 1_000_000_007;
    int N;
    int[][] DP;
    
    public int knightDialer(int N) {
        this.N = N;
        this.moves = new int[][] {{4,6}, {6,8}, {7,9}, {4,8}, {0,3,9}, {}, {0,1,7},{ 2,6}, {1,3}, {2,4}};
        this.DP = new int[N-1][10];
        long validJumpsCount = 0;
        
        for (int i = 0; i < 10; i++) {
            validJumpsCount += knightDialer(0, i);
            validJumpsCount %= mod;
        }
        return (int) validJumpsCount;
    }
    
    public int knightDialer(int currN, int num) {
        if (currN == N - 1) {
            return 1;
        }
        if (DP[currN][num] > 0) {
            return DP[currN][num];
        }
        long validJumpsCount = 0;
        
        for (int nextJump : moves[num]) {
            validJumpsCount += knightDialer(currN + 1, nextJump);       // main logic
            validJumpsCount %= mod;
        }
        return DP[currN][num] = (int) validJumpsCount;
    }
}
*/


/*
    DP tabulation space optimized
    time: n*10      ==> n
    space: 2*10     ==> 1
*/
class Solution {
    public int knightDialer(int N) {
        int[][] moves = new int[][] {{4,6}, {6,8}, {7,9}, {4,8}, {0,3,9}, {}, {0,1,7},{ 2,6}, {1,3}, {2,4}};
        int mod = 1_000_000_007;
        long[][] DP = new long[2][10];
        Arrays.fill(DP[0], 1);
        long validJumpsCount = 0;
        
        for (int i = 0; i < N - 1; i++) {
            for (int num = 0; num < 10; num++) {
                for (int nextJump : moves[num]) {
                    DP[1][nextJump] += DP[0][num];      // main logic
                    DP[1][nextJump] %= mod;
                }
            }
            DP[0] = DP[1];
            DP[1] = new long[10];
        }
        
        for (int i = 0; i < 10; i++) {
            validJumpsCount += DP[0][i];
            validJumpsCount %= mod;
        }
        return ((int) validJumpsCount);
    }
}