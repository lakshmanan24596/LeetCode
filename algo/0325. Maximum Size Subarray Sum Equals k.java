/*
Given an integer array nums and an integer k, return the maximum length of a subarray that sums to k. 
If there isn't one, return 0 instead.

Example 1:
Input: nums = [1,-1,5,-2,3], k = 3
Output: 4
Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.

Example 2:
Input: nums = [-2,-1,2,1], k = 1
Output: 2
Explanation: The subarray [-1, 2] sums to 1 and is the longest.

Constraints:
1 <= nums.length <= 104
-104 <= nums[i] <= 104
-105 <= k <= 105

Follow Up: 
Can you do it in O(n) time?
*/


/*
    1) HashMap
        time: n
        space: n
        
    2) Sliding window
        time: n
        space: 1
        note: this will work only for +ve numbers, so convert all -ve to +ve in pre-processing step
*/

class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> sumIndexMap = new HashMap<Integer, Integer>();
        sumIndexMap.put(0, -1);
        int currSum = 0;
        int currSize, maxSize = 0;
        
        for (int i = 0; i < nums.length; i++) {
            currSum += nums[i];
            if (sumIndexMap.containsKey(currSum - k)) {         // main logic
                currSize = i - sumIndexMap.get(currSum - k);
                maxSize = Math.max(maxSize, currSize);
            }
            if (!sumIndexMap.containsKey(currSum)) {            // because we need maxSize
                sumIndexMap.put(currSum, i);
            }
        }
        return maxSize;
    }
}