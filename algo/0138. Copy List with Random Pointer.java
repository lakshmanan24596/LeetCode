/*
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
Return a deep copy of the list.
The Linked List is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) where random pointer points to, or null if it does not point to any node.

Example 1:
Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]

Example 2:
Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]

Example 3:
Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]

Example 4:
Input: head = []
Output: []
Explanation: Given linked list is empty (null pointer), so return null.

Constraints:
-10000 <= Node.val <= 10000
Node.random is null or pointing to a node in the linked list.
Number of Nodes will not exceed 1000.

*/

/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution 
{
    // time = 3N and space = 1
    public Node copyRandomList(Node head)
    {
        if(head == null)
            return null;
        Node curr = head, future, newNode;
        
        // step 1   : create extra link
        while(curr != null)
        {
            future = curr.next;
            newNode = new Node(curr.val);
            curr.next = newNode;
            newNode.next = future;
            curr = future;
        }
        
        // step2    : form random pointer for cloned node
        curr = head;
        while(curr != null)
        {
        	future = curr.next.next;
            curr.next.random = (curr.random != null) ? curr.random.next : null;        // main logic
            curr = future;
        }
            
        // step 3   : form next pointer for cloned node and remove extra link 
        curr = head;
        Node newCurr = head.next;
        Node newHead = curr.next;  
        
        while(newCurr.next != null)
        {
            curr.next = curr.next.next;
            newCurr.next = newCurr.next.next;
            curr = curr.next;
            newCurr = newCurr.next;
        }
        curr.next = null;
        newCurr.next = null;
        
        return newHead;
    }
}

// time = 2N and space = N
// class Solution 
// {    
//     public Node copyRandomList(Node head) 
//     {
//         if(head == null)
//             return null;
        
//         Map<Node, Node> map = new HashMap<Node, Node>(); // key = origCurrNode, value = cloneCurrNode
//         Node origCurr = head;
//         Node cloneCurr;
        
//         while(origCurr != null)
//         {
//             cloneCurr = new Node(origCurr.val);
//             map.put(origCurr, cloneCurr);
//             origCurr = origCurr.next;
//         }
        
//         origCurr = head;
//         while(origCurr != null)
//         {
//             cloneCurr = map.get(origCurr);
//             cloneCurr.next = map.get(origCurr.next);        // we should get it from map
//             cloneCurr.random = map.get(origCurr.random);   
//             origCurr = origCurr.next;
//         }
        
//         return map.get(head);
//     }
// }