// Given a string, find the length of the longest substring without repeating characters.

// Example 1:

// Input: "abcabcbb"
// Output: 3 
// Explanation: The answer is "abc", with the length of 3. 
// Example 2:

// Input: "bbbbb"
// Output: 1
// Explanation: The answer is "b", with the length of 1.
// Example 3:

// Input: "pwwkew"
// Output: 3
// Explanation: The answer is "wke", with the length of 3. 
// Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

class Solution 
{
    public int lengthOfLongestSubstring(String str) 
    {     
        if(str.equals("") || str == null)
            return 0;
        
        HashMap<Character, Integer> map = new HashMap<Character, Integer>(); // store char and its last occurence
        map.put(str.charAt(0),0);
        int start = 0, maxLen = 1, length = str.length(), i=1;
        
        for(; i<length; i++)
        {
            char ch = str.charAt(i);
            if(map.containsKey(ch))
            {
                int prevLastOccur = map.get(ch);
                if(prevLastOccur >= start)
                {     
                    // duplicate in current window, so change start and update maxLen
                    maxLen = Math.max(maxLen, i-start);
                    start = prevLastOccur+1;
                }
            }
            map.put(ch,i);  // update last occurence
        }
        
        return Math.max(maxLen, i-start);
    }
}
