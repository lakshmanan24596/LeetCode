// Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

// Example 1:
// Input: "(()"
// Output: 2
// Explanation: The longest valid parentheses substring is "()"

// Example 2:
// Input: ")()())"
// Output: 4
// Explanation: The longest valid parentheses substring is "()()"


class Solution 
{
    public int longestValidParentheses(String str) 
    {
        Stack<Data> stack = new Stack<Data>();
		int result = 0, currResult = 0;

		for(int i=0; i<str.length(); i++)
		{
			char ch = str.charAt(i);
			if(ch==')' && !stack.isEmpty() && stack.peek().ch=='(')
			{
				stack.pop();
				currResult = stack.isEmpty() ? i+1 : i-stack.peek().index;      // main logic : (i - stack.peek.index)
				result = Math.max(result, currResult);
			}
			else
			{
				stack.push(new Data(i, ch));
			}
		}

		return result;
    }
}

class Data
{
	int index;
	char ch;
	Data(int index, char ch)
	{
		this.index = index;
		this.ch = ch;
	}
}