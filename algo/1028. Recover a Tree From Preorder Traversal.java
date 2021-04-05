/*
We run a preorder depth-first search (DFS) on the root of a binary tree.
At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of this node.  
If the depth of a node is D, the depth of its immediate child is D + 1.  The depth of the root node is 0.
If a node has only one child, that child is guaranteed to be the left child.
Given the output S of this traversal, recover the tree and return its root.

Example 1:
Input: S = "1-2--3--4-5--6--7"
Output: [1,2,5,3,4,6,7]

Example 2:
Input: S = "1-2--3---4-5--6---7"
Output: [1,2,5,3,null,6,null,4,null,7]

Example 3:
Input: S = "1-401--349---90--88"
Output: [1,401,null,349,88,90]
 
Constraints:
The number of nodes in the original tree is in the range [1, 1000].
1 <= Node.val <= 109
*/



/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */


/*
    1) O(n^2)
        ex1: depth arr = 0, 1, 2, 2, 1, 2, 2
        here root node depth = 0, so find atmost 2 nodes with depth = 1 --> we need a extra loop to find this
        root = (0), left = (1, 2, 2), right = (1, 2, 2)
        do it recursively for left and right
        
    2) O(n)
        preorder traversal with global index variable
*/
class Solution {
    List<Node> nodes;
    int preOrderIndex = 0;
    
    public TreeNode recoverFromPreorder(String s) {
        int val, depth;
        nodes = new ArrayList<Node>();
        
        for (int i = 0; i < s.length(); ) {  // pre-processing (we can also calculate during runtime to avoid extra space)
            val = 0;
            depth = 0;
            while (i < s.length() && s.charAt(i) == '-') {
                depth++;
                i++;
            }
            while (i < s.length() && s.charAt(i) != '-') {
                val = (val * 10) + (s.charAt(i) - '0');
                i++;
            }
            nodes.add(new Node(val, depth));
        } 
        return preOrder(0);
    }
    
    public TreeNode preOrder(int level) {
        if (preOrderIndex >= nodes.size()) {
            return null;
        }
        Node curr = nodes.get(preOrderIndex);
        if (curr.depth != level) {                              // main logic
            return null;
        }
        
        preOrderIndex++;                                        // main logic
        TreeNode root = new TreeNode(curr.val);
        root.left = preOrder(level + 1);
        root.right = preOrder(level + 1);
        return root;
    }
    
    class Node {
        int val;
        int depth;
        Node(int val, int depth) {
            this.val = val;
            this.depth = depth;
        }
    }
}