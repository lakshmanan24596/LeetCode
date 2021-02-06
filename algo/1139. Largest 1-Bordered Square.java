/*
Given a 2D grid of 0s and 1s, return the number of elements in the largest square subgrid that has all 1s on its border, or 0 if such a subgrid doesn't exist in the grid.

Example 1:
Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
Output: 9

Example 2:
Input: grid = [[1,1,0,0]]
Output: 1

Constraints:
1 <= grid.length <= 100
1 <= grid[0].length <= 100
grid[i][j] is 0 or 1
*/


/*
    https://leetcode.com/problems/largest-1-bordered-square/discuss/345265/c%2B%2B-beats-100-(both-time-and-memory)-concise-with-algorithm-and-image
    
    Preprocess horizontal and vertical DP arrays
    The main trick here is to use two dp 2d array to respectively store the maximum left-side outreach point and top-side outreach point.
    
    For each index (i, j) of the grid, we need to check all possible length square
    point p1 = i, j               --> check hori, vert --> len1, len2
          p2 = (i - len + 1), j   --> check hori       --> len3
          p3 = i, (j - len + 1)   --> check vert       --> len4
    maxSquare = min(len1, len2, len3, len4)
    
    Time: n^3
    Space: n^2
    
    another approach: https://leetcode.com/problems/largest-1-bordered-square/discuss/345233/JavaC%2B%2BPython-Straight-Forward
*/

class Solution {
    public int largest1BorderedSquare(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        int[][] hori = new int[r][c];
        int[][] vert = new int[r][c];
        int output = 0;
        
        for (int i = 0; i < r; i++) {       // main logic: calculate hori, vert sum array
            for (int j = 0; j < c; j++) {
                if (grid[i][j] != 0) {
                    hori[i][j] = (j == 0) ? grid[i][j] : hori[i][j-1] + grid[i][j];
                    vert[i][j] = (i == 0) ? grid[i][j] : vert[i-1][j] + grid[i][j]; 
                }
            }
        }  
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                for (int len = Math.min(hori[i][j] , vert[i][j]); len > output; len--) {
                    if (hori[i - len + 1][j] >= len && vert[i][j - len + 1] >= len) {       // main logic (left, top)
                        output = len;
                        break;
                    }
                }
            }
        }
        return output * output;
    }
}