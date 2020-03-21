/*
Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.
Note: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:
Input: [2,2,3,2]
Output: 3

Example 2:
Input: [0,1,0,1,0,1,99]
Output: 99
*/

class Solution 
{
    public int singleNumber(int[] nums) 
    {
        // logic::: nums = 5,5,8,5
        
        // 5 --> 0 1 0 1
        // 5 --> 0 1 0 1
        // 8 --> 1 0 0 0
        // 5 --> 0 1 0 1
        //      
        //       1 3 0 3    --> count number of one
        //       1 0 0 0    --> each bit modulo 3
        // which is equal to 8
        
        // similarly we can do modulo "any number" based on the question
        
        // TIME = O(32 * n)
        
        // How to check if a bit is set or not
        // example: n = 5 and check 3 rd bit is set or not
        
        // n --> 0 1 0 1
        // x --> 0 1 0 0
        //    &    
        // x --> 0 1 0 0  --> So if(n & x)==x then that bit is set to 1
        
        
        
        int INT_SIZE = 32,
            result = 0,
            count,
            x = 1;
        
        for(int i = 0; i < INT_SIZE; i++)
        {     
            count = 0;
            for(int j = 0; j < nums.length; j++)
            {
                if((nums[j] & x) == x)
                    count++;  // count of bit 1
            }
            
            if(count % 3 != 0)
                result |= x;    // OR operator is addition here
            
             x = x << 1;     // x value will be (binary) 1,10,100,1000,10000,... which is (decimal) 1,2,4,8,16
        }
        
        return result;
    }
}