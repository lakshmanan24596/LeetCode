// Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

// Example:

// Given array nums = [-1, 2, 1, -4], and target = 1.

// The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).


class Solution 
{
    public int threeSumClosest(int[] nums, int target) 
    {
        Arrays.sort(nums);
        int len = nums.length;
        int currSum, currDiff;
        int output = 0, minDiff = Integer.MAX_VALUE;
        
        for(int i=0; i<len-2; i++)
        {
            int j = i + 1;
            int k = len-1;
            while(j < k)
            {
                currSum = nums[i] + nums[j] + nums[k];  
                
                if(currSum == target)
                    return currSum;
                else if(currSum < target)
                    j++;
                else
                    k--;
                             
                currDiff = Math.abs(target - currSum);      // main logic
                if(currDiff < minDiff)
                {
                    minDiff = currDiff;
                    output = currSum;
                }
            }
        }
        return output;
    }
}