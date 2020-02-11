class Solution 
{
    public boolean isValid(String s) 
    {
        Stack<Character> stack = new Stack<Character>();
        int len = s.length();
        
        for(int i=0; i<len; i++)
        {
            char ch = s.charAt(i);
            if(ch == ')')
            {
                if(!stack.isEmpty() && stack.peek() == '(')
                    stack.pop();
                else
                    return false;
            }
            else if(ch == '}')
            {
                if(!stack.isEmpty() && stack.peek() == '{')
                    stack.pop();
                else
                    return false;
            }
            else if(ch == ']')
            {
                if(!stack.isEmpty() && stack.peek() == '[')
                    stack.pop();
                else
                    return false;
            }
            else
            {
                stack.push(ch);
            }
        }
        
        return stack.isEmpty();            
    }
}