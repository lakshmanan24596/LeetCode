/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
Note:
The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.

Example 1:
Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:
Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
*/

class Solution 
{
    Set<String> wordDictSet;
    Set<String> DP;
    
    public boolean wordBreak(String s, List<String> wordDict) 
    {
        if(s == null) {
            return false;
        } 
        if(s.equals("")) {
            return true;
        }
        
        wordDictSet = new HashSet<String>(wordDict);
        DP = new HashSet<String>();
        return recur(s);
    }
    
    public boolean recur(String input)
    {   
        if(DP.contains(input)) {
            return false;
        }
        
        String prefix;
        int n = input.length();
        for(int i = 1; i <= n; i++) 
        {
            prefix = input.substring(0, i);
            if(wordDictSet.contains(prefix))                        // prefix
            {                      
                if(i == n || recur(input.substring(i, n))) {        // suffix
                    return true;
                }
            }
        }
        
        DP.add(input);  // DP contains inputs which are false
        return false;
    }
}