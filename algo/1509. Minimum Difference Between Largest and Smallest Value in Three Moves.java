/*
Given an array nums, you are allowed to choose one element of nums and change it by any value in one move.
Return the minimum difference between the largest and smallest value of nums after perfoming at most 3 moves.

Example 1:
Input: nums = [5,3,2,4]
Output: 0
Explanation: Change the array [5,3,2,4] to [2,2,2,2].
The difference between the maximum and minimum is 2-2 = 0.

Example 2:
Input: nums = [1,5,0,10,14]
Output: 1
Explanation: Change the array [1,5,0,10,14] to [1,1,0,1,1]. 
The difference between the maximum and minimum is 1-0 = 1.

Example 3:
Input: nums = [6,6,0,1,1,4,6]
Output: 2

Example 4:
Input: nums = [1,5,6,14,15]
Output: 1

Constraints:
1 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
*/



/*
    We have 4 plans:
        kill 3 biggest elements
        kill 2 biggest elements + 1 smallest elements   // main logic
        kill 1 biggest elements + 2 smallest elements   // main logic
        kill 3 smallest elements

    1) sort
    Time: n*logn
    Space: 1
    https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves/discuss/730526/Simple-Greedy-Approach-or-My-screen-recording
    
    2) Logic: Greedy
    create minHeap and maxHeap of size 3 + 1 = 4
    Time: n * logk, where k = 3 + 1 = 4
    Space: n 
*/

class Solution {
    public int minDifference(int[] nums) {
        int n = nums.length;
        if (n <= 4) {
            return 0;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((a, b) -> (b - a));
        int output = Integer.MAX_VALUE, currOutput;
        List<Integer> minHeapList = new ArrayList<Integer>();
        
        for (int i = 0; i < n; i++) {
            if (minHeap.size() < 4) {                       // find largest 4 values using minHeap
                minHeap.add(nums[i]);
            } else if (nums[i] > minHeap.peek()) {
                minHeap.remove();
                minHeap.add(nums[i]);
            }
            if (maxHeap.size() < 4) {                       // find smallest 4 values using maxHeap
                maxHeap.add(nums[i]);
            } else if (nums[i] < maxHeap.peek()) {
                maxHeap.remove();
                maxHeap.add(nums[i]);
            }
        }
        while (!minHeap.isEmpty()) {
            minHeapList.add(minHeap.remove());
        }
        for (int i = 3; i >= 0; i--) {
            currOutput = minHeapList.get(i) - maxHeap.remove();
            output = Math.min(output, currOutput);
        }
        return output;
    }
}