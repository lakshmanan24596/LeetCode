/*
Given an array A of integers, return the length of the longest arithmetic subsequence in A.
Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1, 
and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).

Example 1:
Input: A = [3,6,9,12]
Output: 4
Explanation: 
The whole array is an arithmetic sequence with steps of length = 3.

Example 2:
Input: A = [9,4,7,2,10]
Output: 3
Explanation: 
The longest arithmetic subsequence is [4,7,10].

Example 3:
Input: A = [20,1,15,3,10,5,8]
Output: 4
Explanation: 
The longest arithmetic subsequence is [20,15,10,5].

Constraints:
2 <= A.length <= 1000
0 <= A[i] <= 500
*/


/*
    DP 
    similar to LIS and use extra hashmap for each array index
    Time: n^2, Space: n^2
*/
class Solution {
    public int longestArithSeqLength(int[] A) {
        int output = 0;
        int diff, count;
        
        Map<Integer, Integer>[] mapArr = new HashMap[A.length]; // (diff, count) for each array index
        for (int i = 0; i < A.length; i++) {
            mapArr[i] = new HashMap<Integer, Integer>();
        }
        
        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                diff = A[i] - A[j];
                count = mapArr[j].getOrDefault(diff, 0);
                mapArr[i].put(diff, count + 1);
                output = Math.max(output, count + 1);
            }
        }
        return output + 1;
    }
}


/*
// Instead of hashmap we can use 2D array of size [n][(2*500)+1] 
class Solution {
    public int longestArithSeqLength(int[] A) {
        int[][] dp = new int[A.length][1001];
        int max = 0;
        for(int i=0 ; i<A.length ; i++) {
            for(int j=0 ; j<i ; j++) {
                int diff = A[i]-A[j]+500;
                dp[i][diff] = Math.max(dp[i][diff], dp[j][diff]+1);
                max = Math.max(max, dp[i][diff]);
            }
        }
        return max==0?0:max+1;
    }
}
*/