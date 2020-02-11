// Write a function to find the longest common prefix string amongst an array of strings.

// If there is no common prefix, return an empty string "".

// Example 1:
// Input: ["flower","flow","flight"]
// Output: "fl"

// Example 2:
// Input: ["dog","racecar","car"]
// Output: ""
// Explanation: There is no common prefix among the input strings.


class Solution 
{
    public String longestCommonPrefix(String[] strs) 
    {
        if(strs == null || strs.length == 0) return "";
        int length = strs.length;
        if(length == 1) return strs[0];
        
        int minLen = Integer.MAX_VALUE;
        for(int j=0; j<length; j++)
        {
            if(strs[j].equals(""))
                return "";
            minLen = Math.min(minLen, strs[j].length());
        }
        
        for(int i = 0; i < minLen; i++)
        {
            for(int j = 0; j < length; j++)
            {
                if(strs[j].charAt(i) != strs[0].charAt(i))     // char not matched, so return
                   return strs[0].substring(0, i);
            }
        }
        
        return strs[0].substring(0, minLen);    // all char matched.. so return minLen string
    }
}