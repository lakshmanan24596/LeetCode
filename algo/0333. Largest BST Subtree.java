/*
Given the root of a binary tree, find the largest subtree, which is also a Binary Search Tree (BST), where the largest means subtree has the largest number of nodes.
A Binary Search Tree (BST) is a tree in which all the nodes follow the below-mentioned properties:

The left subtree values are less than the value of their parent (root) node's value.
The right subtree values are greater than the value of their parent (root) node's value.

Note: A subtree must include all of its descendants.
Follow up: Can you figure out ways to solve it with O(n) time complexity?

Example 1:
Input: root = [10,5,15,1,8,null,7]
Output: 3
Explanation: The Largest BST Subtree in this case is the highlighted one. The return value is the subtree's size, which is 3.

Example 2:
Input: root = [4,2,7,2,3,5,null,2,null,null,null,null,null,1]
Output: 2

Constraints:
The number of nodes in the tree is in the range [0, 104].
-104 <= Node.val <= 104
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
    1) brute force
        time: n^2
        space: h
    2) post order dfs
        time: n
        space: h
        https://leetcode.com/problems/largest-bst-subtree/discuss/78899/Very-Short-Simple-Java-O(N)-Solution
*/
class Solution {
    int maxSize = 0;
    
    public int largestBSTSubtree(TreeNode root) {
        largestBSTSubtreeUtil(root);
        return maxSize;
    }
    
    public ReturnNode largestBSTSubtreeUtil(TreeNode root) {
        if (root == null) {
            return new ReturnNode(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, true);
        }
        ReturnNode left = largestBSTSubtreeUtil(root.left);
        ReturnNode right = largestBSTSubtreeUtil(root.right);
        
        if (left.isBst && right.isBst && root.val > left.max && root.val < right.min) {     // main logic
            int currSize = 1 + left.size + right.size;
            maxSize = Math.max(maxSize, currSize);
            int min = Math.min(left.min, root.val);
            int max = Math.max(right.max, root.val);
            return new ReturnNode(min, max, currSize, true);
        } else {
            return new ReturnNode(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, false);
        }
    }
    
    class ReturnNode {
        int min;
        int max;
        int size;
        boolean isBst;
         
        ReturnNode(int min, int max, int size, boolean isBst) {
            this.min = min;
            this.max = max;
            this.size = size;
            this.isBst = isBst;
        }
    }
}