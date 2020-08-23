/*
Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.
The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

Example 1:
Input: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).

Example 2:
Input: 

          1
         /  
        3    
       / \       
      5   3     

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).

Example 3:
Input: 

          1
         / \
        3   2 
       /        
      5      

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).

Example 4:
Input: 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).

Note: Answer will in the range of 32-bit signed integer.
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
class Solution 
{
    class Data
    {
        TreeNode node;
        int index;
        Data(TreeNode node, int index)
        {
            this.node = node;
            this.index = index;
        }
    }
    
    public int widthOfBinaryTree(TreeNode root)     // Time: N, Space: N
    {        
        if(root == null) {
            return 0;
        }
        Queue<Data> queue = new LinkedList<Data>();
        int output = 1, currOutput;
        
        queue.add(new Data(root, 0));
        while(!queue.isEmpty())
        {
            int startIndex = 0;
            int size = queue.size();
            for(int i = 0; i < size; i++)
            {
                Data data = queue.remove();
                if(i == 0) {
                    startIndex = data.index;
                } 
                else if(i == size - 1) {
                    currOutput = (data.index - startIndex) + 1;     // main logic
                    output = Math.max(output, currOutput);          // update output for each level
                }
                
                if(data.node.left != null) {
                    queue.add(new Data(data.node.left, leftIndex(data.index)));
                }
                if(data.node.right != null) {
                    queue.add(new Data(data.node.right, rightIndex(data.index)));
                }
            }
        }
        return output;
    }
    
    public int leftIndex(int index) {
        return 2 * index;
    }
    public int rightIndex(int index) {
        return (2 * index) + 1;
    }
}
