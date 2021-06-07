/*
Return the length of the shortest, non-empty, contiguous subarray of nums with sum at least k.
If there is no non-empty subarray with sum at least k, return -1.

Example 1:
Input: nums = [1], k = 1
Output: 1

Example 2:
Input: nums = [1,2], k = 4
Output: -1

Example 3:
Input: nums = [2,-1,2], k = 3
Output: 3

Note:
1 <= nums.length <= 50000
-105 <= nums[i] <= 105
1 <= k <= 109
*/


/*
    refer: 209. Minimum Size Subarray Sum, here only positive nums are allowed
    but in this question, negative numbers are allowed, which makes the question hard

    logic: sliding window, monotonic deque
    time: n
    space: n
    
    ex: [84,-37,32,40,95] and k = 167 --> output = 3, which is [32, 40, 95]
    calculate prefix sum array = 0, 84, 47, 79, 119, 215 
    
    k is anyhow going to be positive as 1 <= k <= 109
    main logic: since k is positive, we can keep our sliding window only in increasing order of prefix sum array
    https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/discuss/143726/C%2B%2BJavaPython-O(N)-Using-Deque
*/

class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<Integer>();   // monotonic increasing deque
        int[] prefixSum = new int[n + 1];
        int currOutput, output = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        for (int i = 0; i <= n; i++) {
            while (!deque.isEmpty() && prefixSum[i] < prefixSum[deque.getLast()]) {         // keep in increasing order
                deque.removeLast();
            }
            while (!deque.isEmpty() && prefixSum[i] - prefixSum[deque.getFirst()] >= k) {   // calculate output
                currOutput = i - deque.getFirst();
                output = Math.min(output, currOutput);
                deque.removeFirst();
            } 
            deque.addLast(i);
        }
        return (output == Integer.MAX_VALUE) ? -1 : output;
    }
}