/*
Given an m x n matrix mat where every row is sorted in strictly increasing order, return the smallest common element in all rows.
If there is no common element, return -1.

Example 1:
Input: mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]]
Output: 5

Example 2:
Input: mat = [[1,2,3],[2,3,4],[2,3,5]]
Output: 2

Constraints:
m == mat.length
n == mat[i].length
1 <= m, n <= 500
1 <= mat[i][j] <= 104
mat[i] is sorted in strictly increasing order.
*/


/*
    1) binary search:
        answer could be any element in first row
        pick any element in first row and check whether this element is present in remaining rows
        time:O(c * (r * log(c)))
        space: O(1)
        
    2) priority queue - min heap
        similar to merge K sorted list
        insert all 1st col elements into minHeap
        time: O(r * c * log(r))
        space: O(r)
        
    3) row positions
        exactly same as above, but without priority queue
        we can just track currMax and count alone
        refer: https://leetcode.com/problems/find-smallest-common-element-in-all-rows/solution/
        time: O(r * c)
        space: O(r)
        
    4) hashmap
        given array is in strictly increasing order, so no duplicates allowed in a row
        iterate all elements and store of count of each element
        if count of any element = no of rows, then that is the answer
        
        handling duplicates:
        since each array is sorted, simply checking curr != prev skips the duplicates
        
        optimization:
        iterate reverse (column-wise), so that we can break the loop soon
*/

class Solution {
    public int smallestCommonElement(int[][] mat) {
        int[] freqMap = new int[10000 + 1];                     // or use hashmap
        int rows = mat.length;
        int cols = mat[0].length;
        int curr;
        
        for (int j = 0; j < cols; j++) {                        // iterate reverse (optimization)
            for (int i = 0; i < rows; i++) {
                // if (j != 0 && mat[i][j] != mat[i][j - 1])    // handling for duplicates
                curr = mat[i][j];
                freqMap[curr]++;
                if (freqMap[curr] == rows) {                    // main logic
                    return curr;
                }
            }
        }
        return -1;
    }
}