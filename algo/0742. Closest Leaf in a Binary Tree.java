/*
Given a binary tree where every node has a unique value, and a target key k, 
find the value of the nearest leaf node to target k in the tree.

Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree. 
Also, a node is called a leaf if it has no children.

In the following examples, the input tree is represented in flattened form row by row. 
The actual root tree given will be a TreeNode object.

Example 1:
Input:
root = [1, 3, 2], k = 1
Diagram of binary tree:
          1
         / \
        3   2

Output: 2 (or 3)
Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.

Example 2:
Input:
root = [1], k = 1
Output: 1
Explanation: The nearest leaf node is the root node itself.

Example 3:
Input:
root = [1,2,3,4,null,null,null,5,null,6], k = 2
Diagram of binary tree:
             1
            / \
           2   3
          /
         4
        /
       5
      /
     6
     
Output: 3
Explanation: The leaf node with value 3 (and not the leaf node with value 6) is nearest to the node with value 2.

Note:
root represents a binary tree with at least 1 node and at most 1000 nodes.
Every node has a unique node.val in range [1, 1000].
There exists some node in the given binary tree for which node.val == k.
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
    BFS:
        time: n, space: n
        bfs directions : left, right, parent
        to go to parent, we need to store child vs parent node entry in the map
*/

class Solution {
    Map<TreeNode, TreeNode> childParent;
    TreeNode kthNode;
    int k;
    
    public int findClosestLeaf(TreeNode root, int k) {
        this.k = k;
        this.childParent = new HashMap<TreeNode, TreeNode>();
        createChildParentAndFindKthNode(root, null);
        return findClosestLeafUtil(kthNode, k);
    }
    
    public void createChildParentAndFindKthNode(TreeNode curr, TreeNode parent) {
        if (curr == null) {
            return;
        }
        if (parent != null) {
            childParent.put(curr, parent);                      // create child-parent mapping
        }
        createChildParentAndFindKthNode(curr.left, curr);
        createChildParentAndFindKthNode(curr.right, curr);
        
        if (curr.val == k) {                                    // find kthNode
            kthNode = curr;
        }
    }
    
    public int findClosestLeafUtil(TreeNode kthNode, int k) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Set<TreeNode> visited = new HashSet<TreeNode>();
        queue.add(kthNode);
        visited.add(kthNode);
        int queueSize;
        
        while (!queue.isEmpty()) {
            TreeNode curr = queue.remove();
            if (curr.left == null && curr.right == null) {      // closest leaf to kthNode 
                return curr.val;
            }

            if (curr.left != null && visited.add(curr.left)) {
                queue.add(curr.left);
            }
            if (curr.right != null && visited.add(curr.right)) {
                queue.add(curr.right);
            }
            if (childParent.containsKey(curr)) {
                TreeNode parent = childParent.get(curr);
                if (visited.add(parent)) {
                    queue.add(parent);
                }
            }
        }
        return -1;
    }
}