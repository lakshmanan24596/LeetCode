/*
Given a sorted linked list, delete all duplicates such that each element appear only once.

Example 1:
Input: 1->1->2
Output: 1->2

Example 2:
Input: 1->1->2->3->3
Output: 1->2->3
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
    public ListNode deleteDuplicates(ListNode head) 
    {
        if(head == null || head.next == null)
            return head;
        
        ListNode prev = head;
        ListNode curr = head.next;      
        
        while(curr != null)
        {
            while(curr.val == prev.val)
            {
                curr = curr.next; 
                if(curr == null)    // reached last node
                {
                    prev.next = curr;
                    return head;
                }
            }
            prev.next = curr;       // change links
            
            prev = curr;
            curr = curr.next;
        }
        
        return head;
    }
}