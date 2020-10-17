/*
Given an integer array arr, you should partition the array into (contiguous) subarrays of length at most k. 
After partitioning, each subarray has their values changed to become the maximum value of that subarray.
Return the largest sum of the given array after partitioning.

Example 1:
Input: arr = [1,15,7,9,2,5,10], k = 3
Output: 84
Explanation: arr becomes [15,15,15,9,10,10,10]

Example 2:
Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
Output: 83

Example 3:
Input: arr = [1], k = 1
Output: 1
 
Constraints:
1 <= arr.length <= 500
0 <= arr[i] <= 109
1 <= k <= arr.length
*/

class Solution 
{
    /*
        Consider example-2 in ques --> 1, 4, 1, 5, 7, 3, 6, 1..... and k = 4
        
        For first K elements (1, 4, 1, 5):
            output = 1, 8, 12, 20.. which is (max * (i+1)) because we need not partition it and all elements can be in a single group.
        
        For remaining elements:
            now the next element is 7 at index 4
            answer at 7 = Math.max (DP[3] + (max * 1),   // 1 element in curr group
                                    DP[2] + (max * 2),   // 2 elements in curr group
                                    DP[1] + (max * 3),   // 3 elements in curr group
                                    DP[0] + (max * 4))   // 4 elements in curr group
                        
                         = Math.max (20 + (7*1),
                                    (12 + (7*2),
                                    (8 + (7*3),
                                    (1 + (7*4))                
    */
    
    public int maxSumAfterPartitioning(int[] arr, int k) // DP tabulation --> Time = n * k and Space = n 
    {
        int n = arr.length;
        int[] DP = new int[n];
        DP[0] = arr[0];
        int max = arr[0];
        
        for(int i = 1; i < k; i++)
        {
            max = Math.max(max, arr[i]);
            DP[i] = max * (i+1);
        }
        
        for(int i = k; i < n; i++)
        {
            max = arr[i];
            for(int j = i-1; i-j <= k; j--) // go backwards k places
            {
                DP[i] = Math.max(DP[i], 
                                 DP[j] + (max * (i-j)));
                max = Math.max(max, arr[j]);
            }
        }
        
        return DP[n-1];
    }
}
