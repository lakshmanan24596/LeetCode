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
