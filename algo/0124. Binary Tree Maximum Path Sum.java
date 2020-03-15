/*
Given a non-empty binary tree, find the maximum path sum.
For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:
Input: [1,2,3]

       1
      / \
     2   3

Output: 6

Example 2:
Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
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
    int output = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) 
    {
        maxPathSum(root, 0);
        return output;   
    }
    
    public int maxPathSum(TreeNode root, int currSum)
    {
        if(root == null)
            return 0;
        
        int leftSum = maxPathSum(root.left, currSum);
        int rightSum = maxPathSum(root.right, currSum);
        
        output = max1(output, 
                     root.val, 
                     root.val + leftSum, 
                     root.val + rightSum, 
                     root.val + leftSum + rightSum);
        
        return max2(root.val, 
                   root.val + leftSum, 
                   root.val + rightSum);      
    }
    
    public int max1(int a, int b, int c, int d, int e)
    {
        int max = a;
        if(b > max) max = b;
        if(c > max) max = c;
        if(d > max) max = d;
        if(e > max) max = e;
        return max;
    }
    
    public int max2(int a, int b, int c)
    {
        int max = a;
        if(b > max) max = b;
        if(c > max) max = c;
        return max;
    }
}