/*
Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).

Example 1:
Input: root = [1,3,2,5,3,null,9]
Output: [1,3,9]

Example 2:
Input: root = [1,2,3]
Output: [1,3]

Example 3:
Input: root = [1]
Output: [1]

Example 4:
Input: root = [1,null,2]
Output: [1,2]

Example 5:
Input: root = []
Output: []
 
Constraints:
The number of nodes in the tree will be in the range [0, 104].
-231 <= Node.val <= 231 - 1
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
// BFS
class Solution
{
    public List<Integer> largestValues(TreeNode root) 
    {
        List<Integer> output = new ArrayList<Integer>();
        if(root == null) {
            return output;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        
        while(!queue.isEmpty())
        {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            while(size-- > 0)
            {
                TreeNode curr = queue.remove();
                max = Math.max(max, curr.val);
                
                if(curr.left != null) {
                    queue.add(curr.left);
                }
                if(curr.right != null) {
                    queue.add(curr.right);
                }
            }
            output.add(max);
        }
        return output;
    }
}
*/

// DFS
class Solution
{
    List<Integer> output = new ArrayList<Integer>();
    public List<Integer> largestValues(TreeNode root) 
    {
        recur(root, 1);
        return output;
    }
    
    public void recur(TreeNode root, int level)
    {
        if(root == null) {
            return;
        }
        if(level > output.size()) {
            output.add(root.val);
        } 
        else {
            output.set(level - 1, Math.max(root.val, output.get(level - 1)));
        }
        recur(root.left, level + 1);
        recur(root.right, level + 1);
    }
}
