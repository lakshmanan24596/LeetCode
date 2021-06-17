/*
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
The largest rectangle is shown in the shaded area, which has area = 10 unit. 

Example:
Input: [2,1,5,6,2,3]
Output: 10
*/


/*
    1) brute
        O(n^2)
        pick a element and use 2 pointers to go left and right away from the element
        
    2) stack
        O(2n)
        left and right bar can be found using stack   
    
    3) 2-array
        O(2n)
        Refer:
            https://leetcode.com/problems/largest-rectangle-in-histogram/discuss/28902/5ms-O(n)-Java-solution-explained-(beats-96)
        Main logic: 
            p = lessFromLeft[p];
            this is something similar to path compression in find() in dsu 
        Formula: 
            area = height[i] * (lessFromRight[i] - lessFromLeft[i] - 1)
        Example:
            arr   =  2, 1,  5, 6, 2, 3
            left  = -1,-1, "1", 2, 1, 4
            right =  1, 6, "4", 4,-1,-1
        Logic:
            consider an exmaple: 1,2,3,4,5,6,7,1,2,3,4,5,6,7,1
            loop is formed only at the value second 1 and third 1, making the time = 7 + 7, which is n
            we can think it like a graph (dsu), and add edges accordingly 
*/

// stack based solution
class Solution 
{
    public int largestRectangleArea(int[] arr) 
    {
        
        Stack<Integer> stack = new Stack<Integer>();
        int result = 0, currResult;
        int i, top;
        
        for(i = 0; i < arr.length; )
        {
            if(stack.isEmpty() || arr[i] >= arr[stack.peek()])    // keep the stack in ascending order
            {
                stack.push(i);                                    // push index in stack and increment i
                i++;
            }
            else
            {
                top = stack.pop();
                currResult = arr[top] * (stack.isEmpty() ? i : i-1 - stack.peek()); // i-1 is right bar and stack.peek is left bar
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