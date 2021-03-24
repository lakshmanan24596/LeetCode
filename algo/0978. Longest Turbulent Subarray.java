/*
Given an integer array arr, return the length of a maximum size turbulent subarray of arr.
A subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.

More formally, a subarray [arr[i], arr[i + 1], ..., arr[j]] of arr is said to be turbulent if and only if:
For i <= k < j:
arr[k] > arr[k + 1] when k is odd, and
arr[k] < arr[k + 1] when k is even.
Or, for i <= k < j:
arr[k] > arr[k + 1] when k is even, and
arr[k] < arr[k + 1] when k is odd.

Example 1:
Input: arr = [9,4,2,10,7,8,8,1,9]
Output: 5
Explanation: arr[1] > arr[2] < arr[3] > arr[4] < arr[5]

Example 2:
Input: arr = [4,8,12,16]
Output: 2

Example 3:
Input: arr = [100]
Output: 1
 
Constraints:
1 <= arr.length <= 4 * 104
0 <= arr[i] <= 109
*/



/*
    logic: DP, sliding window
        tabulation space optimized
        states: prevCondition, prevSize
        space: O(n) to O(1) --> curr state depends only on prev state
        time: n
    
    Implementation:
        prevCondition --> -1, 0, 1 --> decreasing, equal, increasing
        currSize --> sliding window size
*/

class Solution {
    public int maxTurbulenceSize(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int prevCondition = 0; 
        int currSize = 1, maxSize = 1;
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                currSize = (prevCondition != -1) ? currSize + 1 : 2;
                prevCondition = -1;
            } else if (arr[i] > arr[i - 1]) {
                currSize = (prevCondition != 1) ? currSize + 1 : 2;
                prevCondition = 1;
            } else {
                currSize = 1;
                prevCondition = 0;
            }
            maxSize = Math.max(maxSize, currSize);
        }
        return maxSize;
    }
}