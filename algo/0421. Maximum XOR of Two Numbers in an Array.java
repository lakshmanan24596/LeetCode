/*
Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 ≤ i ≤ j < n.
Follow up: Could you do this in O(n) runtime?

Example 1:
Input: nums = [3,10,5,25,2,8]
Output: 28
Explanation: The maximum result is 5 XOR 25 = 28.

Example 2:
Input: nums = [0]
Output: 0

Example 3:
Input: nums = [2,4]
Output: 6

Example 4:
Input: nums = [8,10,2]
Output: 10

Example 5:
Input: nums = [14,70,53,83,49,91,36,80,92,51,66,70]
Output: 127

Constraints:
1 <= nums.length <= 2 * 104
0 <= nums[i] <= 231 - 1
*/



/*
    Logic:
        create a trie with max level = 32 (because integer max range is 2^32 which is 32 bits)
        xor of different bits = 1
        check from MSB to LSB (ie; 31 to 0)
        
    time: n * 32 
    space: 2 ^ 32
    
    Also instead of checking 32 bits, we can preprocess to find the most significant bit by checking all nums
*/

class Solution {
    public int findMaximumXOR(int[] nums) {
        int bit;
        Trie root = new Trie();
        Trie curr;
        int currSum, maxSum = 0;
        if (nums == null || nums.length <= 1) {
            return maxSum;
        }
        
        for (int num : nums) {                              // build trie
            curr = root;
            for (int i = 31; i >= 0; i--) {
                bit = (((1 << i) & num) == 0) ? 0 : 1;      // get ith bit in num
                
                if (curr.children[bit] == null) {
                    curr.children[bit] = new Trie();
                }
                curr = curr.children[bit];
            }
        }
        
        for (int num : nums) {                              // iterate through trie tree for each num
            curr = root;
            currSum = 0;
            for (int i = 31; i >= 0; i--) {
                bit = (((1 << i) & num) == 0) ? 0 : 1;
                
                if (curr.children[1 - bit] != null) {       // main logic: check for negated bit
                    curr = curr.children[1 - bit];
                    currSum += 1 << i;                      // main logic
                } else {
                    curr = curr.children[bit];
                }
            }
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }
    
    class Trie {
        Trie[] children;
        
        Trie() {
            children = new Trie[2];
        }
    }
}