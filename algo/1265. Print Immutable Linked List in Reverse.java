/*
You are given an immutable linked list, print out all values of each node in reverse with the help of the following interface:
ImmutableListNode: An interface of immutable linked list, you are given the head of the list.

You need to use the following functions to access the linked list (you can't access the ImmutableListNode directly):
ImmutableListNode.printValue(): Print value of the current node.
ImmutableListNode.getNext(): Return the next node.

The input is only given to initialize the linked list internally. 
You must solve this problem without modifying the linked list. 
In other words, you must operate the linked list using only the mentioned APIs.

Example 1:
Input: head = [1,2,3,4]
Output: [4,3,2,1]

Example 2:
Input: head = [0,-4,-1,3,-5]
Output: [-5,3,-1,-4,0]

Example 3:
Input: head = [-2,0,6,4,4,-6]
Output: [-6,4,4,6,0,-2]

Constraints:
The length of the linked list is between [1, 1000].
The value of each node in the linked list is between [-1000, 1000].

Follow up:
Could you solve this problem in:
Constant space complexity?
Linear time complexity and less than linear space complexity?
*/



/**
 * // This is the ImmutableListNode's API interface.
 * // You should not implement it, or speculate about its implementation.
 * interface ImmutableListNode {
 *     public void printValue(); // print the value of this node.
 *     public ImmutableListNode getNext(); // return the next node.
 * };
 */


/*
    1) recursive                      --> time: n, space: n
    2) iterative using stack          --> time: n, space: n
    3) iterative using count of nodes --> time: n^2, space: 1
    4) optimal algo                   --> time: n, space: sqrt(n)
       
    Logic:
        https://leetcode.com/problems/print-immutable-linked-list-in-reverse/discuss/436647/Jave-Solution-including-Follow-Up
        "split the list to sqrt(n) equal-size small lists"
        print the parts one after the other in reverse order using a naive algo
        this reduces the space complexity
        
        why sqrt(n)?
            let n = 1000, sqrt(n) = 32
            basically we are dividing the list into --> "32 parts, with each having 32 elements" (ie; 32 * 32 = 1000)
            headReferences stack will have size as 32
            calling naive algo will use a stack of size 32
            now total space = 32 + 32 = 64
                            = srqt(n) + sqrt(n) = 2sqrt(n)
                            
        Why log(n) is not used?
            let n = 1000, log2(n) = 10
            basically we are dividing the list into --> "10 parts, with each having 100 elements" (ie; 10 * 100 = 1000)
            headReferences stack will have size as 10
            calling naive algo will use a stack of size 100
            now total space = 10 + 100 = 110
                            = space is increased in this case
*/

/*
// approach 1: time: n, space: n
class Solution {
    public void printLinkedListInReverse(ImmutableListNode head) {
        if (head == null) {
            return;
        }
        printLinkedListInReverse(head.getNext());
        head.printValue();
    }
}
*/

// approach 4: time: O(n), space: O(sqrt(n))
class Solution {
    public void printLinkedListInReverse(ImmutableListNode head) {
        int size = getListSize(head);                                                       // O(n)
        int sqrtSize = ((int) Math.sqrt(size)) + 1;
        Stack<ImmutableListNode> headReferences = getHeadReferences(head, sqrtSize);        // O(n)
        print(headReferences);                                                              // O(n)
    }
    
    public int getListSize(ImmutableListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.getNext();
        }
        return size;
    }
    
    public Stack<ImmutableListNode> getHeadReferences(ImmutableListNode head, int sqrtSize) {
        Stack<ImmutableListNode> headReferences = new Stack<ImmutableListNode>();
        headReferences.push(head);
        int i = 0;
        while (head != null) {
            head = head.getNext();
            i++;
            if ((i % sqrtSize) == 0) {
                headReferences.push(head);                  // main logic
            }
        }
        return headReferences;
    }
    
    public void print(Stack<ImmutableListNode> headReferences) {
        ImmutableListNode currHead;
        ImmutableListNode endNode = null;
        
        while (!headReferences.isEmpty()) {
            currHead = headReferences.pop();
            printListUsingNaiveAlgo(currHead, endNode);      // main logic
            endNode = currHead;
        }
    }
    
    public void printListUsingNaiveAlgo(ImmutableListNode head, ImmutableListNode endNode) {    // naive algo
        if (head == endNode) {
            return;
        }
        printListUsingNaiveAlgo(head.getNext(), endNode);
        head.printValue();
    }
}