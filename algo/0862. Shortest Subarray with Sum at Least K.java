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
    logic: sliding window using deque
    time: n
    space: n
    
    refer: 209. Minimum Size Subarray Sum, here only positive nums are allowed
    but in this question, negative numbers are allowed, which makes the question hard
    corner case:
        ex: [84,-37,32,40,95] and k = 167 --> output = 3, which is [32, 40, 95]
        calculate prefix sum array = 0, 84, 47, 79, 119, 214
    
    Insights:
    1) k is anyhow going to be positive as 1 <= k <= 109
    2) why prefixSum?
       prefixSum[right] - prefixSum[left] gives the subarry sum inbetween left to right index
    3) for this problem, we need "smaller size sub array and larger sum" (main logic)
       for larger sum, we need --> prefixSum[left] to be smaller, this is the reason we keep deque in the increasing order
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
            while (!deque.isEmpty() && prefixSum[i] - prefixSum[deque.getFirst()] >= k) {   // main logic
                currOutput = i - deque.getFirst();
                output = Math.min(output, currOutput);
                deque.removeFirst();                                                        // similar to start++ in sliding window
            } 
            deque.addLast(i);
        }
        return (output == Integer.MAX_VALUE) ? -1 : output;
    }
}