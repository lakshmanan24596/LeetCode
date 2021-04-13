/*
You are given an array nums that consists of non-negative integers. 
Let us define rev(x) as the reverse of the non-negative integer x. 
For example, rev(123) = 321, and rev(120) = 21. A pair of indices (i, j) is nice if it satisfies all of the following conditions:

0 <= i < j < nums.length
nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
Return the number of nice pairs of indices. Since that number can be too large, return it modulo 109 + 7. 

Example 1:
Input: nums = [42,11,1,97]
Output: 2
Explanation: The two pairs are:
 - (0,3) : 42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121.
 - (1,2) : 11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12.

Example 2:
Input: nums = [13,10,35,24,76]
Output: 4

Constraints:
1 <= nums.length <= 105
0 <= nums[i] <= 109
*/



/*
    1) brute force: n * n * log num base 10
        check every pair and apply the condition --> nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
        
    2) HashMap:
        time: n * log num base 10
        space: n
        
        logic: 
            nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
            nums[i] - rev(nums[i]) == nums[j] - rev(nums[j])
            now left side is i and right side is j
            hash the value of nums[i] - rev(nums[i]) and check if it has occurred again
*/

class Solution {
    public int countNicePairs(int[] nums) {
        Map<Integer, Integer> pairDiffMap = new HashMap<Integer, Integer>();
        int nicePairsCount = 0, currCount;
        int revNum, diff;
        final int mod = 1_000_000_007;
        
        for (int i = 0; i < nums.length; i++) {
            revNum = reverseNum(nums[i]);
            diff = nums[i] - revNum;                            // main logic
            currCount = pairDiffMap.getOrDefault(diff, 0);
            nicePairsCount += currCount;
            nicePairsCount %= mod;
            pairDiffMap.put(diff, currCount + 1);
        }
        return nicePairsCount % mod;
    }
    
    public int reverseNum(int num) {                            // time: log num base 10
        int revNum = 0;
        while (num > 0) {
            revNum *= 10;
            revNum += num % 10;
            num /= 10;
        }
        return revNum;
    }
}