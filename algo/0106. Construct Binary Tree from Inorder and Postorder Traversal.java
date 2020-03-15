/*
Given inorder and postorder traversal of a tree, construct the binary tree.
Note:
You may assume that duplicates do not exist in the tree.

For example, given
inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
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
    int postOrderIndex;
    int[] inorder, postorder;
    Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        
    public TreeNode buildTree(int[] inorder, int[] postorder) 
    {
        this.inorder = inorder;
        this.postorder = postorder;
        this.postOrderIndex = postorder.length - 1; 
        
        formInorderMap();
        return buildTree(0, postorder.length - 1);
    }
    
    public TreeNode buildTree(int start, int end)
    {
        if(start > end)
            return null;
        
        int val = postorder[postOrderIndex--];              // from last value
        int inorderIndex = map.get(val);
        
        TreeNode root = new TreeNode(val);
        root.right = buildTree(inorderIndex+1, end);        // first right then left (bcos we traverse postOrder from last)
        root.left = buildTree(start, inorderIndex-1);
        
        return root;
    }
    
    public void formInorderMap()
    {
        for(int i=0; i<inorder.length; i++)
            map.put(inorder[i], i);
    }
}