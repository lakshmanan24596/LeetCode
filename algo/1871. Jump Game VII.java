/*
You are given a 0-indexed binary string s and two integers minJump and maxJump. 
In the beginning, you are standing at index 0, which is equal to '0'. 

You can move from index i to index j if the following conditions are fulfilled:
i + minJump <= j <= min(i + maxJump, s.length - 1), and
s[j] == '0'.
Return true if you can reach index s.length - 1 in s, or false otherwise.

Example 1:
Input: s = "011010", minJump = 2, maxJump = 3
Output: true
Explanation:
In the first step, move from index 0 to index 3. 
In the second step, move from index 3 to index 5.

Example 2:
Input: s = "01101110", minJump = 2, maxJump = 3
Output: false

Constraints:
2 <= s.length <= 105
s[i] is either '0' or '1'.
s[0] == '0'
1 <= minJump <= maxJump < s.length
*/


/*
    1) brute               : time: j^n, space: n
    2) DP                  : time: j*n, space: n
    3) BFS queue           : time: j*n, space: n
    4) DP + sliding window : time: n,   space: n
*/

class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        char[] sArr = s.toCharArray();
        int n = sArr.length;
        if (sArr[0] != '0' || sArr[n - 1] != '0') {
            return false;
        }
        boolean[] DP = new boolean[n];
        DP[0] = true;
        int count = 0;                                      // count of "true" in current window
        
        for (int i = minJump; i < n; i++) {
            count += DP[i - minJump] ? 1 : 0;               // expand the window
            if (i > maxJump) {
                count -= DP[i - maxJump - 1] ? 1 : 0;       // shrink the window
            }
            
            DP[i] = (sArr[i] == '0' && count > 0);          // main logic
        }
        return DP[n - 1];
    }
}