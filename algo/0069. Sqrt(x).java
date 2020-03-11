/*
Implement int sqrt(int x).
Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:
Input: 4
Output: 2

Example 2:
Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.
*/

class Solution
{
    public int mySqrt(int x) 
    {
        // method 1 --> time = sqrt(x)
        // method 2 --> time = log(x,2)
         
        long end, start = x;
        while(true)
        {
            end = start;    // prevMid
            start = start / 2;  // mid
            
            if((start * start) <= x)
                break;
        }
        
        // now output is between start and end
        long output = -1, mid, square;
        while(start <= end)
        {
            mid = (start + end) / 2;
            square = mid * mid;
            
            if(square == x)
               return (int)mid;
              
            if(square < x)
            {	
            	start = mid + 1;
            	output = mid;
            }	
            else
            	end = mid - 1;
        }
               
        return (int)output;
    }
}