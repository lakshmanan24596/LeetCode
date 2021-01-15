/*
Given a binary tree, we install cameras on the nodes of the tree. 
Each camera at a node can monitor its parent, itself, and its immediate children.
Calculate the minimum number of cameras needed to monitor all nodes of the tree.

Example 1:
Input: [0,0,null,0,0]
Output: 1
Explanation: One camera is enough to monitor all nodes if placed as shown.

Example 2:
Input: [0,0,null,0,null,0,null,null,0]
Output: 2
Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.

Note:
The number of nodes in the given tree will be in the range [1, 1000].
Every node has value 0.
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
    technique: greedy
    time: n
    space: h
*/
class Solution
{
    int noOfCamera = 0;
    public int minCameraCover(TreeNode root) 
    {
        Node rootNode = dfs(root);
        if(!rootNode.isMonitored) {
            noOfCamera++;
        }
        return noOfCamera;
    }
    
    public Node dfs(TreeNode root) 
    {
        if(root == null) {
            return new Node(false, true);               // hasCamera, isMonitored
        }
        
        Node left = dfs(root.left);                     // dfs post order traversal
        Node right = dfs(root.right);
        
        boolean hasCamera = false, isMonitored = false;
        if(left.hasCamera || right.hasCamera)           // main logic
        {
            isMonitored = true;
        }
        if(!left.isMonitored || !right.isMonitored)     // main logic
        {
            hasCamera = true;
            isMonitored = true;
            noOfCamera++;
        }
        return new Node(hasCamera, isMonitored);
    }
    
    class Node
    {
        boolean hasCamera, isMonitored;
        Node(boolean hasCamera, boolean isMonitored) {
            this.hasCamera = hasCamera;
            this.isMonitored = isMonitored;
        }
    }
}
