/*
Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into sets of k consecutive numbers
Return True if it is possible. Otherwise, return False.

Example 1:
Input: nums = [1,2,3,3,4,4,5,6], k = 4
Output: true
Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].

Example 2:
Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
Output: true
Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].

Example 3:
Input: nums = [3,3,2,2,1,1], k = 3
Output: true

Example 4:
Input: nums = [1,2,3,4], k = 3
Output: false
Explanation: Each array should be divided in subarrays of size 3.

Constraints:
1 <= k <= nums.length <= 105
1 <= nums[i] <= 109

Note: This question is the same as 846: https://leetcode.com/problems/hand-of-straights/
*/


/*
    Logic: greedy
    store freq in treeMap
    
    Time: n*logn
    Space: n
*/

class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0) {
            return false;
        }
        TreeMap<Integer, Integer> freqTreeMap = new TreeMap<Integer, Integer>();
        Map.Entry<Integer, Integer> curr;
        int start, freq;
        int windowSize = n / k;
        
        for (int num : nums) {
            freqTreeMap.put(num, freqTreeMap.getOrDefault(num, 0) + 1);
        }
        while (windowSize-- > 0) {
            start = freqTreeMap.firstKey();
            for (int i = 0; i < k; i++) {
                if (!freqTreeMap.containsKey(i + start)) {          // main logic
                    return false;
                }
                freq = freqTreeMap.get(i + start);
                if (freq == 1) {
                    freqTreeMap.remove(i + start);
                } else {
                    freqTreeMap.put(i + start, freq - 1);
                }
            }
        }
        return true;
    }
}