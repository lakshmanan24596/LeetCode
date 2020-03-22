/*
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:
Given 1->2->3->4, reorder it to 1->4->2->3.

Example 2:
Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
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
    public void reorderList(ListNode head) 
    {
        // 1) n, n --> use stack for second half
        // 2) n, 1 --> reverse the second half
        
        if(head == null || head.next == null)
            return;
        
        ListNode mid = findMid(head);
        ListNode midPlusOne = mid.next;
        mid.next = null;
        
        midPlusOne = reverse(midPlusOne);
        
        head = mergeAlternatively(head, midPlusOne);        // main logic.. merge head and midPlusONe
    }
    
    public ListNode mergeAlternatively(ListNode n1, ListNode n2)
    {
        ListNode head = n1;
        ListNode futureN1, futureN2;
        boolean flag = true;
        
        while(n1 != null || n2 != null)
        {
            if(flag)
            {
                futureN1 = n1.next;
                n1.next = n2;                   // change link
                n1 = futureN1;
                flag = !flag;
            }
            else             
            {
                futureN2 = n2.next;
                n2.next = n1;                   // change link
                n2 = futureN2;
                flag = !flag;
            }
        }
        return head;
    }
    
    public ListNode findMid(ListNode head)
    {
        if(head == null || head.next == null)
            return head;
        
        ListNode sp = head, fp = head;        
        while(fp.next != null && fp.next.next != null)
        {
            sp = sp.next;
            fp = fp.next.next;
        }        
        return sp;
    }
    
    public ListNode reverse(ListNode head)
    {
        if(head == null || head.next == null)
            return head;
        
        ListNode curr = head,
                 prev = null,
                 future;
        
        while(curr != null)
        {
            future = curr.next;
            curr.next = prev;
            prev = curr;
            curr = future;
        }
        return prev;
    }
}