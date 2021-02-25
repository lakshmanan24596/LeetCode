/*
Given n points on a 1-D plane, where the ith point (from 0 to n-1) is at x = i, 
find the number of ways we can draw exactly k non-overlapping line segments such that each segment covers two or more points. 
The endpoints of each segment must have integral coordinates. 
The k line segments do not have to cover all n points, and they are allowed to share endpoints.
Return the number of ways we can draw k non-overlapping line segments. Since this number can be huge, return it modulo 109 + 7.

Example 1:
Input: n = 4, k = 2
Output: 5
Explanation: 
The two line segments are shown in red and blue.
The image above shows the 5 different ways {(0,2),(2,3)}, {(0,1),(1,3)}, {(0,1),(2,3)}, {(1,2),(2,3)}, {(0,1),(1,2)}.

Example 2:
Input: n = 3, k = 1
Output: 3
Explanation: The 3 ways are {(0,1)}, {(0,2)}, {(1,2)}.

Example 3:
Input: n = 30, k = 7
Output: 796297179
Explanation: The total number of possible ways to draw 7 line segments is 3796297200. Taking this number modulo 109 + 7 gives us 796297179.

Example 4:
Input: n = 5, k = 3
Output: 7

Example 5:
Input: n = 3, k = 2
Output: 1

Constraints:
2 <= n <= 1000
1 <= k <= n-1
*/



/*
    DP
    time: n * k * (n-k)
    space: n * k
    
    logic: we can draw the first line till ith position where i = 1 to n-k
    remaining positions left = n - i (because we have drawn line till ith position)
    remaining lines = k - 1 (because 1 line is used currently)
    
    ex: n = 6, k = 3
    first line can be drawn from 0 till --> 1 to n-k positions, which is 1 to 3
        a) (0,1)              = 1 * dfs(n-1, k-1)
        b) (0,2),(1,2)        = 2 * dfs(n-2, k-1)
        c) (0,3),(1,3),(2,3)  = 3 * dfs(n-3, k-1)
    output = summation of all
*/
/*
// TLE

class Solution {
    final int MOD = 1_000_000_007;
    Integer[][] DP;
    
    public int numberOfSets(int n, int k) {
        DP = new Integer[n+1][k+1];
        return dfs(n, k);
    }
    
    public int dfs(int n, int k) {
        if (k == 0) {
            return 1;
        }
        if (DP[n][k] != null) {
            return DP[n][k];
        } 
        long output = 0;
        long currOutput;
        
        for (int i = 1; i <= n - k; i++) {                  // draw 1st line till 1 to n-k positions
            currOutput = dfs(n - i, k - 1) % MOD;           
            currOutput = (i * currOutput) % MOD;            // main logic: (i * dfs(n-i, k-1))
            output += currOutput;
            output %= MOD;
        }
        return DP[n][k] = (int) output;
    }
}
*/



/*
    DP (different logic when compared to above)
    time: n*k*2
    space: n*k*2
    
    https://leetcode.com/problems/number-of-sets-of-k-non-overlapping-line-segments/discuss/901894/JavaPython-Top-Down-DP-Clean-and-Concise-O(4*n*k)
*/
class Solution {
    Integer[][][] DP;
    int n;
    final int MOD = 1_000_000_007;
    
    public int numberOfSets(int n, int k) {
        this.n = n;
        this.DP = new Integer[n+1][k+1][2];
        return dfs(0, k, 1);
    }
    public int dfs(int i, int k, int isStart) {
        if (k == 0) {                        // Found a way to draw k valid segments
            return 1;
        }
        if (i == n) {                        // Reach end of points
            return 0;
        }
        if (DP[i][k][isStart] != null) {
            return DP[i][k][isStart];
        }
        
        int output = dfs(i+1, k, isStart);   // Skip ith point
        if (isStart == 1) {
            output += dfs(i+1, k, 0);        // Take ith point as start
        } else {
            output += dfs(i, k-1, 1);        // Take ith point as end
        }
        return DP[i][k][isStart] = output % MOD;
    }
}