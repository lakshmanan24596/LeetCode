/*
Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*' where: 
'.' Matches any single character.​​​​
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Example 1:
Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:
Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".

Example 3:
Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".

Example 4:
Input: s = "aab", p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".

Example 5:
Input: s = "mississippi", p = "mis*is*p*."
Output: false

Constraints:
0 <= s.length <= 20
0 <= p.length <= 30
s contains only lowercase English letters.
p contains only lowercase English letters, '.', and '*'.
It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
*/


// DP memorization
class Solution 
{
    char[] text, patt;
    boolean[] isStar;
    Boolean[][] DP;
    
    public boolean isMatch(String s, String p) 
    {
        DP = new Boolean[s.length() + 1][p.length() + 1];
        text = s.toCharArray();
        patt = p.toCharArray();
        List<Character> patternList = new ArrayList<Character>();
        isStar = new boolean[p.length()];
        
        for(char patt : p.toCharArray()) {
            if(patt == '*') {
                isStar[patternList.size() - 1] = true;
            } else {
                patternList.add(patt);
            }
        }
        patt = new char[patternList.size()];
        for (int i = 0; i < patternList.size(); i++) {
            patt[i] = patternList.get(i);         
        }
        
        return recur(0, 0);
    }
    
    public boolean recur(int textIndex, int pattIndex)
    {
        if(DP[textIndex][pattIndex] != null) {
            return DP[textIndex][pattIndex];
        }
        if(textIndex == text.length && pattIndex == patt.length) {
            return true;
        }
        if(pattIndex == patt.length) {
            return false;
        }
        if(textIndex == text.length) {
            return DP[textIndex][pattIndex] = isStar[pattIndex] && recur(textIndex, pattIndex + 1); 
        }
        
        if(text[textIndex] == patt[pattIndex] || patt[pattIndex] == '.')
        { 
            if(isStar[pattIndex]) {
                return DP[textIndex][pattIndex] = recur(textIndex + 1, pattIndex) || recur(textIndex, pattIndex + 1);
            } else {
                return DP[textIndex][pattIndex] = recur(textIndex + 1, pattIndex + 1);
            }
        }
        else 
        {
            if(isStar[pattIndex]) {
                return DP[textIndex][pattIndex] = recur(textIndex, pattIndex + 1); // extra case when compared to 44. Wildcard Matching
            } else {
                return DP[textIndex][pattIndex] = false;
            }
        }
    }
}

/*
    Below solutions are also possible for this ques:
        1) DP tabulation
        2) DP tabulation space optimized
*/
