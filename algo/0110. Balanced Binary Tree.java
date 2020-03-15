/*
Given a binary tree, determine if it is height-balanced.
For this problem, a height-balanced binary tree is defined as:
a binary tree in which the left and right subtrees of every node differ in height by no more than 1.

Example 1:
Given the following tree [3,9,20,null,null,15,7]:

    3
   / \
  9  20
    /  \
   15   7
Return true.

Example 2:
Given the following tree [1,2,2,3,3,null,null,4,4]:

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
Return false.
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
    public boolean isBalanced(TreeNode root) 
    {
        Temp output = checkIsBalanced(root);
        return output.isBalanced;
    }
      
    public Temp checkIsBalanced(TreeNode root)
    {
        if(root == null)
            return new Temp(0, true);
        
        Temp left = checkIsBalanced(root.left);
        Temp right = checkIsBalanced(root.right);
        
        int height = 1 + Math.max(left.height, right.height);
        int balanceFactor = Math.abs(left.height - right.height);
        
        if(left.isBalanced && right.isBalanced && balanceFactor <= 1)
            return new Temp(height, true);
        return new Temp(height, false);
    }
}

class Temp
{
    int height;
    boolean isBalanced;
    Temp(int height, boolean isBalanced)
    {
        this.height = height;
        this.isBalanced = isBalanced;
    }
}