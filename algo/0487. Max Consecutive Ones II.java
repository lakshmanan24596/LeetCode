/*
Given a binary array nums, return the maximum number of consecutive 1's in the array if you can flip at most one 0.

Example 1:
Input: nums = [1,0,1,1,0]
Output: 4
Explanation: Flip the first zero will get the maximum number of consecutive 1s. 
After flipping, the maximum number of consecutive 1s is 4.

Example 2:
Input: nums = [1,0,1,1,0,1]
Output: 4

Constraints:
1 <= nums.length <= 105
nums[i] is either 0 or 1.

Follow up: 
What if the input numbers come in one by one as an infinite stream? 
In other words, you can't store all numbers coming from the stream as it's too large to hold in memory. 
Could you solve it efficiently?
*/


/*
    2-pointer technique
    time: n
    space: 1
    
    Follow up:
    Below solution works for Infinite stream of numbers also because space = O(1)
    we just need to note down leftIndex, rightIndex, zeroIndex, maxOnes
*/

class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int left = 0, right = 0;
        Integer zeroIndex = null;
        int maxOnes = 0;
        
        for ( ; right < nums.length; right++) {
            if (nums[right] == 0) {
                if (zeroIndex == null) {
                    zeroIndex = right;
                } else {
                    maxOnes = Math.max(maxOnes, right - left);      // main logic
                    left = zeroIndex + 1;
                    zeroIndex = right;
                }
            }
        }
        maxOnes = Math.max(maxOnes, right - left);
        return maxOnes;
    }
}