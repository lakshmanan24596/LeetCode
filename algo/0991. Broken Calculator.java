/*
On a broken calculator that has a number showing on its display, we can perform two operations:
Double: Multiply the number on the display by 2, or;
Decrement: Subtract 1 from the number on the display.

Initially, the calculator is displaying the number X.
Return the minimum number of operations needed to display the number Y.

Example 1:
Input: X = 2, Y = 3
Output: 2
Explanation: Use double operation and then decrement operation {2 -> 4 -> 3}.

Example 2:
Input: X = 5, Y = 8
Output: 2
Explanation: Use decrement and then double {5 -> 4 -> 8}.

Example 3:
Input: X = 3, Y = 10
Output: 3
Explanation:  Use double, decrement and double {3 -> 6 -> 5 -> 10}.

Example 4:
Input: X = 1024, Y = 1
Output: 1023
Explanation: Use decrement operations 1023 times.

Note:
1 <= X <= 10^9
1 <= Y <= 10^9
*/


/*
    1) DP
        time: y
        space: y
        states of DP: currIndex
        
        ex: x = 10, y = 21
        output = 10, 9, 8, 7, 6, 12, 11, 22, 21 ==> 8 operations
        base case (upper bound) --> if (curr > y) then return curr - y
        base case (lower bound) --> if (curr <= 0) then return not possible
        
    
    2) logic: greedy
        time: log(y-x) base 2
        space: 1
    
        Main logic is to "Work Backwards" --> change -1 to +1 and *2 to /2  
        ex: x = 10, y = 21
        output = 21, 22, 11, 12, 6, 7, 8, 9, 10 ==> 8 operations
        
        If a number is odd 
            we can't divide it by 2, so add 1 --> (this is the main intuition of this work backwards approach)
        else 
            divide it by 2
*/

class Solution { 
    int target;
    
    public int brokenCalc(int x, int y) {               // time: log(y-x) base 2
        target = x;
        return brokenCalcUtil(y);                       // Work Backwards: curr = y and target = x
    }
    
    public int brokenCalcUtil(int curr) { 
        if (curr == target) {
            return 0;
        }
        if (curr < target) {
            return target - curr;
        }
        
        if (curr % 2 == 1) {
            return 1 + brokenCalcUtil(curr + 1);        // main logic (do it in reverse)
        } else {
            return 1 + brokenCalcUtil(curr / 2);        // main logic (do it in reverse)
        }
    }
}