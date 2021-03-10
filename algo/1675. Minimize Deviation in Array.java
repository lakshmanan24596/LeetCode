/*
You are given an array nums of n positive integers.
You can perform two types of operations on any element of the array any number of times:

If the element is even, divide it by 2.
For example, if the array is [1,2,3,4], then you can do this operation on the last element, and the array will be [1,2,3,2].

If the element is odd, multiply it by 2.
For example, if the array is [1,2,3,4], then you can do this operation on the first element, and the array will be [2,2,3,4].

The deviation of the array is the maximum difference between any two elements in the array.
Return the minimum deviation the array can have after performing some number of operations.

Example 1:
Input: nums = [1,2,3,4]
Output: 1
Explanation: You can transform the array to [1,2,3,2], then to [2,2,3,2], then the deviation will be 3 - 2 = 1.

Example 2:
Input: nums = [4,1,5,20,3]
Output: 3
Explanation: You can transform the array after two operations to [4,2,5,5,3], then the deviation will be 5 - 2 = 3.

Example 3:
Input: nums = [2,10,8]
Output: 3

Constraints:
n == nums.length
2 <= n <= 105
1 <= nums[i] <= 109
*/


/*
    Logic:
    https://leetcode.com/problems/minimize-deviation-in-array/discuss/1041702/Java-solution-using-TreeSet-with-a-brief-explanation

    Initution:
        1) odd numbers can be either nums[i] or nums[i]*2 ===> we know both minRange and maxRange
        2) even number can be either nums[i] or nums[i]/2 or nums[i]/4 or till it becomes odd ===> we know maxRange alone
        
        so in both, we know the maxRange value
        we need to reduce this maxVal to a minimum 
        so output = Math.min(output, pQueue.peek() - minVal), where we will use maxHeap
*/

class Solution {
    public int minimumDeviation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>((a, b) -> (b - a));      // track max val
        int minVal = Integer.MAX_VALUE;                                                     // track min val
        int output = Integer.MAX_VALUE;
        int peekVal;
        
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] % 2) == 0) {
                pQueue.add(nums[i]);
                minVal = Math.min(minVal, nums[i]);
            } else {
                pQueue.add(nums[i] * 2);
                minVal = Math.min(minVal, nums[i] * 2);
            }
        }
        output = Math.min(output, pQueue.peek() - minVal);
            
        while ((pQueue.peek() % 2) == 0) {
            peekVal = pQueue.remove() / 2;
            pQueue.add(peekVal);
            minVal = Math.min(minVal, peekVal);
            output = Math.min(output, pQueue.peek() - minVal);      // main logic
        }
        return output;
    }
}