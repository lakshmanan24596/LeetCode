/*
Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

Example 1:
Input: 2
Output: 1
Explanation: 2 = 1 + 1, 1 × 1 = 1.

Example 2:
Input: 10
Output: 36
Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.

Note: You may assume that n is not less than 2 and not larger than 58.
*/

/*
    n^2 solution
        https://leetcode.com/problems/integer-break/discuss/80694/Java-DP-solution
        
    n solution
       if(n >= 7) then DP[i] = DP[i-3] * 3
               
    n solution
        https://leetcode.com/problems/integer-break/discuss/80720/Easy-to-understand-C%2B%2B-with-explanation   
        
    log n solution
        https://leetcode.com/problems/integer-break/discuss/80785/O(log(n))-Time-solution-with-explanation

    https://leetcode.com/problems/integer-break/discuss/80721/Why-factor-2-or-3-The-math-behind-this-problem.
    
    If an optimal product contains a factor f >= 4, then you can replace it with factors 2 and f-2 without losing optimality, as 2*(f-2) = 2f-4 >= f. So you never need a factor greater than or equal to 4, meaning you only need factors 1, 2 and 3 (and 1 is of course wasteful and you'd only use it for n=2 and n=3, where it's needed).
    For the rest I agree, 3*3 is simply better than 2*2*2, so you'd never use 2 more than twice.
    
*/

class Solution 
{
    public int integerBreak(int n) 
    {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int curr;
        
        for(int i = 2; i <= n; i++) 
        {
            for(int j = 1; j <= i/2; j++)  // for n = 10, check (1,9), (2,8), (3,7), (4,6), (5,5) which is (j, i-j)
            {
                curr = Math.max(j, dp[j]) * Math.max(i - j, dp[i - j]);
                dp[i] = Math.max(dp[i], curr);
            }
        }
        return dp[n];
    }
}
