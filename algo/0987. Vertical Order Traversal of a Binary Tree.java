/*
Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
For each node at position (x, y), its left and right children will be at positions (x - 1, y - 1) and (x + 1, y - 1) respectively.
The vertical order traversal of a binary tree is a list of non-empty reports for each unique x-coordinate from left to right. 
Each report is a list of all nodes at a given x-coordinate. 
The report should be primarily sorted by y-coordinate from highest y-coordinate to lowest. 
If any two nodes have the same y-coordinate in the report, the node with the smaller value should appear earlier.
Return the vertical order traversal of the binary tree.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation: Without loss of generality, we can assume the root node is at position (0, 0):
The node with value 9 occurs at position (-1, -1).
The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2).
The node with value 20 occurs at position (1, -1).
The node with value 7 occurs at position (2, -2).

Example 2:
Input: root = [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation: The node with value 5 and the node with value 6 have the same position according to the given scheme.
However, in the report [1,5,6], the node with value 5 comes first since 5 is smaller than 6.
 
Constraints:
The number of nodes in the tree is in the range [1, 1000].
0 <= Node.val <= 1000
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
class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        Map<Integer, List<Integer>> tempMap;
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(new Node(root, 0));
        int min = 0, max = 0;
        int level;
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        List<Integer> currList, existingList;
            
        while (!queue.isEmpty()) {          // level order traversal is done because, output should be sorted based on y-coordinate
            int queueSize = queue.size();
            tempMap = new HashMap<Integer, List<Integer>>();
            while (queueSize-- > 0) {
                Node node = queue.remove();
                TreeNode curr = node.tnode;
                level = node.level;
                min = Math.min(min, level);
                max = Math.max(max, level);

                List<Integer> list = tempMap.get(level);
                if(list == null) {
                    list = new ArrayList<Integer>();
                    tempMap.put(level, list);
                }
                list.add(curr.val);

                if(curr.left != null) {
                    queue.add(new Node(curr.left, level - 1));
                }
                if(curr.right != null) {
                    queue.add(new Node(curr.right, level + 1));
                }
            }
            for(Map.Entry<Integer, List<Integer>> entry : tempMap.entrySet()) {
                level = entry.getKey();
                currList = entry.getValue();
                Collections.sort(currList);     // sorting (for same y-coordinate, smaller value should come first)
                existingList = map.getOrDefault(level, new ArrayList<Integer>());
                existingList.addAll(currList);
                map.put(level, existingList);
            }
        }
        for(int i = min; i <= max; i++) {       // main logic: output should be sorted based on level of vertical order
            output.add(map.get(i));
        }
        return output;
    }
    
    class Node {
        TreeNode tnode;
        int level;      // level of vertical order teaversal
        Node(TreeNode tnode, int level) {
            this.tnode = tnode;
            this.level = level;
        }
    }
}