/*
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Example 1:
Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:
Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.

Example 3:
Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.

Example 4:
Input: s = "adceb", p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".

Example 5:
Input: s = "acdcb", p = "a*c?b"
Output: false

Constraints:
0 <= s.length, p.length <= 2000
s contains only lowercase English letters.
p contains only lowercase English letters, '?' or '*'.
*/


/*
// recursion + DP memorization
class Solution 
{
    String s, p;
    Boolean[][] DP;
    
    public boolean isMatch(String s, String p) 
    {
        this.s = s; this.p = p;
        DP = new Boolean[s.length() + 1][p.length() + 1];
        return recur(0,0);
    }
    
    public boolean recur(int textIndex, int pattIndex)
    {
        if(DP[textIndex][pattIndex] != null) {
            return DP[textIndex][pattIndex];
        }
        if(textIndex == s.length() && pattIndex == p.length()) {
            return true;
        }
        if(pattIndex == p.length()) {
            return false;
        }
        if(textIndex == s.length()) {
            return DP[textIndex][pattIndex] = p.charAt(pattIndex) == '*' && recur(textIndex, pattIndex + 1); 
        }
        
        if(p.charAt(pattIndex) == '*') {
            return DP[textIndex][pattIndex] = recur(textIndex + 1, pattIndex) || recur(textIndex, pattIndex + 1);
        } 
        else if(s.charAt(textIndex) == p.charAt(pattIndex) || p.charAt(pattIndex) == '?') {
            return DP[textIndex][pattIndex] = recur(textIndex + 1, pattIndex + 1); 
        }
        else {
            return DP[textIndex][pattIndex] = false;
        }
    }
}
*/


/*
// DP tabulation
class Solution 
{
    public boolean isMatch(String s, String p) 
    {
        boolean[][] DP = new boolean[s.length() + 1][p.length() + 1];
        
        DP[0][0] = true;
        for(int i = 1; i <= s.length(); i++) {
            DP[i][0] = false;
        }
        for(int i = 1; i <= p.length(); i++) {
            DP[0][i] = DP[0][i-1] && p.charAt(i-1) == '*';   
        }
        
        for(int i = 1; i <= s.length(); i++) 
        {
            for(int j = 1; j <= p.length(); j++)
            {
                if(p.charAt(j-1) == '*') {
                    DP[i][j] = DP[i-1][j] || DP[i][j-1];
                } 
                else if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?') {
                     DP[i][j] = DP[i-1][j-1];
                } 
                else {
                    DP[i][j] = false;
                }
            }
        }
        return DP[s.length()][p.length()];
    }
}
*/


/*
// DP tabulation space optimized
class Solution 
{
    public boolean isMatch(String s, String p) 
    {
        boolean[] DP = new boolean[p.length() + 1];
        DP[0] = true;
        for(int i = 1; i <= p.length(); i++) {
            DP[i] = DP[i-1] && p.charAt(i-1) == '*';   
        }
        
        boolean prev, temp;
        for(int i = 1; i <= s.length(); i++) 
        {
            prev = DP[0];
            DP[0] = false;
            for(int j = 1; j <= p.length(); j++)
            {
                temp =  DP[j];
                if(p.charAt(j-1) == '*') {
                    DP[j] = DP[j] || DP[j-1];
                } 
                else if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?') {
                    DP[j] = prev;
                } 
                else {
                    DP[j] = false;
                }
                prev = temp;
            }
        }
        return DP[p.length()];
    }   
}
*/


// time: s + p and space: 1
class Solution 
{
    public boolean isMatch(String s, String p)
    {
        int pp = 0, ss = 0;
        int starP = -1, prevS = 0;
        
        while(ss < s.length())
        {
            if(pp < p.length() && (s.charAt(ss) == p.charAt(pp) || p.charAt(pp) == '?')) {
                pp++;
                ss++;
            } 
            else if(pp < p.length() && p.charAt(pp) == '*') {
                starP = pp;
                prevS = ss;
                pp++;
            }
            else if(starP != -1) {
                pp = starP + 1;
                ss = prevS++;
            }
            else {
                return false;
            }
        }
        
        while(pp < p.length() && p.charAt(pp)=='*') {
           pp++; 
        } 
        return pp == p.length();
    }
}
