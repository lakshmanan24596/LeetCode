/*
Given an m x n picture consisting of black 'B' and white 'W' pixels, 
return the number of black lonely pixels.
A black lonely pixel is a character 'B' that located at a specific position where the same row and same column don't have any other black pixels.

Example 1:
Input: picture = [["W","W","B"],["W","B","W"],["B","W","W"]]
Output: 3
Explanation: All the three 'B's are black lonely pixels.

Example 2:
Input: picture = [["B","B","B"],["B","B","B"],["B","B","B"]]
Output: 0
 
Constraints:
m == picture.length
n == picture[i].length
1 <= m, n <= 500
picture[i][j] is 'W' or 'B'.
*/



/*
    1) brute   : (r*c * r*c), (1)
    2) countArr: (r*c),       (r*c)
    3) countArr: (r*c),       (c)
    4) count   : (r*c),       (1)
    
    Solutions:
    2) https://leetcode.com/problems/lonely-pixel-i/discuss/1083295/Java-Count-row-and-col
    3) https://leetcode.com/problems/lonely-pixel-i/discuss/100091/Java-O(mn)-time-O(m)-space.-28ms
    4) https://leetcode.com/problems/lonely-pixel-i/discuss/100018/Java-O(nm)-time-with-O(n%2Bm)-Space-and-O(1)-Space-Solutions
    
    
    Below solution explanation:
        1) B is converted to C --> 1 time
           C is converted to D --> more than 1 time
        2) W is converted to X --> 1 time
           X is converted to Y --> more than 1 time
           
        output += if top cell and left cell == C or X --> then increment 1
*/


// time = r*c*2, space: 1
class Solution {
    public int findLonelyPixel(char[][] picture) {
        int rows = picture.length;
        int cols = picture[0].length;
        int firstRowCount = 0;
        int lonelyPixels = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (picture[i][j] == 'B') {
                    
                    if (i == 0) {
                        firstRowCount++;                                            // left
                    } else {
                        if (picture[i][0] == 'B' || picture[i][0] == 'C' 
                                || picture[i][0] == 'W' || picture[i][0] == 'X') {
                            picture[i][0]++;                                        // left
                        }
                    }
                    
                    if (picture[0][j] == 'B' || picture[0][j] == 'C' 
                            || picture[0][j] == 'W' || picture[0][j] == 'X') {
                        picture[0][j]++;                                            // top
                    }
                }
            }
        }
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (picture[i][j] == 'B' || picture[i][j] == 'C') {
                    
                    if (i == 0) {
                        if (firstRowCount != 1) {                                   // left
                            continue;
                        }
                    } else {
                        if (!(picture[i][0] == 'C' || picture[i][0] == 'X')) {      // left
                            continue;
                        }
                    }
                    
                    if (!(picture[0][j] == 'C' || picture[0][j] == 'X')) {          // top
                        continue;
                    }
                    lonelyPixels++;
                }
            }
        }
        return lonelyPixels;
    }
}