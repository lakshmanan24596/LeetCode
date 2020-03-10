/*
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:
Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Follow up:
If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
*/

class Solution 
{
    public int maxSubArray(int[] nums)  // kadane algo
    {
        if(nums.length == 0)
            return 0;
        
        int currMax = nums[0];
        int overallMax = nums[0];
        
        for(int i = 1; i < nums.length; i++)
        {
            currMax = Math.max(nums[i], currMax + nums[i]);
            overallMax = Math.max(overallMax, currMax);
        }
        
        return overallMax;
        
        // divide and conquer --> https://www.geeksforgeeks.org/maximum-subarray-sum-using-divide-and-conquer-algorithm/
        // similar to merge sort
        // consider mid is part of ans.. then recur left and recur right
    }
}