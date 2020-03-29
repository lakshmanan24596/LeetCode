/*
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." 
Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". 
It will automatically contact the police if two directly-linked houses were broken into on the same night.
Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
Input: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \ 
     3   1
     
Output: 7 
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

Example 2:
Input: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \ 
 1   3   1

Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
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
    // Normal Inorder Time = 1 + 2T(n/2)            ==> n
    // Curr Problem Time   = 1 + 2T(n/2) + 4T(n/4)  ==> exponential
    
    HashMap<TreeNode, Integer> DP = new HashMap<TreeNode, Integer>();
    
    public int rob(TreeNode root) 
    {
        if(root == null)
            return 0;
        
        if(DP.containsKey(root))                                            // if already processed, return it
            return DP.get(root);
        
        int currVal = root.val; 
        
        if(root.left != null)
            currVal += rob(root.left.left) + rob(root.left.right);
        
        if(root.right != null)
            currVal += rob(root.right.left) + rob(root.right.right);
        
        int output = Math.max(currVal, rob(root.left) + rob(root.right));   // main logic
        DP.put(root, output);
        return output;
    }   
}