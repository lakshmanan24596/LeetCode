/*
Given a circular array C of integers represented by A, find the maximum possible sum of a non-empty subarray of C.
Here, a circular array means the end of the array connects to the beginning of the array.  (Formally, C[i] = A[i] when 0 <= i < A.length, and C[i+A.length] = C[i] when i >= 0.)
Also, a subarray may only include each element of the fixed buffer A at most once.  (Formally, for a subarray C[i], C[i+1], ..., C[j], there does not exist i <= k1, k2 <= j with k1 % A.length = k2 % A.length.)

Example 1:
Input: [1,-2,3,-2]
Output: 3
Explanation: Subarray [3] has maximum sum 3

Example 2:
Input: [5,-3,5]
Output: 10
Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10

Example 3:
Input: [3,-1,2,-1]
Output: 4
Explanation: Subarray [2,-1,3] has maximum sum 2 + (-1) + 3 = 4

Example 4:
Input: [3,-2,2,-3]
Output: 3
Explanation: Subarray [3] and [3,-2,2] both have maximum sum 3

Example 5:
Input: [-2,-3,-1]
Output: -1
Explanation: Subarray [-1] has maximum sum -1
 
Note:
-30000 <= A[i] <= 30000
1 <= A.length <= 30000
*/

class Solution 
{
    /*
        1) O(n*2), O(1) --> apply kadane from every index
           index 0 to N and then 1 to 0 and then 2 to 1 --> to do a wrap, around we can apply %
           
        2) O(n), O(n) 
        
        3) O(n), O(1) --> Max(linearMaxSum, totalSum - linearMinSum)
           https://leetcode.com/problems/maximum-sum-circular-subarray/discuss/178422/One-Passhttps://leetcode.com/problems/maximum-sum-circular-subarray/discuss/178422/One-Pass
    */
    
    public int maxSubarraySumCircular(int[] A) 
    {
        if(A == null || A.length == 0) {
            return 0;
        }   
        int currMax = A[0], overallMax = A[0], currMin = A[0], overallMin = A[0], totalSum = A[0];
        
        for(int i = 1; i < A.length; i++)
        {
            totalSum += A[i];
            
            currMax = Math.max(A[i], currMax + A[i]);       // kadane to find max sum subarray
            overallMax = Math.max(overallMax, currMax);
            
            currMin = Math.min(A[i], currMin + A[i]);       // kadane to find min sum subarray
            overallMin = Math.min(overallMin, currMin);
        }
        
        if(overallMax < 0) {        // all elements are -ve case
            return overallMax;
        }
        // linear sum is overallMax
        // circular sum is totalSum - overallMin
        return Math.max(overallMax, totalSum - overallMin);     // main logic
    }
}
