/*
Given a string s and an integer k, return true if s is a k-palindrome.
A string is k-palindrome if it can be transformed into a palindrome by removing at most k characters from it. 

Example 1:
Input: s = "abcdeca", k = 2
Output: true
Explanation: Remove 'b' and 'e' characters.

Example 2:
Input: s = "abbababa", k = 1
Output: true

Constraints:
1 <= s.length <= 1000
s consists of only lowercase English letters.
1 <= k <= s.length
*/



/*
    logic: longPalinSubseq
    time: n^2
    space: n^2 (which can be reduced to n using tabulation space optimization)
*/

class Solution {
    String s;
    Integer[][] memo;
    
    public boolean isValidPalindrome(String s, int k) {
        this.s = s;
        int n = s.length();
        this.memo = new Integer[n][n];
        return longPalinSubseq(0, n - 1) >= n - k;       // main logic
    }
    
    public int longPalinSubseq(int start, int end) {
        if (end - start <= 0) {
            return end - start + 1;
        }
        if (memo[start][end] != null) {
            return memo[start][end];
        }
        if (s.charAt(start) == s.charAt(end)) {
            return memo[start][end] = 2 + longPalinSubseq(start + 1, end - 1);
        } else {
            return memo[start][end] = Math.max(longPalinSubseq(start, end - 1), longPalinSubseq(start + 1, end));
        }
    }
}
