/*
Given a binary tree where node values are digits from 1 to 9. 
A path in the binary tree is said to be pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.
Return the number of pseudo-palindromic paths going from the root node to leaf nodes.

Example 1:
Input: root = [2,3,1,3,1,null,1]
Output: 2 
Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the red path [2,3,3], the green path [2,1,1], and the path [2,3,1]. Among these paths only red path and green path are pseudo-palindromic paths since the red path [2,3,3] can be rearranged in [3,2,3] (palindrome) and the green path [2,1,1] can be rearranged in [1,2,1] (palindrome).

Example 2:
Input: root = [2,1,1,1,3,null,null,null,null,null,1]
Output: 1 
Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the green path [2,1,1], the path [2,1,3,1], and the path [2,1]. Among these paths only the green path is pseudo-palindromic since [2,1,1] can be rearranged in [1,2,1] (palindrome).

Example 3:
Input: root = [9]
Output: 1

Constraints:
The number of nodes in the tree is in the range [1, 105].
1 <= Node.val <= 9
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
    1) use freq[] of size 10
        time: n + (leaf * 10)
        space: h + 10
        logic: even freq will be a part of palindrome, so no need to check it
               odd freq should be <= 1 for a pseudo palindrome (main logic)

    2) bit operator
        logic: same as above (odd freq <= 1)
        time: n
        space: h
        (num & (num-1)) == 0 --> return true, if there is atmost 1 set bit in num
*/


/*
// approach-1
class Solution {
    int[] freq;
    int noOfDigits = 10;
    
    public int pseudoPalindromicPaths (TreeNode root) {
        freq = new int[10];
        return dfs(root);
    }
    
    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        freq[root.val]++;
        if (root.left == null && root.right == null) {      // pre order traversal
            int oddFreqDigits = 0;
            for (int i = 1; i < noOfDigits; i++) {
                if (freq[i] % 2 == 1) {
                    oddFreqDigits++;
                    if (oddFreqDigits > 1) {
                        break;
                    }
                }
            }
            freq[root.val]--;
            return (oddFreqDigits > 1) ? 0 : 1;             // main logic
        }
        
        int left = dfs(root.left);
        int right = dfs(root.right);
        freq[root.val]--;
        return left + right;
    }
}
*/

class Solution {
    public int pseudoPalindromicPaths (TreeNode root) {     // time: n, space: h
        return dfs(root, 0);
    }
    
    public int dfs(TreeNode root, int num) {
        if (root == null) {
            return 0;
        }
        
        num = num ^ (1 << root.val);
        if (root.left == null && root.right == null) {      // pre order traversal
            return ((num & (num - 1)) == 0) ? 1 : 0;        // main logic: check atmost 1 set bit
        }
        
        int left = dfs(root.left, num);
        int right = dfs(root.right, num);
        return left + right;
    }
}