/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
You are given a target value to search. If found in the array return true, otherwise return false.

Example 1:
Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true

Example 2:
Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false

Follow up:
This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
Would this affect the run-time complexity? How and why?
*/

class Solution 
{   
    public boolean search(int[] nums, int target) 
    {
        return binarySearch(nums, target, 0, nums.length-1);   
    }
    
    // use case: 1,1,1,3,1 ---> here arr[left] = arr[mid] = arr[right].. so problem occurs
    // here left side [1,1,1] is sorted.. target is in right.. but we recur left
    // so we need to check in both sides.. O(n)
    
    public boolean binarySearch(int[] nums, int target, int l, int r)
    {
        if(l > r)
            return false;
        
        int mid = (l+r)/2;
        if(nums[mid] == target)
            return true;       
        
        if(nums[l] < nums[mid])         // left side is sorted
        {
            if(target >= nums[l] && target < nums[mid])
                return binarySearch(nums, target, l, mid-1);
            return binarySearch(nums, target, mid+1, r);
        }
        else if(nums[mid] < nums[r])    // right side is sorted
        {
            if(target > nums[mid] && target <= nums[r])
                return binarySearch(nums, target, mid+1, r);
            return binarySearch(nums, target, l, mid-1);
        }
        else
            return (binarySearch(nums, target, l, mid-1) || binarySearch(nums, target, mid+1, r));
    }
}