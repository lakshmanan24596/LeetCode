/*
You are given an m x n matrix of characters box representing a side-view of a box. Each cell of the box is one of the following:
A stone '#'
A stationary obstacle '*'
Empty '.'

The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity. 
Each stone falls down until it lands on an obstacle, another stone, or the bottom of the box. 
Gravity does not affect the obstacles' positions, and the inertia from the box's rotation does not affect the stones' horizontal positions.
It is guaranteed that each stone in box rests on an obstacle, another stone, or the bottom of the box.
Return an n x m matrix representing the box after the rotation described above.


Example 1:
Input: box = [["#",".","#"]]
Output: [["."],
         ["#"],
         ["#"]]

Example 2:
Input: box = [["#",".","*","."],
              ["#","#","*","."]]
Output: [["#","."],
         ["#","#"],
         ["*","*"],
         [".","."]]

Example 3:
Input: box = [["#","#","*",".","*","."],
              ["#","#","#","*",".","."],
              ["#","#","#",".","#","."]]
Output: [[".","#","#"],
         [".","#","#"],
         ["#","#","*"],
         ["#","*","."],
         ["#",".","*"],
         ["#",".","."]]
 

Constraints:
m == box.length
n == box[i].length
1 <= m, n <= 500
box[i][j] is either '#', '*', or '.'.
*/



/*
    Logic: iterate row by row (but trasversing from last column)
            track the emptyPos in a variable
            
    time: r * c
    space: r * c for output arr
*/

class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int rows = box.length;
        int cols = box[0].length;
        char[][] rotatedBox = new char[cols][rows];
        
        for (int i = 0; i < rows; i++) {                          // iterate row by row
            int emptyPos = -1;
            for (int j = cols -1; j >= 0; j--) {                  // iterate from last column
                if (box[i][j] == '*') {
                    rotatedBox[j][rows - 1 - i] = '*';
                    emptyPos = -1;
                } else if (box[i][j] == '.') {
                    rotatedBox[j][rows - 1 - i] = '.';
                    if (emptyPos == -1) {
                        emptyPos = j;
                    }
                } else {
                    if (emptyPos == -1) {
                        rotatedBox[j][rows - 1 - i] = '#';        // dont move the stone
                    } else {
                        rotatedBox[emptyPos][rows - 1 - i] = '#'; // move the stone to the bottom empty space
                        rotatedBox[j][rows - 1 - i] = '.';        // set curr cell to empty
                        emptyPos--;
                    }
                }
            }
        }
        return rotatedBox;
    }
}
