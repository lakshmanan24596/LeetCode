/*
Given a string s, you can convert it to a palindrome by adding characters in front of it.
Find and return the shortest palindrome you can find by performing this transformation.

Example 1:
Input: s = "aacecaaa"
Output: "aaacecaaa"

Example 2:
Input: s = "abcd"
Output: "dcbabcd" 

Constraints:
0 <= s.length <= 5 * 104
s consists of lowercase English letters only.
*/


/*
    Logic: find longest length palindrome from starting. So the remaining should be added in the front
        
    Ex: s = "abcd"
    output = reverse of remaining + s
           = (dcb) + (abcd)
           = dcbabcd
               
    1) brute force    : n^2 time
    2) kmp preprocess : n time, n space
*/


/*
// Time: n^2, TLE
class Solution {
    public String shortestPalindrome(String s) {
        int end = s.length() - 1;
        while (end >= 0) {                              // n^2 time
            if (checkPalin(s, 0, end)) {                // find longest length palindrome from starting char 0
                break;
            }
            end--;
        }
        
        StringBuilder output = new StringBuilder();
        for (int i = s.length() - 1; i > end; i--) {
            output = output.append(s.charAt(i));
        }
        output = output.append(s);
        return output.toString();
    }

    public boolean checkPalin(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
*/


// time: n, space: n, logic: kmp
class Solution {
    public String shortestPalindrome(String s) {
        String revS = new StringBuilder(s).reverse().toString();
        String newS = s + "#"  + revS;                              // main logic
        int[] LPS = kmpPreProcess(newS);
        
        int end = s.length() - LPS[newS.length() - 1];
        return revS.substring(0, end) + s;
    }
    
    public int[] kmpPreProcess(String s) {                          // preprocess step of KMP
        int j = 0, i = 1;
        int[] LSP = new int[s.length()];
        
        while (i < s.length()) {
            if (s.charAt(i) == s.charAt(j)) {
                j++;
                LSP[i] = j;
                i++;
            } else {
                if (j == 0) {
                    LSP[i] = 0;
                    i++;
                } else {
                    j = LSP[j - 1];
                }
            }
        }
        return LSP;
    }
}       