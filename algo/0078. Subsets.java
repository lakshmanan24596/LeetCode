/*
Given a set of distinct integers, nums, return all possible subsets (the power set).
Note: The solution set must not contain duplicate subsets.

Example:
Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/

class Solution 
{    
    List<List<Integer>> output = new ArrayList<List<Integer>>();
    List<Integer> currOutput = new ArrayList<Integer>();
    int[] nums;
    
    public List<List<Integer>> subsets(int[] nums)
    {
        this.nums = nums;
        output.add(currOutput);         // add empty list initially
        recur(0);
        return output;
    }
    
    public void recur(int startIndex)
    {
        for(int i = startIndex; i < nums.length; i++)
        {
            currOutput.add(nums[i]);               
            
            output.add(new ArrayList<Integer>(currOutput));         // because all nodes will come in output
            recur(i+1);                                             // i+1 is next startIndex
            
            currOutput.remove(currOutput.size()-1);
        }
    }
}