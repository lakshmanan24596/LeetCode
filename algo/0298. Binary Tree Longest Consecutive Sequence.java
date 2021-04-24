/*
Given the root of a binary tree, return the length of the longest consecutive sequence path.
The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
The longest consecutive path needs to be from parent to child (cannot be the reverse).

Example 1:
Input: root = [1,null,3,2,4,null,null,null,5]
Output: 3
Explanation: Longest consecutive sequence path is 3-4-5, so return 3.

Example 2:
Input: root = [2,null,3,2,null,1]
Output: 2
Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.

Constraints:
The number of nodes in the tree is in the range [1, 3 * 104].
-3 * 104 <= Node.val <= 3 * 104
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
class Solution {
    int maxCount = 0;
    
    public int longestConsecutive(TreeNode root) {                      // time: n, space: h (stack space)
        longestConsecutive(root, Integer.MIN_VALUE);
        return maxCount + 1;
    }
    
    public int longestConsecutive(TreeNode root, int parentVal) {       // post order dfs traversal
        if (root == null) {
            return 0;
        }
        int leftCount = longestConsecutive(root.left, root.val);
        int rightCount = longestConsecutive(root.right, root.val);
        
        if (root.val == (parentVal + 1)) {
            int currCount = 1 + Math.max(leftCount, rightCount);        // main logic
            maxCount = Math.max(maxCount, currCount);
            return currCount;
        } else {
            return 0;
        }
    }
}