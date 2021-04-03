/*
You are installing a billboard and want it to have the largest height. 
The billboard will have two steel supports, one on each side. Each steel support must be an equal height.
You are given a collection of rods that can be welded together. 
For example, if you have rods of lengths 1, 2, and 3, you can weld them together to make a support of length 6.
Return the largest possible height of your billboard installation. If you cannot support the billboard, return 0.

Example 1:
Input: rods = [1,2,3,6]
Output: 6
Explanation: We have two disjoint subsets {1,2,3} and {6}, which have the same sum = 6.

Example 2:
Input: rods = [1,2,3,4,5,6]
Output: 10
Explanation: We have two disjoint subsets {2,3,5} and {4,6}, which have the same sum = 10.

Example 3:
Input: rods = [1,2]
Output: 0
Explanation: The billboard cannot be supported, so we return 0.

Constraints:
1 <= rods.length <= 20
1 <= rods[i] <= 1000
sum(rods[i]) <= 5000
*/


/*
    similar to 0/1 knapsack
    but instead of 2 states, here we have 3 states:
        1) dont pick
        2) pick in 1st subset
        3) pick in 2nd subset
        
    1) brute force: 3^n
    2) dp memo: n * sum
    
    logic: 
        For each rod x, we can write +x, -x, or 0
        after processing all rods, if the sum is 0, then we have found a valid answer
        valid answer for ex1 --> +1, +2, +3, -6 --> final sum is 0 and output = 6
        add rod length either in pick1 or pick2
*/

class Solution {
    int[] rods;
    Integer[][] cacheBillBoard;
    int totalSum;
    
    public int tallestBillboard(int[] rods) {
        this.rods = rods;
        totalSum = 0;
        for (int i = 0; i < rods.length; i++) {
            totalSum += rods[i];
        }
        this.cacheBillBoard = new Integer[rods.length][(2 * totalSum) + 1];
        return tallestBillboardUtil(0, 0);
    }
    
    public int tallestBillboardUtil(int level, int sum) {
        if (level == rods.length) {
            return (sum == 0) ? 0 : Integer.MIN_VALUE;
        }
        if (cacheBillBoard[level][sum + totalSum] != null) {
            return cacheBillBoard[level][sum + totalSum];
        }
        
        // For each rod x, we can write +x, -x, or 0                                    // main logic
        int dontPick = tallestBillboardUtil(level + 1, sum);
        int pick1 = tallestBillboardUtil(level + 1, sum - rods[level]);
        int pick2 = tallestBillboardUtil(level + 1, sum + rods[level]) + rods[level];   // add rod length
        
        int tallestBillBoard = Math.max(dontPick, Math.max(pick1, pick2));              // main logic
        return cacheBillBoard[level][sum + totalSum] = tallestBillBoard;
    }
}