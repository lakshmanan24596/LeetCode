/*
You are given a binary tree in which each node contains an integer value.
Find the number of paths that sum to a given value.
The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:
root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1
Return 3. 
The paths that sum to 8 are:
1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
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
    // refer: https://www.geeksforgeeks.org/print-k-sum-paths-binary-tree/
    // above problem can be solved using kadane's algo (max sum contiguous sub-array)
    
    // 1) consider each node as root.. then time = O(n^2).. because T(n) = n + T(n-1) + T(1) in worst case for skewed tree      
    // 2) check whether the path traversed gives the sum... then time = O(n * h)        
    // 3) hashmap: Time O(n) and Space O(n)
    
    int output = 0;
    List<Integer> currOutput = new ArrayList<Integer>();
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(); // key = sum, value = occurence of that sum
    int sum;
    
    public int pathSum(TreeNode root, int sum) 
    {    
        this.sum = sum;
        map.put(0, 1);                                              // Default: sum = 0 has one count
        pathSumUtil(root, 0);
        return output;
    }
    
    public void pathSumUtil(TreeNode root, int currSum)             // preOrder
    {
        if(root == null)
            return;
        
        currSum += root.val;                                        // currSum holds sum of all elements in current call stack
        output += map.getOrDefault(currSum - sum, 0);               // check if there is a subarray using map
        
        map.put(currSum, map.getOrDefault(currSum, 0) + 1); 
        pathSumUtil(root.left, currSum);
        pathSumUtil(root.right, currSum);
        map.put(currSum, map.getOrDefault(currSum, 0) - 1);         // backtrack
    }
   
    
//     Time: O(n*h) and Space: O(h)  
    
//     public void pathSumUtil(TreeNode root, int sum)
//     {
//         if(root == null)
//             return;
        
//         currOutput.add(root.val);    
        
//         int currSum = 0;
//         for(int i=currOutput.size()-1; i>=0; i--)                // iterate from last
//         {
//             currSum += currOutput.get(i);
//             if(currSum == sum)
//                 output++;
//         }
        
//         pathSumUtil(root.left, sum);
//         pathSumUtil(root.right, sum);
        
//         currOutput.remove(currOutput.size()-1);                  // backtrack
//     }
}