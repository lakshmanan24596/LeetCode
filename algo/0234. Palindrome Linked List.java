/*
Given a singly linked list, determine if it is a palindrome.

Example 1:
Input: 1->2
Output: false

Example 2:
Input: 1->2->2->1
Output: true
Follow up:
Could you do it in O(n) time and O(1) space?
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
    public boolean isPalindrome(ListNode head) 
    {
        // 1) n space --> use stack for 2nd half
        // 2) 1 space --> reverse 2nd half
        
        if(head == null || head.next == null)
            return true;
        
        ListNode sp = head,
                 fp = head;
        
        while(fp.next != null && fp.next.next != null)
        {
            sp = sp.next;
            fp = fp.next.next;
        }
        
        ListNode l1 = head;
        ListNode l2 = reverse(sp.next);     // reverse 2nd half
        
        while(l2 != null)
        {
            if(l1.val != l2.val) 
                return false;
            l1 = l1.next;
            l2 = l2.next;
        }
        return true;
    }
    
    public ListNode reverse(ListNode head)
    {
        ListNode prev = null,
                 curr = head,
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