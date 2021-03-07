/*
Given two strings s and t, return the number of distinct subsequences of s which equals t.
A string's subsequence is a new string formed from the original string by deleting some (can be none) of the characters without disturbing the remaining characters' relative positions. (i.e., "ACE" is a subsequence of "ABCDE" while "AEC" is not).
It is guaranteed the answer fits on a 32-bit signed integer.

Example 1:
Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from S.
rabbbit
rabbbit
rabbbit

Example 2:
Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from S.
babgbag
babgbag
babgbag
babgbag
babgbag

Constraints:
0 <= s.length, t.length <= 1000
s and t consist of English letters.
*/



/*
    DP:
    states: 
        1) tLevel (t)
        2) sIndex (s)
        
    time: t.len * s.len
    space: t.len * s.len
    
    we can apply space optimization because. current level depends only on prev level
    so space can be reduced from s*t to s
*/


class Solution {
    String s, t;
    Map<Character, List<Integer>> sMap;
    Integer[][] DP;
    
    public int numDistinct(String s, String t) {
        this.s = s;
        this.t = t;
        this.sMap = new HashMap<Character, List<Integer>>();
        this.DP = new Integer[t.length()][s.length()];
        char ch;
        List<Integer> indexList;
        
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            indexList = sMap.get(ch);
            if (indexList == null) {
                indexList = new ArrayList<Integer>();
                sMap.put(ch, indexList);
            }
            indexList.add(i);
        }
        return dfs(0, 0);
    } 
    
    public int dfs(int tLevel, int sIndex) {
        if (tLevel == t.length()) {
            return 1;
        }
        if (sIndex >= s.length()) {
            return 0;
        }
        if (DP[tLevel][sIndex] != null) {
            return DP[tLevel][sIndex];
        }
        List<Integer> indexList = sMap.get(t.charAt(tLevel));
        if (indexList == null) {
            return 0;
        }
        int index;
        int output = 0;
        
        for (int i = indexList.size() - 1; i >= 0; i--) {
            index = indexList.get(i);
            if (index >= sIndex) {
                output += dfs(tLevel + 1, index + 1);
            } else {
                break;
            }
        }
        return DP[tLevel][sIndex] = output;
    }
}