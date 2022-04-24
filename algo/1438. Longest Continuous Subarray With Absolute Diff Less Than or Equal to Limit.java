/*
Given an array of integers nums and an integer limit, 
return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.

Example 1:
Input: nums = [8,2,4,7], limit = 4
Output: 2 
Explanation: All subarrays are: 
[8] with maximum absolute diff |8-8| = 0 <= 4.
[8,2] with maximum absolute diff |8-2| = 6 > 4. 
[8,2,4] with maximum absolute diff |8-2| = 6 > 4.
[8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
[2] with maximum absolute diff |2-2| = 0 <= 4.
[2,4] with maximum absolute diff |2-4| = 2 <= 4.
[2,4,7] with maximum absolute diff |2-7| = 5 > 4.
[4] with maximum absolute diff |4-4| = 0 <= 4.
[4,7] with maximum absolute diff |4-7| = 3 <= 4.
[7] with maximum absolute diff |7-7| = 0 <= 4. 
Therefore, the size of the longest subarray is 2.

Example 2:
Input: nums = [10,1,2,4,7,2], limit = 5
Output: 4 
Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.

Example 3:
Input: nums = [4,2,2,2,4,4,2,2], limit = 0
Output: 3 

Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 109
0 <= limit <= 109
*/


/*
    1) brute force: n^2, 1
    
    2) binary search answer range: n*logn, 1
        because answer is going to be 1 to size of the array
        
    3) sliding window + deque: n, n
        it is similar to https://leetcode.com/problems/sliding-window-maximum/
        but here we need 2 deque to maintain both min and max values
        https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/discuss/1951384/Solve-it-togther-with-sliding-window-maximum
*/

class Solution {
    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> minQ = new LinkedList<Integer>();
        Deque<Integer> maxQ = new LinkedList<Integer>();
        int start = 0, end;
        
        for (end = 0; end < nums.length; end++) {
            while (!minQ.isEmpty() && nums[end] < minQ.peekLast()) {    // keep minQ is ascending order
                minQ.pollLast();
            }
            while (!maxQ.isEmpty() && nums[end] > maxQ.peekLast()) {    // keep maxQ is descending order
                maxQ.pollLast();
            }
            minQ.addLast(nums[end]);
            maxQ.addLast(nums[end]);
            
            if (maxQ.peekFirst() - minQ.peekFirst() > limit) {          // main logic
                if (minQ.peekFirst() == nums[start]) {
                    minQ.pollFirst();
                }
                if (maxQ.peekFirst() == nums[start]) {
                    maxQ.pollFirst();
                }
                start++;
            }
        }
        return end - start;
    }
}
