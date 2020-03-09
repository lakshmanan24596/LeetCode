/*
Implement pow(x, n), which calculates x raised to the power n (xn).

Example 1:
Input: 2.00000, 10
Output: 1024.00000

Example 2:
Input: 2.10000, 3
Output: 9.26100

Example 3:
Input: 2.00000, -2
Output: 0.25000

Explanation: 2-2 = 1/22 = 1/4 = 0.25

Note:
-100.0 < x < 100.0
n is a 32-bit signed integer, within the range [−231, 231 − 1]
*/

class Solution 
{
    public double myPow(double x, int y) 
    {
        return (y < 0) ? (1 / (func(x, -y))) : func(x, y);
    }
    
    public double func(double x, int y)
    {
        if(y == 0)
            return 1;   
        // if(n == 1 || x = 1 || x == 0)
        //     return x;
                    
        double val = func(x, y/2);
        return ((y % 2 == 0) ? val * val : x * val * val);
    }
}