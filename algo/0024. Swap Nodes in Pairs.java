Given a linked list, swap every two adjacent nodes and return its head.

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example:
Given 1->2->3->4, you should return the list as 2->1->4->3.

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
    public ListNode swapPairs(ListNode p1) 
    {   
        if(p1 == null || p1.next == null) return p1;
        
        ListNode p2 = p1.next;
        ListNode p3 = p1.next.next;
            
        p2.next = p1;
        p1.next = swapPairs(p3);
        
        return p2;
    }
}