/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
Note: The algorithm should run in linear time and in O(1) space.

Example 1:
Input: [3,2,3]
Output: [3]

Example 2:
Input: [1,1,1,3,3,2,2,2]
Output: [1,2]
*/

class Solution 
{
    public List<Integer> majorityElement(int[] nums) 
    {
        // moore's voting algo
        // logic : output will contain max 2 elements.. because there can be only max 2 elements whose count > n/3
       
        if(nums == null)
            return null;        
        if(nums.length == 0)
            return new ArrayList<Integer>();
        
        int output1 = -1,   // use 4 variables for tracking
            output2 = -1,  
            count1 = 0, 
            count2 = 0;
        
        for(int i = 0; i < nums.length; i++)
        {
            if(nums[i] == output1)
               count1++;
            
            else if(nums[i] == output2)
                count2++;
            
            else if(count1 == 0)
            {               
                output1 = nums[i];
                count1 = 1;
            }
            
            else if(count2 == 0)
            {
                output2 = nums[i];
                count2 = 1;
            }
            else            // if element is not output1 and output2 then --> cancel out both the counts
            {
                count1--;   
                count2--;
            }
        }
           
        // checking the outputs
        count1 = 0; 
        count2 = 0; 
        for(int i = 0; i < nums.length; i++)        
        {
            if(nums[i] == output1)
                count1++;
            else if(nums[i] == output2)
                count2++;
        } 
        
        List<Integer> output = new ArrayList<Integer>();  
        if(count1 > (nums.length / 3))  
            output.add(output1);        
        if(count2 > (nums.length / 3))  
            output.add(output2);
        return output;
    }
}