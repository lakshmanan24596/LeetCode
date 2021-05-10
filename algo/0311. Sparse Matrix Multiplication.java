/*
Given two sparse matrices mat1 of size m x k and mat2 of size k x n, return the result of mat1 x mat2. 
You may assume that multiplication is always possible.

Example 1:
Input: mat1 = [[1,0,0],[-1,0,3]], mat2 = [[7,0,0],[0,0,0],[0,0,1]]
Output: [[7,0,0],[-7,0,3]]

Example 2:
Input: mat1 = [[0]], mat2 = [[0]]
Output: [[0]]

Constraints:
m == mat1.length
k == mat1[i].length == mat2.length
n == mat2[i].length
1 <= m, n, k <= 100
-100 <= mat1[i][j], mat2[i][j] <= 100
*/


/*
    Sparse matrix: 
        most of the array elements are zero
        so store and process only non-zero elements to reduce time and space
        
    how to store it? refer --> https://www.cs.cmu.edu/~scandal/cacm/node9.html
    how to process it? refer --> https://leetcode.com/problems/sparse-matrix-multiplication/discuss/419538/What-the-interviewer-is-expecting-when-this-problem-is-asked-in-an-interview...
        
    further optimization: What if one vector is significantly longer than the other one? 
        We could say we can traverse the shorter one and use binary search to find the matched index in the other vector. 
        Then the run time would be O(m * logn)
        m is the length of the shorter vector while n is the length of the longer vector
        because, (m * logn) < (m + n), for very larger n
        
    time: (m * n) * k, but only for non-zero elements
    space: m * n, which is for output array
*/

class Solution {
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int m = mat1.length;
        int k = mat2.length;
        int n = mat2[0].length;
        List<int[]>[] mat1Rows = new ArrayList[m];                      // ex1: [[[0,1]], [[0,-1],[2,3]]] --> mat1: r1, r2
        List<int[]>[] mat2Cols = new ArrayList[n];                      // ex1: [[[0,7]], [], [[2,1]]]    --> mat2: c1, c2, c3
        List<int[]> nonZeroElements;                                    // each element is array of size 2 (index, value)
        int[][] mat3 = new int[m][n];
        
        for (int i = 0; i < m; i++) {                                   // iterate row-wise
            nonZeroElements = new ArrayList<int[]>();
            for (int j = 0; j < k; j++) {
                if (mat1[i][j] != 0) {
                    nonZeroElements.add(new int[] {j, mat1[i][j]});     // preprocess: store mat1 rows
                }
            }
            mat1Rows[i] = nonZeroElements;
        }
        for (int j = 0; j < n; j++) {                                   // iterate col-wise
            nonZeroElements = new ArrayList<int[]>();
            for (int i = 0; i < k; i++) {
                if (mat2[i][j] != 0) {
                    nonZeroElements.add(new int[] {i, mat2[i][j]});     // preprocess: store mat2 cols
                }
            }
            mat2Cols[j] = nonZeroElements;
        }
        mat1 = null;                                                    // so that GC will clear the unwanted space
        mat2 = null;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat3[i][j] = multiply(i, j, mat1Rows[i], mat2Cols[j]);  // multiply and store in output array
            }
        }
        return mat3;
    }
    
    public int multiply(int rowNum, int colNum, List<int[]> mat1Row, List<int[]> mat2Col) {
        int i = 0, j = 0;
        int multipliedValue = 0;
        int idx1, idx2, val1, val2;
        
        while (i < mat1Row.size() && j < mat2Col.size()) {              // main logic: 2-pointer
            idx1 = mat1Row.get(i)[0];
            idx2 = mat2Col.get(j)[0];
            
            if (idx1 == idx2) {
                val1 = mat1Row.get(i)[1];
                val2 = mat2Col.get(j)[1];
                multipliedValue += val1 * val2;                         // multiply (only non-zero elements are processed)
                i++;
                j++;
            } else if (idx1 < idx2) {
                i++;
            } else {
                j++;
            }
        }
        return multipliedValue;
    }
}