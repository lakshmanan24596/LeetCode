/*
Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice. 
Find the two elements that appear only once. You can return the answer in any order.
Follow up: Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?

Example 1:
Input: nums = [1,2,1,3,2,5]
Output: [3,5]
Explanation:  [5, 3] is also a valid answer.

Example 2:
Input: nums = [-1,0]
Output: [-1,0]

Example 3:
Input: nums = [0,1]
Output: [1,0]
 
Constraints:
1 <= nums.length <= 30000
Each integer in nums will appear twice, only two integers will appear once.
*/


/*
[VISUALIZATION]
honestly, i really spent a lot of time to understand this. here is the visualization that i hope latecomers get the idea

first run ^ to get the a^b
search the position to do partitioning in binary representation of a^b
partition the array by this position and get a and b correspondingly
e.g. [1,2,1,3,2,5]

1 = 001
2 = 010
1 = 001
3 = 011
2 = 010
5 = 101

after 1st step, we found out that a^b = 3^5 = 6 which is 110
110 means that there are 2 digits on the left are different in binary representation of our result
let's use any one of the digit to partition our array

if we use the middle one, we can see that there are 2 sets of numbers that we can just use the simple single number to find out the single in each partition
1 = 001
1 = 001
5 = 101 ✅

2 = 010
2 = 010
3 = 011 ✅

if we use the leftmost one, we can still partition the array into the sets and do simple single number on it
1 = 001
2 = 010
1 = 001
3 = 011✅
2 = 010

5 = 101✅
*/

class Solution 
{
    public int[] singleNumber(int[] nums) 
    {
        // step 1
        int xor = nums[0];
        for(int i = 1; i < nums.length; i++) {
            xor ^= nums[i];
        }
        
        // step 2
        int rightMostSetBit = xor & ~(xor-1);
        
        // step 3
        int res1 = 0, res2 = 0;
        for(int num : nums)     // we need to split the numbers into 2 groups
        {
            if((num & rightMostSetBit) > 0) {   
                res1 ^= num;    // group 1
            } else {
                res2 ^= num;    // group 2
            }
        }
        return new int[] {res1, res2};
    }
}
