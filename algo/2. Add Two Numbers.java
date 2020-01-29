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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) 
    {
        ListNode head = null, curr = null;
        int sum, carry = 0;
       
        while(l1 != null || l2 != null)
        {
            sum = ((l1 != null) ? l1.val : 0) + ((l2 != null) ? l2.val : 0) + carry;
            if(sum > 9)
            {
                carry = 1;
                sum = sum-10;
            }
            else
                carry = 0;
            
            ListNode newNode = new ListNode(sum);
            if(head == null)
            {
                head = newNode;
                curr = newNode;
            }
            else
            {
                curr.next = newNode;
                curr = curr.next;
            }
            
            if(l1 != null)
                l1 = l1.next;          
            if(l2 != null)
                l2 = l2.next;
        }
        
        if(carry == 1)
            curr.next = new ListNode(1);

        return head;
    }
}