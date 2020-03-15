/*
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:
Given the sorted array: [-10,-3,0,5,9],
One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
      0
     / \
   -3   9
   /   /
 -10  5
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
    int[] nums;
    int index = 0;
    
    public TreeNode sortedArrayToBST(int[] nums)  
    {
        // 1) O(n*logn) --> find mid and set it as root.. recur left and right
        // 2) O(n) --> bottom up construction of bst.. using count of nodes.. recur n/2 for left, right child 
        
        this.nums = nums;
        return sortedListToBST(nums.length);
    }
    
    public TreeNode sortedListToBST(int n)  // inorder traversal
    {
        if(n <= 0)
            return null;
        
        TreeNode left = sortedListToBST(n/2);
        TreeNode root = new TreeNode(nums[index++]);
        root.left = left;    
        root.right = sortedListToBST((n&1)==0 ? n/2 -1 : n/2);      
        return root;
    }
}