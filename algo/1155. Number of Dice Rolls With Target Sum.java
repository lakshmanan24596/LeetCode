/*
You have d dice, and each die has f faces numbered 1, 2, ..., f.
Return the number of possible ways (out of fd total ways) modulo 10^9 + 7 to roll the dice so the sum of the face up numbers equals target.

Example 1:
Input: d = 1, f = 6, target = 3
Output: 1
Explanation: 
You throw one die with 6 faces.  There is only one way to get a sum of 3.

Example 2:
Input: d = 2, f = 6, target = 7
Output: 6
Explanation: 
You throw two dice, each with 6 faces.  There are 6 ways to get a sum of 7:
1+6, 2+5, 3+4, 4+3, 5+2, 6+1.

Example 3:
Input: d = 2, f = 5, target = 10
Output: 1
Explanation: 
You throw two dice, each with 5 faces.  There is only one way to get a sum of 10: 5+5.

Example 4:
Input: d = 1, f = 2, target = 3
Output: 0
Explanation: 
You throw one die with 2 faces.  There is no way to get a sum of 3.

Example 5:
Input: d = 30, f = 30, target = 500
Output: 222616187
Explanation: 
The answer must be returned modulo 10^9 + 7.

Constraints:
1 <= d, f <= 30
1 <= target <= 1000
*/


/*
    DP Memorization
    Time: dice * target * face
    Space: dice * target
*/
class Solution {
    int dice, face, target;
    Integer[][] DP;
    
    public int numRollsToTarget(int d, int f, int target) {
        this.dice = d;
        this.face = f;
        this.target = target;
        this.DP = new Integer[dice][target + 1];
        return dfs(0, 0);
    }
    
    public int dfs(int level, int currSum) {
        if (level == dice) {
            return currSum == target ? 1 : 0;
        }
        if (currSum > target || (currSum + ((dice - level) * face)) < target) {     // target very low or very high
            return 0;
        }
        if (DP[level][currSum] != null) {
            return DP[level][currSum];
        }
        
        int noOfWays = 0;
        for (int i = 1; i <= face; i++) {
            noOfWays += dfs(level + 1, currSum + i);
            noOfWays %= 1_000_000_007;
        }
        return DP[level][currSum] = noOfWays;
    }
}


/*
    Space optimization for above solution
    Current state depends only on previous state
    "Similar to coin change-2 problem"
    Time: dice * target * face
    Space: target
    https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/discuss/355940/C%2B%2B-Coin-Change-2
*/


/*
    Time optimization for above solution
    Time: dice * target
    Space: target
*/
/*
class Solution {
    public int numRollsToTarget(int d, int f, int target) {
        if (d > target || d * f < target) {
            return 0;
        }   
        int MOD = 1_000_000_007;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        
        for (int dice = 1; dice <= d; dice++) {
            int[] nextRow = new int[target + 1];
            int sum = dp[dice-1];
            for (int i = dice; i <= Math.min(target, dice * f); i++) {
                nextRow[i] = sum;
                sum = (sum + dp[i]) % MOD;
                if (i > f) {
                    sum = (sum - dp[i - f] + MOD) % MOD;
                }        
            }
            dp = nextRow;
        }
        return dp[target];       
    }
}
*/