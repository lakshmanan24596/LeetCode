/*
Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n. 
Return the answer in any order.

Example 1:
Input: n = 3
Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]

Example 2:
Input: n = 1
Output: [[1]]

Constraints:
1 <= n <= 8
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
class Solution {
    List<TreeNode>[][] DP;
    public List<TreeNode> generateTrees(int n) {                // catalan number + DP
        this.DP = new ArrayList[n + 1][n + 1];
        return generateTrees(1, n);
    }
    
    public List<TreeNode> generateTrees(int start, int end) {   // same logic as 96. Unique Binary Search Trees
        List<TreeNode> output = new ArrayList<TreeNode>();
        if (start > end) {
            output.add(null);
            return output;
        }
        if (start == end) {
            output.add(new TreeNode(start));
            return output;
        }
        if (DP[start][end] != null) {
            return DP[start][end];
        }
        List<TreeNode> leftList, rightList;
        TreeNode root;
        
        for (int i = start; i <= end; i++) {                    // any node can be a root node
            leftList = generateTrees(start, i - 1);
            rightList = generateTrees(i + 1, end);
            
            for (TreeNode left : leftList) {                    // result += catalanRecur(i) * catalanRecur(n - 1 - i);
                for (TreeNode right : rightList) {
                    root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    output.add(root);
                }
            }
        }
        return DP[start][end] = output;
    }
}