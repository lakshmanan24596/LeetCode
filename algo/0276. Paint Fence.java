/*
You are painting a fence of n posts with k different colors. You must paint the posts following these rules:

Every post must be painted exactly one color.
At most one pair of adjacent fence posts can have the same color.
Given the two integers n and k, return the number of ways you can paint the fence.

Example 1:
Input: n = 3, k = 2
Output: 6
Explanation: All the possibilities are shown.
Note that painting all the posts red or all the posts green is invalid because there can only be at most one pair of adjacent posts that are the same color.

Example 2:
Input: n = 1, k = 1
Output: 1

Example 3:
Input: n = 7, k = 2
Output: 42

Constraints:
1 <= n <= 50
1 <= k <= 105
The answer is guaranteed to be in the range [0, 231 - 1] for the given n and k.
*/



/*
    Question understanding:
        0011 is correct
        0001 is not correct, becuase we cannot paint continous 3 or more fence with same color
        
    Brute force
        time: O(k^n)
    
    DP memo
        states: currN, prevColor, adjSameColorCount ==> n, k+1, 2
        time: (n * k * 2) * k
        space: n * k * 2
        space optimization: k * 2
        
    But it leads to TLE because the DP memo is not useful at all.
*/

/*
// TLE

class Solution {
    int n, k;
    Integer[][][] memo;
    
    public int numWays(int n, int k) {
        this.n = n;
        this.k = k;
        this.memo = new Integer[n][k + 1][2];
        return numWaysUtil(0, 0, 0);
    }
    
    public int numWaysUtil(int currN, int prevColor, int adjSameColorCount) {
        if (currN == n) {
            return 1;
        }
        if (memo[currN][prevColor][adjSameColorCount] != null) {
            return memo[currN][prevColor][adjSameColorCount];
        }
        int totalWays = 0;
        
        for (int currColor = 1; currColor <= k; currColor++) {
            if (currColor == prevColor) {
                if (adjSameColorCount == 0) {
                    totalWays += numWaysUtil(currN + 1, currColor, 1);
                }
            } else {
                totalWays += numWaysUtil(currN + 1, currColor, 0);
            }
        }
        return memo[currN][prevColor][adjSameColorCount] = totalWays;
    }
}
*/


/*
    logic: DP
    time: n
    space: n
    explanation: https://leetcode.com/problems/paint-fence/discuss/178010/The-only-solution-you-need-to-read
    
    brute force: O(k^1 + k^2 + k^3 + .... + k^n) ==> O(k^n)
    so we can fetch the base case for n = 1 and n = 2
    because the condition always holds true for n <= 2
*/
class Solution {
    Integer[] memo;
    
    public int numWays(int n, int k) {
        this.memo = new Integer[n + 1];
        return numWaysUtil(n, k);
    }
    
    public int numWaysUtil(int n, int k) {
        if (n == 1) {
            return k;
        }
        if (n == 2) {
            return k * k;
        }
        if (memo[n] != null) {
            return memo[n];
        }
        
        int sameWays = numWaysUtil(n - 1, k) * (k - 1);     // main logic
        int diffWays = numWaysUtil(n - 2, k) * (k - 1);     // main logic
        return memo[n] = sameWays + diffWays;
    }
}