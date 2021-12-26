/*
You have n coins and you want to build a staircase with these coins. 
The staircase consists of k rows where the ith row has exactly i coins. The last row of the staircase may be incomplete.
Given the integer n, return the number of complete rows of the staircase you will build.

Example 1:
Input: n = 5
Output: 2
Explanation: Because the 3rd row is incomplete, we return 2.

Example 2:
Input: n = 8
Output: 3
Explanation: Because the 4th row is incomplete, we return 3.

Constraints:
1 <= n <= 231 - 1
*/


/*
    1) Linear search
        time: n, space: 1
        
    2) Binary search
        time: logn, space: 1
        example: 
            answer = 3 for n = 6 to 9
            let n = 8 --> 8 >= 6 && 8 <= 6+3

    3) Math
        time: 1, space: 1
        quadratic equation: ((x * (x + 1)) / 2) = n
        given n, we need to find x
*/

class Solution {
    public int arrangeCoins(int n) {
        long left = 1;
        long right = n;
        long rows, coins;
        
        while (left <= right) {
            rows = left + ((right - left) / 2);
            coins = (rows * (rows + 1)) / 2;            // main logic (sum of natural numbers)
            
            if (n >= coins && n <= coins + rows) {      // main logic
                return (int) rows;
            } else if (coins >= n) {
                right = rows - 1;
            } else {
                left = rows + 1;
            }
        }
        return (int) left;
    }
}
