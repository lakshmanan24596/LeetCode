/*
Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
Note: A leaf is a node with no children.

Example:
Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its minimum depth = 2.
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
    public int minDepth(TreeNode root) 
    {
        // we can do any traversal but, better solution : Level Order Treaversal  
        
        if(root == null)
            return 0;
                
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int level = 0;
        
        while(!queue.isEmpty())
        {
            level++;
            int size = queue.size();
            for(int i = 0; i < size; i++)
            {
                TreeNode currNode = queue.remove();
                if(currNode.left == null && currNode.right == null)
                    return level;

                if(currNode.left != null)
                    queue.add(currNode.left);
                if(currNode.right != null)
                    queue.add(currNode.right);
            }
        }
        
        return level;
    }
}