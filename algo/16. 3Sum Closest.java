class Solution 
{
    public int threeSumClosest(int[] nums, int target) 
    {
        Arrays.sort(nums);
        int len = nums.length;
        int currSum, currDiff;
        int output = 0, minDiff = Integer.MAX_VALUE;
        
        for(int i=0; i<len-2; i++)
        {
            int j = i + 1;
            int k = len-1;
            while(j < k)
            {
                currSum = nums[i] + nums[j] + nums[k];  
                
                if(currSum == target)
                    return currSum;
                else if(currSum < target)
                    j++;
                else
                    k--;
                             
                currDiff = Math.abs(target - currSum);      // main logic
                if(currDiff < minDiff)
                {
                    minDiff = currDiff;
                    output = currSum;
                }
            }
        }
        return output;
    }
}