// Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

// Example:
// Input:
// [
//   1->4->5,
//   1->3->4,
//   2->6
// ]
// Output: 1->1->2->3->4->4->5->6


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
    public ListNode mergeKLists(ListNode[] lists)   // nk*log(k) times
    {            
        int k = lists.length, i, j = k-1; 
        
        if(k == 0)
            return null;
        
        while(j != 0)                               // log(k) times
        {  
            i=0;
            while(i<j)                              // nk times
            {     
                lists[i] = mergeTwoList(lists[i], lists[j]);
                i++;
                j--;
            }
        }
        return lists[0];
    }
    
    public ListNode mergeTwoList(ListNode l1, ListNode l2)
    {       
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;
        
        if(l1.val <= l2.val)
        {
            l1.next = mergeTwoList(l1.next, l2);
            return l1;
        }
        else
        {
            l2.next = mergeTwoList(l1, l2.next);
            return l2;
        }
    }
}