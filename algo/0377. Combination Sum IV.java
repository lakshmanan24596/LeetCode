/*
Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.
Example:
nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.
Therefore the output is 7.

Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers?s
*/



 /*
    Note: this is a "permutation" problem and not combination
    because output has (1,1,2), (1,2,1), (2,1,1) which means that order matters here
    
    Recursion: time = O(n ^ target) and space = O(target)
    DP: time = O(n * target) and space = O(target)
*/

/*
class Solution 
{
    int[] nums;
    int[] DP;
    
    public int combinationSum4(int[] nums, int target) 
    {
        this.nums = nums;
        this.DP = new int[target+1];
        DP[0] = 1;                                  // base case
        for(int i = 1; i <= target; i++) {
            DP[i] = -1;
        }
        return dfs(target);
    }
    
    public int dfs(int target)
    {
        if(DP[target] != -1) {
            return DP[target];
        }
        
        int output = 0;
        for(int i = 0; i < nums.length; i++)
        {
            if(nums[i] <= target)
            {
                output += dfs(target - nums[i]);
            }
        }
        return DP[target] = output;
    }
}
*/

// DP tabulation
class Solution
{
    public int combinationSum4(int[] nums, int target) 
    {
        int n = nums.length;
        int[] DP = new int[target+1];
        DP[0] = 1;
        
        for(int currTarget = 1; currTarget <= target; currTarget++)
        {
            for(int j = 0; j < n; j++)
            {
                if(nums[j] <= currTarget) 
                {
                    DP[currTarget] += DP[currTarget - nums[j]];
                }
            }
        }
        return DP[target];
    }
}

/*
    Follow up: If -ve numbers are allowed then infinity combinations are possible.
    https://leetcode.com/problems/combination-sum-iv/discuss/85041/7-liner-in-Python-and-follow-up-question
    
    The problem with negative numbers is that now the combinations could be potentially of infinite length. 
    Think about nums = [-1, 1] and target = 1. 
    
    We can have all sequences of arbitrary length that follow the patterns -1, 1, -1, 1, ..., -1, 1, 1 and 1, -1, 1, -1, ..., 1, -1, 1 (there are also others, of course, just to give an example). So we should limit the length of the combination sequence, so as to give a bound to the problem.
*/