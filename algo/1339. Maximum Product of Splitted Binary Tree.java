/*
Given a binary tree root. Split the binary tree into two subtrees by removing 1 edge such that the product of the sums of the subtrees are maximized.
Since the answer may be too large, return it modulo 10^9 + 7.

Example 1:
Input: root = [1,2,3,4,5,6]
Output: 110
Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)

Example 2:
Input: root = [1,null,2,3,4,null,null,5,6]
Output: 90
Explanation:  Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)

Example 3:
Input: root = [2,3,9,10,7,8,6,5,4,11,1]
Output: 1025

Example 4:
Input: root = [1,1]
Output: 1
 
Constraints:
Each tree has at most 50000 nodes and at least 2 nodes.
Each node's value is between [1, 10000].
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

class Solution 
{
    long currProd, maxProd = 0, totalSum;
    
    public int maxProduct(TreeNode root) 
    {
        totalSum = findTotalSum(root);
        findMaxProd(root);
        return  (int)(maxProd % (int)(1e9 + 7));
    }
    
    public int findTotalSum(TreeNode root)
    {
        if(root == null) {
            return 0;
        }
        int leftSum = findTotalSum(root.left);
        int rightSum = findTotalSum(root.right);
        return leftSum + rightSum + root.val;
    }
    
    public int findMaxProd(TreeNode root) 
    {
        if(root == null) {
            return 0;
        }
        int leftSum = findMaxProd(root.left);
        int rightSum = findMaxProd(root.right);
        
        int returnVal = leftSum + rightSum + root.val;
        currProd = returnVal * (totalSum - returnVal);      // main logic
        maxProd = Math.max(maxProd, currProd);
        return returnVal;
    }
}
