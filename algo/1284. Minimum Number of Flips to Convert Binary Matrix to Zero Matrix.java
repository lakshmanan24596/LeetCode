/*
Given a m x n binary matrix mat. In one step, you can choose one cell and flip it and all the four neighbours of it if they exist (Flip is changing 1 to 0 and 0 to 1). 
A pair of cells are called neighboors if they share one edge.
Return the minimum number of steps required to convert mat to a zero matrix or -1 if you cannot.
Binary matrix is a matrix with all cells equal to 0 or 1 only.
Zero matrix is a matrix with all cells equal to 0.

Example 1:
Input: mat = [[0,0],[0,1]]
Output: 3
Explanation: One possible solution is to flip (1, 0) then (0, 1) and finally (1, 1) as shown.

Example 2:
Input: mat = [[0]]
Output: 0
Explanation: Given matrix is a zero matrix. We don't need to change it.

Example 3:
Input: mat = [[1,1,1],[1,0,1],[0,0,0]]
Output: 6

Example 4:
Input: mat = [[1,0,0],[1,0,0]]
Output: -1
Explanation: Given matrix can't be a zero matrix

Constraints:
m == mat.length
n == mat[0].length
1 <= m <= 3
1 <= n <= 3
mat[i][j] is 0 or 1.
*/



/*
    Logic:
        Flipping same index two times is like not flipping it at all. Each index can be flipped one time. 
        Try all possible combinations.
    
    time: 2^n, where n = rows*cols
    
    other solutions:
        1) memo is based on entire matrix
        https://leetcode.com/problems/minimum-number-of-flips-to-convert-binary-matrix-to-zero-matrix/discuss/446371/Java-Recursion-%2B-Memoization-Explained
        
        2) convert matrix to int
        https://leetcode.com/problems/minimum-number-of-flips-to-convert-binary-matrix-to-zero-matrix/discuss/446342/JavaPython-3-Convert-matrix-to-int%3A-BFS-and-DFS-codes-w-explanation-comments-and-analysis.
     
     
    
    Main logics learnt from this ques:
        1) converting matrix to int:
            matrixIntVal |= mat[i][j] << ((i * cols) + j);
        
        2) convert 2D index to 1D index:
            a) index = (i * cols) + j
            b) i = index / cols and j = index % cols
        
        3) flip 0 to 1 and 1 to 0 in 2D matrix
            mat[i][j] = mat[i][j] ^ 1
*/

class Solution {
    boolean[][] matrix;
    int rows, cols;
    int[] dir = new int[] {0, -1, 0, 1, 0};
    
    public int minFlips(int[][] mat) {
        this.rows = mat.length;
        this.cols = mat[0].length;
        int oneCount = 0;
        this.matrix = new boolean[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 0) {
                    matrix[i][j] = false;
                } else {
                    matrix[i][j] = true;
                    oneCount++;
                }
            }
        }
        int flips = minFlips(0, oneCount); 
        return (flips == Integer.MAX_VALUE) ? -1 : flips;
    }
    
    public int minFlips(int index, int oneCount) {                              // where index = 0 to (row*col)-1
        if (oneCount == 0) {
            return 0;
        }
        if (index >= (rows * cols)) {
            return Integer.MAX_VALUE;
        }
        
        int noFlip = minFlips(index + 1, oneCount);                             // dont flip
        
        oneCount = flipMatrix(index / cols, index % cols, oneCount);
        int flip = minFlips(index + 1, oneCount);                               // flip
        if (flip != Integer.MAX_VALUE) {
            flip += 1;
        }
        oneCount = flipMatrix(index / cols, index % cols, oneCount);            // backtrack
            
        return Math.min(flip, noFlip);                                          // similar to 0/1 knapsack
    }
    
    public int flipMatrix(int m, int n, int oneCount) {
        oneCount += (matrix[m][n]) ? -1 : 1;
        matrix[m][n] = !matrix[m][n];
        int nextM, nextN;
        
        for (int i = 0; i < 4; i++) {
            nextM = m + dir[i];
            nextN = n + dir[i + 1];
            if (nextM >= 0 && nextM < rows && nextN >= 0 && nextN < cols) {
                oneCount += (matrix[nextM][nextN]) ? -1 : 1;
                matrix[nextM][nextN] = !matrix[nextM][nextN];
            }
        }
        return oneCount;
    }
}