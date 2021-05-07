/*
Given two strings s and t, return true if they are both one edit distance apart, otherwise return false.
A string s is said to be one distance apart from a string t if you can:

Insert exactly one character into s to get t.
Delete exactly one character from s to get t.
Replace exactly one character of s with a different character to get t.

Example 1:
Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.

Example 2:
Input: s = "", t = ""
Output: false
Explanation: We cannot get t from s by only one step.

Example 3:
Input: s = "a", t = ""
Output: true

Example 4:
Input: s = "", t = "A"
Output: true

Constraints:
0 <= s.length <= 104
0 <= t.length <= 104
s and t consist of lower-case letters, upper-case letters and/or digits.
*/



/*
    2-pointer
    time: n
    space: 1
*/
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if (s.length() > t.length()) {                          // to make s < t
            return isOneEditDistance(t, s);
        }
        if ((t.length() - s.length()) > 1) {                    // because max 1 edit is allowed
            return false;
        }
        boolean isEditRequired = false;
        boolean isSamelength = s.length() == t.length();
        int i = 0, j = 0;
        
        while(i < s.length()) {
            if (s.charAt(i) != t.charAt(j)) {                   // main logic
                if (isEditRequired) {
                    return false;
                }
                isEditRequired = true;
                if (isSamelength) {
                    i++;
                    j++;
                } else {
                    j++;
                }
            } else {
                i++;
                j++;
            }
        }
        if (j < t.length()) {                                   // example-4
            isEditRequired = true;
        }
        return isEditRequired;
    }
}
