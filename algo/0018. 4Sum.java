// Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? 
// Find all unique quadruplets in the array which gives the sum of target.

// Note:
// The solution set must not contain duplicate quadruplets.

// Example:
// Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
// A solution set is:
// [
//   [-1,  0, 0, 1],
//   [-2, -1, 1, 2],
//   [-2,  0, 0, 2]
// ]


class Solution 
{
    public List<List<Integer>> fourSum(int[] nums, int target) 
    {
        // we need to find unique quadruplets
        // so, implementing with hashmap with O(n^2) is difficult        
        // below solution, O(n^3) with 2 pointer technique
        
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        int length = nums.length;
        if(length < 4) return output;
        List<Integer> currOutput;
        
        Arrays.sort(nums);  // so that we can check (duplicate or not) using adjacent elements
        
        for(int i=0; i<length-3; i++)
        {
            if(i!=0 && nums[i]==nums[i-1])  // avoid duplicates
                continue;
            
            for(int j=i+1; j<length-2; j++)
            {
                if(j!=i+1 && nums[j]==nums[j-1])    // avoid duplicates
                    continue;
                
                int k = j+1;
                int l = length-1;
                
                while(k<l)
                {
                    int currSum = nums[i] + nums[j] + nums[k] + nums[l];
                    if(currSum == target)
                    {
                        currOutput = new ArrayList<Integer>();
                        currOutput.add(nums[i]);
                        currOutput.add(nums[j]);
                        currOutput.add(nums[k]);
                        currOutput.add(nums[l]);
                        output.add(currOutput);
                        k++;
                        l--;
                        
                        while(k<l && nums[k]==nums[k-1])    // avoid duplicates
                            k++;                        
                        while(k<l && nums[l]==nums[l+1])    // avoid duplicates
                            l--;                    
                    }                    
                    else if(currSum < target)
                        k++;
                    else
                        l--;
                }
            }
        }
        return output;
    }
}