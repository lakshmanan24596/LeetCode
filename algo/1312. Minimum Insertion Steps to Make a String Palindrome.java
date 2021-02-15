/*
Given a string s. In one step you can insert any character at any index of the string.
Return the minimum number of steps to make s palindrome.
A Palindrome String is one that reads the same backward as well as forward.

Example 1:
Input: s = "zzazz"
Output: 0
Explanation: The string "zzazz" is already palindrome we don't need any insertions.

Example 2:
Input: s = "mbadm"
Output: 2
Explanation: String can be "mbdadbm" or "mdbabdm".

Example 3:
Input: s = "leetcode"
Output: 5
Explanation: Inserting 5 characters the string becomes "leetcodocteel".

Example 4:
Input: s = "g"
Output: 0

Example 5:
Input: s = "no"
Output: 1

Constraints:
1 <= s.length <= 500
All characters of s are lower case English letters.
*/


/*
    (mbadm) = 0 + (bad)
    (bad) = Min (1 + (ba), 1 + (ad)) --> because we can insert d in the front or insert b in the last
    
    Logic: similar to Longest Palindromic Subsequence
        we can also solve this problem using the below formula:
        output = s.length() - Longest Palindromic Subsequence
        because the remaining character need to be inserted again
    
    We can also solve this using tabulation and then space optimization.
    So time: n^2 and Space: n, as we need only previous state
*/

// recursion + memorization, Time: n^2, Space: n^2
class Solution {
    String s;
    Integer[][] DP;
    
    public int minInsertions(String s) {
        this.s = s;
        int n = s.length();
        this.DP = new Integer[n][n];
        return minInsertionsUtil(0, n - 1);
    }
    
    public int minInsertionsUtil(int start, int end) {
        if (start > end) {
            return 0;
        }
        if (DP[start][end] != null) {
            return DP[start][end];
        }
        
        if (s.charAt(start) == s.charAt(end)) {
            return DP[start][end] = minInsertionsUtil(start + 1, end - 1);      // main logic
        } else {
            int left = 1 + minInsertionsUtil(start, end - 1);
            int right = 1 + minInsertionsUtil(start + 1, end);
            return DP[start][end] = Math.min(left, right);                      // main logic
        }
    }
}
