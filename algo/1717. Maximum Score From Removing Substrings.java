/*
You are given a string s and two integers x and y. You can perform two types of operations any number of times.
Remove substring "ab" and gain x points.
For example, when removing "ab" from "cabxbae" it becomes "cxbae".
Remove substring "ba" and gain y points.
For example, when removing "ba" from "cabxbae" it becomes "cabxe".
Return the maximum points you can gain after applying the above operations on s.

Example 1:
Input: s = "cdbcbbaaabab", x = 4, y = 5
Output: 19
Explanation:
- Remove the "ba" underlined in "cdbcbbaaabab". Now, s = "cdbcbbaaab" and 5 points are added to the score.
- Remove the "ab" underlined in "cdbcbbaaab". Now, s = "cdbcbbaa" and 4 points are added to the score.
- Remove the "ba" underlined in "cdbcbbaa". Now, s = "cdbcba" and 5 points are added to the score.
- Remove the "ba" underlined in "cdbcba". Now, s = "cdbc" and 5 points are added to the score.
Total score = 5 + 4 + 5 + 5 = 19.

Example 2:
Input: s = "aabbaaxybbaabb", x = 5, y = 4
Output: 20

Constraints:
1 <= s.length <= 105
1 <= x, y <= 104
s consists of lowercase English letters.
*/


/*
    Logic: greedy
    Main logic: First remove the substrings with maximum gains, and then remove the remaining with minimum gains.
    https://leetcode.com/problems/maximum-score-from-removing-substrings/discuss/1008862/Java-Stack
    
    1) solution 1 --> time n, space n, stack based
    2) solution 2 --> time n, space 1, 
*/

/*
// Time: n, Space: n
class Solution {
    Stack<Character> stack = new Stack<Character>();
    char first, second;
    int gain1, gain2, totalGain = 0;
    
    public int maximumGain(String s, int x, int y) {
        if (x >= y) {
            first = 'a';
            second = 'b';
            gain1 = x;
            gain2 = y;
        } else {
            first = 'b';
            second = 'a';
            gain1 = y;
            gain2 = x;
        }
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == first) {
                stack.push(ch);
            } else if (ch == second) {
                if (!stack.isEmpty() && stack.peek() == first) {
                    totalGain += gain1;                                     // (first, second), gain1
                    stack.pop();
                } else {
                    stack.push(ch);
                }
            } else {
                getGainFromStack();
            }
        }
        getGainFromStack();
        return totalGain;
    }
    
    public void getGainFromStack() {
        Stack<Character> stack2 = new Stack<Character>();
        while (!stack.isEmpty()) {
            char ch = stack.pop();
            if (ch == first) {
                stack2.push(ch);
            } else if (ch == second) {
                if (!stack2.isEmpty() && stack2.peek() == first) {
                    totalGain += gain2;                                     // (second, first), gain2
                    stack2.pop();
                }
            }
        }
    }
}
*/


// Time: n, Space: 1, logic: same as above
class Solution {
    public int maximumGain(String s, int x, int y) {
        int ac = 0;
        int bc = 0;
        int res = 0;
        for(char c : s.toCharArray()){
            if(c == 'a'){
                ac++;
                if(x < y && bc > 0){
                    res += y;
                    ac--;
                    bc--;
                }
            }else if(c == 'b'){
                bc++;
                if(x > y && ac > 0){
                    res += x;
                    ac--;
                    bc--;
                }
            }else{
                res += Math.min(x, y) * Math.min(ac, bc);
                ac = 0;
                bc = 0;
            }
        }
        res += Math.min(x, y) * Math.min(ac, bc);
        return res;
    }
}