/*
You are given an array of positive integers nums and want to erase a subarray containing unique elements. 
The score you get by erasing the subarray is equal to the sum of its elements.
Return the maximum score you can get by erasing exactly one subarray.
An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).

Example 1:
Input: nums = [4,2,4,5,6]
Output: 17
Explanation: The optimal subarray here is [2,4,5,6].

Example 2:
Input: nums = [5,2,1,2,5,2,1,2,5]
Output: 8
Explanation: The optimal subarray here is [5,2,1] or [1,2,5].

Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 104
*/



/*
    1) brute force: n^2, 1
    2) sliding window + 2-pointer + preSum:
        maintain a window which contains unique elements
        time: 2n
        space: n
*/

class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n+1];
        Set<Integer> numSet = new HashSet<Integer>();           // used to maintain unique elements in a sliding window
        int start = 0;
        int currSum = 0, maxSum = Integer.MIN_VALUE;

        for (int end = 0; end < n; end++) {
            if (numSet.contains(nums[end])) {
                maxSum = Math.max(maxSum, currSum);
                for (; nums[start] != nums[end]; start++) {     // used to reset start
                    currSum -= nums[start];
                    numSet.remove(nums[start]);
                } 
                start++;
            } else {
                currSum += nums[end];
                numSet.add(nums[end]);
            }
        }
        return Math.max(maxSum, currSum);
    }
}