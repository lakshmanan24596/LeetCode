/*
Given an integer array nums, return the number of longest increasing subsequences.
Notice that the sequence has to be strictly increasing.

Example 1:
Input: nums = [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].

Example 2:
Input: nums = [2,2,2,2,2]
Output: 5
Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.

Constraints:
1 <= nums.length <= 2000
-106 <= nums[i] <= 106
*/

class Solution
{
    public int findNumberOfLIS(int[] nums)
    {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int[] length = new int[n];
        int[] count = new int[n];        
        int maxLength = 0, outputCount = 0;
        
        for(int i = 0; i < n; i++)
        {
            length[i] = 1;
            count[i] = 1;
            for(int j = 0; j < i; j++)
            {
                if(nums[i] > nums[j])
                {
                    if(length[j] + 1 > length[i])  // if combining with i makes a longest increasing subsequence
                    {
                        length[i] = length[j] + 1;
                        count[i] = count[j];
                    }
                    else if(length[j] + 1 == length[i])   // if combining with i makes another longest increasing subsequence
                    {
                        count[i] += count[j];   // main logic
                    }
                }
            }
          
            if(length[i] > maxLength)       // new length
            {
                maxLength = length[i];
                outputCount = count[i];
            }
            else if(length[i] == maxLength)     // existing length
            {
                outputCount += count[i];
            }
        }
        return outputCount;
    }
}
