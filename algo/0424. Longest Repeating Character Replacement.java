/*
Given a string s that consists of only uppercase English letters, you can perform at most k operations on that string.
In one operation, you can choose any character of the string and change it to any other uppercase English character.
Find the length of the longest sub-string containing all repeating letters you can get after performing the above operations.

Note:
Both the string's length and k will not exceed 104.

Example 1:
Input:
s = "ABAB", k = 2
Output:
4
Explanation:
Replace the two 'A's with two 'B's or vice versa.
 
Example 2:
Input:
s = "AABABBA", k = 1
Output:
4
Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
*/


/*
    Logic: sliding window and 2 pointer technique
    Time: n, Space: 26
    
    the problem mentions that it is a substring, so we need to think of "sliding window and 2 pointer technique"
    we need to make all char in the substring as same.
    so no of char to be replaced = length of the string - maxFreqLetterCount    // main logic
    
    ex-2: s = "AABCA", k = 2
        here maxFreqLetter is A. so we need to replace remaining all char as A
        no of char to be replaced = length of the string - maxFreqLetterCount 
                                  = 5 - 3
                                  = 2
        we need to replace 2 char to make all char equal.
*/

class Solution 
{
    public int characterReplacement(String s, int k) 
    {
        int n = s.length();
        int[] count = new int[26];
        int maxFreqLetterCount = 0;    
        int start = 0;
        int maxLength = 0;
        
        for(int end = 0; end < n; end++)
        {
            int index = s.charAt(end) - 'A';
            count[index]++;
            maxFreqLetterCount = Math.max(maxFreqLetterCount, count[index]);
            
            while(end - start + 1 - maxFreqLetterCount > k)   // main logic
            {
                count[s.charAt(start) - 'A']--;
                start++;    // move the window
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }
}
