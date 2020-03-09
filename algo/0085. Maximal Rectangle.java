/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example:
Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6
*/

class Solution 
{
    // let N = n*m
    // brute --> O(N^2) --> pick 1 element and go right and down
    // stack --> O(2N)  --> same as largestRectangleArea problem
    
    // find cummulative column wise and send each row to largestRectangleArea
    // 1,0,1,0,0
    // 2,0,2,1,1 
    // 3,1,3,2,2 --> ans = 6
    // 4,1,3,3,2 
    
    int result = 0;
    int currResult;
    int[][] matrix;
    
    public int maximalRectangle(char[][] input) 
    {
        if(input.length == 0)
        {
            return 0;
        }
        int row = input.length;
        int col = input[0].length;
        matrix = new int[row][col];
            
        // char to int
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                matrix[i][j] = input[i][j] - '0';
            }
        }       
        
        // send each row to largestRectangleArea
        largestRectangleArea(matrix[0]);                // first row
        for(int i = 1; i < row; i++)                    // remaining rows
        {
            for(int j = 0; j < col; j++)
            {
                if(matrix[i][j] == 1)
                {
                    matrix[i][j] += matrix[i-1][j];
                }
            }           
            largestRectangleArea(matrix[i]);
        }
        return result;
    }
    
    // https://leetcode.com/problems/largest-rectangle-in-histogram/
    public int largestRectangleArea(int[] arr)
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
