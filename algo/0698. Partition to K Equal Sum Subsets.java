/*
Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

Example 1:
Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True

Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 
Note:
1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.
*/

class Solution {
    boolean[] visited;
    int nums[];
    int n, k, targetSum = 0;
    
    public boolean canPartitionKSubsets(int[] nums, int k) {
        this.nums = nums;
        this.k = k;
        this.n = nums.length;
        this.visited = new boolean[n];
        int largestElem = 0, totalSum = 0;
        
        for(int i = 0; i < nums.length; i++) {
            totalSum += nums[i];
            largestElem = Math.max(largestElem, nums[i]);
        }
        if((totalSum % k != 0) || (largestElem > totalSum / k)) {
            return false;
        }
        
        this.targetSum = totalSum / k;
        return recur(targetSum, k, 0);
    }
    
    public boolean recur(int currSum, int k, int startIndex) {
        if(k == 1) {      // form the sum k-1 times.. because last subset will be always true, if remaining subsets are true
            return true;
        }        
        if(currSum == 0) {
            return recur(targetSum, k - 1, 0);  // reduce K as we have formed a correct subset with targetSum
        }
        
        int nextSum;
        for(int i = startIndex; i < n; i++) {
            nextSum = currSum - nums[i];
            if(!visited[i] && nextSum >= 0) {
                visited[i] = true;
                if(recur(nextSum, k, i+1)) {
                	return true;
                }	
                visited[i] = false;             // backtrack
            }
        }
        return false;
    }
}