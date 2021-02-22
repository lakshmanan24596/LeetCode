/*
Return the lexicographically smallest subsequence of s that contains all the distinct characters of s exactly once.
Note: This question is the same as 316: https://leetcode.com/problems/remove-duplicate-letters/

Example 1:
Input: s = "bcabc"
Output: "abc"

Example 2:
Input: s = "cbacdcbc"
Output: "acdb"

Constraints:
1 <= s.length <= 1000
*/



/*
    ex: cdbcd and output = bcd
    c --> push c
    d --> push d because c < d. It is in lexi order.
    b --> d < b is not in lexi order. So we need to remove d if there is a future d. (main logic) 
          For checking future d existance, we have countArr for all char
          then c also < b. So remove c.
    c --> push c
    d --> push d
    
    
    Logic: greedy + stack
    Time: 2n
    Space: 2n
*/

class Solution {
    public String smallestSubsequence(String s) {
        Stack<Character> stack = new Stack<Character>();
        stack.push(s.charAt(0));
        boolean[] presentInStack = new boolean[26];
        presentInStack[s.charAt(0) - 'a'] = true;
        int[] count = new int[26];
        int index; 
        char ch;
        
        for (int i = 1; i < s.length(); i++) {
            ch = s.charAt(i);
            index = ch - 'a';
            count[index]++;
        }
        
        for (int i = 1; i < s.length(); i++) {
            ch = s.charAt(i);
            index = ch - 'a';
            if (!presentInStack[index]) {
                while (!stack.isEmpty() && s.charAt(i) < stack.peek() && count[stack.peek() - 'a'] > 0) {   // main logic
                    presentInStack[stack.peek() - 'a'] = false;
                    stack.pop();
                }
                presentInStack[index] = true;
                stack.push(ch);
            }
            count[index]--;
        }
        
        StringBuilder output = new StringBuilder();
        while (!stack.isEmpty()) {
            output.insert(0, stack.pop());
        }
        return output.toString();
    }
}