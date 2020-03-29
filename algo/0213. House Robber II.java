/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. 
All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
             because they are adjacent houses.

Example 2:
Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
*/

class Solution 
{
    public int rob(int[] nums) 
    {
        // same as problem: https://leetcode.com/problems/house-robber/
        // only diff: first and last house are adjacent
        
        int n = nums.length;
        if(n == 0)
            return 0;
        if(n == 1)
            return nums[0];
        if(n == 2)
            return Math.max(nums[0], nums[1]);
        
        int excludeFirst = robUtil(nums, 1, n-1);
        int excludeLast = robUtil(nums, 0, n-2);
        return Math.max(excludeFirst, excludeLast);     // Time: 2n, Space: 2
    }
    
    public int robUtil(int[] nums, int start, int end)  // Time: n, Space: 2
    {          
        // logic: DP.. instead of array we can just use 2 variables  
               
        int secondPrev = nums[start];
        int firstPrev = Math.max(nums[start], nums[start+1]);
        
        for(int i = start + 2; i <= end; i++)
        {
            int curr = nums[i] + secondPrev;
            if(curr > firstPrev)
            {
                secondPrev = firstPrev;
                firstPrev = curr;
            }
            else
            {
                secondPrev = firstPrev;
            }
        }
        
        return firstPrev;
    }
}