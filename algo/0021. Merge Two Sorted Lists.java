// Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

// Example:
// Input: 1->2->4, 1->3->4
// Output: 1->1->2->3->4->4


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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) 
    {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        
        ListNode head, curr;
        if(l1.val <= l2.val)
        {
            head = curr = l1;
            l1 = l1.next;
        }
        else
        {
            head = curr = l2;
            l2 = l2.next;
        }
        
        while(l1 != null && l2 != null)
        {
            if(l1.val <= l2.val)
            {
                curr.next = l1;
                l1 = l1.next;
            }
            else
            {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        
        if(l1 == null && l2 != null)
            curr.next = l2;

        else if(l1 != null && l2 == null)
            curr.next = l1;

        return head;
    }
}