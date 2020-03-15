/*
Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
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
    public List<List<Integer>> levelOrderBottom(TreeNode root) 
    {
        List<List<Integer>> output = new LinkedList<List<Integer>>();   // it should be LinkedList (and not ArrayList)
        if(root == null)
            return output;
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        
        while(!queue.isEmpty())
        {
            List<Integer> currOutput = new ArrayList<Integer>();
            int size = queue.size();
            
            for(int i = 0; i < size; i++)
            {
                TreeNode node = queue.remove();
                currOutput.add(node.val);
                
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);  
            }
            output.add(0, currOutput);              // insert front --> O(1) time because of LinkedList        
        }           
        return output;  
    }
}