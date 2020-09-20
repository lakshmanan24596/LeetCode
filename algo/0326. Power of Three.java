/*
Given an integer, write a function to determine if it is a power of three.

Example 1:
Input: 27
Output: true

Example 2:
Input: 0
Output: false

Example 3:
Input: 9
Output: true

Example 4:
Input: 45
Output: false

Follow up:
Could you do it without using any loop / recursion?
*/

class Solution 
{
    public boolean isPowerOfThree(int n) 
    {
        int maxPowerOfThree = (int) Math.pow(3, 19); // because 3 power 20 is bigger than Int.Max  
        return n > 0 && maxPowerOfThree % n == 0;
    }
}