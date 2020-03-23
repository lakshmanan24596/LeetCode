/*
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:
Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1

Example 2:
Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
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
    int kthSmallestVal = Integer.MAX_VALUE;
    int count = 0;
    
    // Space : height + k.. we can do it in O(1) space using morris in-order traversal
    
//    Follow up:
//          1) DLL ==> Tree has leftNode, rightNode, dllNode
//          2) MaxHeap ==> of size K
    
    public int kthSmallest(TreeNode root, int k) 
    {
        inOrder(root, k);
        return kthSmallestVal;
    }
        
    public boolean inOrder(TreeNode root, int k)    // Time: O(height + k)
    {
        if(root == null)
            return false;
        
        if(inOrder(root.left, k))
            return true;
        
        count++;
        if(count == k)
        {
            kthSmallestVal = root.val;
            return true;                            // stop traversal if we found the kth smallest
        }    
            
        return inOrder(root.right, k);
    }
}