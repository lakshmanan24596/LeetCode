/*
Given a binary tree root and a linked list with head as the first node. 
Return True if all the elements in the linked list starting from the head correspond to some downward path connected in the binary tree otherwise return False.
In this context downward path means a path that starts at some node and goes downwards.

Example 1:
Input: head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
Output: true
Explanation: Nodes in blue form a subpath in the binary Tree.  

Example 2:
Input: head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
Output: true

Example 3:
Input: head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
Output: false
Explanation: There is no path in the binary tree that contains all the elements of the linked list from head.
 
Constraints:
1 <= node.val <= 100 for each node in the linked list and binary tree.
The given linked list will contain between 1 and 100 nodes.
The given binary tree will contain between 1 and 2500 nodes.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
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
    List<Integer> givenList;
    List<Integer> currList;
    
    public boolean isSubPath(ListNode head, TreeNode root)  // Time: head.size() * root.size() and Space: head.size()
    {
        if(head == null) {
            return true;
        } 
        
        this.givenList = new ArrayList<Integer>();
        this.currList = new ArrayList<Integer>();
        
        while(head != null)     // convert linkedList to arrayList because we need to traverse from last index
        {
            givenList.add(head.val);
            head = head.next;
        }
        return isSubPath(root);
    }
    
    public boolean isSubPath(TreeNode root) 
    {
        if(root == null) {
            return false;
        }
        
        currList.add(root.val);
        
        boolean isFound = currList.size() >= givenList.size();
        if(isFound) {
            for(int i = givenList.size() - 1, j = currList.size() - 1; i >= 0; i--, j--) {  // main logic: traverse from last
                if(givenList.get(i) != currList.get(j)) {
                    isFound = false;
                    break;
                }
            }   
        }
        
        if(isFound || isSubPath(root.left) || isSubPath(root.right)) {
            return true;
        }
        
        currList.remove(currList.size() - 1);
        return false;
    } 
}
