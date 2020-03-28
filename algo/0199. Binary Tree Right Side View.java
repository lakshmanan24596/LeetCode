/*
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:
Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
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
    public List<Integer> rightSideView(TreeNode root) 
    {   
        List<Integer> output = new ArrayList<Integer>();
        if(root == null)
            return output;
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();   
        queue.add(root);
        
        while(!queue.isEmpty())
        {
            int size = queue.size();            
            for(int i = 0; i < size; i++)
            {
                if(i == 0)
                    output.add(queue.peek().val);   // add first element of curr level in output
                
                TreeNode curr = queue.remove();
                if(curr.right != null)              // push right child, then push left child
                    queue.add(curr.right);
                if(curr.left != null)
                    queue.add(curr.left); 
            }
        }
        return output;
    }
}

// Using preOrder

// class Solution 
// {    
//     List<Integer> result = new ArrayList<Integer>();
    
//     public List<Integer> rightSideView(TreeNode root) 
//     {
//         preOrder(root, 0);    
//         return result;
//     }
    
//     private void preOrder(TreeNode root, int level) 
//     {    
//         if (root == null) 
//             return;
        
//         if (level == result.size())         // main logic
//             result.add(root.val);

//         preOrder(root.right, level+1);
//         preOrder(root.left, level+1);
//     }
// }