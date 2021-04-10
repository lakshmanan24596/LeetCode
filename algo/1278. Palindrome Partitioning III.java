/*
You are given a string s containing lowercase letters and an integer k. You need to :
First, change some characters of s to other lowercase English letters.
Then divide s into k non-empty disjoint substrings such that each substring is palindrome.
Return the minimal number of characters that you need to change to divide the string.

Example 1:
Input: s = "abc", k = 2
Output: 1
Explanation: You can split the string into "ab" and "c", and change 1 character in "ab" to make it palindrome.

Example 2:
Input: s = "aabbc", k = 3
Output: 0
Explanation: You can split the string into "aa", "bb" and "c", all of them are palindrome.

Example 3:
Input: s = "leetcode", k = 8
Output: 0
 
Constraints:
1 <= k <= s.length <= 100.
s only contains lowercase English letters.
*/



/*
    Logic: DP (similar to https://leetcode.com/problems/palindrome-partitioning-iv/)
    
    Implementation:
        1) tabulation --> create cost of changes DP --> time: n^2, space: n^2
        2) memo --> states: string startIndex, remainingK --> time: n*k*n, space: n*k
        
    time: (n^2) + (n*k*n) 
    space: (n^2) + (n*k)
*/

class Solution {
    int[][] changesCountDP;
    int k, length;
    String s;
    Integer[][] palinPartiMemo;
    
    public int palindromePartition(String s, int k) {
        this.s = s;
        this.k = k;
        this.length = s.length();
        this.changesCountDP = new int[length][length];
        this.palinPartiMemo = new Integer[length][k + 1];
        
        for (int i = 1; i < length; i++) {
            for (int start = 0; start < length - i; start++) {
                int end = start + i;
                if (s.charAt(start) == s.charAt(end)) {
                    changesCountDP[start][end] = changesCountDP[start + 1][end - 1];
                    /*Math.min(changesCountDP[start][end - 1], changesCountDP[start + 1][end]);*/ 
                } else {
                    changesCountDP[start][end] = changesCountDP[start + 1][end - 1] + 1;
                }
            }
        }
        return minChangesPalin(0, k);
    }
    
    public int minChangesPalin(int startIndex, int k) {
        if (k == 1) {
            return changesCountDP[startIndex][length - 1];
        }
        if (palinPartiMemo[startIndex][k] != null) {
            return palinPartiMemo[startIndex][k];
        }
        int currChanges;
        int minChanges = Integer.MAX_VALUE;
        
        for (int i = startIndex; i <= length - k; i++) {
            currChanges = changesCountDP[startIndex][i] + minChangesPalin(i + 1, k - 1);    // main logic
            minChanges = Math.min(minChanges, currChanges);
        }
        return palinPartiMemo[startIndex][k] = minChanges;
    }
}