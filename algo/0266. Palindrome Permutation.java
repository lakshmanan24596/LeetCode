/*
Given a string s, return true if a permutation of the string could form a palindrome.

Example 1:
Input: s = "code"
Output: false

Example 2:
Input: s = "aab"
Output: true

Example 3:
Input: s = "carerac"
Output: true

Constraints:
1 <= s.length <= 5000
s consists of only lowercase English letters.
*/


/*
    logic: count arr --> only one odd count is allowed
    time: n
    space: 26
*/

class Solution {
    public boolean canPermutePalindrome(String s) {
        int[] count = new int[26];
        int index;
        boolean isOneCountVisited = false;
        
        for (int i = 0; i < s.length(); i++) {
            index = s.charAt(i) - 'a';
            count[index]++;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 == 1) {
                if (isOneCountVisited) {
                    return false;
                }
                isOneCountVisited = true;
            }
        }
        return true;
    }
}
