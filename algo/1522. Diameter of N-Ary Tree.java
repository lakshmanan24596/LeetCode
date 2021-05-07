/*
Given a root of an N-ary tree, you need to compute the length of the diameter of the tree.
The diameter of an N-ary tree is the length of the longest path between any two nodes in the tree. 
This path may or may not pass through the root.
(Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value.)

Example 1:
Input: root = [1,null,3,2,4,null,5,6]
Output: 3
Explanation: Diameter is shown in red color.

Example 2:
Input: root = [1,null,2,null,3,4,null,5,null,6]
Output: 4

Example 3:
Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: 7

Constraints:
The depth of the n-ary tree is less than or equal to 1000.
The total number of nodes is between [1, 104].
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

class Solution {
    int maxDiameter = 0;
    
    public int diameter(Node root) {
        diameterUtil(root);
        return maxDiameter - 1;
    }
    
    public int diameterUtil(Node curr) {
        int firstMaxHeight = 0, secondMaxHeight = 0;
        int currHeight, currDiameter;
        
        for (Node child : curr.children) {
            currHeight = diameterUtil(child);
            if (currHeight > firstMaxHeight) {
                secondMaxHeight = firstMaxHeight;
                firstMaxHeight = currHeight;
            } else if (currHeight > secondMaxHeight) {
                secondMaxHeight = currHeight;
            }
        }
        currDiameter = 1 + firstMaxHeight + secondMaxHeight;    // main logic
        maxDiameter = Math.max(maxDiameter, currDiameter);
        return 1 + firstMaxHeight;                              // main logic
    }
}