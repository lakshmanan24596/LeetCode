/*
A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all nodes are as far left as possible.
Write a data structure CBTInserter that is initialized with a complete binary tree and supports the following operations:
CBTInserter(TreeNode root) initializes the data structure on a given tree with head node root;
CBTInserter.insert(int v) will insert a TreeNode into the tree with value node.val = v so that the tree remains complete, and returns the value of the parent of the inserted TreeNode;
CBTInserter.get_root() will return the head node of the tree.

Example 1:
Input: inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]]
Output: [null,1,[1,2]]

Example 2:
Input: inputs = ["CBTInserter","insert","insert","get_root"], inputs = [[[1,2,3,4,5,6]],[7],[8],[]]
Output: [null,3,4,[1,2,3,4,5,6,7,8]]

Note:
The initial given tree is complete and contains between 1 and 1000 nodes.
CBTInserter.insert is called at most 10000 times per test case.
Every value of a given or inserted node is between 0 and 5000.
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
    logic: BFS
    maintain a queue where queuePeek is the insert location parent
    
    time: CBTInserter: n, insert: 1, get_root: 1
    space: n
*/
class CBTInserter {
    TreeNode root;
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    
    public CBTInserter(TreeNode root) {
        this.root = root;
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode peekNode = queue.peek();
            if (peekNode.left != null) {
                queue.add(peekNode.left);
            } else {
                break;
            }
            if (peekNode.right != null) {
                queue.add(peekNode.right);
            } else {
                break;
            }
            queue.remove();
        }
    }
    
    public int insert(int v) {
        TreeNode newNode = new TreeNode(v);
        TreeNode parent = queue.peek();             // main logic: queuePeek is the parent node to insert
        
        if (parent.left == null) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
            queue.remove();                         // after filling rightMost child, remove from the queue
        }
        queue.add(newNode);
        return parent.val;
    }
    
    public TreeNode get_root() {
        return this.root;
    }
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(v);
 * TreeNode param_2 = obj.get_root();
 */