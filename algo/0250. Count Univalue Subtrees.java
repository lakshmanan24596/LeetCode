/*
Given the root of a binary tree, return the number of uni-value subtrees.
A uni-value subtree means all nodes of the subtree have the same value.

Example 1:
Input: root = [5,1,5,5,5,null,5]
Output: 4

Example 2:
Input: root = []
Output: 0

Example 3:
Input: root = [5,5,5,5,5,null,5]
Output: 6

Constraints:
The numbrt of the node in the tree will be in the range [0, 1000].
-1000 <= Node.val <= 1000
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
    time: n
    space: h
    Implementation:
        for each node we need to return:
            a) isSubtreeUnival boolean
            b) node.val
            c) subtree output
        another implementation:
            a, b) achieve it in a single variable
            c) use global variable
        another implementation:
            https://leetcode.com/problems/count-univalue-subtrees/discuss/67573/My-Concise-JAVA-Solution
*/

class Solution {
    int univalSubtreeCount = 0;
    final int TRUE = Integer.MAX_VALUE;
    final int FALSE = Integer.MIN_VALUE;
    
    public int countUnivalSubtrees(TreeNode root) {
        univalSubtree(root);
        return univalSubtreeCount;
    }
    
    public int univalSubtree(TreeNode root) {
        if (root == null) {
            return TRUE;
        }
        int left = univalSubtree(root.left);
        int right = univalSubtree(root.right);
        
        if ((left == TRUE || left == root.val) && (right == TRUE || right == root.val)) {   // main logic
            univalSubtreeCount++;
            return root.val;
        } else {
            return FALSE;
        }
    }
}