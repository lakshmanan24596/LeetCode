/*
Given a complete binary tree, count the number of nodes.
Note:
Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Example:
Input: 
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6
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
    /*
    Time:::  T(n) = 2 log(n) + T(n/2)
             = 2 log(n) + 2 log(n/2) + 2 log(n/4) +...
             = 2 (log(n) + log(n/2) + log(n/4)+...)
             = 2 (log(n) * log(n))
             = log(n)^2
    */
    
    public int countNodes(TreeNode root)
    {
        if (root == null) 
            return 0;
        
        int leftHeight = countHeight(root.left);
        int rightHeight = countHeight(root.right);
        
        if (leftHeight == rightHeight)
            return (int)Math.pow(2, leftHeight) + countNodes(root.right);
        else
            return (int)Math.pow(2, rightHeight) + countNodes(root.left);
    }
    
    public int countHeight(TreeNode root) 
    {
        if (root == null)
            return 0;
        else
            return 1 + countHeight(root.left);
    }
}

// class Solution 
// {
//     public int countNodes(TreeNode root) 
//     {
//         if(root == null)
//             return 0;
        
//         return 1 + countNodes(root.left) + countNodes(root.right);
//     }
// }
