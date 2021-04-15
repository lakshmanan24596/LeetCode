/*
You are given the root of a binary search tree (BST), where exactly two nodes of the tree were swapped by mistake. 
Recover the tree without changing its structure.
Follow up: A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?

Example 1:
Input: root = [1,3,null,null,2]
Output: [3,1,null,null,2]
Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.

Example 2:
Input: root = [3,1,4,null,null,2]
Output: [2,1,4,null,null,3]
Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.

Constraints:
The number of nodes in the tree is in the range [2, 1000].
-231 <= Node.val <= 231 - 1
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
    1) HashMap:
        time: n
        space: n
        store each node in a hashmap with key,value = value,node
        recur with min,max range
        if value is not within min,max range then:
            first = curr
            second = pick from hashmap
            swap first, second
            
    2) Inorder recursion:
        time: n
        space: h (stack space)
        main logic: "Inorder of BST gives sorted order"
        using above logic we need to find the 2 nodes and swap them
        
    3) Morris inorder:
        time: n
        space: 1
        logic: same as above
        
*/
class Solution {
    TreeNode firstNode = null;
    TreeNode secondNode = null;
    TreeNode prevNode = new TreeNode(Integer.MIN_VALUE);
    
    public void recoverTree(TreeNode root) {
        recoverTreeUtil(root);
        swapNodesVal(firstNode, secondNode);
    }
    
    public void recoverTreeUtil(TreeNode root) {    // inorder traversal
        if (root == null) {
            return;
        }
        recoverTreeUtil(root.left);
        
        if (root.val < prevNode.val) {              // main logic: condition for "not in sorted order"
            if (firstNode == null) {
                firstNode = prevNode;
                secondNode = root;
            } else {
                secondNode = root;
            }
        }
        prevNode = root;
        
        recoverTreeUtil(root.right);
    }
    
    public void swapNodesVal(TreeNode n1, TreeNode n2) {
        int temp = n1.val;
        n1.val = n2.val;
        n2.val = temp;
    }
}