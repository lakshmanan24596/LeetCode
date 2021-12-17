/*
Given a linked list, swap every two adjacent nodes and return its head. 
You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)

Example 1:
Input: head = [1,2,3,4]
Output: [2,1,4,3]

Example 2:
Input: head = []
Output: []

Example 3:
Input: head = [1]
Output: [1]

Constraints:
The number of nodes in the list is in the range [0, 100].
0 <= Node.val <= 100
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
    public ListNode swapPairs(ListNode p1) 
    {   
        if(p1 == null || p1.next == null) {
            return p1;
        }
        ListNode p2 = p1.next;
        ListNode p3 = p1.next.next;
            
        p2.next = p1;
        p1.next = swapPairs(p3);
        return p2;
    }
}