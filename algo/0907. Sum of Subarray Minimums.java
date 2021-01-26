/*
Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. 
Since the answer may be large, return the answer modulo 109 + 7.

Example 1:
Input: arr = [3,1,2,4]
Output: 17
Explanation: 
Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4]. 
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
Sum is 17.

Example 2:
Input: arr = [11,81,94,43,3]
Output: 444

Constraints:
1 <= arr.length <= 3 * 104
1 <= arr[i] <= 3 * 104
*/

/*
    1) Brute: Time: n^2, Space: 1
    
    2) Monotonic stack: Time: 3n, Space: 2n
    https://leetcode.com/problems/sum-of-subarray-minimums/discuss/178876/stack-solution-with-very-detailed-explanation-step-by-step
*/
class Solution {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        Stack<Integer> stack = new Stack<Integer>();
        int[] prevLessElem = new int[n];
        int[] nextLessElem = new int[n];
        
        for (int i = 0; i < n;) {
            if (stack.isEmpty() || arr[i] > arr[stack.peek()]) {
                prevLessElem[i] = (stack.isEmpty()) ? -1 : stack.peek();
                stack.push(i);
                i++;
            } else {
                stack.pop();
            }
        }
        
        stack = new Stack<Integer>();
        for (int i = n - 1; i >= 0;) {
            if (stack.isEmpty() || arr[i] >= arr[stack.peek()]) {
                nextLessElem[i] = (stack.isEmpty()) ? n : stack.peek();
                stack.push(i);
                i--;
            } else {
                stack.pop();
            }
        }
        
        long sum = 0, noOfSubArraysInLeft, noOfSubArraysInRight;
        int mod = 1000000007; 
        for (int i = 0; i < n; i++) {
            noOfSubArraysInLeft = i - prevLessElem[i];
            noOfSubArraysInRight = nextLessElem[i] - i;
            sum += (arr[i] * noOfSubArraysInLeft * noOfSubArraysInRight);     // main logic
            sum %= mod;
        }
        return (int)sum;
    }
}