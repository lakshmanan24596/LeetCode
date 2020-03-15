/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
Note: A leaf is a node with no children.

Example:
Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1

Return:
[
   [5,4,11,2],
   [5,8,4,5]
]
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
    List<List<Integer>> output = new ArrayList<List<Integer>>();
    List<Integer> currOutput = new ArrayList<Integer>();
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) 
    {
        pathSumUtil(root, sum);
        return output;
    }
    
    public void pathSumUtil(TreeNode root, int sum)
    {
        if(root == null)
            return;
        
        currOutput.add(root.val);    
        
        sum = sum - root.val;
        if(root.left == null && root.right == null && sum == 0)
            output.add(new ArrayList<Integer>(currOutput));
        
        pathSumUtil(root.left, sum);
        pathSumUtil(root.right, sum);
        
        currOutput.remove(currOutput.size()-1);
    }
}