/*
We have a collection of rocks, each rock has a positive integer weight.
Each turn, we choose any two rocks and smash them together.  Suppose the stones have weights x and y with x <= y. 
The result of this smash is:

If x == y, both stones are totally destroyed;
If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
At the end, there is at most 1 stone left.  Return the smallest possible weight of this stone (the weight is 0 if there are no stones left.)

Example 1:
Input: [2,7,4,1,8,1]
Output: 1

Explanation: 
We can combine 2 and 4 to get 2 so the array converts to [2,7,1,8,1] then,
we can combine 7 and 8 to get 1 so the array converts to [2,1,1,1] then,
we can combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we can combine 1 and 1 to get 0 so the array converts to [1] then that's the optimal value.

Note:
1 <= stones.length <= 30
1 <= stones[i] <= 100
*/

/*
    In problem 1046, can be solved using greedy method as always heaviest 2 rocks smash
    but in this problem, any 2 rocks can smash
    so recursion and DP should be used (greedy wont work)
    
    Time: min(2^n, n*sum)
    Space: n*sum
    Logic: Exactly same as 0/1 knapsack
    
    Example: 31, 26, 33, 21, 40
    Group A = (33, 40) and Group B = (31, 26, 21)
    Output = abs(sum of group A - sum of group B)
           = totalWeight - (2 * currWeight)
           = 151 - (2 * 73)
           = 5
    
    How dividing it into 2 groups works for this problem?
    1) https://leetcode.com/problems/last-stone-weight-ii/discuss/653550/Trying-to-Explain-A-bit-(logic-behind-trick)
    2) https://leetcode.com/problems/last-stone-weight-ii/discuss/296350/Explaining-why-this-problem-is-equals-to-finding-the-difference-between-the-sum-of-two-groups
    3) https://leetcode.com/problems/last-stone-weight-ii/discuss/672906/Explanation%3A-Why-Problem-is-Knapsack
*/

class Solution {
    int[] stones;
    int totalWeight = 0;
    Integer[][] DP;
    
    public int lastStoneWeightII(int[] stones) {
        this.stones = stones;
        for (int stone : stones) {
            totalWeight += stone;
        }
        DP = new Integer[stones.length][totalWeight + 1];
        return recur(0, 0);
    }
    
    public int recur(int level, int currWeight) {
        if (level == stones.length) {
            return Math.abs(totalWeight - (2 * currWeight));
        }
        if (DP[level][currWeight] != null) {
            return DP[level][currWeight];
        }
        
        int dontPick = recur(level + 1, currWeight);
        int pick = recur(level + 1, currWeight + stones[level]);
        return DP[level][currWeight] = Math.min(dontPick, pick);
    }
}