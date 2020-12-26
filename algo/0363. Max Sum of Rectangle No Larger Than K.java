/*
Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:
Input: matrix = [[1,0,1],[0,-2,3]], k = 2
Output: 2 
Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
             and 2 is the max number no larger than k (k = 2).

Note:
The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?
*/


/*
    https://www.youtube.com/watch?v=-FgseNO-6Gk
    https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/discuss/741420/Java-3ms-beats-100-No-binary-search-No-treeSet
*/
class Solution 
{
    public int maxSumSubmatrix(int[][] matrix, int k)   // Time: c*c*r*log(r) and Space: r
    {
        int output = Integer.MIN_VALUE, currOutput;
        int row = matrix.length;
        int col = matrix[0].length;
        int[] runningRowDP;
        
        for(int right = 0; right < col; right++)            // c
        {
            runningRowDP = new int[row];
            for(int left = right; left >= 0; left--)        // c
            {
                for (int i = 0; i < row; i++)               // r
                {
                    runningRowDP[i] += matrix[i][left];
                }
                currOutput = kadane(runningRowDP, k);       // r * log(r) using hashSet algo
                output = Math.max(output, currOutput);
                if(output == k) {
                    return output;
                }
            }
        }
        return output;
    }
    
    public int kadane(int[] arr, int k)
    {
        int currSum = 0;
        int maxSum = Integer.MIN_VALUE;
        
        for(int i = 0; i < arr.length; i++)                     // kadane --> O(n) time
        {
            currSum = Math.max(arr[i], currSum + arr[i]);
            maxSum = Math.max(maxSum, currSum);
            if(maxSum == k) {
                return maxSum;
            }
        }
        if(maxSum < k) {
            return maxSum;
        } 
        else {
            return bruteForce(arr, k);
        }
    }
    
    /*
        Using kadane we can find max sum subarray. But we cannot find max sum subarray with sum <= k. So brute force algo is used here.
        Ex: arr = -2, -3, 12 and k = 8 --> kadane will return 12 but the answer is 7.
        Instead of brute force, we can also use hashSet --> n * log(n)
        https://leetcode.com/problems/subarray-sum-equals-k/
        https://www.geeksforgeeks.org/maximum-sum-subarray-having-sum-less-than-or-equal-to-given-sum-using-set/?ref=rp
    */
    
    public int bruteForce(int[] arr, int k)                     // brute force --> O(n^2) time
    {
        int currSum;
        int maxSum = Integer.MIN_VALUE;
        
        for (int i = 0; i < arr.length; i++)                
        {
            currSum = 0;
            for (int j = i; j < arr.length; j++) 
            {
                currSum += arr[j];
                if (currSum <= k) {
                    maxSum = Math.max(maxSum, currSum);
                }
            }
        }
        return maxSum;
    }
}
