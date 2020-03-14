/*
Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
Note:
Each of the array element will not exceed 100.
The array size will not exceed 200.
 
Example 1:
Input: [1, 5, 11, 5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].

Example 2:
Input: [1, 2, 3, 5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
*/

class Solution 
{
    // 1) recursion
    // 2) DP
    // 3) DP space optimized
    // 4) recurion optimized (if currNum = prevNum, then no need to process currNum)
    
    int[] nums;
    int n, targetSum;
    
    public boolean canPartition(int[] nums) 
    {
        this.nums = nums;
        n = nums.length;
        
        int sum = 0, max = 0;
        for(int num : nums)
        {
            sum += num;
            max = Math.max(max, num);
        }    
        targetSum = sum / 2;                                // because we partition by 2
        
        if(sum % 2 == 1 || max > targetSum)                 // odd sum cannot be partitioned
            return false;
        
        boolean isDP = (targetSum * n) < (Math.pow(2, n));  // choose either DP or recursion based on time complexity
        if(isDP)
            return sumOfSubsetDPSpaceOptimzed();
        else
            return sumOfSubsetRecur(0, 0);    
    }
    
    public boolean sumOfSubsetRecur(int currSum, int level)
    {
        if(level == n || currSum > targetSum)
            return false;
        
        if(currSum == targetSum)
            return true;
        
        return (sumOfSubsetRecur(currSum + nums[level], level + 1) ||      // pick
                sumOfSubsetRecur(currSum, level + 1));                     // dont pick
    }    

    public boolean sumOfSubsetDP()                                  // time = n * targetSum, space = n * targetSum
    {
        boolean[][] DP = new boolean[n + 1][targetSum + 1];
        
        for(int i = 0; i <= n; i++)                                 // first col --> targetSum = 0 --> so true
        {
            DP[i][0] = true;
        }
        // it will be false by default
        // for(int i = 1; i <= targetSum; i++)                      // first row --> nums[] empty --> so false
        // {
        //    DP[0][i] = false;
        // }        
        
        for(int i = 1; i <= n; i++)                                 // similar to coin change
        {
            for(int j = 1; j <= targetSum; j++)
            {
            	if(j < nums[i-1])
            		DP[i][j] = DP[i-1][j];							// get from top
            	else
            		DP[i][j] = DP[i-1][j] || DP[i-1][j-nums[i-1]];	// get from top or top-left
            }
        }        
        return DP[n][targetSum];
    }
    
    // space optimized solution
    public boolean sumOfSubsetDPSpaceOptimzed()                     // time = n * targetSum, space = targetSum
    {
        boolean[][] DP = new boolean[2][targetSum + 1];             // only 2 rows for any N
        
        for(int i = 0; i <= 1; i++)                                 // first col --> targetSum = 0 --> so true
        {
            DP[i][0] = true;
        }
        // it will be false by default
        // for(int i = 1; i <= targetSum; i++)                      // first row --> nums[] empty --> so false
        // {
        //   DP[0][i] = false;
        // }        
        
        for(int i = 1; i <= n; i++)                                 // similar to coin change
        {
            for(int j = 1; j <= targetSum; j++)
            {
            	if(j < nums[i-1])
            		DP[1][j] = DP[0][j];							// get from top
            	else
            		DP[1][j] = DP[0][j] || DP[0][j-nums[i-1]];	    // get from top or top-left
            }
            DP[0] = Arrays.copyOf(DP[1], targetSum + 1);            // main logic.. copy first row to zero row for next iteration
        }        
        return DP[1][targetSum];
    }
}


// Time = n! (but it eliminates duplicate input values.. so it works for leetcode input)

// class Solution {
//     public boolean canPartition(int[] num) {
//         int sum = 0;
//         int max = 0;
//         for (int n : num) {
//             sum += n;
//             max = Math.max(max, n);
//         }
        
//         if (sum%2 != 0) return false;
//         if (max > sum/2) return false;
        
//         return helper(num, 0, sum/2);
//     }
    
//     private boolean helper(int[] num, int index, int remain) {       
//         if (index == num.length || remain < 0) return false;
//         if (remain == 0) return true;
        
//         for (int i=index; i<num.length; i++) {                       // time = n!
//             if(i>index && num[i-1] == num[i]) continue;              // main logic to reduce time
//             if (helper(num, i+1, remain-num[i])) return true;
//         }
        
//         return false;
//     }
// }