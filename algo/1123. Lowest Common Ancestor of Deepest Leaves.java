/*
Given the root of a binary tree, return the lowest common ancestor of its deepest leaves.
Recall that:
The node of a binary tree is a leaf if and only if it has no children
The depth of the root of the tree is 0. if the depth of a node is d, the depth of each of its children is d + 1.
The lowest common ancestor of a set S of nodes, is the node A with the largest depth such that every node in S is in the subtree with root A.
Note: This question is the same as 865: https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4]
Output: [2,7,4]
Explanation: We return the node with value 2, colored in yellow in the diagram.
The nodes coloured in blue are the deepest leaf-nodes of the tree.
Note that nodes 6, 0, and 8 are also leaf nodes, but the depth of them is 2, but the depth of nodes 7 and 4 is 3.

Example 2:
Input: root = [1]
Output: [1]
Explanation: The root is the deepest node in the tree, and it's the lca of itself.

Example 3:
Input: root = [0,1,3,null,2]
Output: [2]
Explanation: The deepest leaf node in the tree is 2, the lca of one node is itself.
 
Constraints:
The number of nodes in the tree will be in the range [1, 1000].
0 <= Node.val <= 1000
The values of the nodes in the tree are unique.
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


/*
// Time: O(2n) and Space: O(n+h)

class Solution 
{
    TreeNode lcaNode;
    Set<TreeNode> set;
    
    public TreeNode lcaDeepestLeaves(TreeNode root)  
    {
        if(root == null || (root.left == null && root.right == null)) {     // handling for root as deepest leaf node
            return root;
        }
        findDeepestLeaves(root);
        if(set.size() == 1) {         // if there is only 1 deepest leaf, then that is the lca
            return set.iterator().next();
        }
        lca(root);
        return lcaNode;
    }
    
    public void findDeepestLeaves(TreeNode root)    // use level order
    {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);        
        TreeNode curr;
        
        while(!queue.isEmpty())
        {
            set = new HashSet<TreeNode>(queue);     // holds nodes present in current level
            int size = queue.size();           
            while(size-- > 0)
            {
                curr = queue.remove();
                if(curr.left != null) {
                    queue.add(curr.left);
                }
                if(curr.right != null) {
                    queue.add(curr.right);
                }
            }
        }
    }
    
    public int lca(TreeNode root)   // use post order
    {
        if(root == null) {
            return 0;
        }
        
        if(set.contains(root)) {    // if deepest leaf node, then return 1
            return 1;
        }
        
        int left = lca(root.left);
        int right = lca(root.right);
        
        if(left + right == set.size()) {    // main logic: if visited all deepest leaves, then curr node is the lca
            lcaNode = root;
            return 0;   // temp code to reset return value
        }
        else {
            return left + right;
        }
    }
}
*/

// Time: O(n) and Space: O(h)
class Solution 
{
    // main logic: left height and right height of a node should be same and that should be max height
    
    TreeNode lcaNode = null;
    int maxLevel = 0;
    
    public TreeNode lcaDeepestLeaves(TreeNode root)     // Time: O(2n) and Space: O(n)
    {
        findLca(root, 0);
        return lcaNode;
    }
    
    public int findLca(TreeNode root, int level)    // use post order
    {
        if(root == null) {
            maxLevel = Math.max(level, maxLevel);   // update maxLevel
            return level;
        }
        
        int left = findLca(root.left, level + 1);
        int right = findLca(root.right, level + 1);
        
        if(left == maxLevel && right == maxLevel) {     // main logic
            lcaNode = root;
        }
        return Math.max(left, right); 
    }
}
