// Given a linked list, remove the n-th node from the end of list and return its head.

// Example:
// Given linked list: 1->2->3->4->5, and n = 2.
// After removing the second node from the end, the linked list becomes 1->2->3->5.

// Note:
// Given n will always be valid.

// Follow up:
// Could you do this in one pass?


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
    public ListNode removeNthFromEnd(ListNode head, int n) 
    {
        ListNode p1 = head, p2 = head;
        for(int i=1; i<=n; i++)
        {
            if(p2 == null)                  // invalid input
                return head;
            p2 = p2.next;
        }
        
        if(p2 == null)                      // corner case (delete head itself)
        {
           ListNode newHead = head.next;
           head = null;
           return newHead; 
        }
        
        while(p2.next != null)
        {
            p1 = p1.next;
            p2 = p2.next;
        }
        
        ListNode toBeDeleted = p1.next;
        p1.next = p1.next.next;             // removeNthFromEnd
        toBeDeleted = null;
        return head;
    }
}