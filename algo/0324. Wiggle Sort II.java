/*
Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example 1:
Input: nums = [1, 5, 1, 1, 6, 4]
Output: One possible answer is [1, 4, 1, 5, 1, 6].

Example 2:
Input: nums = [1, 3, 2, 2, 3, 1]
Output: One possible answer is [2, 3, 1, 3, 1, 2].

Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?
*/

class Solution 
{
    public void wiggleSort(int[] nums)  // Brute --> Time = n*log(n) and space = n
    {
        int n = nums.length;
        if(nums.length <= 1) {
            return;
        }
        
        int[] copy = Arrays.copyOf(nums, n);
        Arrays.sort(copy);
        
        /* this wont work for testcase: 4,5,5,6        
        int j = 0;
        for(int i = 0; i < n; i += 2) {
            nums[i] = copy[j++];
        }
        for(int i = 1; i < n; i += 2) {
            nums[i] = copy[j++];
        }
        */
        
        int j = 0;
        int mid = (n - 1) / 2;
        for(int i = mid; i >= 0; i--) {
            nums[j] = copy[i];
            j += 2;
        }
        
        j = 1;
        for(int i = n-1; i > mid; i--) {
            nums[j] = copy[i];
            j += 2;
        }        
    }
    
    /*
    This method fails for duplicate elements (Ex: 1,1,1,2,2,2) test case fails
    
    public void wiggleSort(int[] nums) 
    {
        if(nums.length <= 1) {
            return;
        }
        
        for(int i = 0, j = 1; i < nums.length - 1; i++, j++)
        {
            if(i % 2 == 0) {
                if(nums[i] >= nums[j]) {
                    swap(nums, i, j);   // i should be < j
                }
            } else {
                if(nums[i] <= nums[j]) {
                    swap(nums, i, j);   // i should be > j
                }
            }
        }
    }
    
    public void swap(int[] nums, int i, int j)
    {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    */
}