class Solution 
{
    public int[] twoSum(int[] nums, int target) 
    {
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        
        int size = nums.length;
        for(int i=0; i<size; i++)
        {
            if(map.containsKey(target-nums[i]))
                return new int[]{map.get(target-nums[i]), i};           
            else
                map.put(nums[i], i);
        }
        
        return new int[]{};
    }
}