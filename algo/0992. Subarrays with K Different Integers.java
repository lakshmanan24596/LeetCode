/*
Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number of different integers in that subarray is exactly K.
(For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)
Return the number of good subarrays of A.

Example 1:
Input: A = [1,2,1,2,3], K = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].

Example 2:
Input: A = [1,2,1,3,4], K = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].

Note:
1 <= A.length <= 20000
1 <= A[i] <= A.length
1 <= K <= A.length
*/


/*
    Logic:
        This problem will be a very typical sliding window,
        if it asks the "number of subarrays with at most K distinct elements"
        Just need one more step to reach the folloing equation:
        exactly(K) = atMost(K) - atMost(K-1)    // main logic

    Example 2:
        atMostK(nums, k)   ==> (1), (1,2), (1,2,1), (3,4) ==> 8
        atMostK(nums, k-1) ==> (1), (1,2), (3,4) ==> 5
        output = 8 - 5 = 3
      
    Logic: sliding window + hashmap
    Time: 2n
    Space: n
*/

class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMostK(nums, k) - atMostK(nums, k - 1);     // main logic
    }
    
    public int atMostK(int[] nums, int k) {
        int start = 0;
        Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>();
        int output = 0;
        
        for (int end = 0; end < nums.length; end++) {
            freqMap.put(nums[end], freqMap.getOrDefault(nums[end], 0) + 1);
            while (freqMap.size() > k) {
                if (freqMap.get(nums[start]) == 1) {
                    freqMap.remove(nums[start]);
                } else {
                    freqMap.put(nums[start], freqMap.get(nums[start]) - 1);
                }
                start++;
            }
            output += end - start;
        }
        return output;
    }
}