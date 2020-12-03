/*
Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2.

Example:
Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 
Constraints:
The length of the given array will not exceed 15.
The range of integer in the given array is [-100,100].
The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.
*/

class Solution 
{
    List<List<Integer>> output = new ArrayList<List<Integer>>();
    List<Integer> currOutput = new ArrayList<Integer>();
    int[] nums;
    
    public List<List<Integer>> findSubsequences(int[] nums) 
    {
        this.nums = nums;
        recur(0, Integer.MIN_VALUE);
        return output;
    }
    
    public void recur(int startIndex, int prevVal)
    {
        Set<Integer> usedValues = new HashSet<Integer>();
        
        for(int i = startIndex; i < nums.length; i++)
        {
            if(nums[i] < prevVal || usedValues.contains(nums[i])) {  // increasing order, avoid duplicates
                continue;
            }
            usedValues.add(nums[i]);
            
            currOutput.add(nums[i]);
            if(currOutput.size() > 1) {                 // subsequence should be at least 2
                output.add(new ArrayList<Integer>(currOutput));
            }
            recur(i+1, nums[i]);                        // i+1 is next startIndex
            currOutput.remove(currOutput.size()-1);     // backtracking
        }
    }
}
