/*
In an array A of 0s and 1s, how many non-empty subarrays have sum S?

Example 1:
Input: A = [1,0,1,0,1], S = 2
Output: 4

Explanation: 
The 4 subarrays are bolded below:
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]

Note:
A.length <= 30000
0 <= S <= A.length
A[i] is either 0 or 1.
*/


/*
    1) Brute: n^2
    
    2) Greedy, prefixSum, 2-sum 
        Time: n
        Space: n
        Ex: arr       = 1,0,1,0,1,1,0,1,1,0,1 and sum = 3
            prefixSum = 1,1,2,2,3,4,4,5,6,6,7
            output    = 0,0,0,0,1,2,2,2,1,1,2 ==> 11
    
    3) Greedy, sliding window
        exactly(k) = atMost(k) - atMost(k-1)
        Time: 2n
        Space: 1
        
    https://leetcode.com/problems/binary-subarrays-with-sum/discuss/186683/C%2B%2BJavaPython-Sliding-Window-O(1)-Space
*/


/*
// Greedy, prefixSum, 2-sum 
class Solution {
    public int numSubarraysWithSum(int[] A, int sum) {
        int prefixSum = 0;
        int output = 0;
        int[] freq = new int[A.length + 1];
        freq[0] = 1;
        
        for (int i = 0; i < A.length; i++) {
            prefixSum += A[i];                          // main logic: prefixSum
            if (prefixSum >= sum) {
                output += freq[prefixSum - sum];        // main logic: similar to 2-sum problem
            }
            freq[prefixSum]++;
        }
        return output;
    }
}
*/


// Greedy, sliding window
class Solution {
    public int numSubarraysWithSum(int[] A, int sum) {
        return atMost(A, sum) - atMost(A, sum - 1);     // main logic: exactly(k) = atMost(k) - atMost(k-1)
    }
    
    public int atMost(int[] A, int sum) {
        if (sum < 0) {
            return 0;
        }
        int start = 0;
        int output = 0;
        int currSum = 0;
        
        for (int end = 0; end < A.length; end++) {
            currSum += A[end];
            while (currSum > sum) {
                currSum -= A[start];
                start++;
            }
            output += (end - start) + 1;                // main logic: normal sliding window
        }
        return output;
    }
}