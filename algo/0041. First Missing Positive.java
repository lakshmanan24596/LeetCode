/*
Given an unsorted integer array, find the smallest missing positive integer.

Example 1:
Input: [1,2,0]
Output: 3

Example 2:
Input: [3,4,-1,1]
Output: 2

Example 3:
Input: [7,8,9,11,12]
Output: 1

Note:
Your algorithm should run in O(n) time and uses constant extra space.
*/

class Solution 
{
    public int firstMissingPositive(int[] nums) 
    {
        // 1) O(n), O(n) --> using hashmap
        // 2. O(n), O(1) --> swap it with exact corresponding position (arr[arr[i]])
                
        for(int i = 0; i < nums.length; i++)
        {
            while(nums[i] != i + 1)  // main logic ::: positive number starts from 1... so we need i position to be i+1 data
            {
                if(nums[i] > nums.length || nums[i] <= 0)   // handle out of range elements
                    break;
                if(nums[i] == nums[nums[i]-1])  // handle duplicate
                    break;
                swap(nums, i, nums[i]-1);
            }
        }
        
        for(int i = 0; i < nums.length; i++)
            if(nums[i] != i + 1)
                return i + 1;       
        return nums.length + 1;
    }
    
    public void swap(int[] nums, int a, int b)
    {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}