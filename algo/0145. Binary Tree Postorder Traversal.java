/*
Given a binary tree, return the postorder traversal of its nodes' values.
Example:
Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [3,2,1]
Follow up: Recursive solution is trivial, could you do it iteratively?
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution 
{
    public List<Integer> postorderTraversal(TreeNode root) 
    {
        // preOrder --> use 1 stack and push right, left
        // postOrder --> use 2 stack and push left, right
        
        List<Integer> output = new ArrayList<Integer>();       
        if(root == null) {
            return output;
        }
        
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        stack1.push(root);      
        TreeNode curr;
        
        while(!stack1.isEmpty()) 
        {
            curr = stack1.pop();
            stack2.push(curr);  // instead of directly adding it in output, we push in stack2 because output we obtain is in reverse order
            
            if(curr.left != null) {
                stack1.push(curr.left);
            }
            if(curr.right != null) {
                stack1.push(curr.right);
            }            
        }
        
        // stack2 contains the answer
        while(!stack2.isEmpty()) {
            output.add(stack2.pop().val);
        }
        return output;  
    }
}