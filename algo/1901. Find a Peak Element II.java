/*
A peak element in a 2D grid is an element that is strictly greater than all of its adjacent neighbors to the left, right, top, and bottom.
Given a 0-indexed m x n matrix mat where no two adjacent cells are equal, find any peak element mat[i][j] and return the length 2 array [i,j].
You may assume that the entire matrix is surrounded by an outer perimeter with the value -1 in each cell.
You must write an algorithm that runs in O(m log(n)) or O(n log(m)) time. 

Example 1:
Input: mat = [[1,4],[3,2]]
Output: [0,1]
Explanation: Both 3 and 4 are peak elements so [1,0] and [0,1] are both acceptable answers.

Example 2:
Input: mat = [[10,20,15],[21,30,14],[7,16,32]]
Output: [1,1]
Explanation: Both 30 and 32 are peak elements so [1,1] and [2,2] are both acceptable answers.

Constraints:
m == mat.length
n == mat[i].length
1 <= m, n <= 500
1 <= mat[i][j] <= 105
No two adjacent cells are equal.
*/



/*
    1) brute force
        time: O(r * c)
        space: 1

    2) Binary search
        time: O(r * logc) or O(c * logr)
        space: 1

        https://www.youtube.com/watch?v=HtSuA80QTyo
        view last 10 mins of the video
        https://leetcode.com/problems/find-a-peak-element-ii/discuss/1338377/JavaC%2B%2B-Detailed-explanation

        Algo:
            1. Pick the middle column.
            2. Find the global maximum in the column.
            3a. If the row-neighbours of this element are smaller, then we found a 2D peak. 
            3b. Else if, we recurse at the right-half of the matrix if the right-neighbour was bigger
            3c. Else, left-half of the matrix if the left-neighbour was bigger.
*/

class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        int left = 0, right = cols - 1;
        int leftVal, rightVal;
        int r, c;
        
        while (left <= right) {
            c = left + ((right - left) / 2);                            // step 1
            r = 0;
            
            for (int i = 1; i < rows; i++) {                            // step 2
                if (mat[i][c] > mat[r][c]) {
                    r = i;
                }
            }
            
            leftVal = (c != 0) ? mat[r][c - 1] : Integer.MIN_VALUE;
            rightVal = (c != cols - 1) ? mat[r][c + 1] : Integer.MIN_VALUE;
            
            if(mat[r][c] > leftVal && mat[r][c] > rightVal) {           // step 3
                return new int[] {r, c}; 
            } else if(mat[r][c] < rightVal) {
                left = c + 1;
            } else {
                right = c - 1;
            }
        }
        return new int[] {};
    }
}
