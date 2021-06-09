/*
In a row of trees, the i-th tree produces fruit with type tree[i].

You start at any tree of your choice, then repeatedly perform the following steps:
Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.

Note that you do not have any choice after the initial choice of starting tree: 
you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.

You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.
What is the total amount of fruit you can collect with this procedure?

Example 1:
Input: [1,2,1]
Output: 3
Explanation: We can collect [1,2,1].

Example 2:
Input: [0,1,2,2]
Output: 3
Explanation: We can collect [1,2,2].
If we started at the first tree, we would only collect [0, 1].

Example 3:
Input: [1,2,3,2,2]
Output: 4
Explanation: We can collect [2,3,2,2].
If we started at the first tree, we would only collect [1, 2].

Example 4:
Input: [3,3,3,1,2,1,1,2,3,3,4]
Output: 5
Explanation: We can collect [1,2,1,1,2].
If we started at the first tree or the eighth tree, we would only collect 4 fruits.

Note:
1 <= tree.length <= 40000
0 <= tree[i] < tree.length
*/


/*
    Problem statement: find the largest subarray with atmost 2 unique elements
    logic: sliding window
    time: n, space: 1
    
    without sliding window, using count variable
    https://leetcode.com/problems/fruit-into-baskets/discuss/171954/Java-Very-simple-solution-few-lines-Time-O(n)-Space-O(1)
*/

class Solution {
    public int totalFruit(int[] tree) {
        int first = 0, second = -1;
        int currOutput, output = 1;
        int start = 0;
        
        for (int i = 1; i < tree.length; i++) {
            if (tree[i] == tree[first]) {
                first = i;
                if (second != -1 && first > second) {
                    int temp = first;
                    first = second;
                    second = temp;
                }
            } else if (second == -1 || tree[i] == tree[second]) {
                if (tree[i] != tree[i - 1]) {
                    second = i;
                }
            } else {                                        // curr tree[i] != (first, second)
                currOutput = i - start;                     // sliding window length
                output = Math.max(currOutput, output);
                first = second;
                second = i;
                start = first;
            }
        }
        currOutput = tree.length - start;
        output = Math.max(currOutput, output);
        return output;
    }
}
