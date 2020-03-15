/*
Given preorder and inorder traversal of a tree, construct the binary tree.
Note:
You may assume that duplicates do not exist in the tree.

For example, given
preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:
    3
   / \
  9  20
    /  \
   15   7
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
    int preOrderIndex = 0;
    int[] preorder, inorder;
    Map<Integer,Integer> inorderMap;   // key=data, value=index
    
    public TreeNode buildTree(int[] preorder, int[] inorder) 
    {
        this.preorder = preorder;
        this.inorder = inorder;
        this.inorderMap = new HashMap<Integer,Integer>();
        
        formMapForInorder();
        return buildTree(0, preorder.length - 1);
    }
    
    public TreeNode buildTree(int start, int end)
    {
        if(start > end)
            return null;
        
        int preorderValue = preorder[preOrderIndex++];
        int inorderIndex = inorderMap.get(preorderValue);
        
        TreeNode root = new TreeNode(preorderValue);
        root.left = buildTree(start, inorderIndex - 1);
        root.right = buildTree(inorderIndex + 1, end);
        
        return root;
    }
    
    public void formMapForInorder()
    {
        for(int i = 0; i < inorder.length; i++)
            inorderMap.put(inorder[i], i);
    }
}