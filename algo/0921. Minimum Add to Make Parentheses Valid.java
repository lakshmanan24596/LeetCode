/*
A parentheses string is valid if and only if:

It is the empty string,
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.

For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
Return the minimum number of moves required to make s valid.

Example 1:
Input: s = "())"
Output: 1

Example 2:
Input: s = "((("
Output: 3

Constraints:
1 <= s.length <= 1000
s[i] is either '(' or ')'.
*/



/*
// logic: stack, time: O(n), space: O(n)

class Solution {
    public int minAddToMakeValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        char brace;
        
        for (int i = 0; i < s.length(); i++) {
            brace = s.charAt(i);
            if (brace == '(') {         // open brace
                stack.push(brace);
            } else {                    // close brace
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    stack.push(brace);
                }
            }
        }
        return stack.size();
    }
}
*/


// logic: without stack, time: O(n), space: O(1)
class Solution {
    public int minAddToMakeValid(String s) {
        char brace;
        int open = 0, close = 0;
        
        for (int i = 0; i < s.length(); i++) {
            brace = s.charAt(i);
            if (brace == '(') {         // open brace
                open++;
            } else {                    // close brace
                if (open > 0) {
                    open--;
                } else {
                    close++;
                }
            }
        }
        return open + close;
    }
}
