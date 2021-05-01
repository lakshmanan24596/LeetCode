/*
Given a grid where each entry is only 0 or 1, find the number of corner rectangles.
A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned rectangle. 
Note that only the corners need to have the value 1. Also, all four 1s used must be distinct. 

Example 1:
Input: grid = 
[[1, 0, 0, 1, 0],
 [0, 0, 1, 0, 1],
 [0, 0, 0, 1, 0],
 [1, 0, 1, 0, 1]]
Output: 1
Explanation: There is only one corner rectangle, with corners grid[1][2], grid[1][4], grid[3][2], grid[3][4].

Example 2:
Input: grid = 
[[1, 1, 1],
 [1, 1, 1],
 [1, 1, 1]]
Output: 9
Explanation: There are four 2x2 rectangles, four 2x3 and 3x2 rectangles, and one 3x3 rectangle.

Example 3:
Input: grid = 
[[1, 1, 1, 1]]
Output: 0
Explanation: Rectangles must have four distinct corners.

Note:
The number of rows and columns of grid will each be in the range [1, 200].
Each grid[i][j] will be either 0 or 1.
The number of 1s in the grid will be at most 6000.
*/



/*
    2-pointer technique, where 2 points will be pointing leftCol and rightCol
    time: c * c * r
    space: 1
    
    Furthur optimization:
    https://leetcode.com/problems/number-of-corner-rectangles/discuss/110200/Summary-of-three-solutions-based-on-three-different-ideas
    refer Idea-3 in above link
    
    Follow up:
    https://leetcode.com/problems/number-of-corner-rectangles/discuss/188581/Google-follow-up-question.-A-general-case-solution.
    Solution: hashmap
*/

class Solution {
    public int countCornerRectangles(int[][] grid) {
        int count = 0;
        int currCount;
        int rows = grid.length;
        int cols = grid[0].length;
        
        for (int i = 0; i < cols - 1; i++) {                    // cols
            for (int j = i + 1; j < cols; j++) {                // cols
                currCount = 0;
                for (int k = 0; k < rows; k++) {                // rows
                    if (grid[k][i] == 1 && grid[k][j] == 1) {   // pair of 1's in new row
                        currCount++;
                    }
                }
                count += (currCount * (currCount - 1)) / 2;     // (n * n-1) / 2
            }
        }
        return count;
    }
}