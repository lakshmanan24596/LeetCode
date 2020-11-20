/*
Given the root of a binary tree and an integer distance. 
A pair of two different leaf nodes of a binary tree is said to be good if the length of the shortest path between them is less than or equal to distance.
Return the number of good leaf node pairs in the tree.

Example 1:
Input: root = [1,2,3,null,4], distance = 3
Output: 1
Explanation: The leaf nodes of the tree are 3 and 4 and the length of the shortest path between them is 3. This is the only good pair.

Example 2:
Input: root = [1,2,3,4,5,6,7], distance = 3
Output: 2
Explanation: The good pairs are [4,5] and [6,7] with shortest path = 2. 
The pair [4,6] is not good because the length of ther shortest path between them is 4.

Example 3:
Input: root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3
Output: 1
Explanation: The only good pair is [2,5].

Example 4:
Input: root = [100], distance = 1
Output: 0

Example 5:
Input: root = [1,1,1], distance = 2
Output: 1
 
Constraints:
The number of nodes in the tree is in the range [1, 2^10].
Each node's value is between [1, 100].
1 <= distance <= 10
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

// https://leetcode.com/problems/number-of-good-leaf-nodes-pairs/discuss/756198/Java-DFS-Solution-with-a-Twist-100-Faster-Explained
class Solution 
{
    int output = 0;
    int d;
    
    public int countPairs(TreeNode root, int distance)
    {
        this.d = distance;
        dfs(root);
        return output;
    }
    
    public int[] dfs(TreeNode root)
    {
        int[] returnArr = new int[d+1]; // for d = 3, create arr with index 0 to 3 where index refers distance
        if(root == null)
        {
            return returnArr;
        }
        if(root.left == null && root.right == null) 
        {
            returnArr[1] = 1;
            return returnArr;
        }
        
        // post order traversal
        int[] left = dfs(root.left);    // for ex1: node 1 left[] = [0,0,1,0] --> there is 1 node at distance 2         
        int[] right = dfs(root.right);  // for ex1: node 1 right[] = [0,1,0,0] --> there is 1 node at distance 1
        
        for(int i = 1; i <= d; i++) {
            for(int j = 1; j <= d; j++) {
                if(i + j <= d) {                    // main logic
                    output += left[i] * right[j];   // calculate total number of good node pairs formed 
                } else {
                    break;
                }
            }
        }
        
        // if a leaf node is at distance i from current node, it would be at distance i+1 from its parent
        for(int i = 1; i < d; i++) {
            returnArr[i+1] = left[i] + right[i];    
        }
        return returnArr;
    }
}
