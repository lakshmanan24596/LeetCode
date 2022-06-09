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
In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.

For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. 
These are the only nodes with twins for n = 4.
The twin sum is defined as the sum of a node and its twin.

Given the head of a linked list with even length, return the maximum twin sum of the linked list.

Example 1:
Input: head = [5,4,2,1]
Output: 6
Explanation:
Nodes 0 and 1 are the twins of nodes 3 and 2, respectively. All have twin sum = 6.
There are no other nodes with twins in the linked list.
Thus, the maximum twin sum of the linked list is 6. 

Example 2:
Input: head = [4,2,2,3]
Output: 7
Explanation:
The nodes with twins present in this linked list are:
- Node 0 is the twin of node 3 having a twin sum of 4 + 3 = 7.
- Node 1 is the twin of node 2 having a twin sum of 2 + 2 = 4.
Thus, the maximum twin sum of the linked list is max(7, 4) = 7. 

Example 3:
Input: head = [1,100000]
Output: 100001
Explanation:
There is only one node with a twin in the linked list having twin sum of 1 + 100000 = 100001. 

Constraints:
The number of nodes in the list is an even integer in the range [2, 105].
1 <= Node.val <= 105
*/


/*
    1) stack:
        time: n
        space: n/2
        
    2) reverse
        time: n
        space: 1
*/
class Solution {
    public int pairSum(ListNode head) {
        int maxSum = 0, currSum;
        int count = getCount(head);                             // time: n
        ListNode[] newHeads = reverse(head, count / 2);         // time: n/2
        ListNode head1 = newHeads[0];
        ListNode head2 = newHeads[1];
        
        while (head1 != null) {                                 // time: n
            currSum = head1.val + head2.val;
            maxSum = Math.max(maxSum, currSum);
            head1 = head1.next;
            head2 = head2.next;
        }
        return maxSum;
    }
    
    public int getCount(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }
    
    public ListNode[] reverse(ListNode head, int end) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode future;
        
        while (curr != null && end-- > 0) {
            future = curr.next;
            curr.next = prev;
            prev = curr;
            curr = future;
        }
        return new ListNode[] {prev, curr};
    }
}
