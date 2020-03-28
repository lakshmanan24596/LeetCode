/*
Remove all elements from a linked list of integers that have value val.

Example:
Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5
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
    public ListNode removeElements(ListNode head, int val) 
    {
        if(head == null) 
            return null;
        
        while(head != null && head.val == val)
            head = head.next;                           // remove duplicates in front
        
        if(head == null)
            return null;                                // if all elements are duplicate values, then return null
        
        ListNode prev = head;
        ListNode curr = head.next;
        
        while(curr != null)
        {
            if(curr.val == val)
            {
                do
                {
                    curr = curr.next;
                }
                while(curr != null && curr.val == val);
                
                prev.next = curr;                       // remove duplicates
            }
                     
            prev = curr;                                // for next iteration, change prev and curr
            curr = (curr != null) ? curr.next : null;
        }

        return head;
    }
}