/*
You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
Find this single element that appears only once.
Follow up: Your solution should run in O(log n) time and O(1) space.

Example 1:
Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2

Example 2:
Input: nums = [3,3,7,7,10,11,11]
Output: 10
 
Constraints:
1 <= nums.length <= 10^5
0 <= nums[i] <= 10^5
*/

class Solution 
{
    public int singleNonDuplicate(int[] nums)   // Time: log(n), Space: 1 and Logic: binary search
    {
        int left = 0, right = nums.length - 1, mid, len;
        while(left < right)
        {
            mid = (left + right) / 2;
            len = mid - left + 1;   // based on length is odd/even we can check either left/right side of nums
            
            if(nums[mid] == nums[mid-1])
            {
                if(len % 2 == 1) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            else 
            {
                if(len % 2 == 1) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
        }
        return nums[left];  // both left and right pointer will be in same place
    }
}
