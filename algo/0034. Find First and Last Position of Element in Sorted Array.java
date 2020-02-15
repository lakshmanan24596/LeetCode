// Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

// Your algorithm's runtime complexity must be in the order of O(log n).

// If the target is not found in the array, return [-1, -1].

// Example 1:
// Input: nums = [5,7,7,8,8,10], target = 8
// Output: [3,4]

// Example 2:
// Input: nums = [5,7,7,8,8,10], target = 6
// Output: [-1,-1]


class Solution 
{
    int[] nums;
    int[] output = new int[]{-1, -1};
    Type typeObj;
    int target;
    
    enum Type
    {
        FIRST, LAST;
    }
    
    public int[] searchRange(int[] nums, int target) 
    {   
        this.nums = nums;
        this.target = target;
        
        typeObj = Type.FIRST;
        output[0] = binarySearch(0, nums.length-1);   // O(log n)
        
        typeObj = Type.LAST;
        output[1] = binarySearch(0, nums.length-1);   // O(log n)      
        
        return output;
    }
    
    public int binarySearch(int l, int r)
    {
        if(r < l)
            return -1;
        
        int mid = (typeObj == Type.FIRST) ? (l+r)/2 : (int)Math.ceil((double)(l+r)/2); 
        
        if(nums[mid] < target)
        {
            return binarySearch(mid+1, r);
        }
        else if(nums[mid] > target)
        {
            return binarySearch(l, mid-1);
        }
        else
        {
            if(typeObj == Type.FIRST)
            {
                if(mid == 0 || (nums[mid] == target && nums[mid-1] != target))
                    return mid;
                return binarySearch(l, mid);
            }
            else
            {
                if(mid == nums.length-1 || (nums[mid] == target && nums[mid+1] != target))
                    return mid;
                return binarySearch(mid, r);
            }
        }
    }
}