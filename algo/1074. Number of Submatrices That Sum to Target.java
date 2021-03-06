/*
Given a matrix and a target, return the number of non-empty submatrices that sum to target.
A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.
Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.

Example 1:
Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
Output: 4
Explanation: The four 1x1 submatrices that only contain 0.

Example 2:
Input: matrix = [[1,-1],[-1,1]], target = 0
Output: 5
Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.

Example 3:
Input: matrix = [[904]], target = 0
Output: 0 

Constraints:
1 <= matrix.length <= 100
1 <= matrix[0].length <= 100
-1000 <= matrix[i] <= 1000
-10^8 <= target <= 10^8
*/


/*
    1) brute: 
        time: n^6, space: 1
        4 loops to pick 4 points and then 2 loops to calculate sum
        
    2) prefixSum DP in 2D matrix: 
        time n^4, space: n^2
        pick 4 points (start1, end1, start2, end2)
        to find sum in the curr subarray we can use prefixSum DP
        sum = curr + (left + right - diagnol)
        
    3) sliding window
        time: n^3, space: n
        
    reference:
        1) https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/
        2) https://leetcode.com/problems/subarray-sum-equals-k/
*/

class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] arr;
        int output = 0;
        
        for (int leftCol = 0; leftCol < cols; leftCol++) {                  // c
            arr = new int[rows];
            for (int rightCol = leftCol; rightCol < cols; rightCol++) {     // c
                for (int i = 0; i < rows; i++) {                            // r
                    arr[i] += matrix[i][rightCol];
                }
                output += subarraySumEqualsK(arr, target);
            }
        }
        return output;
    }
    
    public int subarraySumEqualsK(int[] arr, int k) {           // https://leetcode.com/problems/subarray-sum-equals-k/
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 1);
        int output = 0, prefixSum = 0;
        
        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];
            if (map.containsKey(prefixSum - k)) {
                output += map.get(prefixSum - k);
            }
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return output;
    }
}