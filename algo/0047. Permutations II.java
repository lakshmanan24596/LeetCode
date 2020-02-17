/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
*/

class Solution 
{
    List<List<Integer>> output = new ArrayList<List<Integer>>();
    Set<List<Integer>> set = new HashSet<List<Integer>>();          // for unique permutations output
    int[] nums;
    
    public List<List<Integer>> permuteUnique(int[] nums) 
    {
        this.nums = nums;
        permuteUnique(0);
        return output;
    }

    public void permuteUnique(int level)
    {
        if(level == nums.length-1)
        {
            List<Integer> currOutput = new ArrayList<Integer>();
            for(int val : nums)
                currOutput.add(val);
            
            if(!set.contains(currOutput))
            {   
                set.add(currOutput);
                output.add(currOutput);
            }    
            return;
        }

        // https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
        for(int i=level; i<nums.length; i++)
        {
            swap(level, i);         
            permuteUnique(level+1);
            swap(i, level); 
        }
    }
    
    public void swap(int i, int j)
    {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}