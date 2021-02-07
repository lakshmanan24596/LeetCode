/*
You are given an integer array nums and an integer x. 
In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x. 
Note that this modifies the array for future operations.
Return the minimum number of operations to reduce x to exactly 0 if it's possible, otherwise, return -1.

Example 1:
Input: nums = [1,1,4,2,3], x = 5
Output: 2
Explanation: The optimal solution is to remove the last two elements to reduce x to zero.

Example 2:
Input: nums = [5,6,7,8,9], x = 4
Output: -1

Example 3:
Input: nums = [3,2,20,1,1,3], x = 10
Output: 5
Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.

Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 104
1 <= x <= 109
*/


/*
    1) DP + memo
        pick either startIndex or either endIndex at each state
        states of DP: remainingX, startIndex, endIndex --> 3D array required
        time: x * n * n
        space: x * n * n
    
    2) Greedy, sliding window
        similar to https://leetcode.com/problems/minimum-size-subarray-sum/
        
        General logic: 
            split array into start, mid, end
            now logic is, "start + end = total - mid" --> this can be used for many other problems
        
        So instead of finding minSize subarray sum by considering start and end,
        we can find maxSize subarray with sum = totalSum - X
        output = n - maxSize
        
        https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/discuss/935935/Java-Detailed-Explanation-O(N)-Prefix-SumMap-Longest-Target-Sub-Array
        
        time: n
        space: 1
*/

class Solution {
    public int minOperations(int[] nums, int x) {
        int totalSum = 0;
        
        for (int num : nums) {
            totalSum += num;
        }
        int maxSize = maxSubArrayLen(nums, totalSum - x);
        return (maxSize == Integer.MIN_VALUE) ? -1 : nums.length - maxSize;
    }
    
    public int maxSubArrayLen(int[] nums, int target) { // https://leetcode.com/problems/minimum-size-subarray-sum/
        int start = 0;
        int sum = 0;
        int maxLen = Integer.MIN_VALUE, currLen;
        
        for (int end = 0; end < nums.length; end++) {   // sliding window
            sum += nums[end];
            while (sum > target && start <= end) {
                sum -= nums[start];
                start++;
            }
            if (sum == target) {
                currLen = (end - start) + 1;
                maxLen = Math.max(maxLen, currLen);
            }
        }
        return maxLen;
    }
}