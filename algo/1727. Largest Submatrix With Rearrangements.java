/*
You are given a binary matrix matrix of size m x n, and you are allowed to rearrange the columns of the matrix in any order.
Return the area of the largest submatrix within matrix where every element of the submatrix is 1 after reordering the columns optimally.

Example 1:
Input: matrix = [[0,0,1],[1,1,1],[1,0,1]]
Output: 4
Explanation: You can rearrange the columns as shown above.
The largest submatrix of 1s, in bold, has an area of 4.

Example 2:
Input: matrix = [[1,0,1,0,1]]
Output: 3
Explanation: You can rearrange the columns as shown above.
The largest submatrix of 1s, in bold, has an area of 3.

Example 3:
Input: matrix = [[1,1,0],[1,0,1]]
Output: 2
Explanation: Notice that you must rearrange entire columns, and there is no way to make a submatrix of 1s larger than an area of 2.

Example 4:
Input: matrix = [[0,0],[0,0]]
Output: 0
Explanation: As there are no 1s, no submatrix of 1s can be formed and the area is 0.

Constraints:
m == matrix.length
n == matrix[i].length
1 <= m * n <= 105
matrix[i][j] is 0 or 1.
*/


/*
    Exactly similar to https://leetcode.com/problems/maximal-rectangle/
    Additionally we need to sort each row
    Time: n*m*logn as sorting is involved
*/
/*
class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;        
        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(matrix[i][j] == 1) {
                    matrix[i][j] += matrix[i-1][j];
                }
            }
        }
        int output = 0;
        for (int[] currRow : matrix) {
            Arrays.sort(currRow);       // main logic: additional change compared to maximal-rectangle problem
            output = Math.max(output, largestRectangleArea(currRow));
        }
        return output;
    }
    
    public int largestRectangleArea(int[] arr) {    // https://leetcode.com/problems/largest-rectangle-in-histogram/
        Stack<Integer> stack = new Stack<Integer>();
        int i, top, currResult, result = 0;
        for (i = 0; i < arr.length; ) {
			if (stack.isEmpty() || arr[i] >= arr[stack.peek()]) {   // keep the stack in ascending order
                stack.push(i++);
			} else {
                top = stack.pop();
                currResult = arr[top] * (stack.isEmpty() ? i : i-1 - stack.peek()); // i-1 is right bar and stack.peek is left bar
				result = Math.max(result, currResult);
			}
		}
        while(!stack.isEmpty()) {
            top = stack.pop();
            currResult = arr[top] * (stack.isEmpty() ? i : i-1 - stack.peek());
			result = Math.max(result, currResult);
        }
		return result;
    }
}
*/


/*
    https://leetcode.com/problems/largest-submatrix-with-rearrangements/discuss/1020710/C%2B%2B-Clean-and-Clear-With-Intuitive-Pictures-O(m-*-n-*-logn)
    Same as above solution
    Since we have already done sorting, largestRectangleArea can be modified optimally
    Area = arr[i] * (rigtBar - leftBar), since the arr is sorted always --> arr.length is rightBar and i is leftBar
    Time: n*m*logn as sorting is involved
*/
class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(matrix[i][j] == 1) {
                    matrix[i][j] += matrix[i-1][j];
                }
            }
        }
        int output = 0;
        for (int[] currRow : matrix) {
            Arrays.sort(currRow);       // main logic: additional change compared to maximal-rectangle problem
            output = Math.max(output, largestRectangleArea(currRow));
        }
        return output;
    }
    
    public int largestRectangleArea(int[] arr) {
        int output = 0, currOutput;
        for(int i = 0; i < arr.length; i++) {
            currOutput = arr[i] * (arr.length - i); // arr.length is rightBar and i is leftBar
            output = Math.max(output, currOutput);
        }
        return output;
    }
}