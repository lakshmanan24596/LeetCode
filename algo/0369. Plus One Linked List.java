/*
Given a non-negative integer represented as a linked list of digits, plus one to the integer.
The digits are stored such that the most significant digit is at the head of the list.

Example 1:
Input: head = [1,2,3]
Output: [1,2,4]

Example 2:
Input: head = [0]
Output: [1]

Constraints:
The number of nodes in the linked list is in the range [1, 100].
0 <= Node.val <= 9
The number represented by the linked list does not contain leading zeros except for the zero itself. 
*/



/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode plusOne(ListNode head) {                    // time: 2n, space: 1
        if (head == null) {
            return null;
        }
        ListNode prev = head;
        ListNode curr = head;
        
        while (true) {
            if (curr.next == null) {
                if (curr.val != 9) {                            // ex: 123 --> 124
                    curr.val += 1;
                } else if (prev.val != 9) {                     // ex: 299 --> 300
                    prev.val += 1;
                    prev = prev.next;
                    setToZero(prev);
                } else {    // prev.val == 9                    // ex: 999 --> 1000
                    ListNode newHead = new ListNode(1);
                    newHead.next = head;
                    head = newHead;
                    setToZero(prev);
                }
                break;
            }
            
            if (curr.next.val != 9) {
                prev = curr.next;
            }
            curr = curr.next;
        }
        return head;
    }
    
    public void setToZero(ListNode node) {
        while (node != null) {
            node.val = 0;
            node = node.next;
        }
    }
}