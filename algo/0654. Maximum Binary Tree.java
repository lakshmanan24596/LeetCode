/*
Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    / 
     2  0   
       \
        1
Note:
The size of the given array will be in the range [1,1000].
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
    /*
        brute force recursive: O(n^2) in worst case (for skewed tree) and O(n*logn) in average case
        monotonic stack: O(2n) - https://leetcode.com/problems/maximum-binary-tree/discuss/106147/C%2B%2B-8-lines-O(n-log-n)-map-plus-stack-with-binary-search
    */
    
    
    /* Time: O(2n) because each node pushed and poped only once in stack 
       Use monotonic stack. Keep the stack values in descending order */
    
    public TreeNode constructMaximumBinaryTree(int[] nums) 
    {
        if(nums == null || nums.length == 0) {
            return null;
        }
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curr = new TreeNode(nums[0]);
        stack.push(curr);
        TreeNode maxNode = curr;
        
        
        for(int i = 1; i < nums.length; i++)
        {
            curr = new TreeNode(nums[i]);
            while(!stack.isEmpty() && curr.val > stack.peek().val) {    // keep the stack in descending order
                curr.left = stack.pop();
            }
            if(!stack.isEmpty() && curr.val < stack.peek().val) {
                stack.peek().right = curr;
            }
            stack.push(curr);
            
            if(curr.val > maxNode.val) {
                maxNode = curr;
            }
        }
        return maxNode;
    }
    
    /*
    O(n^2) solution
    
    public TreeNode constructMaximumBinaryTree(int[] nums) 
    {
        if(nums == null || nums.length == 0) {
            return null;
        }
        return constructMaximumBinaryTree(nums, 0, nums.length-1);
    }
    
     public TreeNode constructMaximumBinaryTree(int[] nums, int left, int right)
     {
         if(left > right) {
             return null;
         }
         
         int maxValue = Integer.MIN_VALUE;
         int maxIndex = -1;
         
         for(int i = left; i <= right; i++) {
             if(nums[i] > maxValue) {
                 maxValue = nums[i];
                 maxIndex = i;
             }
         }
         
         TreeNode root = new TreeNode(maxValue);
         root.left = constructMaximumBinaryTree(nums, left, maxIndex-1);
         root.right = constructMaximumBinaryTree(nums, maxIndex+1, right);
         return root;
     }
     */
}