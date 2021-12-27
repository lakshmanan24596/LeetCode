/*
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:
Input: [0,1,0,3,12]
Output: [1,3,12,0,0]

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
*/


/*
    j --> position till non-zero element 
             0 to j-1: non-zero elements
             j to n-1: zero elements
    i --> curr position
*/
class Solution 
{
    public void moveZeroes(int[] nums) 
    {   
        int j = 0;
        for(int i = 0 ; i < nums.length; i++)
        {
            if(nums[i] != 0)
            {
                nums[j] = nums[i]; 
                j++;
            }    
        }
        
        for(; j < nums.length; j++)     
        {
            nums[j] = 0;                // put zero in the last
        }
    }
}