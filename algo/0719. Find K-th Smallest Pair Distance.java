/*
Given an integer array, return the k-th smallest distance among all the pairs. 
The distance of a pair (A, B) is defined as the absolute difference between A and B.

Example 1:
Input:
nums = [1,3,1]
k = 1
Output: 0 

Explanation:
Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1st smallest distance pair is (1,1), and its distance is 0.

Note:
2 <= len(nums) <= 10000.
0 <= nums[i] < 1000000.
1 <= k <= len(nums) * (len(nums) - 1) / 2.
*/



/*
    1) heap
        generate all possible pairs --> (time: n^2)
        we can create a heap of size k and process all pairs --> (time: log k for each pair)
        time = n^2 * logk
        space: k
    
    
    2) "binary search answer" and sliding window
        https://leetcode.com/problems/find-k-th-smallest-pair-distance/solution/
    
        sort nums
        answer range = 0 to W, where W = nums[size-1] - nums[0] --> (time: logW)
        we need to check how many pairs have distance <= X
        for this we can use sliding windown --> (time: n for each answer)
        time: nlogn for sorting + nlogW for processing
        space: 1
*/

class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int n = nums.length;
        Arrays.sort(nums);                                  // step 1: sort the array
        int low = 0, high = nums[n-1] - nums[0], mid;
        int count, left, right;
        
        while (low < high) {                                // step 2: binary search answer range
            mid = (low + high) / 2;
            count = 0;
            left = 0;
            for (right = 0; right < n; right++) {
                while (nums[right] - nums[left] > mid) {    // distance should be less than or equal to mid
                    left++;
                }
                count += right - left;
            }
            if (count >= k) {                               // count = number of pairs with distance <= mid
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}