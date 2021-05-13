/*
Given the root of a binary search tree, a target value, and an integer k, return the k values in the BST that are closest to the target. 
You may return the answer in any order.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.

Example 1:
Input: root = [4,2,5,1,3], target = 3.714286, k = 2
Output: [4,3]

Example 2:
Input: root = [1], target = 0.000000, k = 1
Output: [1]

Constraints:
The number of nodes in the tree is n.
1 <= k <= n <= 104.
0 <= Node.val <= 109
-109 <= target <= 109

Follow up: Assume that the BST is balanced. Could you solve it in less than O(n) runtime (where n = total nodes)?
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
    1) priorityQueue:
        time: nlogk
        space: k
        
    2) inorder:
        inorder of bst gives sorted array
        do inorder traversal and store it in array (time: n)
        do binary search and find most closest val (time: logn)
        use 2-pointer technique by expanding left, right in both sides till we find k elements (time: k)
        time: n+k
        space: n
        
    3) inorder with early termination:
        time: k for the best case, when target is very close to smallest value of the BST 
        time: n for the worse case, when target is close to the largest value of the BST
        space: n
    
    4) inorder predecessor and successor stack:
        https://leetcode.com/problems/closest-binary-search-tree-value-ii/discuss/281703/From-O(n)-to-O(logn%2Bk)-in-extreme-details
        each elements is pushed and popped only once
        time: logn + k
        space: logn
*/


/*
// approach 3 mentioned above
class Solution {
    LinkedList<Integer> closestKValues;
    int k;
    double target;
    
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        this.closestKValues = new LinkedList<Integer>();
        this.k = k;
        this.target = target;
        
        inorder(root);
        return closestKValues;
    }
    
    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        
        if (closestKValues.size() < k) {
            closestKValues.add(root.val);
        } else {
            if (isClosest(closestKValues.get(0), root.val)) {   // main logic
                closestKValues.removeFirst();
                closestKValues.add(root.val);
            } else {
                return;                                         // early termination
            }
        }
        
        inorder(root.right);
    }
    
    // returns true if curr is closer to the target
    public boolean isClosest(int prev, int curr) {
        return Math.abs(target - curr) <  Math.abs(target - prev);
    }
}
*/


// approach 4 mentioned above
// https://leetcode.com/problems/closest-binary-search-tree-value-ii/discuss/281703/From-O(n)-to-O(logn%2Bk)-in-extreme-details
class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> closestKValues = new ArrayList<Integer>();
        Stack<TreeNode> predecessor = new Stack<TreeNode>();
        Stack<TreeNode> successor = new Stack<TreeNode>();
        buildStack(root, predecessor, true, target);
        buildStack(root, successor, false, target);
        int predVal, succVal;
        
        if (!predecessor.isEmpty() && !successor.isEmpty() && predecessor.peek().val == successor.peek().val) {     // corner case
            next(predecessor, true);
        }
        while (k-- > 0) {
            if (predecessor.isEmpty()) {
                succVal = successor.peek().val;
                closestKValues.add(succVal);
                next(successor, false);
            } 
            else if (successor.isEmpty()) {
                predVal = predecessor.peek().val;
                closestKValues.add(predVal);
                next(predecessor, true);
            } 
            else {
                predVal = predecessor.peek().val;
                succVal = successor.peek().val;
                if (Math.abs(target - predVal) <  Math.abs(target - succVal)) {
                    closestKValues.add(predVal);
                    next(predecessor, true);
                } else {
                    closestKValues.add(succVal);
                    next(successor, false);
                }
            }
        }
        return closestKValues;
    }
    
    public void buildStack(TreeNode root, Stack<TreeNode> stack, boolean isPred, double target) {
        while (root != null) {
            if (root.val == target) {
                stack.push(root);
                break;
            } 
            else if (root.val < target) {
                if (isPred) {
                    stack.push(root);
                }
                root = root.right;
            } 
            else {
                if (!isPred) {
                    stack.push(root);
                }
                root = root.left;
            }
        }
    }
    
    public void next(Stack<TreeNode> stack, boolean isPred) {       // amortized O(1), every element pushed and popped only once
        TreeNode curr = stack.pop();
        curr = (isPred) ? curr.left : curr.right;
        while (curr != null) {
            stack.push(curr);
            curr = (isPred) ? curr.right : curr.left;
        }
    }
}