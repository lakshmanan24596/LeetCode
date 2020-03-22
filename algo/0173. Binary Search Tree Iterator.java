/*
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
Calling next() will return the next smallest number in the BST.

Example:
BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false
 
Note:
next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.
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
class BSTIterator 
{   
    // logic 1: using array --> O(1) time, O(N) space
    // logic 2: using stack --> O(2) time, O(h) space   // because each element is pushed and popped only once in stack
    
    Stack<TreeNode> stack = new Stack<TreeNode>();
    
    public BSTIterator(TreeNode root) 
    {
        leftInorder(root);
    }
    
    public void leftInorder(TreeNode root)
    {
        while(root != null)
        {
            stack.push(root);
            root = root.left;                               // left
        }
    }
    
    /** @return the next smallest number */
    public int next() 
    {
        TreeNode nextElementNode = stack.pop();             // root
        leftInorder(nextElementNode.right);                 // right
        return nextElementNode.val;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() 
    {
        return (!stack.isEmpty());
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */