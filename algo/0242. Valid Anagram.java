/*
Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:
Input: s = "anagram", t = "nagaram"
Output: true

Example 2:
Input: s = "rat", t = "car"
Output: false

Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?
*/

class Solution 
{
    public boolean isAnagram(String s, String t) 
    {
        int sLength = s.length();
        int tLength = t.length();
        if(sLength != tLength) {
            return false;
        }
        
        int[] hashArr = new int[26];
        int index;
        for(int i = 0; i < sLength; i++)
        {
            index = s.charAt(i) - 'a';
            hashArr[index]++;
        }
        
        for(int i = 0; i < tLength; i++)
        {
            index = t.charAt(i) - 'a';
            if(hashArr[index] == 0) {
                return false;
            }
            hashArr[index]--;
        }
        
        return true;
    }
}