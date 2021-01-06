/*
We are given a binary tree (with root node root), a target node, and an integer value K.
Return a list of the values of all nodes that have a distance K from the target node.  
The answer can be returned in any order.

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
Output: [7,4,1]
Explanation: 
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.

Note that the inputs "root" and "target" are actually TreeNodes.
The descriptions of the inputs above are just serializations of these objects.
 
Note:
The given tree is non-empty.
Each node in the tree has unique values 0 <= node.val <= 500.
The target node is a node in the tree.
0 <= K <= 1000.
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
class Solution
{
    Map<TreeNode, TreeNode> parentMap = new HashMap<TreeNode, TreeNode>();  // key, value = child, parent
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) 
    {
        if(root == null) {
            return null;
        }
        createParentMap(root);
        return bfs(root, target, K);
    }
    
    public void createParentMap(TreeNode root)
    {
        if(root.left != null) 
        {
            parentMap.put(root.left, root);
            createParentMap(root.left);
        }
        if(root.right != null) 
        {
            parentMap.put(root.right, root);
            createParentMap(root.right);
        }
    }
    
    public List<Integer> bfs(TreeNode root, TreeNode target, int K)
    {
        List<Integer> output = new ArrayList<Integer>();
        int level = 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(target);
        Set<TreeNode> visitedSet = new HashSet<TreeNode>();
        visitedSet.add(target);
        
        while(!queue.isEmpty())
        {
            if(level == K) {
                break;
            }
            int size = queue.size();
            while(size-- > 0)
            {
                TreeNode curr = queue.remove();
                if(curr.left != null && !visitedSet.contains(curr.left))        // left
                {
                    visitedSet.add(curr.left);
                    queue.add(curr.left);
                }
                if(curr.right != null && !visitedSet.contains(curr.right))      // right
                {
                    visitedSet.add(curr.right);
                    queue.add(curr.right);
                }
                if(parentMap.containsKey(curr))                                 // parent
                {
                    TreeNode parent = parentMap.get(curr);
                    if(!visitedSet.contains(parent)) 
                    {
                        visitedSet.add(parent);
                        queue.add(parent);
                    }
                }
            }
            level++;
        }
        while(!queue.isEmpty()) {
            output.add(queue.remove().val);
        }
        return output;
    }
}
