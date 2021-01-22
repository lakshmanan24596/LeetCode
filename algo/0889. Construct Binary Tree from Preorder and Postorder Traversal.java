/*
Return any binary tree that matches the given preorder and postorder traversals.
Values in the traversals pre and post are distinct positive integers.

Example 1:
Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]

Note:
1 <= pre.length == post.length <= 30
pre[] and post[] are both permutations of 1, 2, ..., pre.length.
It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.
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
    Time: n, Space: h
    Logic: ex-1 --> root = [1], left = [2,4,5], right = [3,6,7]
                    for doing this, we need to find nextValue (2) in postOrder using hashMap
*/
class Solution {
    int preOrderIndex = 0;
    int[] pre, post;
    Map<Integer,Integer> postOrderMap;   // key=data, value=index
    
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        this.pre = pre;
        this.post = post;
        fillPostOrderMap();
        return constructTree(0, pre.length - 1, 0, post.length - 1);
    }
    
    public void fillPostOrderMap() {
        postOrderMap = new HashMap<Integer, Integer>();
        for(int i = 0; i < post.length; i++) {
            postOrderMap.put(post[i], i);
        }
    }
    
    public TreeNode constructTree(int preStart, int preEnd, int postStart, int postEnd) {
        if (preStart > preEnd) {
            return null;
        }
        
        TreeNode root = new TreeNode(pre[preStart]);
        if (preStart == preEnd) {
            return root;
        }
        
        int nextVal = pre[preStart + 1];
        int postMidIndex = postOrderMap.get(nextVal);   // main logic
        
        root.left = constructTree(preStart + 1, 
                                  preStart + 1 + postMidIndex - postStart, 
                                  postStart, 
                                  postMidIndex);
        root.right = constructTree(preStart + 1 + postMidIndex + 1 - postStart, 
                                   preEnd, 
                                   postMidIndex + 1, 
                                   postEnd - 1);
        return root;
    }
    
    /*
    public TreeNode constructTree(int postStart, int postEnd) {
	    if (postStart > postEnd) {
            return null;
        }
	    
	    TreeNode root = new TreeNode(pre[preindex++]);
	    if(postStart == postEnd) {
            return root;
        }
	    
        int nextVal = pre[preindex];
	    int postMidIndex = postOrderMap.get(nextVal);
        
	    root.left = constructTree(postStart, postMidIndex);
	    root.right = constructTree(postMidIndex + 1, postEnd - 1);
	    return root;
	}
    */
}