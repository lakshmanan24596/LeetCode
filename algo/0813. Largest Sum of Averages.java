/*
We partition a row of numbers A into at most K adjacent (non-empty) groups, then our score is the sum of the average of each group. 
What is the largest score we can achieve?
Note that our partition must use every number in A, and that scores are not necessarily integers.

Example:
Input: 
A = [9,1,2,3,9]
K = 3
Output: 20

Explanation: 
The best choice is to partition A into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
We could have also partitioned A into [9, 1], [2], [3, 9], for example.
That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.

Note:
1 <= A.length <= 100.
1 <= A[i] <= 10000.
1 <= K <= A.length.
Answers within 10^-6 of the correct answer will be accepted as correct.
*/


// without DP --> Time: n!
// with DP --> Time: n * k, Space: n * k
class Solution 
{
    int[] prefixSumArr;
    double[][] DP;
    int k, n;
    
    public double largestSumOfAverages(int[] A, int K) 
    {
        this.k = K;
        n = A.length;
        prefixSumArr = new int[n+1];
        for(int i = 1; i <= n; i++) {  
            prefixSumArr[i] = prefixSumArr[i-1] + A[i-1];
        }
        DP = new double[n+1][k];
        return recur(0, 0);
    }
    
    public double recur(int currIndex, int level)
    {
        if(level == k) {
            return 0;
        }
        if(DP[currIndex][level] > 0.0) {
            return DP[currIndex][level];
        }
        
        double output = 0, currOutput, currPartitionScore;
        for(int nextIndex = currIndex + 1; nextIndex <= n; nextIndex++)
        {
            if(level == k-1 && nextIndex != n) {    // because in the last partition, we should select all the remaining elements 
                continue;
            }
            currPartitionScore = (prefixSumArr[nextIndex] - prefixSumArr[currIndex]) / (double)(nextIndex - currIndex);
            currOutput = currPartitionScore + recur(nextIndex, level + 1);
            output = Math.max(output, currOutput);
        }
        return DP[currIndex][level] = output;
    }
}
