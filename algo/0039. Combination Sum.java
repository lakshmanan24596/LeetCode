/*
Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.

Example 1:
Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]

Example 2:
Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
*/


class Solution 
{
    int[] candidates;
    List<Integer> currOutput = new ArrayList<Integer>();
    List<List<Integer>> output = new ArrayList<List<Integer>>();
    
    // Time ==> O(candiates.length ^ target)
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) 
    {
        this.candidates = candidates;
        //Array.sort(candidates);        
        recur(target, 0);
        return output;
    }
    
    public void recur(int target, int startIndex)
    {
        if(target == 0)
        {      	
            output.add(new ArrayList<Integer>(currOutput));
            return;
        }
        
        for(int i=startIndex; i<candidates.length; i++)         // i = startIndex --> because output should be unique
        {
            if(candidates[i] <= target)
            {
            	currOutput.add(candidates[i]);
                recur(target-candidates[i], i);                 // i --> next startIndex
                currOutput.remove(currOutput.size() - 1);
            }
        }
    }
    
    // we can also use DP for this problem. 
    // For each target we need to store List<List<Integer>>
    // if target is already solved then just return it.
}