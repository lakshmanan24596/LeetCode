/*
Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
Formally the function should:
Return true if there exists i, j, k
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.

Example 1:
Input: [1,2,3,4,5]
Output: true

Example 2:
Input: [5,4,3,2,1]
Output: false
*/

class Solution 
{
    public boolean increasingTriplet(int[] nums) 
    {
        /*
        solution 1 --> LIS --> n log k --> n log 3 --> n
        solution 2 --> using 2 variables --> n
        
        logic --> It is given in the ques that, arr[i] < arr[j] < arr[k] and also "i < j < k"
        
        I would like to point out that for [1, 3, 0, 5] we will eventually arrive at big = 3 and small = 0
        Here, big comes before small.
        However, the solution still works, because "big only gets updated when there exists a small that comes before it."
        */
        
        int n = nums.length;
        if(n < 3) {
            return false;
        }
        
        int small = Integer.MAX_VALUE;
        int big = Integer.MAX_VALUE;
        
        for(int i = 0; i < n; i++)
        {
            if(nums[i] <= small) {
                small = nums[i];
            } 
            else if(nums[i] <= big) {
                big = nums[i];
            } 
            else {
                return true;    // nums[i] > small && nums[i] > big
            }
        }
        return false;
    }
}