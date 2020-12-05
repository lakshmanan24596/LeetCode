/*
Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.

Example 1:
Input: c = 5
Output: true
Explanation: 1 * 1 + 2 * 2 = 5

Example 2:
Input: c = 3
Output: false

Example 3:
Input: c = 4
Output: true

Example 4:
Input: c = 2
Output: true

Example 5:
Input: c = 1
Output: true
*/

/*
    sqrt of a number can be found in log n time using bianry search
    https://www.geeksforgeeks.org/square-root-of-an-integer/
    
    1) Brute force ==> O(c)
    2) Math        ==> O(sqrt(c) * log(c))
    3) 2 pointer   ==> O(sqrt(c))
*/

/*
// O(c) solution
class Solution 
{
    public boolean judgeSquareSum(int c)    // Time: log(c) + (sqrt(c) ^ 2) ==> log(c) + c ==> O(c)
    {
        int sqrtC = (int) Math.sqrt(c); 
        
        for(int a = 0; a <= sqrtC; a++)
        {
            for(int b = 0; b <= sqrtC; b++)
            {
                if((a*a) + (b*b) == c) {
                    return true;
                }
            }
        }
        return false;
    }
}
*/

/*
// O(sqrtC * log C) solution
class Solution
{
    public boolean judgeSquareSum(int c)         // Time: log(c) + (sqrt(c) * log(c)) ==> O(sqrt(c) * log(c))
    {
        int sqrtC = (int) Math.sqrt(c);
        
        for(int a = 0; a <= sqrtC; a++)
        {
            int b = (int) Math.sqrt(c - (a*a));  // main logic when compared to prev solution
            if((a*a) + (b*b) == c) {
                return true;
            }
        }
        return false;
    }
}
*/

// O(sqrt(c)) solution
class Solution 
{
    public boolean judgeSquareSum(int c)    // Time: log(c) + sqrt(c) ==> O(sqrt(c))
    {
        int left = 0;
        int right = (int) Math.sqrt(c);
        
        while(left <= right)                // using 2 pointer technique
        {
            int real = (left * left) + (right * right);
            if(real == c) {
                return true;
            }
            
            if(real < c) {
                left++;
            } else {
                right--;
            }
        }
        return false;    
    }
}
