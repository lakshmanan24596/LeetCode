/*
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.

Example 1:
Input: s1 = "ab" s2 = "eidbaooo"
Output: True
Explanation: s2 contains one permutation of s1 ("ba").

Example 2:
Input:s1= "ab" s2 = "eidboaoo"
Output: False

Constraints:
The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].
*/

class Solution 
{
    public boolean checkInclusion(String s1, String s2)     // Time: strlen * 26
    {
        int len1 = s1.length();
        int len2 = s2.length();
        if(len1 > len2) {
            return false;
        }
        int i = 0;
        int[] count1 = new int[26];
        int[] count2 = new int[26];
        
        while(i < len1) {
            count1[s1.charAt(i) - 'a']++;
            count2[s2.charAt(i++) - 'a']++;
        }
        if(isSame(count1, count2)) {
            return true;
        }
        
        while(i < len2) 
        {
            count2[s2.charAt(i - len1) - 'a']--;      // sliding window
            count2[s2.charAt(i++) - 'a']++;
            if(isSame(count1, count2)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSame(int[] count1, int[] count2) // check one string is a permutation of another string using their count
    {
        for(int i = 0; i < 26; i++) {
            if(count1[i] != count2[i]) {
                return false;
            }
        }
        return true;
    }
}
