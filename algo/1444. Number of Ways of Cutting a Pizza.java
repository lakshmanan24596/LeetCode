/*
Given a rectangular pizza represented as a rows x cols matrix containing the following characters: 'A' (an apple) and '.' (empty cell) and given the integer k. 
You have to cut the pizza into k pieces using k-1 cuts. 
For each cut you choose the direction: vertical or horizontal, then you choose a cut position at the cell boundary and cut the pizza into two pieces. 
If you cut the pizza vertically, give the left part of the pizza to a person. 
If you cut the pizza horizontally, give the upper part of the pizza to a person. Give the last piece of pizza to the last person.

Return the number of ways of cutting the pizza such that each piece contains at least one apple. 
Since the answer can be a huge number, return this modulo 10^9 + 7.

Example 1:
Input: pizza = ["A..","AAA","..."], k = 3
Output: 3 
Explanation: The figure above shows the three ways to cut the pizza. Note that pieces must contain at least one apple.

Example 2:
Input: pizza = ["A..","AA.","..."], k = 3
Output: 1

Example 3:
Input: pizza = ["A..","A..","..."], k = 1
Output: 1

Constraints:
1 <= rows, cols <= 50
rows == pizza.length
cols == pizza[i].length
1 <= k <= 10
pizza consists of characters 'A' and '.' only.
*/


/*
    DP memo
    DP states: r, c, k
    time: O(r * c * k * (r + c))
    space: O(r * c * k)
*/

class Solution {
    int m, n, k;
    int[] rowCount, colCount;
    Integer[][][] DP;
    int[][] prefixSum;
    int totalPizzaCount = 0;
    int mod = 1_000_000_007;
    
    public int ways(String[] pizza, int k) {
        m = pizza.length;
        n = pizza[0].length();
        this.k = k;
        DP = new Integer[m][n][k-1];
        prefixSum = new int[m+1][n+1];              // used to check if a piece of pizza contains at least one apple
        String currPizza;
        int currCount;
        
        for (int i = m-1; i >= 0; i--) {            // calculate prefixSum in reverse order
            currPizza = pizza[i];
            for (int j = n-1; j >= 0; j--) {
                currCount = (currPizza.charAt(j) == 'A') ? 1 : 0;
                prefixSum[i][j] = currCount + ((prefixSum[i][j+1] + prefixSum[i+1][j]) - prefixSum[i+1][j+1]);
            }
        }
        return dfs(0, 0, 0);
    }
    
    public int dfs(int r, int c, int cuts) {
        if (prefixSum[r][c] == 0) {                 // no apples left
            return 0;
        }
        if (cuts == k - 1) {                        // cut the pizza into k pieces using k-1 cuts
            return 1;
        }
        if (DP[r][c][cuts] != null) {
            return DP[r][c][cuts];
        }
        int output = 0;
        
        for (int nr = r + 1; nr < m; nr++) {
            if (prefixSum[r][c] - prefixSum[nr][c] > 0) {
                output += dfs(nr, c, cuts + 1);
                output %= mod;
            }
        }
        for (int nc = c + 1; nc < n; nc++) {
            if (prefixSum[r][c] - prefixSum[r][nc] > 0) {
                output += dfs(r, nc, cuts + 1);
                output %= mod;
            }
        }
        return DP[r][c][cuts] = output;
    }
}