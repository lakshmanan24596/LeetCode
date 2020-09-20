/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. 
You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.
The encoded string should be as compact as possible.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
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
public class Codec 
{
    public final String splitStr = ",";    
    public String serialize(TreeNode root) // Encodes a tree to a single string.
    {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }
    
    public void serialize(TreeNode root, StringBuilder sb) 
    {
        if(root == null) {                 // no need to store null node (This is the difference between serialize BT and BST)
            return;
        }
        sb.append(root.val).append(splitStr);
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    
    int i = 0;              // deSerializeIndex
    String[] arr = null;    // deSerializeArray
    public TreeNode deserialize(String data) 
    {
        if (data.length() == 0) {
            return null;
        }    
        arr = data.split(splitStr);
        return deserialize(Integer.MIN_VALUE, Integer.MAX_VALUE);
    } 
    
    public TreeNode deserialize(int min, int max) // Decodes your encoded data to tree.
    {
        if(i >= arr.length) {
            return null;
        }
        
        int currVal = Integer.valueOf(arr[i]);
        if(currVal < min || currVal > max) {      // main logic: no need to store null node and use BST property
            return null;
        }
        ++i;
        
        TreeNode root = new TreeNode(currVal);
        root.left = deserialize(min, root.val);
        root.right = deserialize(root.val, max);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));