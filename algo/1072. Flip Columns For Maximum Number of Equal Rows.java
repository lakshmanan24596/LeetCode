/*
You are given an m x n binary matrix matrix.
You can choose any number of columns in the matrix and flip every cell in that column (i.e., Change the value of the cell from 0 to 1 or vice versa).
Return the maximum number of rows that have all values equal after some number of flips.

Example 1:
Input: matrix = [[0,1],[1,1]]
Output: 1
Explanation: After flipping no values, 1 row has all values equal.

Example 2:
Input: matrix = [[0,1],[1,0]]
Output: 2
Explanation: After flipping values in the first column, both rows have equal values.

Example 3:
Input: matrix = [[0,0,0],[0,0,1],[1,1,0]]
Output: 2
Explanation: After flipping values in the first two columns, the last two rows have equal values.

Constraints:
m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] is either 0 or 1.
*/



/*
    1) brute force:
        time: O((2^c) * (r*c))
        0/1 knapsack, where each column can be swapped or not swapped

    2) hashmap
        https://leetcode.com/problems/flip-columns-for-maximum-number-of-equal-rows/discuss/303758/My-java-solution-with-hashmap
        time: O(r*c)
        space: O(r*c)
        we can just flip the rows and count the pattern occurrence instead of brainlessly flipping the columns
        
    3) Binary trie
        logic is same as above approach
        instead of creating string for hashmap key, we can create a binary trie
        binary trie can have 2 children for 0 and 1.
*/

class Solution {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<String, Integer> freqMap = new HashMap<String, Integer>();
        StringBuilder noSwap, swap;
        String noSwapStr, swapStr;
        int maxRows = 0;
        
        for (int[] row : matrix) {
            noSwap = new StringBuilder();
            swap = new StringBuilder();
            for (int val : row) {
                noSwap.append(val);         // no swap
                swap.append(1 - val);       // swap
            }
            noSwapStr = noSwap.toString();
            swapStr = swap.toString();
    
            freqMap.put(noSwapStr, freqMap.getOrDefault(noSwapStr, 0) + 1);
            freqMap.put(swapStr, freqMap.getOrDefault(swapStr, 0) + 1);
        }
        
        for (int freq : freqMap.values()) {
            maxRows = Math.max(maxRows, freq);
        }
        return maxRows;
    }
}


/*
// BINARY TRIE

class Solution {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        BinaryTrie root = new BinaryTrie();
        int max = 0;
        
        for (int[] row : matrix) {
            BinaryTrie zeros = root;
            BinaryTrie ones = root;
            for (int j = 0; j < row.length; j++) {
                zeros = row[j] == 0 ? zeros.dontFlip() : zeros.flip();
                ones = row[j] == 1 ? ones.dontFlip() : ones.flip();
            }
            max = Math.max(max, ++zeros.count);
            max = Math.max(max, ++ones.count);
        }
        return max;
    }
    
    private static final class BinaryTrie {
        private BinaryTrie flip; 
        private BinaryTrie dontFlip;
        private int count;
        
        BinaryTrie flip() {
            return flip == null ? (flip = new BinaryTrie()) : flip;
        }

        BinaryTrie dontFlip() {
            return dontFlip == null ? (dontFlip = new BinaryTrie()) : dontFlip;
        }
    }
}
*/