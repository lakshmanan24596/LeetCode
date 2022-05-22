/*
Given a pattern and a string s, return true if s matches the pattern.
A string s matches a pattern if there is some bijective mapping of single characters to strings such that if each character in pattern is replaced by the string it maps to, then the resulting string is s. 
A bijective mapping means that no two characters map to the same string, and no character maps to two different strings.

Example 1:
Input: pattern = "abab", s = "redblueredblue"
Output: true
Explanation: One possible mapping is as follows:
'a' -> "red"
'b' -> "blue"

Example 2:
Input: pattern = "aaaa", s = "asdasdasdasd"
Output: true
Explanation: One possible mapping is as follows:
'a' -> "asd"

Example 3:
Input: pattern = "aabb", s = "xyzabcxzyabc"
Output: false

Constraints:
1 <= pattern.length, s.length <= 20
pattern and s consist of only lowercase English letters.
*/


/*
    https://leetcode.com/problems/word-pattern-ii/discuss/73664/Share-my-Java-backtracking-solution
    logic: hashmap + backtracking
    
    time: t^p
    space: t^p
    where t = text string
          p = pattern
*/

class Solution {
    String pattern, text;
    Map<Character, String> map;
    Set<String> assignedSet;
    
    public boolean wordPatternMatch(String pattern, String text) {
        this.pattern = pattern;
        this.text = text;
        this.map = new HashMap<Character, String>();
        this.assignedSet = new HashSet<String>();
        return wordPatternMatchUtil(0, 0);
    }
    
    public boolean wordPatternMatchUtil(int t, int p) {
        if (t == text.length() && p == pattern.length()) {
            return true;
        }
        if (t == text.length() || p == pattern.length()) {
            return false;
        }
        char pChar = pattern.charAt(p);
        String tStr;
        
        if (map.containsKey(pChar)) {
            tStr = map.get(pChar);
            if (!text.startsWith(tStr, t)) {
                return false;
            }
            return wordPatternMatchUtil(t + tStr.length(), p + 1);
        } else {
            for (int i = t; i < text.length(); i++) {
                tStr = text.substring(t, i + 1);
                if (assignedSet.contains(tStr)) {
                    continue;
                }
                
                map.put(pChar, tStr);
                assignedSet.add(tStr);
                if (wordPatternMatchUtil(i + 1, p + 1)) {
                    return true;
                }
                map.remove(pChar);
                assignedSet.remove(tStr);
            }
        }
        return false;
    }
}
