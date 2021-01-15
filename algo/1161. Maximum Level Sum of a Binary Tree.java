/*
Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
Return the smallest level X such that the sum of all the values of nodes at level X is maximal.

Example 1:
Input: root = [1,7,0,7,-8,null,null]
Output: 2
Explanation: 
Level 1 sum = 1.
Level 2 sum = 7 + 0 = 7.
Level 3 sum = 7 + -8 = -1.
So we return the level with the maximum sum which is level 2.

Example 2:
Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
Output: 2

Constraints:
The number of nodes in the tree is in the range [1, 104].
-105 <= Node.val <= 105
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
    public int maxLevelSum(TreeNode root) 
    {
        if(root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int level = 0, outputLevel = 1, queueSize, sum, maxSum = Integer.MIN_VALUE;
        
        while(!queue.isEmpty())
        {
            queueSize = queue.size();
            sum = 0;
            level++;
            
            while(queueSize-- > 0)
            {
                TreeNode curr = queue.remove();
                sum += curr.val;
                if(curr.left != null) {
                    queue.add(curr.left);
                }
                if(curr.right != null) {
                    queue.add(curr.right);
                }
            }
            
            if(sum > maxSum) {
                maxSum = sum;
                outputLevel = level;
            }
        }
        return outputLevel;
    }
}


/*
// dfs --> (arr[100] or hashmap with key,value = level,sum)

class Solution {
   int arr[] = new int[100];
    public int maxLevelSum(TreeNode root) {
        dfs(root,0);
        int lvl=0, max=Integer.MIN_VALUE;
        for( int x=0;x<arr.length;x++){
            if(arr[x]!=0 && arr[x]>max) { 
                max=arr[x]; 
                lvl=x+1;
            }
        }
        return lvl;
    }
    public void dfs(TreeNode n, int level){
        if(n!=null){
            arr[level]+=n.val;
            dfs(n.left,level+1);
            dfs(n.right,level+1);
        }
    }
}
*/
