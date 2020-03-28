/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
 
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
*/

class MinStack 
{
    // 1) getMin = n time, 1 space
    // 2) getMin = 1 time, n space
    // 3) getMin = 1 time, (1 to n) space
    // 4) getMin = 1 time, 1 space 
    
    // formula:     push --> 2x - min,   pop --> 2min - y    
    /*
        if (x > min) then push x
        else push 2x - min because:
            Reason:
            if(x < min) then,
            x - min         <   0
            x - min + x     <   0 + x
            2x - min        <   x
            2x - min        <   new min element 

        if(top > min) then pop
        else pop and update min = 2min - x;
        because,
            newMin = 2min - y
            newMin = 2x - (2x - prevMin)
            newMin = prevMin.. this is what we wanted
    */
        
    long min;   
    Stack<Long> stack; 
    public MinStack() 
    {
        stack = new Stack<Long>();
    }
    
    public void push(int val) 
    {
        long x = (long)val;         // long is used because we push (2x - min) which may lead to integer overflow
        if(stack.isEmpty())
        {
            stack.push(x);
            min = x;
        }
        else
        {
            if(x > min)
            {
                stack.push(x);
            }
            else
            {
                stack.push((2*x) - min);
                min = x;
            }
        }
    }
    
    public void pop() 
    {
        if(!stack.isEmpty())
        {
            long y = stack.pop();
            if(y < min)
            {
                min = (2*min) - y;
            }
        }
    }
    
    public int top() 
    {
        return (int)(Math.max(stack.peek(), min));
    }
    
    public int getMin() 
    {
        return (int)min;
    }
}

// Logic: if(x <= min) then push both min and x.. So time: 1 and space <= n in worst case

// class MinStack {
//     int min = Integer.MAX_VALUE;
//     Stack<Integer> stack = new Stack<Integer>();
//     public void push(int x) {
//         if(x <= min){          
//             stack.push(min);
//             min=x;
//         }
//         stack.push(x);
//     }

//     public void pop() {
//         if(stack.pop() == min) min=stack.pop();
//     }

//     public int top() {
//         return stack.peek();
//     }

//     public int getMin() {
//         return min;
//     }
    
// }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */