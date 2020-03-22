/*
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:
Input: [3,2,3]
Output: 3
Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2
*/

class Solution 
{
    public int majorityElement(int[] nums) 
    {
        // O(2n), O(n) --> using HashMap
        // O(n), O(1) --> if we cancel out output element with other element, then output element will still exist
        
        int output = nums[0],
            count = 1;
        
        for(int i = 1; i < nums.length; i++)
        {
            if(nums[i] == output)
            {
                count++;
            }    
            else
            {
                count--;
                if(count == 0)
                {
                    output = nums[i];
                    count = 1;
                }
            }              
        }
        
        return output;
    }
}