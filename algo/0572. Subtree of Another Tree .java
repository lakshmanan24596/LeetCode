/*
Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

Example 1:
Given tree s:
     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4 
  / \
 1   2
Return true, because t has the same structure and node values with a subtree of s.

Example 2:
Given tree s:
     3
    / \
   4   5
  / \
 1   2
    /
   0
Given tree t:
   4
  / \
 1   2
Return false.
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */


/*
    optimal O(S + T)
    https://leetcode.com/problems/subtree-of-another-tree/discuss/102736/Java-Concise-O(n%2Bm)-Time-O(n%2Bm)-Space
*/
class Solution 
{
    public boolean isSubtree(TreeNode s, TreeNode t) 
    {
        if(s == null) {
            return false;
        }
        if(isIdentical(s, t)) {
            return true;
        }
        return isSubtree(s.left, t) ? true : isSubtree(s.right, t); 
    }
    
    public boolean isIdentical(TreeNode s, TreeNode t) 
    {
        if(s == null && t == null) {
            return true;
        }
        if(s == null || t == null || s.val != t.val) {
            return false;
        }
        return isIdentical(s.left, t.left) && isIdentical(s.right, t.right);
    }
}