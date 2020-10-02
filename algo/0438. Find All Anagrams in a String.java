/*
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
The order of output does not matter.

Example 1:
Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:
Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
*/

class Solution 
{
    public List<Integer> findAnagrams(String s, String p) 
    {
        List<Integer> output = new ArrayList<Integer>();
        int pLength = p.length();
        int sLength = s.length();
        if(pLength > sLength) {
            return output;
        }
        
        char[] pKey = new char[26];
        char[] sKey = new char[26];     
        for(int i = 0; i < pLength; i++)  
        {
        	pKey[p.charAt(i) - 'a']++; 
        	sKey[s.charAt(i) - 'a']++;
        }
        if(Arrays.equals(sKey, pKey)) 
        {
            output.add(0);
        }
        
        int iterationSize = sLength - pLength + 1;
        for(int i = 1; i < iterationSize; i++)
        {
        	sKey[s.charAt(i - 1) - 'a'] -= 1; // Add current character to current window
        	sKey[s.charAt(i + pLength - 1) - 'a'] += 1; // Remove the first character of previous window
            if(Arrays.equals(sKey, pKey)) // Main logic
            {
                output.add(i);
            }
        }
        return output;
    }
}
