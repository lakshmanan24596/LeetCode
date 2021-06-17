/*
You are given an n x n grid representing a field of cherries, each cell is one of three possible integers.

0 means the cell is empty, so you can pass through,
1 means the cell contains a cherry that you can pick up and pass through, or
-1 means the cell contains a thorn that blocks your way.

Return the maximum number of cherries you can collect by following the rules below:

Starting at the position (0, 0) and reaching (n - 1, n - 1) by moving right or down through valid path cells (cells with value 0 or 1).
After reaching (n - 1, n - 1), returning to (0, 0) by moving left or up through valid path cells.
When passing through a path cell containing a cherry, you pick it up, and the cell becomes an empty cell 0.
If there is no valid path between (0, 0) and (n - 1, n - 1), then no cherries can be collected.
 

Example 1:
Input: grid = [[0,1,-1],[1,0,-1],[1,1,1]]
Output: 5
Explanation: The player started at (0, 0) and went down, down, right right to reach (2, 2).
4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
Then, the player went left, up, up, left to return home, picking up one more cherry.
The total number of cherries picked up is 5, and this is the maximum possible.

Example 2:
Input: grid = [[1,1,-1],[1,-1,1],[-1,1,1]]
Output: 0

Constraints:
n == grid.length
n == grid[i].length
1 <= n <= 50
grid[i][j] is -1, 0, or 1.
grid[0][0] != -1
grid[n - 1][n - 1] != -1
*/



/*
    1) DP-greedy (not correct) 
        time: n^2
        space: n^2
        similar to https://leetcode.com/problems/minimum-path-sum/
        logic: 
            In first iteration do normally and mark 1 to 0. 
            In second iteration, follow the same steps and pick only unpicked cherries
            this type of 2 iteration is done greedily
        Not correct:
            refer to test case in approach 1 in: https://leetcode.com/problems/cherry-pickup/solution/
            
    2) DP
        time: n^4
        space: n^4
        logic:
            Instead of walking from end to start, we walk from start to end twice. 
            Assume two persons Person1(r1, c1) and Person2(r2, c2) are present at the start location (0,0).
            These 2 persons can collect cherries and go to bottom location (n-1, n-1). 
            Now we have 4 possible ways for person1 and person2:
                a) down, down
                b) down, right
                c) right, down
                d) right, right
            Note: If both person are at same cell, then only one person can collect a cherry
            DP states: r1, c1, r2, c2
            
    3) DP optimal
        time: n^3
        space: n^3
        logic: 
            Apply solution approach logic
            Also, we can note that both persons will always have been moved same number of steps
            So, (r1 + c1) = (r2 + c2) --> Hence r2 = (r1 + c1) - c2 --> (main logic)
            If we use the above formula, then we can reduce the DP states to r1, c1, c2
        We can also apply tabulation space optimization to reduce space from n^3 to n^2
*/



/*
// approach-2: DP, n^4, n^4

class Solution {
    int[][] grid;
    int n;
    Integer[][][][] DP;
    
    public int cherryPickup(int[][] grid) {
        this.grid = grid;
        this.n = grid.length;
        if (grid[0][0] == -1 || grid[n - 1][n - 1] == -1) {
            return 0;
        }
        this.DP = new Integer[n][n][n][n];
        
        int maxCherry = dfs(0, 0, 0, 0);
        return (maxCherry == -1) ? 0 : maxCherry;
    }
    
    public int dfs(int r1, int c1, int r2, int c2) {
        if (r1 == n - 1 && c1 == n - 1 && r2 == n - 1 && c2 == n - 1) {     // destination
            return grid[r1][c1];
        }
        if (r1 >= n || c1 >= n || r2 >= n || c2 >= n) {                     // out of grid
            return -1;
        }
        if (grid[r1][c1] == -1 || grid[r2][c2] == -1) {                     // contains a thorn that blocks your way
            return -1;
        }
        if (DP[r1][c1][r2][c2] != null) {                                   // already processed
            return DP[r1][c1][r2][c2];
        }
        
        int way1 = dfs(r1 + 1, c1, r2 + 1, c2);                             // DD
        int way2 = dfs(r1 + 1, c1, r2, c2 + 1);                             // DR
        int way3 = dfs(r1, c1 + 1, r2 + 1, c2);                             // RD
        int way4 = dfs(r1, c1 + 1, r2, c2 + 1);                             // RR
        int maxCherry = Math.max(way1, Math.max(way2, Math.max(way3, way4)));
        
        if (maxCherry != -1) { 
            if (r1 == r2 && c1 == c2) {                                     // only one person can collect a cherry
                maxCherry += grid[r1][c1];
            } else {
                maxCherry += grid[r1][c1];
                maxCherry += grid[r2][c2];
            }
        }
        return DP[r1][c1][r2][c2] = maxCherry;
    }
}
*/


// approach-3: DP, n^3, n^3
class Solution {
    int[][] grid;
    int n;
    Integer[][][] DP;
    
    public int cherryPickup(int[][] grid) {
        this.grid = grid;
        this.n = grid.length;
        if (grid[0][0] == -1 || grid[n - 1][n - 1] == -1) {
            return 0;
        }
        this.DP = new Integer[n][n][n];
        
        int maxCherry = dfs(0, 0, 0);
        return (maxCherry == -1) ? 0 : maxCherry;
    } 
    
    public int dfs(int r1, int c1, int c2) {
        int r2 = (r1 + c1) - c2;                                            // MAIN LOGIC
        
        if (r1 == n - 1 && c1 == n - 1 && r2 == n - 1 && c2 == n - 1) {     // destination
            return grid[r1][c1];
        }
        if (r1 >= n || c1 >= n || r2 >= n || c2 >= n) {                     // out of grid
            return -1;
        }
        if (grid[r1][c1] == -1 || grid[r2][c2] == -1) {                     // contains a thorn that blocks your way
            return -1;
        }
        if (DP[r1][c1][c2] != null) {                                       // already processed
            return DP[r1][c1][c2];
        }
        
        int way1 = dfs(r1 + 1, c1, c2);                                     // DD
        int way2 = dfs(r1 + 1, c1, c2 + 1);                                 // DR
        int way3 = dfs(r1, c1 + 1, c2);                                     // RD
        int way4 = dfs(r1, c1 + 1, c2 + 1);                                 // RR
        int maxCherry = Math.max(way1, Math.max(way2, Math.max(way3, way4)));
        
        if (maxCherry != -1) {
            if (r1 == r2 && c1 == c2) {                                     // only one person can collect a cherry
                maxCherry += grid[r1][c1];
            } else {
                maxCherry += grid[r1][c1];
                maxCherry += grid[r2][c2];
            }
        }
        return DP[r1][c1][c2] = maxCherry;
    }
}