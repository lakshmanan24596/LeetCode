/*
Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

Example 1:
Input: [1,2,1]
Output: [2,-1,2]

Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number; 
The second 1's next greater number needs to search circularly, which is also 2.

Note: The length of given array won't exceed 10000.
*/

class Solution 
{
    // same as problem 739
    public int[] nextGreaterElements(int[] arr) // Time: 2N + 2N = 4N and space: N
    {
        if(arr == null || arr.length == 0) {
            return arr;
        }
        
        Stack<Integer> stack = new Stack<Integer>(); // store arr index
        stack.push(0);
        int[] output = new int[arr.length];
        
        for(int i = 1; i < arr.length; i++)
        {
            while(!stack.isEmpty() && arr[i] > arr[stack.peek()]) // Main logic: keep the stack in descending order (Monotonic stack)
            {
                output[stack.pop()] = arr[i];
            }
            stack.push(i);
        }
        
        for(int i = 0; i < arr.length; i++) // do it again for circular array use case
        {
            while(stack.size() > 1 && arr[i] > arr[stack.peek()])
            {
                output[stack.pop()] = arr[i];
            }
            // dont push in stack
        }
        
        while(!stack.isEmpty()) // greatest elements in given array alone will be left in the stack
        {
            output[stack.pop()] = -1;
        }
        
        return output;  
    }
}
