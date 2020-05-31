/*
Invert a binary tree.
Example:
Input:
     4
   /   \
  2     7
 / \   / \
1   3 6   9
Output:
     4
   /   \
  7     2
 / \   / \
9   6 3   1

Trivia:
This problem was inspired by this original tweet by Max Howell:
Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so f*** off.
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
    // the problem is not to swap 1 and 9 (in given ques). Instead if we swap left and right we can get the answer
    
    public TreeNode invertTree(TreeNode root)       // Time: O(n), Space: O(h)
    {
        if(root == null)
            return null;
        
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        
        root.left = right;                          // post or pre order can be used
        root.right = left;
        return root;
    }
}

// class Solution
// {
//     public TreeNode invertTree(TreeNode root) 
//     {
//         if (root == null) 
//             return null;
        
//         Queue<TreeNode> queue = new LinkedList<TreeNode>();
//         queue.add(root);
        
//         while (!queue.isEmpty()) 
//         {
//             TreeNode curr = queue.poll();
            
//             TreeNode temp = curr.left;
//             curr.left = curr.right;
//             curr.right = temp;
            
//             if (curr.left != null) 
//                 queue.add(curr.left);
//             if (curr.right != null) 
//                 queue.add(curr.right);
//         }
//         return root;
//     }
// }    