/*
Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.
(Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)

Example 1:
Input: N = 10
Output: 9

Example 2:
Input: N = 1234
Output: 1234

Example 3:
Input: N = 332
Output: 299

Note: N is an integer in the range [0, 10^9].
*/

class Solution 
{
    public int monotoneIncreasingDigits(int n) 
    {
        if(n < 10) {
            return n;
        }
        
        int origN = n;
        int val = n % 10;
        n /= 10;
        
        int futureDigit = val;
        int currDigit;
        int diff = 0;
        int offset = 10;
        boolean prevDiffCalculated = false;
        
        while(n > 0)                            // Time: log n base 10 (no of digits of n), Space: 1
        {
            currDigit = n % 10;
            n /= 10;
            
            if(currDigit > futureDigit || (currDigit == futureDigit && prevDiffCalculated))  {  // greedy
                diff = val + 1;                 // main logic 
                prevDiffCalculated = true;
            } else {
                prevDiffCalculated = false;
            }
            
            val += currDigit * offset;
            offset *= 10;
            futureDigit = currDigit;
        }
        
        return origN - diff;
    }
}
