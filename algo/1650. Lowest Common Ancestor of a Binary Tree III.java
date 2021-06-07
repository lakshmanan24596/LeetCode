/*
Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
Each node will have a reference to its parent node. The definition for Node is below:
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}

According to the definition of LCA on Wikipedia: 
"The lowest common ancestor of two nodes p and q in a tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)."

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.

Example 2:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.

Example 3:
Input: root = [1,2], p = 1, q = 2
Output: 1

Constraints:
The number of nodes in the tree is in the range [2, 105].
-109 <= Node.val <= 109
All Node.val are unique.
p != q
p and q exist in the tree.
*/



/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/


/*
    similar to intersection of 2 linkeslist
    https://www.geeksforgeeks.org/write-a-function-to-get-the-intersection-point-of-two-linked-lists/

    1) brute:
        time: h*h, space: 1
        for each parent nodes of p, check all parent nodes of q

    2) hashset:
        time: h, space: h
        use hashset for all parents for p
        iterate all parent of q and check if exist in hashset

    3) using 2 pointers:
        time: h, space: 1
        https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/discuss/1149753/JAVA-or-Clean-and-Concise-Code-or-100-Beats-or-Easy-to-Understand
        
    4) using height or count of parents:
        time: h, space: 1
        https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/discuss/932598/Simple-O(h)-runtime-O(1)-space-Java-solution-for-slow-learners-like-myself
*/

// approach 4
class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        int count1 = getParentCount(p);
        int count2 = getParentCount(q);
        
        while (count1 > count2) {
            p = p.parent;
            count1--;
        }
        while (count2 > count1) {
            q = q.parent;
            count2--;
        }
        while (p != q) {
            p = p.parent;
            q = q.parent;
        }
        return p;
    }
    
    public int getParentCount(Node node) {
        int count = 0;
        while (node != null) {
            count++;
            node = node.parent;
        }
        return count;
    }
}