/*
You are given all the nodes of an N-ary tree as an array of Node objects, where each node has a unique value.
Return the root of the N-ary tree.

Custom testing:
An N-ary tree can be serialized as represented in its level order traversal where each group of children is separated by the null value (see examples).
For example, the above tree is serialized as [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14].

The testing will be done in the following way:
The input data should be provided as a serialization of the tree.
The driver code will construct the tree from the serialized input data and put each Node object into an array in an arbitrary order.
The driver code will pass the array to findRoot, and your function should find and return the root Node object in the array.
The driver code will take the returned Node object and serialize it. If the serialized value and the input data are the same, the test passes.

Example 1:
Input: tree = [1,null,3,2,4,null,5,6]
Output: [1,null,3,2,4,null,5,6]
Explanation: The tree from the input data is shown above.
The driver code creates the tree and gives findRoot the Node objects in an arbitrary order.
For example, the passed array could be [Node(5),Node(4),Node(3),Node(6),Node(2),Node(1)] or [Node(2),Node(6),Node(1),Node(3),Node(5),Node(4)].
The findRoot function should return the root Node(1), and the driver code will serialize it and compare with the input data.
The input data and serialized Node(1) are the same, so the test passes.

Example 2:
Input: tree = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]

Constraints:
The total number of nodes is between [1, 5 * 104].
Each node has a unique value.

Follow up:
Could you solve this problem in constant space complexity with a linear time algorithm?
*/




/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    
    public Node() {
        children = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        children = new ArrayList<Node>();
    }
    
    public Node(int _val,ArrayList<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/



/*
    Logic: hashset
    root node alone will have in-degree as 0
    
    time: 2n + n
    space: n
*/
/*
class Solution {
    public Node findRoot(List<Node> tree) {
        Set<Node> childNodes = new HashSet<Node>();
        
        for (Node curr : tree) {
            for (Node child : curr.children) {
                childNodes.add(child);              // ex-1: add nodes 3,2,4,5,6
            }
        }
        for (Node curr : tree) {
            if (!childNodes.contains(curr)) {       // ex-1: node 1 is not present in set, so return it
                return curr;
            }
        }
        return null;
    }
}
*/


/*
    Logic: You Only Look Once
    all nodes will be visited twice, expect the root node
    
    Implementation: XOR or addition/deduction
    time: 2n + n
    space: 1
    
    ex-1:
    nodeValSum = +3, -5, -6, +2, +4, +5, +6, +1, -3, -2, -4
    addition all above values, we get 1
*/
class Solution {
    public Node findRoot(List<Node> tree) {
        int nodeValSum = 0;
        
        for (Node curr : tree) {
            nodeValSum += curr.val;                     // addition
            for (Node child : curr.children) {
                nodeValSum -= child.val;                // deduction
            }
        }
        for (Node curr : tree) {
            if (curr.val == nodeValSum) {               // main logic
                return curr;
            }
        }
        return null;
    }
}