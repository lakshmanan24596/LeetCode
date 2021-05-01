/*
Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).
If two nodes are in the same row and column, the order should be from left to right.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]

Example 2:
Input: root = [3,9,8,4,0,1,7]
Output: [[4],[9],[3,0,1],[8],[7]]

Example 3:
Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
Output: [[4],[9,5],[3,0,1],[8,2],[7]]

Example 4:
Input: root = []
Output: []

Constraints:
The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
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
    DFS (pre-order) will give wrong output 
    for example-3, expected = [8,2] but actual = [2,8]
    because we need to process from top to bottom
    
    Approach-1:
        BFS + treemap
        time: n*logn
        space: n
        
    Approach-2:
        BFS + hashmap
        instead of treemap, we just make a note of minCol and maxCol, so hashmap is enough
        time: n
        space: n
*/

class Solution {
    Map<Integer, List<Integer>> levelNodes;
    int minWidth = 1, maxWidth = -1;
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        levelNodes = new HashMap<Integer, List<Integer>>();
        bfs(root);
        
        List<List<Integer>> verticalOrder = new ArrayList<List<Integer>>();
        for (int i = minWidth; i <= maxWidth; i++) {                // main logic
            verticalOrder.add(levelNodes.get(i));
        }
        return verticalOrder;
    }
    
    public void bfs(TreeNode root) {
        if (root == null) {
            return;
        }
        int level = 0;
        minWidth = 0;
        maxWidth = 0;
        Queue<Object[]> queue = new LinkedList<Object[]>();
        queue.add(new Object[] {root, level});
        int queueSize;
        
        while (!queue.isEmpty()) {
            level++;
            queueSize = queue.size();
            while (queueSize-- > 0) {
                Object[] currObj = queue.remove();
                TreeNode node = (TreeNode) currObj[0];
                int currLevel = (int) currObj[1];
                
                List<Integer> currLevelNodes = levelNodes.get(currLevel);
                if (currLevelNodes == null) {
                    currLevelNodes = new ArrayList<Integer>();
                    levelNodes.put(currLevel, currLevelNodes);
                }
                currLevelNodes.add(node.val);
                
                if (node.left != null) {
                    queue.add(new Object[] {node.left, currLevel - 1});
                    minWidth = Math.min(minWidth, currLevel - 1);
                }
                if (node.right != null) {
                    queue.add(new Object[] {node.right, currLevel + 1});
                    maxWidth = Math.max(maxWidth, currLevel + 1);
                }
            }
        }
    }
}