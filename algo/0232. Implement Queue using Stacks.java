/*
Implement the following operations of a queue using stacks.
push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.

Example:
MyQueue queue = new MyQueue();
queue.push(1);
queue.push(2);  
queue.peek();  // returns 1
queue.pop();   // returns 1
queue.empty(); // returns false

Notes:
You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, stack may not be supported natively. 
You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
*/

class MyQueue 
{
    // Space: n
    // Time: push(2n), pop(1).. we can alter this and make push(1) and pop(n)
    
    Stack<Integer> stack1;
    Stack<Integer> stack2;
   
    /** Initialize your data structure here. */
    public MyQueue() 
    {
        stack1 = new Stack<Integer>();          // main stack (which replaces queue)
        stack2 = new Stack<Integer>();          // temp stack
    }
    
    /** Push element x to the back of queue. */
    public void push(int x)                     // push costly
    {
        if(stack1.isEmpty())
        {
            stack1.push(x);
        }
        else
        {
            while(!stack1.isEmpty())
            {
                stack2.push(stack1.pop());      // pop from main stack
            }
            stack1.push(x);                     // push the current element to bottom of main stack
            while(!stack2.isEmpty())
            {
                stack1.push(stack2.pop());      // push it back to main stack  
            }
        }
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() 
    {
        return stack1.pop();
    }
    
    /** Get the front element. */
    public int peek()
    {
        return stack1.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() 
    {
        return stack1.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */