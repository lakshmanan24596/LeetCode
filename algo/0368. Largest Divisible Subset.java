/*
Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:
Si % Sj = 0 or Sj % Si = 0.
If there are multiple solutions, return any subset is fine.

Example 1:
Input: [1,2,3]
Output: [1,2] (of course, [1,3] will also be ok)

Example 2:
Input: [1,2,4,8]
Output: [1,2,4,8]
*/

class Solution
{
    /* 
        logic: [1,2,4] = output is 4
        now if 8 % 4 == 0, then ans is ([8] + answer of 4) = ([8] + [4,2,1]) = ([8,4,2,1])
        In this example, instead of calculating answer of 4 again, we can use DP cache       
        Similar to longest increasing subsequence
        Time: O(n^2), Space: O(n^2)
    */
    public List<Integer> largestDivisibleSubset(int[] nums) 
    {
        int n = nums.length;
        List<Integer>[] DP = new ArrayList[n];  // each index is a arraylist
        List<Integer> outputList = new ArrayList<Integer>();
        List<Integer> currList;
        Arrays.sort(nums);      // main logic: if we sort, then checking (nums[i] % nums[j] == 0) is enough and no need Sj % Si
        
        for(int i = 0; i < n; i++)
        {
            currList = new ArrayList<Integer>(); 
            for(int j = 0; j < i; j++)
            {
                if(nums[i] % nums[j] == 0 && DP[j].size() > currList.size()) {   // main logic
                    currList = DP[j];
                }
            }
            
            DP[i] = new ArrayList<Integer>();
            DP[i].add(nums[i]);
            DP[i].addAll(currList);     // DP is useful here
            
            if(DP[i].size() > outputList.size()) {
                outputList = DP[i];
            }
        }
        return outputList;
    }
}
