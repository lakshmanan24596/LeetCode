/*
Given a string s, the power of the string is the maximum length of a non-empty substring that contains only one unique character.
Return the power of the string.

Example 1:
Input: s = "leetcode"
Output: 2
Explanation: The substring "ee" is of length 2 with the character 'e' only.

Example 2:
Input: s = "abbcccddddeeeeedcba"
Output: 5
Explanation: The substring "eeeee" is of length 5 with the character 'e' only.

Example 3:
Input: s = "triplepillooooow"
Output: 5

Example 4:
Input: s = "hooraaaaaaaaaaay"
Output: 11

Example 5:
Input: s = "tourist"
Output: 1
 
Constraints:
1 <= s.length <= 500
s contains only lowercase English letters.
*/

class Solution 
{
    public int maxPower(String s)
    {
        int maxLength = 0;
        if(s == null || s.length() == 0) {
            return maxLength;
        }
        
        char prevChar = s.charAt(0);
        char currChar;
        maxLength = 1;
        int currLength = 1;
        
        for(int i = 1; i < s.length(); i++)
        {
            currChar = s.charAt(i);
            if(currChar == prevChar) 
            {
                currLength++;
                maxLength = Math.max(maxLength, currLength);
            } 
            else 
            {
                currLength = 1; 
                prevChar = currChar;
            }
        }
        return maxLength;
    }
}