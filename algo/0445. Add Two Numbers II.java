/*
You are given two non-empty linked lists representing two non-negative integers. 
The most significant digit comes first and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:
Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
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
Different approches
    1) Iterate L1, L2 and form a long number. Add the long numbers. Form a new linkeslist using that long number.
    2) Reverse the given linkedlist
    3) N space --> Use two stacks
    4) 1 space --> https://leetcode.com/problems/add-two-numbers-ii/discuss/687339/Java-O(N)-solution-with-follow-up-question-no-recursion-no-stacks
        (a) Compute size of the fist list
        (b) Compute size of the second list
        (c) Create intermediate result  (insert front)
        (d) Normalize the intermediate result (split sum into sum and carry) (insert front)
*/
class Solution 
{
    public ListNode addTwoNumbers(ListNode l1, ListNode l2)     // Time: O(4n), Space: O(1)
    {
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }
        
        // step 1, 2
        int size1 = findSize(l1);
        int size2 = findSize(l2);
        int diff = size1 - size2;
        ListNode output = null;
        ListNode curr;       
        if(diff > 0) {
            do {
                curr = new ListNode(l1.val);
                curr.next = output;  // insert front
                output = curr;
                l1 = l1.next;
                diff--;
            } while(diff > 0);
        } 
        else if(diff < 0) {
            do {
                curr = new ListNode(l2.val);
                curr.next = output;  // insert front
                output = curr;
                l2 = l2.next;
                diff++;
            } while(diff < 0);
        }
        
        // step 3
        while(l1 != null || l2 != null)
        {
            curr = new ListNode(l1.val + l2.val);
            curr.next = output;     // insert front
            output = curr;
            l1 = l1.next;
            l2 = l2.next;
        }
        
        // step 4
        int carry = 0, sum;
        ListNode temp = output;
        output = null;
        while(temp != null)
        {
            sum = (temp.val + carry) % 10; 
            carry = (temp.val + carry) / 10;
            temp = temp.next;
            curr = new ListNode(sum);
            curr.next = output;     // insert front
            output = curr;
        }
        if(carry == 1) {
            curr = new ListNode(1);
            curr.next = output;     // insert front
            output = curr;
        }
        return output;
    }
    
    public int findSize(ListNode head)
    {
        int size = 0;
        ListNode curr = head;
        while(curr != null) {
            size++;
            curr = curr.next;
        }
        return size;
    }
}