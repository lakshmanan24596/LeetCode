/*
You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. 
Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5

Explanation: 
-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
 
Constraints:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.
*/

class Solution {
    int[] nums;
    int target;
    Integer[][] DP;
    int sumLimit;
    
    public int findTargetSumWays(int[] nums, int target) {
        this.sumLimit = Arrays.stream(nums).sum();
        int sumSize = (2 * sumLimit) + 1;
        if(target > sumLimit || target < -sumLimit) {
            return 0;
        }
        
        this.nums = nums;
        this.target = target;
        this.DP = new Integer[nums.length][sumSize];
        return findTargetSumWays(0, 0);
    }
    
    public int findTargetSumWays(int currIndex, int currSum) {
        if(currIndex == nums.length) {
            return (currSum == target) ? 1 : 0;
        }
        if(DP[currIndex][currSum + sumLimit] != null) {
            return DP[currIndex][currSum + sumLimit];
        }
        
        int addition = findTargetSumWays(currIndex + 1, currSum + nums[currIndex]);
        int subtraction = findTargetSumWays(currIndex + 1, currSum - nums[currIndex]);
       
        return DP[currIndex][currSum + sumLimit] = addition + subtraction;
    }
}


/*
// Tabulation
// refer image in: https://leetcode.com/problems/target-sum/discuss/97335/Short-Java-DP-Solution-with-Explanation

class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sumLimit = Arrays.stream(nums).sum();
        int sumSize = (2 * sumLimit) + 1;
        if(target > sumLimit || target < -sumLimit) {
            return 0;
        }
        
        int[][] DP = new int[nums.length + 1][sumSize];
        DP[0][0 + sumLimit] = 1;    // refer image in the link
        int addition, subtraction;
        
        for (int currIndex = 0; currIndex < nums.length; currIndex++) {
            for (int currSum = 0; currSum < sumSize; currSum++) {
                int dpCurrIndex = currIndex + 1;
                
                addition = currSum + nums[currIndex] < sumSize ?
                            DP[dpCurrIndex - 1][currSum + nums[currIndex]] : 
                            0;
                subtraction = currSum - nums[currIndex] >= 0 ?
                                DP[dpCurrIndex - 1][currSum - nums[currIndex]] : 
                                0;
                DP[dpCurrIndex][currSum] = addition + subtraction;
            }
        }
        return DP[nums.length][target + sumLimit];
    }
}
*/


/*
// Tabulation space optimized

class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sumLimit = Arrays.stream(nums).sum();
        int sumSize = (2 * sumLimit) + 1;
        if(target > sumLimit || target < -sumLimit) {
            return 0;
        }
        
        int[] prevDP = new int[sumSize];
        int[] currDP = new int[sumSize];
        prevDP[0 + sumLimit] = 1;    // refer image in the link
        int addition, subtraction;
        
        for (int currIndex = 0; currIndex < nums.length; currIndex++) {
            for (int currSum = 0; currSum < sumSize; currSum++) {
                int dpCurrIndex = currIndex + 1;
                
                addition = currSum + nums[currIndex] < sumSize ?
                            prevDP[currSum + nums[currIndex]] : 
                            0;
                subtraction = currSum - nums[currIndex] >= 0 ?
                                prevDP[currSum - nums[currIndex]] : 
                                0;
                currDP[currSum] = addition + subtraction;
            }
            prevDP = currDP;
            currDP = new int[sumSize];
        }
        return prevDP[target + sumLimit];
    }
}
*/
