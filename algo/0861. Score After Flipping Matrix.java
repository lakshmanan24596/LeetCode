/*
We have a two dimensional matrix A where each value is 0 or 1.
A move consists of choosing any row or column, and toggling each value in that row or column: changing all 0s to 1s, and all 1s to 0s.
After making any number of moves, every row of this matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.
Return the highest possible score.

Example 1:
Input: [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
Output: 39

Explanation:
Toggled to [[1,1,1,1],[1,0,0,1],[1,1,1,1]].
0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39

Note:
1 <= A.length <= 20
1 <= A[0].length <= 20
A[i][j] is 0 or 1.
*/


/*
    1) Brute force:
        each row can be either toggled or not toggled --> time 2^r
        In each state in step1, we need to toggle the column if it has more zero than one --> time: r*c
        Time: 2^r * r * c, which is exponential
    
    2) Greedy:
        a) Left most bit is the most significant bit. So the first value in all row should be 1. (Ex: 1000 > 0111)
           this can be done by --> flip all rows that start with zero
        b) for columns 2 to n,
           flip all columns where the number of zeros is greater than the number of ones
        c) now the output matrix is ready
           we need to calculate the binary sum 
          
        To flip a bit, we need to do --> xor with 1
        Time: 3 * r * c
        Space: r * c
*/

class Solution {
    int[][] arr;
    int row, col;
    
    public int matrixScore(int[][] arr) {
        this.arr = arr;
        row = arr.length;
        col = arr[0].length; 
        int zeroCount;
        int totalSum = 0, currSum;
        
        for (int i = 0; i < row; i++) {                     // step 1: flip all rows that start with zero
            if (arr[i][0] == 0) {
                flipRow(i);
            }
        }
        
        for (int currCol = 1; currCol < col; currCol++) {   // step 2: flip all columns where the number of 0 > number of 1
            zeroCount = 0;
            for (int i = 0; i < row; i++) {
                if (arr[i][currCol] == 0) {
                    zeroCount++;
                }
            }
            if (zeroCount > (row / 2)) {
                flipCol(currCol);
            }
        }
        
        for (int i = 0; i < row; i++) {                     // step 3: calculate the binary sum 
            currSum = 0;
            for (int j = 0; j < col; j++) {
                currSum = (currSum << 1) + arr[i][j];
            }
            totalSum += currSum;
        }
        return totalSum;
    }
    
    public void flipRow(int currRow) {
        for (int i = 0; i < col; i++) {
            arr[currRow][i] ^= 1;                           // "xor with 1" is used to flip 0 to 1 and vice versa   
        }
    }
    
    public void flipCol(int currCol) {
        for (int i = 0; i < row; i++) {
            arr[i][currCol] ^= 1;
        }
    }
}

/*
    one pass algo:
    https://leetcode.com/problems/score-after-flipping-matrix/discuss/143722/C%2B%2BJavaPython-Easy-and-Concise
*/
