/*
Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
Note: The solution set must not contain duplicate subsets.

Example:
Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/

class Solution 
{   
    List<List<Integer>> output = new ArrayList<List<Integer>>();
    List<Integer> currOutput = new ArrayList<Integer>();
    int[] nums;
    
    public List<List<Integer>> subsetsWithDup(int[] nums)
    {
        this.nums = nums;
        Arrays.sort(nums);                                  // avoid duplicates                    
        output.add(currOutput);                             // add empty list in output
        
        recur(0);
        return output;
    }
    
    public void recur(int startIndex)                       // Time: n!
    {
        for(int i = startIndex; i < nums.length; i++)
        {
            if(i != startIndex && nums[i] == nums[i-1])     // avoid duplicates
                continue;
            
            currOutput.add(nums[i]);
            
            output.add(new ArrayList<Integer>(currOutput));
            recur(i+1);                                     // i+1 is next startIndex
            
            currOutput.remove(currOutput.size()-1);
        }
    }
}