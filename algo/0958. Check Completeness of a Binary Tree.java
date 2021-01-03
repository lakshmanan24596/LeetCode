/*
Given the root of a binary tree, determine if it is a complete binary tree.
In a complete binary tree, every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. 
It can have between 1 and 2h nodes inclusive at the last level h.

Example 1:
Input: root = [1,2,3,4,5,6]
Output: true
Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.

Example 2:
Input: root = [1,2,3,4,5,null,7]
Output: false
Explanation: The node with value 7 isn't as far left as possible.

Constraints:
The number of nodes in the tree is in the range [1, 100].
1 <= Node.val <= 1000
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
class Solution 
{
    public boolean isCompleteTree(TreeNode root) 
    {
        if(root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        boolean isEndAlreadyReached = false;
        
        while(!queue.isEmpty())
        {
            int size = queue.size();
            while(size-- > 0)
            {
                TreeNode curr = queue.remove();
                if(curr.left != null) {
                    if(isEndAlreadyReached) {
                        return false;
                    }
                    queue.add(curr.left);
                } else {
                    isEndAlreadyReached = true;
                }
                
                if(curr.right != null) {
                    if(isEndAlreadyReached) {
                        return false;
                    }
                    queue.add(curr.right);
                } else {
                    isEndAlreadyReached = true;
                }
            }
        }
        return true;
    }
}
*/

class Solution 
{
    public boolean isCompleteTree(TreeNode root) 
    {
        if(root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        boolean isEndAlreadyReached = false;
        
        while(!queue.isEmpty())
        {
            TreeNode curr = queue.remove();
            if(curr == null) {
                isEndAlreadyReached = true;
            }
            else {
                if(isEndAlreadyReached) {
                    return false;
                }
                queue.add(curr.left);
                queue.add(curr.right);
            }
        }
        return true;
    }
}
