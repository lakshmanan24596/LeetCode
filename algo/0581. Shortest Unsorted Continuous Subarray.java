/*
Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.
You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.

Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.
*/

class Solution 
{
    public int findUnsortedSubarray(int[] nums)     // Time = 2N, Space = 1
    {
        if(nums == null || nums.length <= 1) {
            return 0;
        }
        
        int begin = -1, end = -1;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        
        for(int i = 0; i < nums.length; i++)    // find end from left to right
        {
            max = Math.max(max, nums[i]);
            if(nums[i] < max) {
                end = i;
            }
        }
        if(end == -1) {  // already array is sorted
            return 0;
        }
        
        for(int i = nums.length - 1; i >= 0; i--)   // find begin right to left
        {
            min = Math.min(min, nums[i]);
            if(nums[i] > min) {
                begin = i;
            }
        }
        if(begin == -1) {
            return 0;
        }
        
        return end - begin + 1;
    }
}
