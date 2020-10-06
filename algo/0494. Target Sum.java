/*
You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. 
Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5

Explanation: 
-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
 
Constraints:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.
*/

class Solution 
{
    int[] nums;
    int sum;
    int[][] DP;
    int sumLimit = 1000;
    int sumLimitTotalSize = (2 * sumLimit) + 1; // because ques is, sum wont exceed 1000. So range is -1000 to +1000 including 0. if limit is not given then instead of array we need to use hashMap for DP.
    
    public int findTargetSumWays(int[] nums, int sum) 
    {
        this.nums = nums;
        this.sum = sum;
        this.DP = new int[nums.length][sumLimitTotalSize]; 
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < sumLimitTotalSize; j++) {
                DP[i][j] = Integer.MIN_VALUE;
            }
        }
        return findTargetSumWays(0, 0);
    }
    
    public int findTargetSumWays(int currIndex, int currSum)
    {
        if(currIndex == nums.length) {
            return (currSum == sum) ? 1 : 0;
        }
        if(DP[currIndex][currSum+sumLimit] != Integer.MIN_VALUE) {  // memorization
            return DP[currIndex][currSum+sumLimit];
        }
        
        int left = findTargetSumWays(currIndex + 1, currSum - nums[currIndex]);
        int right = findTargetSumWays(currIndex + 1, currSum + nums[currIndex]);
        return DP[currIndex][currSum+sumLimit] = left + right;
    }
}
