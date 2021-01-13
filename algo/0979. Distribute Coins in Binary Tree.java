/*
You are given the root of a binary tree with n nodes where each node in the tree has node.val coins and there are n coins total.
In one move, we may choose two adjacent nodes and move one coin from one node to another. 
(A move may be from parent to child, or from child to parent.)
Return the number of moves required to make every node have exactly one coin.

Example 1:
Input: root = [3,0,0]
Output: 2
Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.

Example 2:
Input: root = [0,3,0]
Output: 3
Explanation: From the left child of the root, we move two coins to the root [taking two moves].  Then, we move one coin from the root of the tree to the right child.

Example 3:
Input: root = [1,0,2]
Output: 2

Example 4:
Input: root = [1,0,0,null,3]
Output: 4
 
Constraints: 
The number of nodes in the tree is n.
1 <= n <= 100
0 <= Node.val <= n
The sum of Node.val is n.
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
    Possible cases:
        1) greater than 1 --> have extra x coins                 --> return x
        2) less than 1    --> need x coins                       --> return -x
        3) equal to 1     --> no extra coins and dont need coins --> return 0
        
    do post order dfs traversal to achieve this
    output will be the sum of abs value of return value
*/
class Solution 
{
    int output = 0;
    public int distributeCoins(TreeNode root) 
    {
        dfs(root);
        return output;
    }
    
    public int dfs(TreeNode root)   // post order dfs
    {
        if(root == null) {
            return 0;
        }
        
        int left = dfs(root.left);
        int right = dfs(root.right);
        
        int returnVal = (left + right) + (root.val - 1);    // main logic
        output += Math.abs(returnVal);
        return returnVal;
    }
}
