/*
Given a rows * columns matrix mat of ones and zeros, return how many submatrices have all ones.

Example 1:
Input: mat = [[1,0,1],
              [1,1,0],
              [1,1,0]]
Output: 13
Explanation:
There are 6 rectangles of side 1x1.
There are 2 rectangles of side 1x2.
There are 3 rectangles of side 2x1.
There is 1 rectangle of side 2x2. 
There is 1 rectangle of side 3x1.
Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.

Example 2:
Input: mat = [[0,1,1,0],
              [0,1,1,1],
              [1,1,1,0]]
Output: 24
Explanation:
There are 8 rectangles of side 1x1.
There are 5 rectangles of side 1x2.
There are 2 rectangles of side 1x3. 
There are 4 rectangles of side 2x1.
There are 2 rectangles of side 2x2. 
There are 2 rectangles of side 3x1. 
There is 1 rectangle of side 3x2. 
Total number of rectangles = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24.

Example 3:
Input: mat = [[1,1,1,1,1,1]]
Output: 21

Example 4:
Input: mat = [[1,0,1],[0,1,0],[1,0,1]]
Output: 5

Constraints:
1 <= rows <= 150
1 <= columns <= 150
0 <= mat[i][j] <= 1
*/


/*
    https://leetcode.com/problems/count-submatrices-with-all-ones/discuss/720280/Java-histogram-count
    https://leetcode.com/problems/count-submatrices-with-all-ones/discuss/720265/Java-Detailed-Explanation-From-O(MNM)-to-O(MN)-by-using-Stack
    
    Ref prob:
        1) https://leetcode.com/problems/maximal-rectangle/
        2) https://leetcode.com/problems/largest-rectangle-in-histogram/
        3) https://leetcode.com/problems/count-square-submatrices-with-all-ones/
     
    Example 2:
    given matrix          height               output
    [[0,1,1,0],         [[0,1,1,0],          [[0,1,2,0],
     [0,1,1,1],   -->    [0,2,2,1],   -->     [0,2,4,3],
     [1,1,1,0]]          [1,3,3,0]]           [1,4,7,0]]
     
     1) Time: n^3, Space: n --> main logic: extra loop to find largest histogram from right to left in height arr
     2) Time: n^2, Space: n --> using stack to sum up all histogram values
*/


// Time: n^3, Space: n
class Solution {
    public int numSubmat(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        int[] height = new int[col];                   // arr to store histogram values
        int min;
        int output = 0;
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                height[j] = (mat[i][j] == 0) ? 0 : height[j] + 1;
                min = Integer.MAX_VALUE;
                
                for (int k = j; k >= 0; k--) {          // loop to find largest histogram from right to left in height arr
                    min = Math.min(min, height[k]);
                    if (min <= 0) {
                        break;
                    }
                    output += min;
                }
            }
        }
        return output;
    }
}


/*
    Logic: same as above but implemented using histogram
    Time: n^2, Space: n
*/
/*
class Solution {
    public int numSubmat(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        int[] height = new int[col];                   // arr to store histogram values
        int output = 0;
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                height[j] = (mat[i][j] == 0) ? 0 : height[j] + 1;
            }
            output += sumUpAllHistograms(height);      // sum up all histogram values
        }
        return output;
    }
    
    public int sumUpAllHistograms(int[] arr) {         // https://leetcode.com/problems/largest-rectangle-in-histogram/
        Stack<Integer> stack = new Stack<Integer>();
        int output = 0;
        int top, dist, i;
        
        for (i = 0; i < arr.length; ) {
            if (stack.isEmpty() || arr[i] > arr[stack.peek()]) {    // monotonically increasing stack
                stack.push(i);
                i++;
            } else {
                top = stack.pop();
                dist = (stack.isEmpty()) ? i : ((i - 1)  - stack.peek());
                output += arr[top] * dist;
            }
        }
        while (!stack.isEmpty()) {
            top = stack.pop();
            dist = (stack.isEmpty()) ? i : ((i - 1)  - stack.peek());
            output += arr[top] * dist;
        }
        return output;
    }
}
*/