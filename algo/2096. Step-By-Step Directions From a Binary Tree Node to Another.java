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
You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. 
You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.

Find the shortest path starting from node s and ending at node t. 
Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. 
Each letter indicates a specific direction:

'L' means to go from a node to its left child node.
'R' means to go from a node to its right child node.
'U' means to go from a node to its parent node.
Return the step-by-step directions of the shortest path from node s to node t.


Example 1:
Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
Output: "UURL"
Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.

Example 2:
Input: root = [2,1], startValue = 2, destValue = 1
Output: "L"
Explanation: The shortest path is: 2 → 1.
 

Constraints:
The number of nodes in the tree is n.
2 <= n <= 105
1 <= Node.val <= n
All the values in the tree are unique.
1 <= startValue, destValue <= n
startValue != destValue
*/


/*
    1) parent pointer:
        time: 3n
        space: n
        
    2) LCA:
        logic: lca node to startValue node is the number of "U". So no need to store parent pointer
        time: 3n
        space: 1
*/

class Solution {
    int startValue, destValue;
    StringBuilder output;
    
    public String getDirections(TreeNode root, int startValue, int destValue) {
        this.startValue = startValue;
        this.destValue = destValue;
        output = new StringBuilder("");
        
        TreeNode lcaNode = findLca(root);
        dfsUpPath(lcaNode, 0);
        dfsDownPath(lcaNode);
        return output.toString();
    }
    
    public TreeNode findLca(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.val == startValue || root.val == destValue) {
            return root;
        }
        TreeNode left = findLca(root.left);
        TreeNode right = findLca(root.right);

        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        } else {
            return null;
        }
    }
    
    public boolean dfsUpPath(TreeNode root, int level) {    // dfs from lca node to startValue node
        if (root == null) {
            return false;
        }
        if (root.val == startValue) {
            while (level-- > 0) {
                output.append("U");
            }
            return true;
        }
        return dfsUpPath(root.left, level + 1) || dfsUpPath(root.right, level + 1);
    }
    
    public boolean dfsDownPath(TreeNode root) {             // dfs from lca node to destValue node
        if (root == null) {
            return false;
        }
        if (root.val == destValue) {
            return true;
        }
        
        output.append("L");
        boolean left = dfsDownPath(root.left);
        if (!left) {
            output.deleteCharAt(output.length() - 1);
        }
        
        output.append("R");
        boolean right = dfsDownPath(root.right);
        if (!right) {
            output.deleteCharAt(output.length() - 1);
        }
        
        return left || right;
    }
}