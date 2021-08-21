/*
Given an array of integers nums and an integer threshold, we will choose a positive integer divisor, divide all the array by it, and sum the division's result. 
Find the smallest divisor such that the result mentioned above is less than or equal to threshold.
Each result of the division is rounded to the nearest integer greater than or equal to that element. 
(For example: 7/3 = 3 and 10/2 = 5).
It is guaranteed that there will be an answer.

Example 1:
Input: nums = [1,2,5,9], threshold = 6
Output: 5
Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1. 
If the divisor is 4 we can get a sum of 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2). 

Example 2:
Input: nums = [44,22,33,11,1], threshold = 5
Output: 44

Example 3:
Input: nums = [21212,10101,12121], threshold = 1000000
Output: 1

Example 4:
Input: nums = [2,3,5,7,11], threshold = 11
Output: 3

Constraints:
1 <= nums.length <= 5 * 104
1 <= nums[i] <= 106
nums.length <= threshold <= 106
*/


/*
    logic: binary search answer range
    time: n*log(maxValue) = n*32 in worst case
    space: 1
*/
class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int left = 1;
        int right = getMaxValue(nums);
        int mid;
        int output = right;
        
        while (left <= right) {
            mid = left + ((right - left) / 2);
            if (isValidDivisor(mid, nums, threshold)) {     // main logic
                output = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return output;
    }
    
    public int getMaxValue(int[] nums) {
        int maxVal = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxVal = Math.max(maxVal, nums[i]);
        }
        return maxVal;
    }
    
    public boolean isValidDivisor(int divisor, int[] nums, int threshold) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] / divisor;                   // main logic
            sum += (nums[i] % divisor != 0) ? 1 : 0;    // ceil round off
        }
        return sum <= threshold;
    }
}
