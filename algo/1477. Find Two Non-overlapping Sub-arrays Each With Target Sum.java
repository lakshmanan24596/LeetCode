/*
Given an array of integers arr and an integer target.
You have to find two non-overlapping sub-arrays of arr each with a sum equal target. 
There can be multiple answers so you have to find an answer where the sum of the lengths of the two sub-arrays is minimum.
Return the minimum sum of the lengths of the two required sub-arrays, or return -1 if you cannot find such two sub-arrays. 

Example 1:
Input: arr = [3,2,2,4,3], target = 3
Output: 2
Explanation: Only two sub-arrays have sum = 3 ([3] and [3]). The sum of their lengths is 2.

Example 2:
Input: arr = [7,3,4,7], target = 7
Output: 2
Explanation: Although we have three non-overlapping sub-arrays of sum = 7 ([7], [3,4] and [7]), but we will choose the first and third sub-arrays as the sum of their lengths is 2.

Example 3:
Input: arr = [4,3,2,6,2,3,4], target = 6
Output: -1
Explanation: We have only one sub-array of sum = 6.

Example 4:
Input: arr = [5,5,4,4,5], target = 3
Output: -1
Explanation: We cannot find a sub-array of sum = 3.

Example 5:
Input: arr = [3,1,1,1,5,1,2,1], target = 3
Output: 3
Explanation: Note that sub-arrays [1,2] and [2,1] cannot be an answer because they overlap.

Constraints:
1 <= arr.length <= 105
1 <= arr[i] <= 1000
1 <= target <= 108
*/



/*
    There are many corner cases in this question
        ex: 1,1,1,2,3,1,1 and target = 5
        output = 7 which is ((1,1,1,2), (3,1,1))
        
    1) prefix, suffix + sliding window
        time: 2n
        space: 2n
        logic:
            prefix[]   = -1, -1, -1, 4, 2,  2,  2  --> iterating left to right
            suffix[]   =  2,  2,  2, 2, 3, -1, -1  --> iterating right to left
            currOutput = prefix[i] + suffix[i+1], where both != -1
                       = 4 + 3
            output = min(currOutput) in each iteration
            
    2) prefix + sliding window
        time: n (one pass algo)
        space: n
        logic:
            prefix[] = -1, -1, -1,  4,  2,  2, 2     --> same as previous
            output   = -1, -1, -1, -1, -1, -1, 7     --> track overall minOutput
            main logic is, "curr sliding window is suffix and prefix[start - 1] is prefix"
        
        https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/discuss/686105/JAVA-or-Sliding-window-with-only-one-array-or-No-HasMap     
        https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/discuss/685486/JAVA-O(N)-Time-Two-Pass-Solution-using-HashMap.
*/

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] prefix = new int[n];
        Arrays.fill(prefix, Integer.MAX_VALUE);
        int sum = 0;
        int start = 0;
        int output = Integer.MAX_VALUE;
        int currOutput;
        
        for (int end = 0; end < n; end++) {
            sum += arr[end];
            while (sum >= target) {
                if (sum == target) {
                    prefix[end] = end - start + 1;
                    if (start > 0 && prefix[start - 1] != Integer.MAX_VALUE) {  // main logic to avoid overlap
                        currOutput = prefix[start - 1] + prefix[end];
                        output = Math.min(output, currOutput);
                    }
                }
                sum -= arr[start];
                start++;
            }
            if (end > 0) {
                prefix[end] = Math.min(prefix[end - 1], prefix[end]);
            }
        }
        return (output == Integer.MAX_VALUE) ? -1 : output;
    }
}