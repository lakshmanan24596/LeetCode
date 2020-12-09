/*
Given an array nums sorted in ascending order, return true if and only if you can split it into 1 or more subsequences such that each subsequence consists of consecutive integers and has length at least 3.

Example 1:
Input: [1,2,3,3,4,5]
Output: True

Explanation:
You can split them into two consecutive subsequences : 
1, 2, 3
3, 4, 5

Example 2:
Input: [1,2,3,3,4,4,5,5]
Output: True

Explanation:
You can split them into two consecutive subsequences : 
1, 2, 3, 4, 5
3, 4, 5

Example 3:
Input: [1,2,3,4,4,5]
Output: False
 
Constraints:
1 <= nums.length <= 10000
*/

/*
    https://leetcode.com/problems/split-array-into-consecutive-subsequences/discuss/311938/Just-visual-description-(had-a-lot-of-English-trouble-from-solution-descriptions)
    
    https://leetcode.com/problems/split-array-into-consecutive-subsequences/discuss/416994/Java-greedy-two-pass-solution-with-clear-comment
    
    Main logic: when we visit a number then we have 2 choice.
        1) add in existing sequence
        2) create a new sequence
    Greedily we should always add in existing sequence if available.
*/

class Solution 
{
    public boolean isPossible(int[] nums)   // Time: 2n, Space: 2n
    {
        if (nums == null || nums.length < 3) {
            return false;
        } 
        
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        Map<Integer, Integer> tail = new HashMap<Integer, Integer>();
        
        for (int i : nums) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }
        
        for (int i : nums) 
        {
            if (freq.get(i) == 0) {  // there is no such number available, because we have already processed and reduced its frequency
                continue;
            }
            if (tail.getOrDefault(i-1, 0) > 0)  // greedy: add in existing sequence
            {
                tail.put(i-1, tail.get(i-1) - 1);
                tail.put(i, tail.getOrDefault(i, 0) + 1);
                freq.put(i, freq.get(i) - 1);
            } 
            else if (freq.getOrDefault(i+1, 0) > 0 && freq.getOrDefault(i+2, 0) > 0) // create a new sequence
            {
                tail.put(i+2, tail.getOrDefault(i+2, 0) + 1);
                freq.put(i, freq.get(i) - 1);
                freq.put(i + 1, freq.get(i + 1) - 1);
                freq.put(i + 2, freq.get(i + 2) - 1);
            } 
            else {
                return false;   // not able to add in existing sequence and also not able to form new sequence
            }
        }
        
        return true;
    }
}
