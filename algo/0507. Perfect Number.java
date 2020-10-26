/*
A perfect number is a positive integer that is equal to the sum of its positive divisors, excluding the number itself. A divisor of an integer x is an integer that can divide x evenly.
Given an integer n, return true if n is a perfect number, otherwise return false.

Example 1:
Input: num = 28
Output: true
Explanation: 28 = 1 + 2 + 4 + 7 + 14
1, 2, 4, 7, and 14 are all divisors of 28.

Example 2:
Input: num = 6
Output: true

Example 3:
Input: num = 496
Output: true

Example 4:
Input: num = 8128
Output: true

Example 5:
Input: num = 2
Output: false
 
Constraints:
1 <= num <= 108
*/

/*
class Solution 
{
    public boolean checkPerfectNumber(int num)  // Time: num/2
    {
        if(num == 1) {
            return false;
        }
        int sum = 1;
        int size = num/2;
        
        for(int i = 2; i <= size; i++) {
            if(num % i == 0) {
                sum += i;
            }
        }
        
        return sum == num;
    }
}
*/

class Solution 
{
    public boolean checkPerfectNumber(int num)  // Time: sqrt(num)
    {
        if(num == 1) {
            return false;
        }
        int sum = 1;    // input = 28 --> sum = 1 + (2 + 14) + (4 + 7)
        
        for(int i = 2; i*i <= num; i++) {
            if(num % i == 0) {
                sum += i;
                sum += num/i;
            }
        }
        
        return sum == num;
    }
}
