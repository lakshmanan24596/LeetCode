/*
Given the root of a binary tree, return all duplicate subtrees.
For each kind of duplicate subtrees, you only need to return the root node of any one of them.
Two trees are duplicate if they have the same structure with the same node values.

Example 1:
Input: root = [1,2,3,4,null,2,4,null,null,4]
Output: [[2,4],[4]]

Example 2:
Input: root = [2,1,1]
Output: [[1]]

Example 3:
Input: root = [2,2,2,3,null,3,null]
Output: [[2,3],[3]]
 
Constraints:
The number of the nodes in the tree will be in the range [1, 10^4]
-200 <= Node.val <= 200
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


/*
// https://leetcode.com/problems/find-duplicate-subtrees/discuss/106011/Java-Concise-Postorder-Traversal-Solution
// Time: n^2
class Solution 
{
    List<TreeNode> output;
    Map<String, Integer> map;
    
    public List<TreeNode> findDuplicateSubtrees(TreeNode root)  // Time: n^2
    {
        output = new ArrayList<TreeNode>();
        map = new HashMap<String, Integer>();
        postOrder(root);
        return output;
    }
    
    public String postOrder(TreeNode root)
    {
        if(root == null) {
            return "#";
        }
        String hashKey = new StringBuilder(Integer.toString(root.val))
                            .append(",").append(postOrder(root.left))
                            .append(",").append(postOrder(root.right)).toString();  // main logic
        
        map.put(hashKey, map.getOrDefault(hashKey, 0) + 1);
        if(map.get(hashKey) == 2) {
            output.add(root);
        }
        return hashKey;
    }
}
*/
    

/*
// Same as above solution but instead of string hashing, integer hashing is done using prime number.
// So time complexity is O(n)
class Solution {
    private static long PRIME = 9677, PRIME2 = (long) Math.pow(PRIME,2), PRIME3 = (long) Math.pow(PRIME,3), PRIME4 = (long) Math.pow(PRIME,4);
    
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        dfs(root, new HashSet<>(), new HashSet<>(), result);
        return result;
    }
    
    private int dfs(TreeNode node, Set<Integer> seen, Set<Integer> added, List<TreeNode> result) {
        if(node == null) {
            return (int) PRIME;
        }
        
        int left = dfs(node.left, seen, added, result);
        int right = dfs(node.right, seen, added, result);
        
        int val = (int) ((left * PRIME2 + right * PRIME3 + node.val*PRIME4) % 1000000007);  // main logic
        if(seen.contains(val) && !added.contains(val)) {
            result.add(node);
            added.add(val);
        } else {
            seen.add(val);
        }
        
        return val;
    }
}
*/


/*
    https://leetcode.com/problems/find-duplicate-subtrees/discuss/106011/Java-Concise-Postorder-Traversal-Solution
    Time: n --> mapping a string to a serial id
    instead of string, serial id is appended which reduces time from n^2 to n
*/
class Solution 
{
    List<TreeNode> output = new ArrayList<TreeNode>();
    Map<String, Integer> stringToSerialIdMap = new HashMap<String, Integer>();
    Map<Integer, Integer> serialIdToCountMap = new HashMap<Integer, Integer>();
    int serialId = 1;
    
    public List<TreeNode> findDuplicateSubtrees(TreeNode root)
    {
        postOrder(root);
        return output;
    }
    
    public int postOrder(TreeNode root)
    {
        if(root == null) {
            return 0;
        }
        String hashKey = new StringBuilder(Integer.toString(root.val))
                            .append(",").append(postOrder(root.left))
                            .append(",").append(postOrder(root.right)).toString();   
        
        int currSerialId;
        if(stringToSerialIdMap.containsKey(hashKey)) // duplicate subtree
        {
            currSerialId = stringToSerialIdMap.get(hashKey);
            serialIdToCountMap.put(currSerialId, serialIdToCountMap.get(currSerialId) + 1);
            if(serialIdToCountMap.get(currSerialId) == 2) {
                output.add(root);
            }
        } 
        else // unique subtree
        {
            currSerialId = serialId++;
            stringToSerialIdMap.put(hashKey, currSerialId);
            serialIdToCountMap.put(currSerialId, 1);
        }
        return currSerialId;
    }
}
