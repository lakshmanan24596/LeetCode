/*
Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.
Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums. 

Example 1:
Input: nums = [1,2,2,3,1]
Output: 2
Explanation: 
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.

Example 2:
Input: nums = [1,2,2,3,1,4,2]
Output: 6
Explanation: 
The degree is 3 because the element 2 is repeated 3 times.
So [2,2,3,1,4,2] is the shortest subarray, therefore returning 6.

Constraints:
nums.length will be between 1 and 50,000.
nums[i] will be an integer between 0 and 49,999.
*/


/*
    time: n, space: 1
    logic: store start and end index of each unique number
*/
class Solution {
    public int findShortestSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Map<Integer, Degree> numsMap = new HashMap<Integer, Degree>();
        Degree currDegree;
        int maxFreq = 1;
        int minDiff = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            if (!numsMap.containsKey(nums[i])) {
                currDegree = new Degree(i, i, 1);       // startIndex, endIndex, freq
                numsMap.put(nums[i], currDegree);
            } else {
                currDegree = numsMap.get(nums[i]);
                currDegree.end = i;
                currDegree.freq++;
                maxFreq = Math.max(maxFreq, currDegree.freq);
            }
        }
        for (Degree currDegre : numsMap.values()) {
            if (currDegre.freq == maxFreq) {
                minDiff = Math.min(minDiff, currDegre.end - currDegre.start + 1);   // main logic
            }
        }
        return minDiff;
    }
    
    class Degree {
        int start;
        int end;
        int freq;
        Degree(int start, int end, int freq) {
            this.start = start;
            this.end = end;
            this.freq = freq;
        }
    }
}