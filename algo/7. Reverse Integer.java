// Given a 32-bit signed integer, reverse digits of an integer.

// Example 1:
// Input: 123
// Output: 321
	
// Example 2:
// Input: -123
// Output: -321
	
// Example 3:
// Input: 120
// Output: 21

// Note:
// Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

class Solution 
{
    public int reverse(int n) 
    {
        int output = 0;
        while(n!=0)
        {
            int digit = n % 10;
            
            if((output > Integer.MAX_VALUE/10) || 
                (output < Integer.MIN_VALUE/10) ||
                (output == Integer.MAX_VALUE/10 && digit > 7) ||
                (output == Integer.MIN_VALUE/10 && digit < -8))
                return 0;
            
            output = digit + (output * 10);
            n = n / 10;
        }
        return output;
    }
}
