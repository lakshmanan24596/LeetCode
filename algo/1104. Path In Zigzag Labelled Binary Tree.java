/*
In an infinite binary tree where every node has two children, the nodes are labelled in row order.
In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right, while in the even numbered rows (second, fourth, sixth,...), the labelling is right to left.
Given the label of a node in this tree, return the labels in the path from the root of the tree to the node with that label.

Example 1:
Input: label = 14
Output: [1,3,4,14]

Example 2:
Input: label = 26
Output: [1,2,6,10,26]

Constraints:
1 <= label <= 10^6
*/


/*
    logic: traverse from bottom to top of the tree
    time: log(n), which is the output size or height of the binary tree
    
    implementation:
        level   ==> minus 1  (height)
        index   ==> div 2    (parent index)
        label   ==> formula for odd level and even level
*/
class Solution {
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> path = new LinkedList<Integer>();
        int level = (int) (Math.floor(Math.log(label) / Math.log(2))) + 1;
        int index;
        if (level % 2 == 0) {
            index = ((int) Math.pow(2, level) - 1) - label;
        } else {
            index = label - ((int) Math.pow(2, level - 1));
        }
        path.add(0, label);
        
        while (level > 1) {
            level -= 1;
            index /= 2;
            if (level % 2 == 0) {
                label = (int) Math.pow(2, level) - (index + 1);
            } else {
                label = (int) Math.pow(2, level - 1) + (index);
            }
            path.add(0, label);
        }
        return path;
    }
}