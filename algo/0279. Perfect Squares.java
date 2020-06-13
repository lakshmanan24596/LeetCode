/*
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:
Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.

Example 2:
Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
*/

class Solution
{
    List<Integer> sqrList;
    int[] dpArr;                        
    // if we use static DP array, then for all test cases, we can use same DP array.
    // but the problem is, memory will not be cleaned up after execution.
    
    public int numSquares(int n) {
        if(n <= 0) {
            return 0;
        }
        dpArr = new int[n+1];
        sqrList = new ArrayList<Integer>();
        for(int i = 1; i * i <= n; i++) {
            sqrList.add(i * i);
        }
        return recur(n);
    }
    
    public int recur(int currSum) {     // time = n * sqrt(n)
        if(currSum == 0) {
            return 0;
        }
        if(dpArr[currSum] != 0) {
            return dpArr[currSum];
        }
        int output = Integer.MAX_VALUE;
        int currOutput = 0;
        int nextSum;    

        for(int i = sqrList.size()-1; i >= 0; i--) {
            nextSum = currSum - sqrList.get(i);
            if(nextSum >= 0) {
                currOutput = recur(nextSum) + 1;
                output = Math.min(currOutput, output);
            }
        } 
        
        return dpArr[currSum] = output;
    }
}

// class Solution 
// {
//     /*
//         Tabulation:
//             // To get the value of dp[n], we should choose the min value from:
//             // dp[i] = Math.min(dp[i], 
//                                 dp[n - 1] + 1, 
//                                 dp[n - 4] + 1, 
//                                 dp[n - 9] + 1,
//                                 ...)
//     */
    
//     public int numSquares(int n)             // time = n * sqrt(n)
//     {
//         if(n <= 0) {
//             return 0;
//         }
//         int[] dp = new int[n+1]; 
//         for (int i = 1; i <= n; i++) {
//             dp[i] = Integer.MAX_VALUE;
//         }
        
//         for (int i = 1; i <= n; i++) {
//             int sqrt = (int)Math.sqrt(i);    
//             if (sqrt * sqrt == i) {
//                 dp[i] = 1;
//                 continue;
//             }
//             for (int j = 1; j <= sqrt; j++) {
//                 int dif = i - (j * j);
//                 dp[i] = Math.min(dp[i], (dp[dif] + 1));
//             }
//         }

//         return dp[n];
//     }
// }
