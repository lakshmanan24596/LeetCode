/*
Sort a linked list in O(n log n) time using constant space complexity.

Example 1:
Input: 4->2->1->3
Output: 1->2->3->4

Example 2:
Input: -1->5->3->4->0
Output: -1->0->3->4->5
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
    public ListNode sortList(ListNode head)
    {
        if(head == null)
            return null;
        return sortListUtil(head);
    }
    
    public ListNode sortListUtil(ListNode head) 
    {   
        // Time = 2n * log n --> n*logn
        // because log n base 2 levels are there and in each level 2n work is done
        
        if(head.next == null)                           // return when only 1 element is present in the list
            return head;
        
        ListNode mid = findMid(head);
        ListNode midPlusOne = mid.next;
        mid.next = null;                                // split into 2 list
        
        ListNode left = sortList(head);                
        ListNode right = sortList(midPlusOne);
        return mergeTwoSortedList(left, right);         // post-order
    }
    
    public ListNode findMid(ListNode head)
    {
        if(head == null)
            return head;
        
        ListNode sp = head, fp = head;
        while(fp.next != null && fp.next.next != null)  // because we need (floor of mid) for even size list
        {
            sp = sp.next;
            fp = fp.next.next;
        }
        return sp;
    }
    
    public ListNode mergeTwoSortedList(ListNode n1, ListNode n2)
    {
        if(n1 == null)
            return n2;
        
        if(n2 == null)
            return n1;
        
        if(n1.val < n2.val)
        {
            n1.next = mergeTwoSortedList(n1.next, n2);
            return n1;
        }
        else
        {
            n2.next = mergeTwoSortedList(n1, n2.next);
            return n2;
        }
    }
}