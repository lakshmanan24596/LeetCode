/*
Given an array of integers nums, find the maximum length of a subarray where the product of all its elements is positive.
A subarray of an array is a consecutive sequence of zero or more values taken out of that array.
Return the maximum length of a subarray with positive product.

Example 1:
Input: nums = [1,-2,-3,4]
Output: 4
Explanation: The array nums already has a positive product of 24.

Example 2:
Input: nums = [0,1,-2,-3,-4]
Output: 3
Explanation: The longest subarray with positive product is [1,-2,-3] which has a product of 6.
Notice that we cannot include 0 in the subarray since that'll make the product 0 which is not positive.

Example 3:
Input: nums = [-1,-2,-3,0,1]
Output: 2
Explanation: The longest subarray with positive product is [-1,-2] or [-2,-3].

Example 4:
Input: nums = [-1,2]
Output: 1

Example 5:
Input: nums = [1,2,3,5,-6,4,0,10]
Output: 4

Constraints:
1 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
*/


/*
    Any subarray with
        1) any number of positive 
        2) even number of negatives
        3) no zeros 
    will produce a positve product
    
    Algo:
        1) record the first positive index and first negative index of current section
           sections are split by 0
        2) compute product up to current number, 
            if product is positive, compute the length to its first positive index
            else if product is neg, compute the length to its first negative index
            so we now the max length up to the cur index
        3) if we meet a 0, reset the first positive and first negative index
        
    Logic: greedy
    Time: n
    Space: 1
*/

class Solution {
    public int getMaxLen(int[] nums) {
        Integer firstPositiveIndex = -1, firstNegativeIndex = null;
        int negativeCount = 0;
        int output = Integer.MIN_VALUE, currOutput;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                firstPositiveIndex = i;
                firstNegativeIndex = null;
                negativeCount = 0;
            } else {
                if (nums[i] < 0) {
                    negativeCount++;
                    if (firstNegativeIndex == null) {
                        firstNegativeIndex = i;
                        continue;
                    }
                }
                currOutput = (negativeCount % 2 == 0) ? i - firstPositiveIndex : i - firstNegativeIndex;   // main logic
                output = Math.max(output, currOutput);
            }
        }
        return output == Integer.MIN_VALUE ? 0 : output;
    }
}