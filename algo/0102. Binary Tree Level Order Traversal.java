/*
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
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
class Solution 
{   
    public List<List<Integer>> levelOrder(TreeNode root) 
    {
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        if(root == null)
            return output;      
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
                
        while(!queue.isEmpty())
        {
            List<Integer> currOutput = new ArrayList<Integer>();
            int size = queue.size();        // take size before iteration, because we add elements in queue inside loop
            
            for(int i = 0; i < size; i++)
            {
                TreeNode node = queue.remove();                     
                currOutput.add(node.val);
                
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
            } 
            output.add(currOutput);
        }           
        return output;
    }
}