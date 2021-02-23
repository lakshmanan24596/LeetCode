/*
A string is called happy if it does not have any of the strings 'aaa', 'bbb' or 'ccc' as a substring.
Given three integers a, b and c, return any string s, which satisfies following conditions:
s is happy and longest possible.
s contains at most a occurrences of the letter 'a', at most b occurrences of the letter 'b' and at most c occurrences of the letter 'c'.
s will only contain 'a', 'b' and 'c' letters.
If there is no such string s return the empty string "".

Example 1:
Input: a = 1, b = 1, c = 7
Output: "ccaccbcc"
Explanation: "ccbccacc" would also be a correct answer.

Example 2:
Input: a = 2, b = 2, c = 1
Output: "aabbc"

Example 3:
Input: a = 7, b = 1, c = 0
Output: "aabaa"
Explanation: It's the only correct answer in this case.

Constraints:
0 <= a, b, c <= 100
a + b + c > 0
*/


/*
    1) DP
    logic: at each step we can either pick a or b or c, by applying the neccessay condition
    state: a, b, c and also prev 2 characters
    time: a*b*c
    
    2) Greedy
    logic: pick the firstMax first and if condition fails then pick the secondMax.
    Time: a+b+c
    Space: a+b+c
    
    Other implementations:
    https://leetcode.com/problems/longest-happy-string/discuss/564273/Java-Happy-Greedy-String-without-PQ
    https://leetcode.com/problems/longest-happy-string/discuss/565831/Java-Detailed-Explanation-Greedy-PriorityQueue-Easy-Understand-%2B-Clear
*/

class Solution {
    public String longestDiverseString(int a, int b, int c) {
        StringBuilder output = new StringBuilder();
        int[] val = new int[] {a, b, c};
        
        while (true) {
            char firstMax = getFirstMax(val);
            if (val[firstMax - 'a'] == 0) {
                break;
            }
            int size = output.length();
            if ((size >= 2) && (output.charAt(size - 1) == firstMax) && (output.charAt(size - 2) == firstMax)) {    // main condition
                char secondMax = getSecondMax(val);
                if (val[secondMax - 'a'] == 0) {
                    break;
                }
                output.append(secondMax);       // if not able to take 1st max, then take 2nd max
                val[secondMax - 'a']--;
            } else {
                output.append(firstMax);        // take 1st max
                val[firstMax - 'a']--;
            }
        }
        return output.toString();
    }
    
    public char getFirstMax(int[] val) {
        if (val[0] >= val[1] && val[0] >= val[2]) {
            return 'a';
        } 
        return (val[1] >= val[2]) ? 'b' : 'c';
    }
    
    public char getSecondMax(int[] val) {
        if (val[0] >= val[1] && val[0] >= val[2]) {
            return (val[1] >= val[2]) ? 'b' : 'c';
        } 
        else if (val[1] >= val[0] && val[1] >= val[2]) {
            return (val[0] >= val[2]) ? 'a' : 'c';
        } 
        else {
            return (val[0] >= val[1]) ? 'a' : 'b';
        }
    }
}