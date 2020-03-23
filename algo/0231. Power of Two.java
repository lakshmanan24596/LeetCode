/*
Given an integer, write a function to determine if it is a power of two.

Example 1:
Input: 1
Output: true 
Explanation: 20 = 1

Example 2:
Input: 16
Output: true
Explanation: 24 = 16

Example 3:
Input: 218
Output: false
*/

class Solution 
{
    public boolean isPowerOfTwo(int n) 
    {
        // logic: Math.log(n) / Math.log(2) returns the answer.. now we need to check this is a whole number or not
        // return ((Math.log(n) / Math.log(2)) % 1 == 0);        
                
        if(n <= 0) 
            return false;
        
        long logN_base2_Value = Math.round(Math.log(n)/Math.log(2));     // round() is done for corner case : n = 536870912
        return (n == Math.pow(2, logN_base2_Value));                      
                
        // for(int i = 0; i < n; i++)
        // {
        //     if(Math.pow(2,i) == n)
        //     {
        //         return true;
        //     }
        // }
        // return false;
    }
}