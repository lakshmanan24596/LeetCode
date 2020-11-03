/*
Given a root node reference of a BST and a key, delete the node with the given key in the BST. 
Return the root node reference (possibly updated) of the BST.
Basically, the deletion can be divided into two stages:
Search for a node to remove.
If the node is found, delete the node.
Follow up: Can you solve it with time complexity O(height of tree)?

Example 1:
Input: root = [5,3,6,2,4,null,7], key = 3
Output: [5,4,6,2,null,null,7]
Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.

Example 2:
Input: root = [5,3,6,2,4,null,7], key = 0
Output: [5,3,6,2,4,null,7]
Explanation: The tree does not contain a node with value = 0.

Example 3:
Input: root = [], key = 0
Output: []
 
Constraints:
The number of nodes in the tree is in the range [0, 104].
-105 <= Node.val <= 105
Each node has a unique value.
root is a valid binary search tree.
-105 <= key <= 105
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
    /* logic: if we want to delete the node, then the node should be replaced for that should be:
              either largest in left subtree of the deleteNode
              or smallest in right subtree of the deleteNode 
              
        Implementation: First search for the node to be search.
                        Replace the nodes value with the smallest in rightSide or largest in left side */
    
    TreeNode replaceNode;
    public TreeNode deleteNode(TreeNode root, int key)  // Time: O(h)
    {
        if(root == null) {
            return null;
        }
        
        if(key == root.val) 
        {
            if(root.left == null) {
                return root.right;
            }
            if(root.right == null) {
                return root.left;
            }
            
            findMaxNode(root.left);  // find max node in left subtree
            root.val = replaceNode.val; // main case
            if(root.left == replaceNode) {  
                root.left = root.left.left; // corner case
            }
        }
        else if(key < root.val) {
            root.left = deleteNode(root.left, key);
        }
        else {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }
    
    public TreeNode findMaxNode(TreeNode root)
    {
        if(root.right != null) {
            root.right = findMaxNode(root.right);
            return root;
        }
        else {
            replaceNode = root;  // restore the delete node
            return root.left;   // deletion happens here
        }
    }
}
