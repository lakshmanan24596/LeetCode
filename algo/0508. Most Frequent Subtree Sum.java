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
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();    // (key, val) = (sum, count)
    int maxCount = 0;
    
    public int[] findFrequentTreeSum(TreeNode root)     // Time: O(2n)
    {
        dfs(root);
        
        List<Integer> outputList = new ArrayList<Integer>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() == maxCount) {
                outputList.add(entry.getKey());
            }
        }
        int size = outputList.size();
        int[] output = new int[size];
        for(int i = 0; i < size; i++) {
            output[i] = outputList.get(i);
        }
        return output;
    }
    
    public int dfs(TreeNode root) // Do post order traversal 
    {
        if(root == null) {
            return 0;
        }
        
        int leftSum = dfs(root.left);
        int rightSum = dfs(root.right);
        int sum = leftSum + rightSum + root.val;
        
        int currCount = map.getOrDefault(sum, 0) + 1;
        maxCount = Math.max(maxCount, currCount);
        map.put(sum, currCount);
        return sum;
    }
}
