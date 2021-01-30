/*
Given the root of a binary tree, find the maximum value V for which there exist different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.
A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.

Example 1:
Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
Output: 7
Explanation: We have various ancestor-node differences, some of which are given below :
|8 - 3| = 5
|3 - 7| = 4
|8 - 1| = 7
|10 - 13| = 3
Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.

Example 2:
Input: root = [1,null,2,null,0,3]
Output: 3

Constraints:
The number of nodes in the tree is in the range [2, 5000].
0 <= Node.val <= 105
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
    1) Time: n * h and Space: h
        For each node, check all the ancestor node
        We can store all ancestor node (current recursions stack) in an arrayList
        
    2) Time: n, Space: h
        We need maxDiff, so we just need minVal and maxVal for each subtree
        for each node, return new int[] {minVal, maxVal} in postOrder traversal
*/
class Solution {
    int maxDiff = 0;
    public int maxAncestorDiff(TreeNode root) {
        dfs(root);
        return this.maxDiff;
    }
    
    public int[] dfs(TreeNode root) {   // post order traversal
        int[] leftArr = (root.left != null) ? dfs(root.left) : new int[] {root.val, root.val};
        int[] rightArr = (root.right != null) ? dfs(root.right) : new int[] {root.val, root.val};
        
        int min = Math.min(leftArr[0], rightArr[0]);
        int max = Math.max(leftArr[1], rightArr[1]);
        int currDiff = Math.max(Math.abs(root.val - min), Math.abs(root.val - max));
        this.maxDiff = Math.max(maxDiff, currDiff);
        return new int[] {Math.min(min, root.val), Math.max(max, root.val)};
    }
}


/*
// another simple solution using preOrder traversal, Time: n, Space: h
class Solution {
    public int maxAncestorDiff(TreeNode root) {
       return helper(root, root.val, root.val);
    }
    
    public int helper(TreeNode node, int curMax, int curMin) {
        if (node == null) {
            return curMax - curMin;
        }
        curMax = Math.max(curMax, node.val);
        curMin = Math.min(curMin, node.val);
        int left = helper(node.left, curMax, curMin);
        int right = helper(node.right, curMax, curMin);
        return Math.max(left, right);
    }
}
*/