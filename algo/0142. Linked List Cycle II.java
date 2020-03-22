/*
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
Note: Do not modify the linked list.

Example 1:
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.

Example 2:
Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.

Example 3:
Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.

Follow-up:
Can you solve it without using extra space?
*/

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution 
{
    // n space : using visited set.. to remove loop, just set prev.next = null;
    // 1 space : (m + n*x + k) = 2*(m + n*y + k) =====> m+k = (x-2y)*n =====> m+k is a multiple of n
    
    public ListNode detectCycle(ListNode head) 
    {
        ListNode sp = head, fp = head;
        boolean isCycleExist = false;
        
        while(fp != null && fp.next != null)
        {
            sp = sp.next;
            fp = fp.next.next;
            if(sp == fp)
            {
                isCycleExist = true;
                break;
            }
        }
        
        if(!isCycleExist)
            return null;
        
        sp = head;        
        while(true)
        {
            if(sp == fp)
                return sp;
            sp = sp.next;
            fp = fp.next;
        }
    }
}