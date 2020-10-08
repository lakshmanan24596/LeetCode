/*
Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

Example 1:
Input: "bbbab"
Output: 4
One possible longest palindromic subsequence is "bbbb".

Example 2:
Input: "cbbd"
Output: 2
One possible longest palindromic subsequence is "bb".
 
Constraints:
1 <= s.length <= 1000
s consists only of lowercase English letters.
*/

class Solution 
{
    public int longestPalindromeSubseq(String s) 
    {
        int n = s.length();
        int[][] DP = new int[n][n];
        
        // length 1
        for(int i = 0; i < n; i++) {
            DP[i][i] = 1;
        }
        
        // length 2 to n
        for(int k = 2; k <= n; k++)
        {
            for(int i = 0; i < n-k+1; i++)
            {
                int j = i+k-1;
                if(s.charAt(i) == s.charAt(j)) {
                    DP[i][j] = 2 + DP[i+1][j-1];
                }
                else {
                    DP[i][j] = Math.max(DP[i][j-1], DP[i+1][j]);
                }
            }
        }

        return DP[0][n-1];
    }
}

