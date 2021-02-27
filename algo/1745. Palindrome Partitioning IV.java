/*
Given a string s, return true if it is possible to split the string s into three non-empty palindromic substrings. Otherwise, return false.​​​​​
A string is said to be palindrome if it the same string when reversed.

Example 1:
Input: s = "abcbdd"
Output: true
Explanation: "abcbdd" = "a" + "bcb" + "dd", and all three substrings are palindromes.

Example 2:
Input: s = "bcbddxy"
Output: false
Explanation: s cannot be split into 3 palindromes.

Constraints:
3 <= s.length <= 2000
s​​​​​​ consists only of lowercase English letters.
*/


/*
    Logic: 
        We need to split the string into 3 parts.
        So we need 2 separaters in the string namely i, j
        The 3 parts are: 
            0 to i
            i+1 to j
            j+1 to n-1
        If all 3 parts are palindrome, then return true.
        
        
    Implementation:
        1) time: n^3, space: 1
           2 loops for i, j and inside it another loop to check palindrome
           
        2) time: n^2, space: n^2, DP
           pre-calculate isPalindrome for all substring --> time: n^2, space: n^2
           2 loops for i, j and inside it we can check in O(1) using the pre-calculated values --> time: n^2  
*/

class Solution {
    public boolean checkPartitioning(String s) {
        if (s == null || s.length() < 3) {
            return false;
        }
        int n = s.length();
        boolean[][] DP = new boolean[n][n];
        char[] arr = s.toCharArray();
        
        for (int i = 0; i < n; i++) {
            for (int start = 0, end = i; end < n; start++, end++) {                 // fill upper half in DP
                if (start == end) {
                    DP[start][end] = true;                                          // size = 1
                } else if (end - start == 1) {
                    DP[start][end] = arr[start] == arr[end];                        // size = 2
                } else {
                    DP[start][end] = arr[start] == arr[end] && DP[start+1][end-1];  // size >= 3
                }
            }
        }
        for (int i = 0; i <= n - 3; i++) {
            for (int j = i + 1; j <= n - 2; j++) {
                if (DP[0][i] && DP[i+1][j] && DP[j+1][n-1]) {                       // main logic
                    return true;
                }
            }
        }
        return false;
    }
}