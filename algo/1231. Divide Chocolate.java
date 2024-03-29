/*
You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.
You want to share the chocolate with your K friends so you start cutting the chocolate bar into K+1 pieces using K cuts, each piece consists of some consecutive chunks.
Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.
Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.

Example 1:
Input: sweetness = [1,2,3,4,5,6,7,8,9], K = 5
Output: 6
Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]

Example 2:
Input: sweetness = [5,6,7,8,9,1,2,3,4], K = 8
Output: 1
Explanation: There is only one way to cut the bar into 9 pieces.

Example 3:
Input: sweetness = [1,2,2,1,2,2,1,2,2], K = 2
Output: 5
Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]

Constraints:
0 <= K < sweetness.length <= 10^4
1 <= sweetness[i] <= 10^5
*/



/*
    Same like problem 410. Split Array Largest Sum
    
    1) DP memo
        time: O((n*k)*n)
        space: O(n*k)
        
    2) Greedy binary search answer range
        time: O(log(sum/k) * n)
        space: O(1)
*/


/*
class Solution {
    int[] prefixSum;
    int n;
    Integer[][] DP;
    
    public int maximizeSweetness(int[] sweetness, int K) {
        this.n = sweetness.length;
        this.prefixSum = new int[n + 1];
        this.DP = new Integer[n][K+2];
        
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + sweetness[i - 1];
        }
        return dfs(0, K + 1);
    }
    
    public int dfs(int startIndex, int remainingK) {
        if (remainingK == 1) {
            return prefixSum[n] - prefixSum[startIndex];
        }
        if (DP[startIndex][remainingK] != null) {
            return DP[startIndex][remainingK];
        }
        int currOutput;
        int maxOutput = Integer.MIN_VALUE;
        int currPartitionSweet;
        
        for (int nextIndex = startIndex; nextIndex <= n - remainingK; nextIndex++) {
            currOutput = dfs(nextIndex + 1, remainingK - 1);
            currPartitionSweet = prefixSum[nextIndex + 1] - prefixSum[startIndex];
            
            currOutput = Math.min(currOutput, currPartitionSweet); 
            maxOutput = Math.max(maxOutput, currOutput);
        }
        return DP[startIndex][remainingK] = maxOutput;
    }
}
*/

class Solution {
    public int maximizeSweetness(int[] sweetness, int K) {
        int sum = 0;
        for (int sweet : sweetness) {
            sum += sweet;
        }
        K += 1;
        int left = 1;
        int right = sum / K;
        int mid;
        int output = 1;
        
        while (left <= right) {
            mid = left + ((right - left) / 2);
            if (isCorrectPartition(mid, sweetness, K)) {
                output = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return output;
    }
    
    public boolean isCorrectPartition(int mid, int[] sweetness, int k) {
        int currSum = 0;
        
        for (int i = 0; i < sweetness.length; i++) {
            currSum += sweetness[i];
            if (currSum >= mid) {
                k--;
                currSum = 0;
                if (k == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}