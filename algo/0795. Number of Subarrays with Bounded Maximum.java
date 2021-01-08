/*
We are given an array A of positive integers, and two positive integers L and R (L <= R).
Return the number of (contiguous, non-empty) subarrays such that the value of the maximum array element in that subarray is at least L and at most R.

Example :
Input: 
A = [2, 1, 4, 3]
L = 2
R = 3
Output: 3
Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].

Note:
L, R  and A[i] will be an integer in the range [0, 10^9].
The length of A will be in the range of [1, 50000].
*/


/*
    1) Brute force: n^2 time and 1 space --> with 2 loops
    
    2) DP tabulation: n time, n time
        form a DP array
        iterate the DP array and add all elements to form output
        for ex: arr = [3, 1, 0, 5, 2, 3, 1, 4] and range = 2, 3
            --> DP  = [1, 1, 1, 0, 1, 2, 2, 0] 
            output = sum of all DP elements = 8
        
    3) DP tabulation space optimized: n time, 1 space --> curr output depends only on prev output
*/
class Solution 
{
    public int numSubarrayBoundedMax(int[] nums, int L, int R) 
    {
        int prev = -1, prevOutput = 0, output = 0;
        
        for(int i = 0; i < nums.length; i++)
        {
            if(nums[i] < L) {
                output += prevOutput;           // DP[i] = DP[i-1]
            }
            else if(nums[i] > R) {
                prevOutput = 0;
                prev = i;
            }
            else {                              // num >= L && num <= R
                prevOutput = i - prev;
                output += prevOutput;
            }
        }
        return output;
    }
}
