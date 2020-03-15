/*
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:
Given the sorted linked list: [-10,-3,0,5,9],
One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
      0
     / \
   -3   9
   /   /
 -10  5
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
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
    ListNode head;
    public TreeNode sortedListToBST(ListNode head) 
    {
        // 1) O(n*logn) --> find mid and set it as root.. recur left and right
        // 2) O(n) --> bottom up construction of bst.. using count of nodes.. recur n/2 for left, right child 
        
        this.head = head;
        return sortedListToBST(count());
    }
    
    public int count()
    {
        ListNode curr = head;
        int count = 0;
        while(curr != null)
        {
            curr = curr.next;
            count++;
        }
        return count;
    }
    
    public TreeNode sortedListToBST(int n)  // inorder traversal
    {
        if(n <= 0)
            return null;
        
        TreeNode left = sortedListToBST(n/2);
        TreeNode root = new TreeNode(head.val);
        head = head.next;
        root.left = left;    
        root.right = sortedListToBST((n&1)==0 ? n/2 -1 : n/2);
        return root;
    }
}