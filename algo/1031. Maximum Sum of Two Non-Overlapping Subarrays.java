/*
Given an array A of non-negative integers, return the maximum sum of elements in two non-overlapping (contiguous) subarrays, which have lengths L and M.  
(For clarification, the L-length subarray could occur before or after the M-length subarray.)

Formally, return the largest V for which V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) and either:
0 <= i < i + L - 1 < j < j + M - 1 < A.length, or
0 <= j < j + M - 1 < i < i + L - 1 < A.length.

Example 1:
Input: A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
Output: 20
Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.

Example 2:
Input: A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
Output: 29
Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.

Example 3:
Input: A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
Output: 31
Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [3,8] with length 3.

Note:
L >= 1
M >= 1
L + M <= A.length <= 1000
0 <= A[i] <= 1000
*/



/*
    similar to stocks buy sell with 2 transactions allowed
    https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
    
    we have 2 possibilities here, 
        1) First L and then M
        2) First M and then L
        implement stock buy sell twice --> output = max((1), (2))
        
        time: 2 * 2n
        space: 2n
        
    Another approach:
        https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/discuss/278251/JavaC%2B%2BPython-O(N)Time-O(1)-Space
*/

class Solution {
    public int maxSumTwoNoOverlap(int[] arr, int L, int M) {
        return Math.max(maxSumTwoNoOverlapUtil(arr, L, M), maxSumTwoNoOverlapUtil(arr, M, L));    // main logic
    }
    
    public int maxSumTwoNoOverlapUtil(int[] arr, int L, int M) {
        int n = arr.length;
        int[] leftSum = new int[n];
        int[] rightSum = new int[n];
        int sum = 0, maxSum = 0;
        
        // calculate leftSum
        for (int i = 0; i < L; i++) {
            sum += arr[i];
            leftSum[i] = sum;
        }
        for (int i = L; i < n - M; i++) {
            sum += arr[i];
            sum -= arr[i - L];
            leftSum[i] = Math.max(leftSum[i - 1], sum);
        }
        
        // calculate rightSum
        sum = 0;
        for (int i = n - 1; i >= n - M; i--) {
            sum += arr[i];
            rightSum[i] = sum;
            maxSum = Math.max(maxSum, leftSum[i - 1] + rightSum[i]);        // main logic
        }
        for (int i = n - M - 1; i >= L; i--) {
            sum += arr[i];
            sum -= arr[i + M];
            rightSum[i] = Math.max(rightSum[i + 1], sum);
            maxSum = Math.max(maxSum, leftSum[i - 1] + rightSum[i]);        // main logic
        }
        return maxSum;
    }
}