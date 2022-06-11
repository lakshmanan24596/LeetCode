/*
You are given an integer array nums. 
The range of a subarray of nums is the difference between the largest and smallest element in the subarray.

Return the sum of all subarray ranges of nums.
A subarray is a contiguous non-empty sequence of elements within an array. 

Example 1:
Input: nums = [1,2,3]
Output: 4
Explanation: The 6 subarrays of nums are the following:
[1], range = largest - smallest = 1 - 1 = 0 
[2], range = 2 - 2 = 0
[3], range = 3 - 3 = 0
[1,2], range = 2 - 1 = 1
[2,3], range = 3 - 2 = 1
[1,2,3], range = 3 - 1 = 2
So the sum of all ranges is 0 + 0 + 0 + 1 + 1 + 2 = 4.

Example 2:
Input: nums = [1,3,3]
Output: 4
Explanation: The 6 subarrays of nums are the following:
[1], range = largest - smallest = 1 - 1 = 0
[3], range = 3 - 3 = 0
[3], range = 3 - 3 = 0
[1,3], range = 3 - 1 = 2
[3,3], range = 3 - 3 = 0
[1,3,3], range = 3 - 1 = 2
So the sum of all ranges is 0 + 0 + 0 + 2 + 0 + 2 = 4.

Example 3:
Input: nums = [4,-2,-3,4,1]
Output: 59
Explanation: The sum of all subarray ranges of nums is 59.


Constraints:
1 <= nums.length <= 1000
-109 <= nums[i] <= 109

Follow-up: Could you find a solution with O(n) time complexity?
*/


/*
    1) brute:
        time: n^2
        space: 1
        
    2) mono stack
        logic: if an element is smaller than x elements before and y elements after, that element is the smallest for (x + 1) * (y + 1) subarrays.
        refer: https://leetcode.com/problems/sum-of-subarray-ranges/discuss/1624268/Reformulate-Problem-O(n)
        
        first iteration (greater) : mono decreasing stack
        second iteration (lesser) : mono increasing stack
*/

class Solution {
    public long subArrayRanges(int[] nums) {
        return subArrayRanges(nums, false) - subArrayRanges(nums, true);  // main logic: greater - lesser
    }
    
    public long subArrayRanges(int[] nums, boolean isIncreasing) {
        Stack<Integer> stack = new Stack<Integer>();
        long output = 0;
        int j, k;
        
        for (int i = 0; i <= nums.length; i++) {
            while (!stack.isEmpty() && 
                   (i == nums.length ||
                   (isIncreasing && nums[i] < nums[stack.peek()]) || (!isIncreasing && nums[i] > nums[stack.peek()]))) {
                j = stack.pop();
                k = stack.isEmpty() ? -1 : stack.peek();
                output += (long)((i - j) * (j - k)) * nums[j];          // main logic
            }
            stack.push(i);
        }
        return output;
    }
}
