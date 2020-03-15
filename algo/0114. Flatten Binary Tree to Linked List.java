/*
Given a binary tree, flatten it to a linked list in-place.
For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
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
    public void flatten(TreeNode root) 
    {
        if(root == null)
            return;
                   
        flatten(root.left);
        
        TreeNode originalLeft = root.left;
        TreeNode originalRight = root.right;
        
        if(originalLeft != null)
        {
            root.right = originalLeft;
           
            while(originalLeft.right != null)
                originalLeft = originalLeft.right;
            
            originalLeft.right = originalRight;
            root.left = null;
        }
        
        flatten(originalRight);
    }
}