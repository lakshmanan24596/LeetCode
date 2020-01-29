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