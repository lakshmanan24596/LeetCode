/*
Given an array nums which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays.
Write an algorithm to minimize the largest sum among these m subarrays.

Example 1:
Input: nums = [7,2,5,10,8], m = 2
Output: 18
Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.

Example 2:
Input: nums = [1,2,3,4,5], m = 2
Output: 9

Example 3:
Input: nums = [1,4,4], m = 3
Output: 4

Constraints:
1 <= nums.length <= 1000
0 <= nums[i] <= 106
1 <= m <= min(50, nums.length)
*/



/*
    Same like problem 1231. Divide Chocolate
    
    DP memo
        time: n * m * n
        space: n * m
*/
/*
class Solution {
    int[] nums, prefixSum;
    Integer[][] DP;
    int m, n;
    
    public int splitArray(int[] nums, int m) {
        this.nums = nums;
        this.n = nums.length;
        this.m = m;
        this.prefixSum = new int[n + 1];
        this.DP = new Integer[n][m - 1];
        
        for (int i = 0; i < n; i++) {
            prefixSum[i+1] = prefixSum[i] + nums[i];
        }
        return splitArrayUtil(0, 0);
    }
    
    public int splitArrayUtil(int startIndex, int currM) {  
        if (currM == m - 1) {
            return getSum(startIndex, n - 1);
        }
        if (DP[startIndex][currM] != null) {
            return DP[startIndex][currM];
        }
        int output = Integer.MAX_VALUE;
        int currOutput;
        int remainingM = m - (currM + 1);
        
        for (int i = startIndex; i < n - remainingM; i++) {
            currOutput = getSum(startIndex, i);
            currOutput = Math.max(currOutput, splitArrayUtil(i + 1, currM + 1));    // currOutput is max
            output = Math.min(output, currOutput);                                  // output is min
        }
        return DP[startIndex][currM] = output;
    }
    
    public int getSum(int start, int end) {
        return prefixSum[end + 1] - prefixSum[start];
    }
}
*/


/*
    Binary search the answer range
        right = sum of all nums[i]
        left = Math.max(right / m, maxVal);
    
    time: n * log(sum - maxVal)
    space: 1
*/
class Solution {
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int left, right = 0, mid;
        int output = Integer.MAX_VALUE, maxVal = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i++) {
            right += nums[i];
            maxVal = Math.max(maxVal, nums[i]);
        }
        left = Math.max(right / m, maxVal);
        
        while (left <= right) {                     // binary search answer range
            mid = left + ((right - left) / 2);
            if (checkSplit(mid, nums, m, n)) {
                output = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return output;
    }
    
    public boolean checkSplit(int splitSum, int[] nums, int m, int n) { // check split possible with "splitSum"
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (sum > splitSum) {
                sum = nums[i];
                m--;
                if (m == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}