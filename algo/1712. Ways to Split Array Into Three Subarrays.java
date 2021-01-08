/*
A split of an integer array is good if:
The array is split into three non-empty contiguous subarrays - named left, mid, right respectively from left to right.
The sum of the elements in left is less than or equal to the sum of the elements in mid, and the sum of the elements in mid is less than or equal to the sum of the elements in right.
Given nums, an array of non-negative integers, return the number of good ways to split nums. As the number may be too large, return it modulo 109 + 7.

Example 1:
Input: nums = [1,1,1]
Output: 1
Explanation: The only good way to split nums is [1] [1] [1].

Example 2:
Input: nums = [1,2,2,2,5,0]
Output: 3
Explanation: There are three good ways of splitting nums:
[1] [2] [2,2,5,0]
[1] [2,2] [2,5,0]
[1,2] [2,2] [5,0]

Example 3:
Input: nums = [3,2,1]
Output: 0
Explanation: There is no good way to split nums.

Constraints:
3 <= nums.length <= 105
0 <= nums[i] <= 104
*/



/*
// recursion exponential
class Solution
{
    int[] prefixSumArr;
    int n;
    int k = 3;

    public int waysToSplit(int[] nums) 
    {
        n = nums.length;
        prefixSumArr = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            prefixSumArr[i] = prefixSumArr[i - 1] + nums[i - 1];
        }
        return recur(0, 0, 0);
    }
    
    public int recur(int currIndex, int level, int prevSum) // similar to 813. Largest Sum of Averages
    {
        if(level == k) {
            return 1;
        }
        int output = 0;
        for(int nextIndex = currIndex + 1; nextIndex <= n; nextIndex++)
        {
            if(level == k-1 && nextIndex != n) {    // because in the last partition, we should select all the remaining elements 
                continue;
            }
            int currSum = prefixSumArr[nextIndex] - prefixSumArr[currIndex];
            if(currSum >= prevSum) {
                output += recur(nextIndex, level + 1, currSum);
            }
        }
        return output;
    }
}
*/


/*
    1) n^2 --> we need to find left and right wall by iterating (2 pointer technique)
    2) n * logn --> left wall (n time) and right wall (nlogn time) using binary search
    https://leetcode.com/problems/ways-to-split-array-into-three-subarrays/discuss/999113/JavaScala-Detailed-Explanation-Prefix-Sum-Binary-Search
*/
/*
class Solution
{
    int[] prefixSum;
    int n;
    
    public int waysToSplit(int[] nums) 
    {
        int MOD = (int) (1e9 + 7);
        n = nums.length;
        
        prefixSum = new int[n];
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; ++i) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        
        int output = 0;
        for (int i = 1; i < n - 1; i++) 
        {
            if (prefixSum[i - 1] > (prefixSum[n - 1] - prefixSum[i - 1])) {   // early termination
                break;
            }
    
            int j = binarySearch(prefixSum[i - 1], i, true);
            int k = binarySearch(prefixSum[i - 1], i, false);

            if (j != -1 && k != -1) {    // both are satisfied
                output += k - j + 1;     // main logic
                output %= MOD;
            }
        }
        return output;
    }
    
    public int binarySearch(int leftSum, int index, boolean isSearchLeft) {

        int l = index, r = n - 2;
        int res = -1;

        while (l <= r) 
        {
            int m = (l + r) / 2;
            int midSum = prefixSum[m] - prefixSum[index - 1];
            int rightSum = prefixSum[n - 1] - prefixSum[m];

            if (leftSum <= midSum && midSum <= rightSum) 
            {
                res = m;
                if (isSearchLeft) {
                    r = m - 1;
                }           
                else {
                    l = m + 1;
                }
            } 
            else if (leftSum > midSum) {
                l = m + 1;
            } 
            else {
                r = m - 1;
            }
        }
        return res;
    }
}
*/


/*
    3n time, 1 space
    for each point i, we find the minimum (j) and maximum (k) boundaries 
    j and k are always moving forward, so time = n
    https://leetcode.com/problems/ways-to-split-array-into-three-subarrays/discuss/999257/C%2B%2BJavaPython-O(n)-with-picture  
    
    For all those who did not understand the solution:
    In the final solution,
    subArray1 : nums[0,i]
    subArray2 : minimum_length --> nums[i+1,j]
    maximum_length --> nums[i+1,k-1]
    subarray3 : nums[k,size-1] and,
    i < (j <= k) < size-1

    Explanation:
    convert nums to prefix_sum Array.

    Note: minimum sum(subArray2) --> nums[j] - nums[i]
    maximum sum(subArray2) --> nums[k-1] - nums[i]

    Increase j till
    nums[j] - nums[i] < nums[i]
    i.e. minimum sum(subArray2) < sum(subArray1)

    Increase k till
    nums[size-1] - nums[k] >= nums[k] - nums[i]
    sum(subArray3) >= maximum sum(subArray2)

    increment answer by
    (k - i) - (j-i) = k - j
    i.e
    res = res + maxlen(subArray2) - minlen(subArray2) in each step

    DO 1,2 for all i from 0 to n-3
*/    
class Solution 
{
    public int waysToSplit(int[] nums) 
    {
        int n = nums.length;
        for (int i = 1; i < n; ++i) {
            nums[i] += nums[i - 1];
        }
        
        int j = 0, k = 0, output = 0;
        for (int i = 0; i < n - 2; i++)     // Time: 3n
        {
            while (j <= i || (j < n - 1 && nums[j] - nums[i] < nums[i])) {
                j++;
            }
            while (k < j || ( k < n - 1 && nums[k] - nums[i] <= nums[n - 1] - nums[k])) {
                k++;
            }
            output += k - j;
            output %= 1000000007;
        }    
        return output;
    } 
}
