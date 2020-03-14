/*
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
You should preserve the original relative order of the nodes in each of the two partitions.

Example:
Input: head = 1->4->3->2->5->2, x = 3
Output: 1->2->2->4->3->5
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
    public ListNode partition(ListNode head, int x) 
    {   
        ListNode h1 = null, p1 = null;  // for value < x 
        ListNode h2 = null, p2 = null;  // for value >= x 
        
        while(head != null)
        {
            if(head.val < x)
            {
                if(p1 == null)
                {
                    p1 = head;
                    h1 = head;
                }    
                else
                {   
                    p1.next = head;
                    p1 = p1.next;
                }    
            }
            else
            {
                if(p2 == null)
                {   
                    p2 = head;
                    h2 = head;   
                }    
                else
                {   
                    p2.next = head;
                    p2 = p2.next;
                }  
            }
            head = head.next;
        }
        
        if(p1 != null)
            p1.next = h2;
        
        if(p2 != null)
            p2.next = null;
             
        return (h1 != null) ? h1 : h2;
    }
}