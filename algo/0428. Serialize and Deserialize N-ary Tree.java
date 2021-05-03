/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize an N-ary tree. 
An N-ary tree is a rooted tree in which each node has no more than N children. 
There is no restriction on how your serialization/deserialization algorithm should work. 
You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following 3-ary tree
as [1 [3[5 6] 2 4]]. Note that this is just an example, you do not necessarily need to follow this format.
Or you can follow LeetCode's level order traversal serialization format, where each group of children is separated by the null value.

For example, the above tree may be serialized as [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14].
You do not necessarily need to follow the above suggested formats, there are many more different formats that work so please be creative and come up with different approaches yourself.

Constraints:
The number of nodes in the tree is in the range [0, 104].
0 <= Node.val <= 104
The height of the n-ary tree is less than or equal to 1000
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
*/


/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Codec {
    final char nullDelimeter = '#';
    
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            return sb.toString();
        }
        
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        sb.append((char) (root.val + '0'));
        sb.append(nullDelimeter);
        
        while (!queue.isEmpty()) {
            Node curr = queue.remove();
            for (Node child : curr.children) {
                queue.add(child);
                sb.append((char) (child.val + '0'));
            }
            sb.append(nullDelimeter);          
        }
        return sb.toString();
    }
	
    public Node deserialize(String data) {
        if (data.length() == 0) {
            return null;
        }
        Node root = new Node(data.charAt(0) - '0', new ArrayList<Node>());
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        int i = 2;
        
        while (!queue.isEmpty()) {
            Node curr = queue.remove();
            while (true) {
                char ch = data.charAt(i++);
                if (ch == nullDelimeter) {
                    break;
                }
                Node child = new Node(ch - '0', new ArrayList<Node>());
                curr.children.add(child);
                queue.add(child);
            }
        }
        return root;
    }
}