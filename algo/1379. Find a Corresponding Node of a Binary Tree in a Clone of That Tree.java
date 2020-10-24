/*
Given two binary trees original and cloned and given a reference to a node target in the original tree.
The cloned tree is a copy of the original tree.
Return a reference to the same node in the cloned tree.

Note that you are not allowed to change any of the two trees or the target node and the answer must be a reference to a node in the cloned tree.

Follow up: Solve the problem if repeated values on the tree are allowed.

Example 1:
Input: tree = [7,4,3,null,null,6,19], target = 3
Output: 3
Explanation: In all examples the original and cloned trees are shown. The target node is a green node from the original tree. The answer is the yellow node from the cloned tree.
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
    /* Time: O(n)
       logic: Do traversal in both the trees simultaneously */
    
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) 
    {
        if(original == null) {
            return null;
        }    
        if(original == target) {    // this will handle duplicate values also (follow up question)
            return cloned;
        }
        
        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        if(left != null) {
            return left;
        }
        
        TreeNode right = getTargetCopy(original.right, cloned.right, target);
        return right;
    }
}
