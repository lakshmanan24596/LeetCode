/*
Given an array nums of integers, we need to find the maximum possible sum of elements of the array such that it is divisible by three.

Example 1:
Input: nums = [3,6,5,1,8]
Output: 18
Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).

Example 2:
Input: nums = [4]
Output: 0
Explanation: Since 4 is not divisible by 3, do not pick any number.

Example 3:
Input: nums = [1,2,3,4,4]
Output: 12
Explanation: Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).
 
Constraints:
1 <= nums.length <= 4 * 10^4
1 <= nums[i] <= 10^4
*/


/*
    Solution:
    https://leetcode.com/problems/greatest-sum-divisible-by-three/discuss/927253/step-by-step%3A-top-down-greater-bottom-up-greater-optimised-DP-U-should-do-a-recursive-solution-first

    Implementation:
    1) recur + memo    = n * sum, n * sum
    2) recur + memo    = n * 3, n * 3
    3) tabu            = n * 3, n * 3
    4) tabu space opti = n * 3, 3
*/

/*
// 1) recur + memo = n * sum, n * sum
class Solution 
{
    int[] nums;
    Integer[][] DP;
    public int maxSumDivThree(int[] nums) 
    {
        this.nums = nums;
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        this.DP = new Integer[nums.length][sum + 1];
        return recur(0, 0);
    }
    
    public int recur(int level, int sum)
    {
        if(level == nums.length) {
            return sum % 3 == 0 ? sum : 0;
        }
        if(DP[level][sum] != null) {
            return DP[level][sum];
        }
        
        int dontPick = 0;
        if(nums[level] % 3 != 0) {
            dontPick = recur(level + 1, sum);
        }
        int pick = recur(level + 1, sum + nums[level]);
        
        return DP[level][sum] = Math.max(dontPick, pick);
    }
}
*/

/*
// 2) recur + memo = n * 3, n * 3
class Solution 
{
    int[] nums;
    Integer[][] DP;
    public int maxSumDivThree(int[] nums) 
    {
        this.nums = nums;
        this.DP = new Integer[nums.length][3];
        return recur(0, 0);
    }
    
    public int recur(int level, int mod)
    {
        if(level == nums.length) {
            return mod == 0 ? 0 : Integer.MIN_VALUE;
        }
        if(DP[level][mod] != null) {
            return DP[level][mod];
        }
        
        int dontPick = 0;
        if(nums[level] % 3 != 0) {
            dontPick = recur(level + 1, mod);
        }
        int pick = nums[level] + recur(level + 1, (mod + nums[level]) % 3);
        
        return DP[level][mod] = Math.max(dontPick, pick);
    }
}
*/

/*
// 3) tabu = n * 3, n * 3
class Solution 
{
    public int maxSumDivThree(int[] nums) 
    {
        int n = nums.length;
        int[][] DP = new int[n + 1][3];
        for(int level = 0; level <= n; level++) {
            for(int mod = 1; mod < 3; mod++) {
                DP[level][mod] = Integer.MIN_VALUE;
            }
        }
        
        for(int level = 1; level <= n; level++)
        {
            for(int mod = 0; mod < 3; mod++)
            {
                int dontPick = DP[level - 1][mod];
                int pick = nums[level - 1] + DP[level - 1][(mod + nums[level-1]) % 3];
                DP[level][mod] = Math.max(dontPick, pick);
            }   
        }
        return DP[n][0];
    }
}
*/


/* 
    4) tabu space opti
    time: n * 3
    space: 3
    curr state depends only on prev state, so we can store prevState DP array alone
    since DP is reduced from 2D to 1D, time is also reduced to fetch and store data
*/    
class Solution 
{
    public int maxSumDivThree(int[] nums) 
    {
        int n = nums.length;
        int[] DP = new int[3];
        DP[1] = DP[2] = Integer.MIN_VALUE;
        
        for(int level = 1; level <= n; level++)
        {
            int[] tempDP = new int[3];
            for(int mod = 0; mod < 3; mod++)
            {
                int dontPick = DP[mod];
                int pick = nums[level - 1] + DP[(mod + nums[level-1]) % 3];
                tempDP[mod] = Math.max(dontPick, pick);
            } 
            DP = tempDP;
        }
        return DP[0];
    }
}
