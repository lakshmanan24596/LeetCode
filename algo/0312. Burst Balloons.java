/*
You are given n balloons, indexed from 0 to n - 1. 
Each balloon is painted with a number on it represented by an array nums. 
You are asked to burst all the balloons.
If you burst the ith balloon, you will get nums[left] * nums[i] * nums[right] coins. 
Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
Return the maximum coins you can collect by bursting the balloons wisely.

Example 1:
Input: nums = [3,1,5,8]
Output: 167
Explanation:
nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167

Example 2:
Input: nums = [1,5]
Output: 10

Constraints:
n == nums.length
1 <= n <= 500
0 <= nums[i] <= 100
*/


/*
// Time: without DP: n! and with DP: 2^n
class Solution 
{
    int[] nums;
    Integer[] DP;
    int n;
    
    public int maxCoins(int[] nums)         // Time: 2^n
    {
        this.nums = nums;
        n = nums.length;
        int allMask = (int)Math.pow(2, n);
        DP = new Integer[allMask];
        DP[allMask - 1] = 0;                // base case for recursion
        return recur(0);
    }
    
    public int recur(int mask)
    {
        if(DP[mask] != null) {
            return DP[mask];
        }
        int output = 0, currOutput, nextMask, bitValue;
        for(int i = 0; i < n; i++)
        {
            bitValue = 1 << i;
            if((mask & bitValue) == 0)      // check if current balloon is not already bursted
            {
                nextMask = mask | bitValue;
                currOutput = getCoins(i, mask) + recur(nextMask);
                output = Math.max(output, currOutput);
            }
        }
        return DP[mask] = output;
    }
    
    public int getCoins(int i, int mask)    // return coins for bursting i-th balloon
    {
        int leftCoin = 1, rightCoin = 1;
        int bitValue;
        
        for(int j = i - 1; j >= 0; j--) {
            bitValue = 1 << j;
            if((mask & bitValue) == 0) {
                leftCoin = nums[j];
                break;
            }
        }
        for(int j = i + 1; j < n; j++) {
            bitValue = 1 << j;
            if((mask & bitValue) == 0) {
                rightCoin = nums[j];
                break;
            }
        }
        return leftCoin * nums[i] * rightCoin;
    }
}
*/


/*
    DP: Time: n^3 and Space: n^2
    https://leetcode.com/problems/burst-balloons/discuss/76228/Share-some-analysis-and-explanations
    https://www.youtube.com/watch?v=IFNibRVgFBo
*/
class Solution 
{
    int[][] DP;
    int[] nums;
    public int maxCoins(int[] iNums) 
    {
        nums = new int[iNums.length + 2];
        int n = 1;
        for(int x : iNums) {      // remove 0 because no coins can be collected when multiplied with 0
            if (x > 0) {
                nums[n++] = x;
            }
        }       
        nums[0] = nums[n++] = 1;  // append 1 in both end
        DP = new int[n][n];
        return recur(0, n - 1);
    }

    public int recur(int left, int right) 
    {
        if(left + 1 == right) {
            return 0;
        }
        if(DP[left][right] > 0) {
            return DP[left][right];
        }

        int output = 0, currOutput;
        for(int i = left + 1; i <= right - 1; ++i) 
        {
            currOutput = nums[left] * nums[i] * nums[right] + recur(left, i) + recur(i, right);
            output = Math.max(output, currOutput);
        }
        return DP[left][right] = output;
    }
}
