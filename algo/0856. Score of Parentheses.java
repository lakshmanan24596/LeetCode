/*
Given a balanced parentheses string S, compute the score of the string based on the following rule:
() has score 1
AB has score A + B, where A and B are balanced parentheses strings.
(A) has score 2 * A, where A is a balanced parentheses string.

Example 1:
Input: "()"
Output: 1

Example 2:
Input: "(())"
Output: 2

Example 3:
Input: "()()"
Output: 2

Example 4:
Input: "(()(()))"
Output: 6

Note:
S is a balanced parentheses string, containing only ( and ).
2 <= S.length <= 50
*/


/*
    Time: n, Space: n
    logic: store output in stack
    https://leetcode.com/problems/score-of-parentheses/discuss/141975/c%2B%2B-solution-using-stack-(5ms)-detail-explained
*/
class Solution
{
    public int scoreOfParentheses(String S) 
    {
        Stack<Integer> stack = new Stack<Integer>();    // store output in stack
        stack.push(0);
        
        for(char ch : S.toCharArray())
        {
            if(ch == '(') 
            {
                stack.push(0);
            }
            else 
            {
                int topVal = stack.pop();
                int existingVal = stack.pop();
                
                int currVal = (topVal == 0) ? 1 : topVal * 2;       // +1 or *2
                stack.push(currVal + existingVal);
            }
        }
        return stack.pop();
    }
}

/*
    Time: n, Space: 1
    It just occurs to me the input can be treated as a tree. Every () is a tree node. 
    ()() can be treated as two sibling nodes while (()) can be treated as a parent and a child node. 
    Each leaf node has value 1, while non-leaf node has double the value of all of its direct children's value. 
    All we have to do is to calculate the root node value via post-order traverse. 
    And this is exactly what the approach 1 did as it always get the value of deeper layers before gets its own value.
    
    For example, the S = '(()(()()))' can be treated as the tree below.
                            (10)            layer 0
                            ／ \
                          (1)  (4)          layer 1
                               /  \
                             (1)  (1)       layer 2
                             
    As you can see, the value of the root node is the sum of each leaf node value to the power of it's depth.
    There are three leaf nodes in the tree. One leaf in layer 1 and two leafs in layer 2. 
    So the final answer is 2^1 + 2^2 + 2^2 = 10.
*/
/*
class Solution 
{
    public int scoreOfParentheses(String S) 
    {
        int ans = 0, bal = 0;
        for(int i = 0; i < S.length(); ++i) 
        {
            if(S.charAt(i) == '(') 
            {
                bal++;
            } 
            else 
            {
                bal--;
                if (S.charAt(i-1) == '(') {
                    ans += 1 << bal;
                }
            }
        }
        return ans;
    }
}
*/