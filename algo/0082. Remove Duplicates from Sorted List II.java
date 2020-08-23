/*
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
Return the linked list sorted as well.

Example 1:
Input: 1->2->3->3->4->4->5
Output: 1->2->5

Example 2:
Input: 1->1->1->2->3
Output: 2->3
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
class Solution 
{
    public ListNode deleteDuplicates(ListNode head) 
    {  
        if(head == null || head.next == null) {
            return head;
        }

        head = checkDuplicate(head);
        if(head == null) {      //if  all elements are duplicates
            return null;
        }
        
        ListNode prev = head;
        ListNode curr = head.next;
        
        while(curr != null)
        {
            curr = checkDuplicate(curr);      
            prev.next = curr;   // change link

            prev = curr;        // change prev and curr for next iteration
            if(curr != null) {
                curr = curr.next;
            }
        }
        return head;
    }
    
    public ListNode checkDuplicate(ListNode curr)
    {
        boolean containDuplicate = false;
        if(curr.next != null && curr.val == curr.next.val) {
            containDuplicate = true;
        }
        while(curr.next != null && curr.val == curr.next.val) {
            curr = curr.next;
        }
        if(containDuplicate) {
            curr = curr.next;
        }
        
        if(curr != null && curr.next != null && curr.val == curr.next.val) {
            curr = checkDuplicate(curr);    // ex: 1,2,3,3,4,4,5 --> here 3,3,4,4 is a continuous duplicate. So do recursion.
        }
        return curr;
    }
}


/*
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null) return null;
        ListNode FakeHead=new ListNode(0);
        FakeHead.next=head;
        ListNode pre=FakeHead;
        ListNode cur=head;
        while(cur!=null){
            while(cur.next!=null&&cur.val==cur.next.val){
                cur=cur.next;
            }
            if(pre.next==cur){
                pre=pre.next;
            }
            else{
                pre.next=cur.next;
            }
            cur=cur.next;
        }
        return FakeHead.next;
    }
 */   