/*
You are given an array of binary strings strs and two integers m and n.
Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
A set x is a subset of a set y if all elements of x are also elements of y.

Example 1:
Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
Output: 4
Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4.
Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
{"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.

Example 2:
Input: strs = ["10","0","1"], m = 1, n = 1
Output: 2
Explanation: The largest subset is {"0", "1"}, so the answer is 2.
 
Constraints:
1 <= strs.length <= 600
1 <= strs[i].length <= 100
strs[i] consists only of digits '0' and '1'.
1 <= m, n <= 100
*/

class Solution 
{
    int[] countM, countN;
    Integer[][][] DP;
    int len;
    int m, n;
    
    public int findMaxForm(String[] strs, int m, int n)     // Time: len * m * n, Space: len * m * n
    {
        this.m = m;
        this.n = n;
        len = strs.length;
        countM = new int[len];
        countN = new int[len];
        DP = new Integer[len][m+1][n+1];
        
        char[] chArr;
        for(int i = 0; i < len; i++)  // pre-process strs and store count of zero and one in countM and countN respectively
        {
            chArr = strs[i].toCharArray();
            for(char ch : chArr) 
            {
                if(ch == '0') {
                    countM[i]++;
                } else {
                    countN[i]++;
                }
            }
        }
        return recur(0, 0, 0);
    }
    
    public int recur(int level, int currM, int currN)    // similar to 0/1 knapsack
    {
        if(level == len) {
            return 0;
        }
        if(DP[level][currM][currN] != null) {            // DP states: 1) level, 2) m, n combined
            return DP[level][currM][currN];
        }
        
        int dontPick = recur(level + 1, currM, currN);
        int pick = 0;
        if(currM + countM[level] <= m && currN + countN[level] <= n) {
            pick = 1 + recur(level + 1, currM + countM[level], currN + countN[level]);
        }
        return DP[level][currM][currN] = Math.max(dontPick, pick);
    }
}

/*
// DP tabulation space optimized
class Solution
{
    public int findMaxForm(String[] strs, int m, int n)     // Time: len * m * n, Space: m * n --> (Space optimized)
    {
        int[][] dp = new int[m+1][n+1];
        for (String s: strs) 
        {
            int len = s.length();
            int one = 0;
            int zero = 0;
            for(int i = 0; i < len; i++){
                if(s.charAt(i) == '0') {
                    zero++;
                } else {
                    one++;
                }
            }
            
            for (int i = m; i >= zero; i--) {
                for (int j = n; j >= one; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-zero][j-one] + 1);
                }
            }
        }
        return dp[m][n];
        
    }
}
*/