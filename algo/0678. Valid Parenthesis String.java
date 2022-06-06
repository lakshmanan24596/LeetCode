/*
Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.

The following rules define a valid string:
Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".

Example 1:
Input: s = "()"
Output: true

Example 2:
Input: s = "(*)"
Output: true

Example 3:
Input: s = "(*))"
Output: true

Constraints:
1 <= s.length <= 100
s[i] is '(', ')' or '*'.
*/


/*
    1) two iteration
        time: 2n, space: 1
        logic: 
            example 1: "(*)(" ==> braceCount=1, starCount=1 ==> false
            example 2: "(*" ==> braceCount=1, starCount=1 ==> true
            the braceCount and starCount are the same, but output differs
            so, the position of the star matters
            to validate that, we need to check normal iteration and also reverse iteration
            in reverse iteration, "(" should be considered as ")" and vice verse
    
    2) single iteration
        time: n, space: 1
        logic: "could" and "must" open brackets count
        https://leetcode.com/problems/valid-parenthesis-string/discuss/107570/JavaC%2B%2BPython-One-Pass-Count-the-Open-Parenthesis
*/

class Solution {
    public boolean checkValidString(String s) {
        return checkValidStringUtil(s, false) && checkValidStringUtil(new StringBuffer(s).reverse().toString(), true);
    }
    
    public boolean checkValidStringUtil(String s, boolean isReverse) {
        int braceCount = 0, starCount = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                starCount++;
            } else if ((!isReverse && s.charAt(i) == '(') || (isReverse && s.charAt(i) == ')')) {
                braceCount++;
            } else {
                if (braceCount > 0) {
                    braceCount--;
                } else if (starCount > 0) {
                    starCount--;
                } else {
                    return false;
                }
            }
        }
        return starCount >= braceCount;
    }
}
