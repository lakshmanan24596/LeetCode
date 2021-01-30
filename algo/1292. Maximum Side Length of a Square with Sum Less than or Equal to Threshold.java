/*
Given a m x n matrix mat and an integer threshold. 
Return the maximum side-length of a square with a sum less than or equal to threshold or return 0 if there is no such square.

Example 1:
Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
Output: 2
Explanation: The maximum side length of square with sum less than 4 is 2 as shown.

Example 2:
Input: mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
Output: 0

Example 3:
Input: mat = [[1,1,1,1],[1,0,0,0],[1,0,0,0],[1,0,0,0]], threshold = 6
Output: 3

Example 4:
Input: mat = [[18,70],[61,1],[25,85],[14,40],[11,96],[97,96],[63,45]], threshold = 40184
Output: 2
*/


/*
    Use prefixSum array (formula = ((left+top)-topLeftDiagnol)+currVal)
    Possible answer: 1 to min(r, c)
    Logic: "binary search the answer"
    This works because all given elements are positive
    Time: r*c*log(min(r,c))
    Space: r*c
*/
/*
class Solution {
    int[][] prefixSumMat;
    int row, col;
    
    public int maxSideLength(int[][] mat, int threshold) {
        row = mat.length;
        col = mat[0].length;
        prefixSumMat = new int[row + 1][col + 1];
        int left = 1, right = Math.min(row, col), mid;
        int output = 0;
        
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                prefixSumMat[i][j] = ((prefixSumMat[i][j-1] + prefixSumMat[i-1][j]) - prefixSumMat[i-1][j-1]) + mat[i-1][j-1];
            }
        }

        while (left <= right) {             // binary search the answer
            mid = (left + right) / 2;
            if (binarySearch(mid, threshold)) {
                output = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return output;
    }
    
    public boolean binarySearch(int len, int threshold) {
        int currSum;
        
        for (int i = len; i <= row; i++) {
            for (int j = len; j <= col; j++) {
                currSum = prefixSumMat[i][j] - ((prefixSumMat[i][j-len] + prefixSumMat[i-len][j]) - prefixSumMat[i-len][j-len]);
                if (currSum <= threshold) {      
                    return true;                // main logic
                }
            }
        }
        return false;
    }
}
*/


/*
    Logic: exactly same as above
    but instead of doing a binary search, we can update output while filling the prefixSum array itself
    Time: r*c, Space: r*c
*/
class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int row = mat.length;
        int col = mat[0].length;
        int[][] prefixSumMat = new int[row + 1][col + 1];
        int currSum;
        int output = 0, len = 1;
        
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                prefixSumMat[i][j] = ((prefixSumMat[i][j-1] + prefixSumMat[i-1][j]) - prefixSumMat[i-1][j-1]) + mat[i-1][j-1];
                if (i >= len && j >= len) {
                    currSum = prefixSumMat[i][j] - ((prefixSumMat[i][j-len] + prefixSumMat[i-len][j]) - prefixSumMat[i-len][j-len]);
                    if (currSum <= threshold) {
                        output = len;
                        len++;
                    }
                }
            }
        }
        return output;
    }
}