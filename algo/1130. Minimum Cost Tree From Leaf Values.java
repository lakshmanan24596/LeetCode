/*
Given an array arr of positive integers, consider all binary trees such that:
Each node has either 0 or 2 children;
The values of arr correspond to the values of each leaf in an in-order traversal of the tree.  
(Recall that a node is a leaf if and only if it has 0 children.)
The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node. 
It is guaranteed this sum fits into a 32-bit integer.

Example 1:
Input: arr = [6,2,4]
Output: 32
Explanation:
There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.

    24            24
   /  \          /  \
  12   4        6    8
 /  \               / \
6    2             2   4
 

Constraints:
2 <= arr.length <= 40
1 <= arr[i] <= 15
It is guaranteed that the answer fits into a 32-bit signed integer (ie. it is less than 2^31).
*/



/*
    any number of nodes can come in left and right
    ex: arr = [1,2,3,4]
        then possible values in left and right are: [1][234], [12][34], [123][4] 
        which is n-1 partition index in total
        
        so we need to try to split it at all possible indexes and do this:
            currProduct = findMax(start, partition) * findMax(partition + 1, end);
            currOutput = currProduct + dfs(start, partition) + dfs(partition + 1, end);
    
    DP states: startIndex, endIndex
    Time: n^3  (n^2 states and n time for each state)
    Space: n^2
*/

/*
class Solution {
    int[] arr;
    int n;
    int[][] DP;
    
    public int mctFromLeafValues(int[] arr) {
        this.arr = arr;
        this.n = arr.length;
        this.DP = new int[n][n];
        return dfs(0, n - 1);
    }
    
    public int dfs(int start, int end) {
        if (start == end) {
            return 0;
        }
        if (DP[start][end] > 0) {
            return DP[start][end];
        }
        int currOutput, output = Integer.MAX_VALUE;
        int currProduct;
        
        for (int partition = start; partition < end; partition++) {
            currProduct = findMax(start, partition) * findMax(partition + 1, end);
            currOutput = currProduct + dfs(start, partition) + dfs(partition + 1, end);     // main logic
            output = Math.min(currOutput, output);
        }
        return DP[start][end] = output;
    }
    
    public int findMax(int start, int end) {
        int max = Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }
}
*/


/*
    Logic: Smallest value should always come in the leaf to get overall minimum output
    https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/discuss/340489/Summary-and-reasoning-of-different-solutions
    
    Technique: greedy, monotonically decreasong stack
    Time: 2n
    Space: n
*/
class Solution {
    public int mctFromLeafValues(int[] arr) {
        int output = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);
        
        for (int val : arr) {
            while (val >= stack.peek()) {                               // monotonically decreasong stack
                output += stack.pop() * Math.min(stack.peek(), val);    // main logic
            }
            stack.push(val);
        }
        while (stack.size() > 2) {
            output += stack.pop() * stack.peek();
        }
        return output;
    }
}