/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.
You may assume no duplicate exists in the array.

Example 1:
Input: [3,4,5,1,2] 
Output: 1

Example 2:
Input: [4,5,6,7,0,1,2]
Output: 0
*/

class Solution 
{
    public int findMin(int[] nums) 
    {
        // logic: finding pivot element is the answer
        // 3 use cases:
        //            a) 1, 2, 3
        //            b) 3, 1, 2
        //            c) 2, 3, 1
       
        if(nums == null || nums.length == 0)
            return -1;
        
        int left = 0, right = nums.length-1;
        
        while(left <= right)
        {            
            if(nums[left] <= nums[right])           // main logic.. if sorted then return first element
                return nums[left];
            
            int mid = (left + right) / 2;
            
            if(nums[mid] >= nums[left])
                left = mid + 1;                     // right side
            else
                right = mid;                        // left side
        }
        
        return -1;
    }
}

// class Solution 
// {
//     public int findMin(int[] nums) 
//     {
//         // logic: finding pivot element is the answer
       
//         int left = 0, right = nums.length-1;
        
//         while(left <= right)
//         {
//             int mid = (left + right) / 2;
            
//             if(nums[left] <= nums[right])                           // sorted use case
//                 return nums[left];
            
//             if(nums[mid] < nums[mid+1] && nums[mid] < nums[mid-1])  // mid wont go to first and last element
//                 return nums[mid];           
            
//             if(nums[left] > nums[mid])                              // if right side sorted --> go left
//                 right = mid - 1;                     
            
//             else // nums[left] > nums[mid]                          // if left side sorted --> go right
//                 left = mid + 1;                     
//         }
        
//         return (nums.length == 1) ? nums[0] : -1;
//     }
// }