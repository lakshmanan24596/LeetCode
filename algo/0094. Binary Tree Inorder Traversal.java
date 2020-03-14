/*
Given a binary tree, return the inorder traversal of its nodes' values.
Example:
Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
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
    public List<Integer> inorderTraversal(TreeNode curr)    // Morris Inorder Traversal
    {
        List<Integer> output = new ArrayList<Integer>();
       
        while(curr != null)
        {
            if(curr.left == null)
            {
                output.add(curr.val);
                curr = curr.right;
            }
            else
            {
                TreeNode pred = findPred(curr);
                if(pred.right == null)
                {
                    pred.right = curr;  // form new link
                    curr = curr.left;
                }
                else
                {
                    pred.right = null;  // remove extra link
                    output.add(curr.val);
                    curr = curr.right;                  
                }
            }
        }
        return output;
    }
    
    public TreeNode findPred(TreeNode curr)
    {
        TreeNode pred = curr.left;
        while(pred.right != null && pred.right != curr) // to avoid loop
        {
            pred = pred.right;
        }
        return pred;
    }
}