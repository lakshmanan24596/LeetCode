/*
Given a binary tree, return all root-to-leaf paths.
Note: A leaf is a node with no children.

Example:
Input:

   1
 /   \
2     3
 \
  5
Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3
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
    public List<String> outputList = new ArrayList<String>();
    public List<Integer> currList = new ArrayList<Integer>();
    
    public List<String> binaryTreePaths(TreeNode root) {
        recur(root, currList);
        return outputList;
    }
    
    public void recur(TreeNode root, List<Integer> currList) {
        if(root == null) {
            return;
        }
           
        currList.add(root.val);
        
        if(root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while(i < currList.size() - 1) {
                sb.append(String.valueOf(currList.get(i++))).append("->");
            }
            sb.append(String.valueOf(currList.get(i)));
            outputList.add(sb.toString());
        }
        
        recur(root.left, currList);
        recur(root.right, currList);
        
        currList.remove(currList.size()-1); 
    }
}