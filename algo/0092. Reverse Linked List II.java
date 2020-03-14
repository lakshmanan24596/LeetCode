/*
Reverse a linked list from position m to n. Do it in one-pass.
Note: 1 ≤ m ≤ n ≤ length of list.

Example:
Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL
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
    public ListNode reverseBetween(ListNode head, int m, int n) 
    {    
        if(m == 0)
            return head;
                
        // initialize mMinusOne
        int count = 0;
        ListNode mMinusOne;
        if(m == 1)
        	mMinusOne = null;
        else
        {
        	mMinusOne = head;
            count++;
        }    
                
        // move mMinusOne to m-1 position
        for(int i = 2; i < m; i++)
        {
            if(mMinusOne.next == null)
                return head;
            mMinusOne = mMinusOne.next;
            count++;
        }
                
        // initialize mNode
        ListNode mNode;
        if(mMinusOne != null)
        	mNode = mMinusOne.next;
        else
        	mNode = head;
                
        // reverse
        ListNode prev = null;     
        ListNode curr = mNode;
        ListNode future = curr.next;
        while(curr != null && count < n)
        {
            future = curr.next;
            curr.next = prev;   // reverse
            prev = curr;
            curr = future;
            count++;
        }
        
        // change links
        if(mMinusOne != null)
        	mMinusOne.next = prev;  // 1 -> 4
        else
            head = prev;
        
        mNode.next = curr;  // 2 -> 5
        
        return head;
    }
}