/*
Reverse a singly linked list.

Example:
Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL

Follow up:
A linked list can be reversed either iteratively or recursively. Could you implement both?
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution 
{
//     public ListNode reverseList(ListNode node) 
//     {
//         ListNode curr = node,
//                  prev = null,
//                  future;
        
//         while(curr != null)
//         {
//             future = curr.next;
//             curr.next = prev;
//             prev = curr;
//             curr = future;
//         }
//         return prev;
//     }   
    
//     Using recursion
    
    ListNode newHead;
    public ListNode reverseList(ListNode node) 
    {
        if(node == null)
            return null;
        
        recur(node);
        return newHead;
    }
    
    public void recur(ListNode node)
    {
        if(node.next == null)
        {
            newHead = node;
            return;
        }
        
        recur(node.next);
        
        node.next.next = node;
        node.next = null;
    }
}