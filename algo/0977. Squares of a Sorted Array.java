/*
Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.

Example 1:
Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].

Example 2:
Input: nums = [-7,-3,2,3,11]
Output: [4,9,9,49,121]

Constraints:
1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in non-decreasing order.

Follow up: 
Squaring each element and sorting the new array is very trivial, could you find an O(n) solution using a different approach?
*/



/*
    1) sort
        time: nlogn
        space: n
        
    2) 2-pointer
        time: 2n
        space: n
        logic: find the index of first positive number and then use 2 pointer technique
               iterate over the negative part in reverse, and the positive part in the forward direction.
        Implementation: 
            below solution
    
    3) same as above, but one pass algo
        time: n
        space: n
        https://leetcode.com/problems/squares-of-a-sorted-array/solution/
*/

class Solution {
    int[] output;
    
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        output = new int[n];
        int posi, neg;
        int outputIndex = 0;
        
        for (posi = 0; posi < n; posi++) {      // find the index of first positive number
            if (nums[posi] >= 0) {
                break;
            }
        }
        neg = posi - 1;
        
        while (neg >= 0 && posi < n) {          // 2 pointer solution (like merge sort implementation)
            if (Math.abs(nums[posi]) < Math.abs(nums[neg])) {
                output[outputIndex++] = nums[posi] * nums[posi]; 
                posi++;
            } else {
                output[outputIndex++] = nums[neg] * nums[neg]; 
                neg--;
            }
        }
        while (posi < n) {
            output[outputIndex++] = nums[posi] * nums[posi]; 
            posi++;
        }
        while (neg >= 0) {
            output[outputIndex++] = nums[neg] * nums[neg]; 
            neg--;
        }
        return output;
    }
}