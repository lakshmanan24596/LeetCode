// Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

// You may assume no duplicates in the array.

// Example 1:
// Input: [1,3,5,6], 5
// Output: 2

// Example 2:
// Input: [1,3,5,6], 2
// Output: 1

// Example 3:
// Input: [1,3,5,6], 7
// Output: 4

// Example 4:
// Input: [1,3,5,6], 0
// Output: 0


class Solution 
{
    int[] nums;
    int target;
    
    public int searchInsert(int[] nums, int target) 
    {
        this.nums = nums;
        this.target = target;
        return binarySearch(0, nums.length-1);   
    }
    
    public int binarySearch(int l, int r)
    {
        if(l > r)
            return -1;
        
        int mid = (l+r)/2;
        
        if(nums[mid] == target)
        {   
            return mid;
        }
        else if(nums[mid] < target)
        {
            if(mid == nums.length-1 || nums[mid+1] > target)
                return mid+1;  
            else    
                return binarySearch(mid+1, r);
        }  
        else
        {
            if(mid == 0 || nums[mid-1] < target)
                return mid;
            else
                return binarySearch(l, mid-1);
        }
    }
}