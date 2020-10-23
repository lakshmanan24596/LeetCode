/*
Return the root node of a binary search tree that matches the given preorder traversal.
(Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)
It's guaranteed that for the given test cases there is always possible to find a binary search tree with the given requirements.

Example 1:
Input: [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]

Constraints:
1 <= preorder.length <= 100
1 <= preorder[i] <= 10^8
The values of preorder are distinct.
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
        1) O(n^2) or O(n^logn): 
            Root = 8. Then left = 5,1,7 and right = 10, 12
            For finding the index of 10 we can use linear search O(n^2) or binary search O(n^logn)
        2) O(n):
            Using -inf and +inf range.
    */
    
    int preorderIndex;
    int[] preorderArr;
    
    public TreeNode bstFromPreorder(int[] preorder) 
    {
        this.preorderIndex = 0;
        this.preorderArr = preorder;
        return bstFromPreorder(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    public TreeNode bstFromPreorder(int min, int max)
    {
        if(preorderIndex >= preorderArr.length) {
            return null;
        }
        
        int key = preorderArr[preorderIndex];
        if(key < min || key > max) {    // main logic
            return null;
        }
        
        preorderIndex++;    // main logic
        
        TreeNode root = new TreeNode(key);
        root.left = bstFromPreorder(min, key);
        root.right = bstFromPreorder(key, max);
        return root;
    }
}    
 

/* 
O(n) solution with slight optimization than above code
No need to check lower bound

class Solution 
{
    int index = 0;
    public TreeNode bstFromPreorder(int[] preorder) 
    {
        return bst(preorder, Integer.MAX_VALUE);
    }

    private TreeNode bst(int[] preorder, int upper) 
    {
        if (index == preorder.length || preorder[index] > upper)
            return null;
        TreeNode root = new TreeNode(preorder[index++]);
        root.left = bst(preorder, root.val);
        root.right = bst(preorder, upper);
        return root;
    }
}        
*/
