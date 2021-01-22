/*
Given two sequences pushed and popped with distinct values, return true if and only if this could have been the result of a sequence of push and pop operations on an initially empty stack.

Example 1:
Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
Output: true
Explanation: We might do the following sequence:
push(1), push(2), push(3), push(4), pop() -> 4,
push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1

Example 2:
Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
Output: false
Explanation: 1 cannot be popped before 2.

Constraints:
0 <= pushed.length == popped.length <= 1000
0 <= pushed[i], popped[i] < 1000
pushed is a permutation of popped.
pushed and popped have distinct values.
*/


/*
// Time: n, Space: n, Technique: Simulation
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if(pushed.length != popped.length) {
            return false;
        }
        Stack<Integer> stack = new Stack<Integer>();
        int i = 0, j = 0, n = pushed.length;
        
        while (i < n || j < n) {
            if (stack.isEmpty() || stack.peek() != popped[j]) {     // main logic
                if (i == n) {
                    return false;
                }
                stack.push(pushed[i]);
                i++;
            } 
            else {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
*/


/*
    Logic: Exactly same as above. To reduce space, we alter it in the given array itself.
    Use given pushed array as stack
    Time: n, Space: 1
    Technique: Simulation
*/
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int i = 0, j = 0;
        for (int x : pushed) {
            pushed[i++] = x;        // change in original array itself to reduce space from n to 1
            while (i > 0 && pushed[i - 1] == popped[j]) {
                --i; 
                ++j;
            }
        }
        return i == 0;
    }
}