/*
Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until there are no such sequences.
After doing so, return the head of the final linked list.  You may return any such answer.
(Note that in the examples below, all sequences are serializations of ListNode objects.)

Example 1:
Input: head = [1,2,-3,3,1]
Output: [3,1]
Note: The answer [1,2,1] would also be accepted.

Example 2:
Input: head = [1,2,3,-3,4]
Output: [1,2,4]

Example 3:
Input: head = [1,2,3,-3,-2]
Output: [1]

Constraints:
The given linked list will contain between 1 and 1000 nodes.
Each node in the linked list has -1000 <= node.val <= 1000.
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
    Time: n, Space: n
    This solution fails for the testcase: [1,3,2,-3,-2,5,5,-5,1]
    Output: [1,5,5,-5,1] and Expected: [1,5,1]
    Reason: When we delete the nodes, we didnt't clean up the map references. To fix that, we need another loop which increase time
*/
/*
class Solution 
{
    public ListNode removeZeroSumSublists(ListNode head) 
    {
        Map<Integer, ListNode> sumMap = new HashMap<Integer, ListNode>();
        int preSum = 0;
        ListNode curr = head;
        
        while(curr != null)
        {
            preSum += curr.val;
            if(preSum == 0) {
                head = curr.next;
            }
            else if(sumMap.containsKey(preSum))  {
                sumMap.get(preSum).next = curr.next;    // main logic
            }
            else {
                sumMap.put(preSum, curr);
            }
            curr = curr.next;
        }
        return head;
    }
}
*/

// 2 pass algo --> Time: 2n, Space: n
class Solution 
{
    public ListNode removeZeroSumSublists(ListNode head) 
    {
        Map<Integer, ListNode> sumMap = new HashMap<Integer, ListNode>();
        int preSum = 0;
        ListNode curr = head;
        
        while(curr != null)
        {
            preSum += curr.val;
            sumMap.put(preSum, curr);                       // populate the map
            curr = curr.next;
        }
        
        if(sumMap.containsKey(0)) {                         // handling for preSum 0
            head = sumMap.get(0).next;
        }
        preSum = 0;
        curr = head;
        
        while(curr != null)
        {
            preSum += curr.val;
            curr.next = sumMap.get(preSum).next;            // main logic
            curr = curr.next;
        }
        return head;
    }
}
