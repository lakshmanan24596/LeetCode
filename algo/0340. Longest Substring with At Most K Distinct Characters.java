/*
Given a string s and an integer k, return the length of the longest substring of s that contains at most k distinct characters.

Example 1:
Input: s = "eceba", k = 2
Output: 3
Explanation: The substring is "ece" with length 3.

Example 2:
Input: s = "aa", k = 1
Output: 2
Explanation: The substring is "aa" with length 2.

Constraints:
1 <= s.length <= 5 * 104
0 <= k <= 50
*/



/*
    Logic: sliding window, 2-pointer, hashmap
    time: 2n
    space: 256 or n
*/

class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) {
            return 0;
        }
        int[] freq = new int[256];          // or use hashmap
        int maxLen = 0, currLen = 0;
        int letterIndex;
        int distinctCount = 0;              // or use hashmap.size()
        
        for (int start = 0, end = 0; end < s.length(); end++) {
            letterIndex = (int) s.charAt(end);                              // move end of window
            if (freq[letterIndex] == 0) {
                distinctCount++;
            }
            freq[letterIndex]++;
            
            while (distinctCount > k) {
                letterIndex = (int) s.charAt(start);                        // move start of window
                start++;
                freq[letterIndex]--;
                if (freq[letterIndex] == 0) {
                    distinctCount--;
                }
            }
            currLen = (end - start) + 1;
            maxLen = Math.max(maxLen, currLen);
        }
        return maxLen;
    }
}