/*
In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the ith domino.  
(A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
We may rotate the ith domino, so that A[i] and B[i] swap values.
Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.
If it cannot be done, return -1.

Example 1:
Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
Output: 2
Explanation: 
The first figure represents the dominoes as given by A and B: before we do any rotations.
If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.

Example 2:
Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
Output: -1
Explanation: 
In this case, it is not possible to rotate the dominoes to make one row of values equal.

Constraints:
2 <= A.length == B.length <= 2 * 104
1 <= A[i], B[i] <= 6
*/


/*
    There are 4 possible cases:
        make values in A equal to A[0]
        make values in B equal to A[0]
        make values in A equal to B[0]
        make values in B equal to B[0]
        
    Logic: greedy
    Time: n
    Space: 1
*/

class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        int makeValueinA = helper(A, B, A[0]);                  // case 1, 2
        int makeValueinB = helper(A, B, B[0]);                  // case 3, 4
        int output = Math.min(makeValueinA, makeValueinB);
        return (output == Integer.MAX_VALUE) ? -1 : output;
    }
    
    public int helper(int[] A, int[] B, int num) {
        int n = A.length;
        int count1 = 0, count2 = 0;
        
        for (int i = 0; i < n; i++) { 
            if (A[i] == num || B[i] == num) {
                if (A[i] != num) {
                    count1++;
                }
                if (B[i] != num) {
                    count2++;
                }
            } else {
                return Integer.MAX_VALUE;
            }
        }
        return Math.min(count1, count2);
    }
}