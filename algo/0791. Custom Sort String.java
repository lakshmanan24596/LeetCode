/*
You are given two strings order and s. All the words of order are unique and were sorted in some custom order previously.
Permute the characters of s so that they match the order that order was sorted. 
More specifically, if a character x occurs before a character y in order, then x should occur before y in the permuted string.
Return any permutation of s that satisfies this property.

Example 1:
Input: order = "cba", s = "abcd"
Output: "cbad"
Explanation: 
"a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a". 
Since "d" does not appear in order, it can be at any position in the returned string. "dcba", "cdba", "cbda" are also valid outputs.

Example 2:
Input: order = "cbafg", s = "abcd"
Output: "cbad"

Constraints:
1 <= order.length <= 26
1 <= s.length <= 200
order and s consist of lowercase English letters.
All the characters of order are unique.
*/



/*
    1) sort
        time: order + s log s
        space: 1
        
    2) bucket sort
        time: s + order
        space: 26
*/
class Solution {
    public String customSortString(String order, String s) {
        int[] sCount = new int[26];
        int index;
        StringBuilder output = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
            index = s.charAt(i) - 'a';
            sCount[index]++;
        }
        for (int i = 0; i < order.length(); i++) {
            index = order.charAt(i) - 'a';
            while (sCount[index]-- > 0) {
                output.append(order.charAt(i));
            }
        }
        for (int i = 0; i < 26; i++) {
            while (sCount[i]-- > 0) {
                output.append((char) (i + 97));
            }
        }
        return output.toString();
    }
}
