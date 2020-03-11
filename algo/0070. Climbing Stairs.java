/*
You are climbing a stair case. It takes n steps to reach to the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
Note: Given n will be a positive integer.

Example 1:
Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps

Example 2:
Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
*/

class Solution 
{
    public int climbStairs(int n) 
    {
        // recursion --> recur(n-1) + recur(n-2);
        // DP --> DP[i] = DP[i-1] + DP[i-2]
        // nth fibonacci number  (time ::: n, logn, 1)
        
        // output : 0,1,2,3,5,8,13,21.....
        
        
        if(n <= 3)
            return n;
        
        int first = 2, second = 3, third = 0, count = 0;
        while(count < n-3)
        {
            third = first + second;
            first = second;
            second = third;
            count++;
        }
        
        return third;
    }
}