/*
Given a collection of distinct integers, return all possible permutations.

Example:
Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
*/

class Solution 
{    
    List<List<Integer>> output = new ArrayList<List<Integer>>();
    LinkedHashSet<Integer> set = new LinkedHashSet<Integer>();      // LinkedHashSet to maintain insertion order
    int[] nums;
    
    public List<List<Integer>> permute(int[] nums) 
    {
        this.nums = nums;
        permute(0);     
        return output;
    }

    public void permute(int level)
    {
        if(level == nums.length)
        {
            output.add(new ArrayList<Integer>(set));
            return;
        }

        for(int i=0; i<nums.length; i++)
        {
            // we can do without using LinkedHashSet by swaping the data.. so duplicate integers can also be handled by swapping
            // https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
            if(!set.contains(nums[i]))        
            {
                set.add(nums[i]);
                permute(level+1);
                set.remove(nums[i]);
            }
        }
    }
}