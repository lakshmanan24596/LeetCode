/*
Given the roots of two binary search trees, root1 and root2, return true if and only if there is a node in the first tree and a node in the second tree whose values sum up to a given integer target.

Example 1:
Input: root1 = [2,1,4], root2 = [1,0,3], target = 5
Output: true
Explanation: 2 and 3 sum up to 5.

Example 2:
Input: root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
Output: false

Constraints:
The number of nodes in each tree is in the range [1, 5000].
-109 <= Node.val, target <= 109
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

// logic: stack and 2-pointer, time: n+m, space: n+m
class Solution {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        int sum;
        
        while (true) {
            while (root1 != null) {
                stack1.push(root1);
                root1 = root1.left;                             // inorder
            }
            while (root2 != null) {
                stack2.push(root2);
                root2 = root2.right;                            // reverse inorder
            }
            if (stack1.isEmpty() || stack2.isEmpty()) {
                break;
            }
            
            sum = stack1.peek().val + stack2.peek().val;
            if (sum == target) {
                return true;
            } else if (sum < target) {
                root1 = stack1.pop().right;
            } else {
                root2 = stack2.pop().left;
            }
        }
        return false;
    }
}