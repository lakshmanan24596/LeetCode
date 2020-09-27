/*
Implement a basic calculator to evaluate a simple expression string.
The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

Example 1:
Input: "3+2*2"
Output: 7

Example 2:
Input: " 3/2 "
Output: 1

Example 3:
Input: " 3+5 / 2 "
Output: 5

Note:
You may assume that the given expression is always valid.
Do not use the eval built-in library function.
*/

class Solution 
{
      /* logic --> Div and mul has "same and higher" preference
                   Add and sub has "same and lower" preference 
                   because, 
                        consider 3+4-2 ---> it gives (7-2 = 5)--> process first pair of digits
                        consider 3-4+2 ---> it gives (-1+2 = 1)--> process first pair of digits
                        and
                        consider 5*4/3 --> it gives (20/3 = 6) --> process first pair of digits
                        consider 5/4*3 --> it gives (1*3 = 3) --> process first pair of digits */
    
    public int calculate(String s)
    {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int sum = 0;
        int tempSum = 0;
        char lastSign = '+';    // Example: 3+2*2 can be considered as +3+2*2 with a plus in starting
        int num = 0;
        
        for(int i = 0; i < s.length(); i++) 
        {
            char c = s.charAt(i);
            boolean isDigit = Character.isDigit(c);
            if(isDigit) 
            {
                num = (num * 10) + (c - '0');   // Example: "34" should be (3 * 10) + 4
            }            
            if((!isDigit && c != ' ') || i == s.length() - 1) 
            {
                switch(lastSign)
                {
                    case '+' :
                        sum += tempSum;
                        tempSum = num;
                        break;
                    case '-' :
                        sum += tempSum;
                        tempSum = -num;
                        break;
                    case '*' :
                        tempSum *= num;
                        break;
                    case '/' :
                        tempSum /= num;
                        break;
                }
                num = 0;
                lastSign = c;
            }
        }
        sum += tempSum; // last iteration
        return sum;
    }
}

/*
Using Stack:

public int calculate(String s) {
    Stack<Integer> stack = new Stack<>();
    char sign = '+';
    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (Character.isDigit(c)) {
            int start = i;
            while (Character.isDigit(c)) {
                i++;
                if (i >= s.length())
                    break;
                c = s.charAt(i);
            }
            int operand = Integer.valueOf(s.substring(start, i));
            i--;
            if (sign == '+') {
                stack.push(operand);
            } else if (sign == '-') {
                stack.push(-operand);
            } else if (sign == '*') {
                stack.push(stack.pop() * operand);
            } else if (sign == '/') {
                stack.push(stack.pop() / operand);
            }
        } else if (c != ' ') {
            sign = c;
        }
    }
    int res = 0;
    for (int i : stack) {
        res += i;
    }
    return res;
}
*/