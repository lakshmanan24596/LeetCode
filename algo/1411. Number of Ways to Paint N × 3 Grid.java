/*
You have a grid of size n x 3 and you want to paint each cell of the grid with exactly one of the three colours: Red, Yellow or Green while making sure that no two adjacent cells have the same colour (i.e no two cells that share vertical or horizontal sides have the same colour).
You are given n the number of rows of the grid.
Return the number of ways you can paint this grid. As the answer may grow large, the answer must be computed modulo 10^9 + 7.

Example 1:
Input: n = 1
Output: 12
Explanation: There are 12 possible way to paint the grid as shown:

Example 2:
Input: n = 2
Output: 54

Example 3:
Input: n = 3
Output: 246

Example 4:
Input: n = 7
Output: 106494

Example 5:
Input: n = 5000
Output: 30228214

Constraints:
n == grid.length
grid[i].length == 3
1 <= n <= 5000
*/


/*
    1) recur + DP memo
        https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/discuss/574912/JavaC%2B%2B-DFS-Memoization-with-Picture-Clean-code
        consider it as a 2D matrix where rows = n and cols = 3
        we need to check left (horizontal) and top (vertical), and assign a diff color to curr grid
        time, space ==> n*3*3*3 ==> n*27
        
    2) math
        https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/discuss/574943/Java-Detailed-Explanation-with-Graph-Demo-DP-Easy-Understand
        time = n
        space = 1
*/

class Solution {
    int[] colors = new int[] {1, 2, 3};
    int mod = 1_000_000_007;
    Integer[][][][] DP;
    int n;
    
    public int numOfWays(int n) {
        this.n = n;
        DP = new Integer[n][4][4][4];
        return dfs(0, 0, 0, 0);
    }
    
    public int dfs(int level, int prevA, int prevB, int prevC) {            // prev a, b, c are the colors of previous row
        if (level == n) {
            return 1;
        }
        if (DP[level][prevA][prevB][prevC] != null) {                       // 4 states for DP
            return DP[level][prevA][prevB][prevC];
        }
        int output = 0;
        int currA, currB, currC;
        
        for(int a : colors) {
            if (a != prevA) {                                               // check top (left is not present for a)
                for (int b : colors) {
                    if (b != prevB && b != a) {                             // check top and left
                        for (int c : colors) {
                            if (c != prevC && c != b) {                     // check top and left
                                output += dfs(level + 1, a, b, c);
                                output %= mod;
                            }
                        }
                    }
                }
            }
        }
        return DP[level][prevA][prevB][prevC] = output;
    }
}