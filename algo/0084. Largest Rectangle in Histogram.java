/*
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
The largest rectangle is shown in the shaded area, which has area = 10 unit. 

Example:
Input: [2,1,5,6,2,3]
Output: 10
*/

class Solution 
{
    public int largestRectangleArea(int[] arr) /
    {
        // 1) brute : O(n^2) --> consider all bars as starting points and calculate area of all rectangles starting with every bar.
        // 2) stack : O(2n)  --> using stack --> where left can be found using stack and right is curr index
        // formula for both methods = (arr[curr]) * (rightBar - leftBar);

        Stack<Integer> stack = new Stack<Integer>();
        int result = 0, currResult;
        int i, top;

        for(i = 0; i < arr.length; )
        {   
            if(stack.isEmpty() || arr[i] >= arr[stack.peek()])    // keep the stack in ascending order
            {
                stack.push(i++);                                  // push index in stack and increment i
            }
            else
            {
                top = stack.pop();
                currResult = arr[top] * (stack.isEmpty() ? i : i-1 - stack.peek()); // where i-1 is right bar and stack.peek is left bar
                result = Math.max(result, currResult);
            }
        }

        while(!stack.isEmpty())
        {
            top = stack.pop();
            currResult = arr[top] * (stack.isEmpty() ? i : i-1 - stack.peek());
            result = Math.max(result, currResult);
        }

        return result;
    }
}
