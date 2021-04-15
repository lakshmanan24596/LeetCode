/*
Given an integer n, return a list of all possible full binary trees with n nodes. 
Each node of each tree in the answer must have Node.val == 0.
Each element of the answer is the root node of one possible tree. You may return the final list of trees in any order.
A full binary tree is a binary tree where each node has exactly 0 or 2 children.

Example 1:
Input: n = 7
Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]

Example 2:
Input: n = 3
Output: [[0,0,0]]

Constraints:
1 <= n <= 20
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
    Logic:
    if N = 7, then --> root count = 1, and recur remaining 6 in both sides
        recur left = 1, right = 5
        recur left = 3, right = 3
        recur left = 5, right = 1
        
    If deep copy is needed then:
        1) memo cache cannot be used
        2) we need to clone the node
        
    https://leetcode.com/problems/all-possible-full-binary-trees/discuss/163433/Java-Recursive-Solution-with-Explanation
*/

class Solution {
    public List<TreeNode> allPossibleFBT(int N) {
        if (N % 2 == 0) {                                    // corner case for N even
            return new ArrayList<TreeNode>();
        }
        return dfs(N);
    }
    
    public List<TreeNode> dfs(int N) {
        // if deep copy is not needed, then we can add DP with state = N
        List<TreeNode> fullBTList = new ArrayList<TreeNode>();
        if (N == 1) {
            fullBTList.add(new TreeNode(0));
            return fullBTList;
        }
        N--;                                                // root node count = 1
        
        for (int i = 1; i < N; i += 2) {
            List<TreeNode> leftFBT = dfs(i);                // recur left
            List<TreeNode> rightFBT = dfs(N - i);           // recur right
            
            for (TreeNode leftNode : leftFBT) {
                for (TreeNode rightNode : rightFBT) {
                    TreeNode curr = new TreeNode(0);
                    curr.left = leftNode;                   // clone(leftNode) and then assign
                    curr.right = rightNode;                 // clone(rightNode) and then assign
                    fullBTList.add(curr);
                }
            }
        }
        return fullBTList;
    }
    
    /*
    public TreeNode clone(TreeNode root) {                  // use this, if deep copy is needed
        if (root == null) {
            return null;
        }
        TreeNode cloneRoot = new TreeNode(root.val);
        cloneRoot.left = clone(root.left);
        cloneRoot.right = clone(root.right);
        return cloneRoot;
    }
    */
}