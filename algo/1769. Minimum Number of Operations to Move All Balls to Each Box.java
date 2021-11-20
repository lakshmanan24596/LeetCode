/*
You have n boxes. You are given a binary string boxes of length n, where boxes[i] is '0' if the ith box is empty, and '1' if it contains one ball.
In one operation, you can move one ball from a box to an adjacent box. 
Box i is adjacent to box j if abs(i - j) == 1. Note that after doing so, there may be more than one ball in some boxes.
Return an array answer of size n, where answer[i] is the minimum number of operations needed to move all the balls to the ith box.
Each answer[i] is calculated considering the initial state of the boxes.

Example 1:
Input: boxes = "110"
Output: [1,1,3]
Explanation: The answer for each box is as follows:
1) First box: you will have to move one ball from the second box to the first box in one operation.
2) Second box: you will have to move one ball from the first box to the second box in one operation.
3) Third box: you will have to move one ball from the first box to the third box in two operations, and move one ball from the second box to the third box in one operation.

Example 2:
Input: boxes = "001011"
Output: [11,8,5,4,3,4]

Constraints:
n == boxes.length
1 <= n <= 2000
boxes[i] is either '0' or '1'.
*/


/*
    Question explanation
    consider example 2 --> index of 1 are 2, 4, 5
    output:
        2 + 4 + 5 = 11
        1 + 3 + 4 = 8
        0 + 2 + 3 = 5
        1 + 1 + 2 = 4
        2 + 0 + 1 = 3
        3 + 1 + 0 = 4
*/
/*
    LTR + RTL, greedy
    time: 2n
    space: n for output
*/
class Solution {
    public int[] minOperations(String boxes) {
        int noOfBoxes = boxes.length();
        int[] minOperations = new int[noOfBoxes];
        int left = 0, right = 0, count = 0;
        
        for (int i = 0; i < noOfBoxes; i++) {
            minOperations[i] = left;
            count += boxes.charAt(i) - '0';
            left += count;
        }
        
        count = 0;
        for (int i = noOfBoxes - 1; i >= 0; i--) {
            minOperations[i] += right;
            count += boxes.charAt(i) - '0';
            right += count;
        }
        return minOperations;
    }
}