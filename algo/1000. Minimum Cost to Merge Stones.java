/*
There are N piles of stones arranged in a row.  The i-th pile has stones[i] stones.
A move consists of merging exactly K consecutive piles into one pile, and the cost of this move is equal to the total number of stones in these K piles.
Find the minimum cost to merge all piles of stones into one pile.  
If it is impossible, return -1.

Example 1:
Input: stones = [3,2,4,1], K = 2
Output: 20
Explanation: 
We start with [3, 2, 4, 1].
We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1].
We merge [4, 1] for a cost of 5, and we are left with [5, 5].
We merge [5, 5] for a cost of 10, and we are left with [10].
The total cost was 20, and this is the minimum possible.

Example 2:
Input: stones = [3,2,4,1], K = 3
Output: -1
Explanation: After any merge operation, there are 2 piles left, and we can't merge anymore.  So the task is impossible.

Example 3:
Input: stones = [3,5,1,2,6], K = 3
Output: 25
Explanation: 
We start with [3, 5, 1, 2, 6].
We merge [5, 1, 2] for a cost of 8, and we are left with [3, 8, 6].
We merge [3, 8, 6] for a cost of 17, and we are left with [17].
The total cost was 25, and this is the minimum possible.

Note:
1 <= stones.length <= 30
2 <= K <= 30
1 <= stones[i] <= 100
*/


/*
    Ques-1:
        https://www.geeksforgeeks.org/connect-n-ropes-minimum-cost/
        We can merge any stones
        So greedy works by using minHeap
        
    Ques-2:
        https://www.geeksforgeeks.org/matrix-chain-multiplication-dp-8/
        This question is exactly similar to matrix chain multiplication
        It is also similar to burst balloons problem.
        Here, 2 consecutive elements (K = 2) can be merged.
        So greedy wont work and it needs "DP + DC"
        States of DP: "leftIndex, rightIndex" and solve it using Divide and Conquer
        Time: n^3, Space: n^2
        Solution:
            a) https://www.youtube.com/watch?v=prx1psByp7U
            b) https://www.youtube.com/watch?v=vgLJZMUfnsU
            
    Ques-3:
        Here, K consecutive stones can be merged.
        Using the ques-2 logic, we can just alter the code to work for any K value
        Differences is mentioned in the implementation
        Time: n^3, Space: n^2
        
    Difference between 3 questions:
        1) any order            --> greedy
        2) 2 consecutive order  --> DP + DC
        3) k consecutive order  --> DP + DC + alteration to above solution
*/


/*
    Solution for QUESTION-2 mentioned above
    which is merging 2-consecutive elements (K = 2), exactly similar to matrix chain multiplication
    We can also refer burst balloons for divide and conquer strategy
    Below solved solution is memorization + recursion (It can be solved using tabulation also using 3 loops)
    Time: n^3, Space: n^2
*/
/*
class Solution {
    int n;
    int[] prefixSum;
    int[][] DP;
    
    public int mergeStones(int[] stones, int K) {   // this solution will work only if K = 2
        n = stones.length;
        DP = new int[n + 1][n + 1];
        prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = stones[i - 1] + prefixSum[i - 1];
        }
        return recur(1, stones.length);
    }
    
    public int recur(int left, int right) {
        if (left == right) {
            return 0;
        }
        if (DP[left][right] > 0) {
            return DP[left][right];
        }
        
        int output = Integer.MAX_VALUE, currOutput;
        int currSum = prefixSum[right] - prefixSum[left - 1];   // sum of elements from left to right
        
        for (int i = left; i < right; i++) 
        {
            currOutput =  currSum + recur(left, i) + recur(i + 1, right);   // divide and conquer
            output = Math.min(output, currOutput);
        }
        return DP[left][right] = output;
    }
}
*/


/*
    Solution for QUESTION-3 mentioned above (It is the actual question for this problem)
    Technique: DP + DC
    Time: n^3, Space: n^2
    
    Difference compared to above solution:
    a) cannot be merged into 1 stone
        if ((n - 1) % (K - 1) != 0) then return -1;
    b) base case in recursion:
        if (right - left + 1 < K) then return 0;
    c) main logic of currSum:
        calculate currSum only if ((right - left) % (K - 1) == 0) 
    d) main logic of split:
        instead of i++ in the split, we should split only at i += K - 1
        because remaining splits are not valid
*/
class Solution {
    int n, K;
    int[] prefixSum;
    int[][] DP;
    
    public int mergeStones(int[] stones, int K) {
        n = stones.length;
        this.K = K;
        if ((n - 1) % (K - 1) != 0) {   // cannot be merged into 1 stone
            return -1;
        }
        
        DP = new int[n + 1][n + 1];
        prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = stones[i - 1] + prefixSum[i - 1];
        }
        return recur(1, stones.length);
    }
    
    public int recur(int left, int right) {
        if (right - left + 1 < K) {
            return 0;
        }
        if (DP[left][right] > 0) {
            return DP[left][right];
        }
        
        int output = Integer.MAX_VALUE, currOutput;
        int currSum = ((right - left) % (K - 1) == 0) ? (prefixSum[right] - prefixSum[left - 1]) : 0;
        
        for (int i = left; i < right; i += K - 1)
        {
            currOutput = currSum + recur(left, i) + recur(i + 1, right);
            output = Math.min(output, currOutput);
        }
        return DP[left][right] = output;
    }
}