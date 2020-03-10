/*
Given a linked list, rotate the list to the right by k places, where k is non-negative.

Example 1:
Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL

Example 2:
Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL
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
    public ListNode rotateRight(ListNode head, int k) 
    {
        if(k == 0 || head == null)
            return head;
        
        ListNode fastPtr = head, slowPtr = head;
        int count = 0; 
        int listLength;
        
        for(count = 0; count < k; count++)
        {
            if(fastPtr.next != null)
            {
                fastPtr = fastPtr.next;
            }
            else  // handling for k >= listLength... (reset --> count, k, fastPtr)
            {
                listLength = count + 1;
                k = k % listLength;
                if(k == 0)
                    return head;
                count = -1;
                fastPtr = head;
            }
        }        
        // now slowPtr = 1 and fastPtr = 3
        
        while(fastPtr.next != null)
        {
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next;
        }
        // now slowPtr = 3 and fastPtr = 5  (both have moved 2 steps)
        
        // rotate
        fastPtr.next = head;    // 5.next = 1
        head = slowPtr.next;    // head = 4
        slowPtr.next = null;    // 3.next = null;
        
        return head;
    }
}