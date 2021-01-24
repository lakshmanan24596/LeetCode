/*
On an infinite plane, a robot initially stands at (0, 0) and faces north. The robot can receive one of three instructions:
"G": go straight 1 unit;
"L": turn 90 degrees to the left;
"R": turn 90 degrees to the right.

The robot performs the instructions given in order, and repeats them forever.
Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.

Example 1:
Input: instructions = "GGLLGG"
Output: true
Explanation: The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.

Example 2:
Input: instructions = "GG"
Output: false
Explanation: The robot moves north indefinitely.

Example 3:
Input: instructions = "GL"
Output: true
Explanation: The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...

Constraints:
1 <= instructions.length <= 100
instructions[i] is 'G', 'L' or, 'R'.
*/

/*
    Logic:
    The robot stays in the circle if:
        a) it changes direction (not top) or 
        b) it moves 0
        
    Time: n, Space: 1
*/
class Solution {
    public boolean isRobotBounded(String instructions) {
        int[] dirVal = new int[] {0, 1, 0, -1, 0};
        int currX = 0, currY = 0;
        int currDir = 0;    // currDir will be in range 0 to 3 for top, right, bottom, left respectively
        
        for (int i = 0; i < instructions.length(); i++) {
            char ch = instructions.charAt(i);
            if (ch == 'G') {
                currX += dirVal[currDir];
                currY += dirVal[currDir + 1];
            } 
            else if (ch == 'R') {
                currDir++;
                if(currDir == 4) {
                    currDir = 0;
                }
            } 
            else if (ch == 'L') { 
                currDir--;
                if(currDir == -1) {
                    currDir = 3;
                }
            }
        }
        return (currDir != 0) || (currX == 0 && currY == 0);    // main logic: (not top dir) or (equal to origin)
    }
}