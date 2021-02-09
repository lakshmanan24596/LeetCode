/*
You are given the head of a linked list, and an integer k.
Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).

Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [1,4,3,2,5]

Example 2:
Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
Output: [7,9,6,6,8,7,3,0,9,5]

Example 3:
Input: head = [1], k = 1
Output: [1]

Example 4:
Input: head = [1,2], k = 1
Output: [2,1]

Example 5:
Input: head = [1,2,3], k = 2
Output: [1,2,3]

Constraints:
The number of nodes in the list is n.
1 <= k <= n <= 105
0 <= Node.val <= 100
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


/*
// swap actual node (instead of simply changing the value)
// time: 2n

class Solution {
    public ListNode swapNodes(ListNode head, int k) {
        if (head == null || k < 1) {
            return head;
        }
        int len = findLength(head);     // step1: find length
        if (k > len / 2) {
            k = (len - k) + 1;
        }
        ListNode prev1 = null, curr1 = head;
        ListNode prev2, curr2, future2;
        int count = 1;
        
        while (count != k) {            // step2: find prev1, curr1
            prev1 = curr1;
            curr1 = curr1.next;
            count++;
        }
        
        k = (len - k) + 1;              // step3: find prev2, curr2, future2
        prev2 = prev1;
        curr2 = curr1;
        while (count != k) {            
            prev2 = curr2;
            curr2 = curr2.next;
            count++;
        }
        future2 = curr2.next;
        
        if (curr1 != curr2) {           // step4: swap curr1, curr2
            if (prev1 != null) {
                prev1.next = curr2;
            } else {
                head = curr2;
            }
            if (curr1.next == curr2) {
                curr2.next = curr1;
            } else {
                curr2.next = curr1.next;
                prev2.next = curr1;
            }
            curr1.next = future2;
        }
        return head;
    }
    
    public int findLength(ListNode head) {
        int len = 0;
        ListNode curr = head;
        while (curr != null) {
            curr = curr.next;
            len++;
        }
        return len;
    }
}
*/


// swap node value alone
// time: n (one pass algo)
class Solution {
    public ListNode swapNodes(ListNode head, int k) {
        ListNode first = head;                          // step1: find first
        for(int i = 1; i < k; i++) {
            first = first.next;
        }
            
        ListNode second = head, curr = first.next;      // step2: find second
        while(curr != null){
            curr = curr.next;
            second = second.next;
        }
        
        int temp = second.val;                          // step3: swap first and second node value
        second.val = first.val;
        first.val = temp;
        return head;
    }
}