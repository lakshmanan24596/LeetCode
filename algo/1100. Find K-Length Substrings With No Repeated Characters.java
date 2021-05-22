/*
Given a string s, return the number of substrings of length k with no repeated characters.

Example 1:
Input: s = "havefunonleetcode", k = 5
Output: 6
Explanation: 
There are 6 substrings they are : 'havef','avefu','vefun','efuno','etcod','tcode'.

Example 2:
Input: s = "home", k = 5
Output: 0
Explanation: 
Notice k can be larger than the length of s. In this case is not possible to find any substring.

Note:
1 <= s.length <= 104
All characters of s are lowercase English letters.
1 <= k <= 104
*/


/*
    sliding window
    time: n, space: 26
*/
class Solution {
    public int numKLenSubstrNoRepeats(String s, int k) {
        if (s == null || s.length() == 0 || k == 0 || k > s.length()) {
            return 0;
        }
        if (k == 1) {
            return s.length();
        }
        int[] pos = new int[26];
        Arrays.fill(pos, -1);
        int output = 0;
        int index;
        int start = 0;
        
        for (int end = 0; end < s.length(); end++) {
            index = s.charAt(end) - 'a';
            if (pos[index] == -1) {                         // not repeated char  
                if (end - start == k) {
                    pos[s.charAt(start) - 'a'] = -1;
                    start++;
                }
            } else {                                        // repeated char
                while (start <= pos[index]) {
                    pos[s.charAt(start) - 'a'] = -1;
                    start++;
                }
            }
            pos[index] = end;
            if (end - start + 1 == k) {
                output++;
            }
        }
        return output;
    }
}