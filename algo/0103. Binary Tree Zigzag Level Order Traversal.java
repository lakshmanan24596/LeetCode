/*
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) 
    {
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        if(root == null)
            return output;      
        List<Integer> currOutput = new ArrayList<Integer>();
                
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        stack1.push(root);
        
        while(!stack1.isEmpty() || !stack2.isEmpty())
        {
            while(!stack1.isEmpty())
            {
                TreeNode node = stack1.pop();   // pop from S1 and push in S2
                currOutput.add(node.val);
                
                if(node.left != null)
                    stack2.push(node.left);     // push left and then right
                
                if(node.right != null)
                    stack2.push(node.right);
            }
            output.add(currOutput);
            currOutput = new ArrayList<Integer>();
           
            if(stack2.isEmpty())
                return output;            
            
            while(!stack2.isEmpty())
            {
                TreeNode node = stack2.pop();   // pop from S2 and push in S1
                currOutput.add(node.val);
                
                if(node.right != null)
                    stack1.push(node.right);    // push right and then left
                
                if(node.left != null)
                    stack1.push(node.left);              
            }                            
            output.add(currOutput);
            currOutput = new ArrayList<Integer>();
        }                
        return output; 
    }
}