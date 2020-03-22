/*
Given an integer n, return the number of trailing zeroes in n!.

Example 1:
Input: 3
Output: 0
Explanation: 3! = 6, no trailing zero.

Example 2:
Input: 5
Output: 1
Explanation: 5! = 120, one trailing zero.
Note: Your solution should be in logarithmic time complexity.
*/

class Solution 
{
    public int trailingZeroes(int n) 
    {
        // represent the number as prime factors
        // count of 5 in prime factors is the answer
        // so answer = floor(n/5) + floor(n/25) + floor(n/125) + ...
        
        // 5! ==> 2 * 2 * 2 * 3 * 5  ==> ans = 1
        // 11! ==> (2 pow 8) * (3 pow 4) * (5 pow 2) * 7 ==> ans 2
                
        int count = 0; 

        for (long i = 5; (n / i) > 0; i *= 5) 
            count += n / i; 
  
        return count; 
    }
}