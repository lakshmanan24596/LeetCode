/*
Given an m x n binary matrix mat, return the length of the longest line of consecutive one in the matrix.
The line could be horizontal, vertical, diagonal, or anti-diagonal.

Example 1:
Input: mat = [[0,1,1,0],[0,1,1,0],[0,0,0,1]]
Output: 3

Example 2:
Input: mat = [[1,1,1,1],[0,1,1,0],[0,0,0,1]]
Output: 4
 
Constraints:
m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
mat[i][j] is either 0 or 1.
*/


/*
    1) brute force:
        time: r*c*(r+c)
        space: 1
        
    2) 3D DP:
        time: r*c*4
        space: r*c*4
        
    3) 2D DP: (space optimization for above solution)
        time: same as above
        space: c*4
        logic: current row depends only on prev row
*/

class Solution {
    public int longestLine(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        int[][] dir = new int[][] {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};            // hori, diag, vert, anti-diag
        int[][][] cache = new int[rows][cols][dir.length];
        int prevI, prevJ, prevValue;
        int longestLine = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 1) {                                               // main logic
                    for (int d = 0; d < dir.length; d++) {
                        prevI = i + dir[d][0];
                        prevJ = j + dir[d][1];
                        prevValue = (prevI >= 0 && prevI < rows && prevJ >= 0 && prevJ < cols) ? 
                                        cache[prevI][prevJ][d] : 0;
                        cache[i][j][d] =   prevValue + 1;                           // main logic
                        longestLine = Math.max(longestLine, cache[i][j][d]);
                    }
                }
            }
        }
        return longestLine;
    }
}