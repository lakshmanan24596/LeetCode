/*
Given a binary array nums, you should delete one element from it.
Return the size of the longest non-empty subarray containing only 1's in the resulting array.
Return 0 if there is no such subarray.

Example 1:
Input: nums = [1,1,0,1]
Output: 3
Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.

Example 2:
Input: nums = [0,1,1,1,0,1,1,0,1]
Output: 5
Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].

Example 3:
Input: nums = [1,1,1]
Output: 2
Explanation: You must delete one element.

Example 4:
Input: nums = [1,1,0,0,1,1,1,0,1]
Output: 4

Example 5:
Input: nums = [0,0,0]
Output: 0

Constraints:
1 <= nums.length <= 10^5
nums[i] is either 0 or 1.
*/


/*
    time: n
    space: 1
    
    1,1,1,0,1,1
    my logic, here count1 = 3, count2 = 2
    output = 3 + 2
    for the next iteration, count1 = 2, count2 = 0
    
    this problem is same as, Maintain a sliding window where there is at most one zero on it. 
*/
class Solution {
    public int longestSubarray(int[] nums) {
        int count1 = 0, count2 = 0;
        int currCount, longestCount = 0;
        boolean isZeroPresent = false;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count2++;
            } else {
                currCount = count1 + count2;                            // main logic
                longestCount = Math.max(longestCount, currCount);
                count1 = count2;
                count2 = 0;
                isZeroPresent = true;                                   // corner case ex: 3
            }
        }
        currCount = count1 + count2;
        longestCount = Math.max(longestCount, currCount);
        return (isZeroPresent) ? longestCount : longestCount - 1;
    }
}