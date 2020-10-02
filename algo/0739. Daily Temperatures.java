/*
Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. 
If there is no future day for which this is possible, put 0 instead.

For example, 
given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], 
your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: 
The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
*/

class Solution 
{
    // Time: 2N, Space: N
    // this question is same as next greater element on right side
    
    public int[] dailyTemperatures(int[] arr)  
    {
        if(arr == null || arr.length == 0) {
            return arr;
        }
        Stack<Integer> stack = new Stack<Integer>(); // store arr index
        stack.push(0);
        
        for(int i = 1; i < arr.length; i++)
        {
            while(!stack.isEmpty() && arr[i] > arr[stack.peek()]) // Main logic: keep the stack in descending order (Monotonic stack)
            {
                int stackIndex = stack.pop();
                arr[stackIndex] = i - stackIndex; // change it in given array itself, where "i - stackIndex" is the answer
            }
            stack.push(i);
        }
        
        while(!stack.isEmpty())
        {
            arr[stack.pop()] = 0;
        }
        return arr;
    }
}
