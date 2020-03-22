/*
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:
Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

Example 2:
Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
*/

class Solution 
{
    public int maxProduct(int[] nums) 
    {
        // Logic: output will always contains either first element or last element or both
        // Time = 2N
        
        int frontOverallMax = Integer.MIN_VALUE, 
            frontCurrMax = 1,                                           // starting from first element
            backOverallMax = Integer.MIN_VALUE, 
            backCurrMax = 1;                                            // starting from last element
        boolean isZeroPresent = false;                                  // corner case
        
        for(int i = 0, j = nums.length-1; i < nums.length; i++, j--)    // Time = 2N because multiplication is done twice inside the loop
        {
            // implementation similar to maximum sum subarray problem
            
            frontCurrMax = frontCurrMax * nums[i];
            if(frontCurrMax == 0)
            {
                frontCurrMax = 1;                                       // reset
                isZeroPresent = true;
            }
            else
                frontOverallMax = Math.max(frontOverallMax, frontCurrMax);
            
            backCurrMax = backCurrMax * nums[j];
            if(backCurrMax == 0) 
                backCurrMax = 1;                                        // reset
            else
                backOverallMax = Math.max(backOverallMax, backCurrMax);
        }

        int output = Math.max(frontOverallMax, backOverallMax);
        return (isZeroPresent && output < 0) ? 0 : output;              // case: -2, 0, -1 --> for this case zero will be the output
    }
}