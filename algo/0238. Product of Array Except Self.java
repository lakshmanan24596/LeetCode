/*
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:
Input:  [1,2,3,4]
Output: [24,12,8,6]

Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.
Note: Please solve it without division and in O(n).
Follow up: Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
*/

class Solution 
{
    // simple solution is to use division
        // 1) without division, using 2 arrays  --> 2n space, 3n time 
        // 2) without division, using log       --> 1 space, 2n time 
            
    public int[] productExceptSelf(int[] nums) 
    {        
        int[] left = new int[nums.length];
        left[0] = 1;
        int[] right = new int[nums.length];
        right[nums.length-1] = 1;
        
        for(int i = 0; i < nums.length - 1; i++)        // left 
            left[i+1] = left[i] * nums[i];

        for(int i = nums.length-1; i > 0; i--)          // right
            right[i-1] = right[i] * nums[i];
        
        for(int i = 0; i < nums.length; i++)            // output
            nums[i] = left[i] * right[i];               // exclude current and include all elements in left and right
        
        return nums;
    }
    
    
    // x = a * b * c * d
    // log(x) = log(a * b * c * d)
    // log(x) = log(a) + log(b) + log(c) + log(d)
    // x = antilog(log(a) + log(b) + log(c) + log(d))   // this can be used to avoid multiplication of very large numbers
    
//     static final double EPS = 1e-9; 
    
//     public int[] productExceptSelf(int[] nums) 
//     { 
//         double sum = 0;  
//         int n = nums.length;
        
//         for (int i = 0; i < n; i++) 
//             sum += Math.log10(nums[i]); 
  
//         // anti log to find original product value 
//         for (int i = 0; i < n; i++) 
//             nums[i] = ((int)(EPS + Math.pow(10.00, sum - Math.log10(nums[i]))));  
        
//         return nums;
//     }     
}