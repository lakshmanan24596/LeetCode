/*
There are n people standing in a queue, and they numbered from 0 to n - 1 in left to right order. 
You are given an array heights of distinct integers where heights[i] represents the height of the ith person.

A person can see another person to their right in the queue if everybody in between is shorter than both of them. 
More formally, the ith person can see the jth person if i < j and min(heights[i], heights[j]) > max(heights[i+1], heights[i+2], ..., heights[j-1]).

Return an array answer of length n where answer[i] is the number of people the ith person can see to their right in the queue.


Example 1:
Input: heights = [10,6,8,5,11,9]
Output: [3,1,2,1,1,0]
Explanation:
Person 0 can see person 1, 2, and 4.
Person 1 can see person 2.
Person 2 can see person 3 and 4.
Person 3 can see person 4.
Person 4 can see person 5.
Person 5 can see no one since nobody is to the right of them.

Example 2:
Input: heights = [5,1,2,3,10]
Output: [4,1,1,1,0]

Constraints:
n == heights.length
1 <= n <= 105
1 <= heights[i] <= 105
All the values of heights are unique.
*/


/*
    Diagram: https://leetcode.com/problems/number-of-visible-people-in-a-queue/discuss/1363940/C%2B%2BJavaPython-Monotonic-stack-Visualize-picture-Clean-and-Concise
    Logic: mono decreasing stack
    time: n
    space: n
*/

class Solution {
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<Integer>();    // mono decreasing stack
        stack.push(n - 1);
        int[] output = new int[n];
        output[n - 1] = 0;
        
        for (int i = n - 2; i >= 0; i--) {              // iterate from reverse
            if (heights[i] < heights[stack.peek()]) {
                output[i] = 1;
                stack.push(i);
            } else {
                int popCount = 0;
                while (!stack.isEmpty() && heights[i] > heights[stack.peek()]) {
                    stack.pop();
                    popCount++;
                }
                output[i] = (stack.isEmpty()) ? popCount : popCount + 1;
                stack.push(i);
            }
        }
        return output;
    }
}