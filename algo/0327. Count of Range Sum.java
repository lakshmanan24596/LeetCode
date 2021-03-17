/*
Given an integer array nums and two integers lower and upper, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j inclusive, where i <= j.

Example 1:
Input: nums = [-2,5,-1], lower = -2, upper = 2
Output: 3
Explanation: The three ranges are: [0,0], [2,2], and [0,2] and their respective sums are: -2, -1, 2.

Example 2:
Input: nums = [0], lower = 0, upper = 0
Output: 1

Constraints:
1 <= nums.length <= 104
-231 <= nums[i] <= 231 - 1
-3 * 104 <= lower <= upper <= 3 * 104

Follow up: A naive algorithm of O(n2) is trivial, Could you do better than that?
*/

class Solution {
    int count = 0;
    int lower, upper;
    long[] sum, temp;
    
    public int countRangeSum(int[] nums, int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
        int n = nums.length;
        sum = new long[n + 1];
        temp = new long[n + 1];
        
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + (long) nums[i];
        }
        mergesort(0, n);
        return count;
    }
    
    private void mergesort(int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            mergesort(start, mid);
            mergesort(mid + 1, end);
            merge(start, mid, end);
        }
    }
    
    private void merge(int start, int mid, int end) {
        int index = start;
        int left = start, right = mid + 1;
        int low = mid + 1, high = mid + 1; 
        
        for (; left <= mid; left++) {
            while (low <= end && sum[low] - sum[left] < lower) {
                low++;
            }
            while (high <= end && sum[high] - sum[left] <= upper) {
                high++;
            }
            while (right <= end && sum[right] < sum[left]) {
                temp[index++] = sum[right++];
            }
            temp[index++] = sum[left];
            count += high - low;
        }
        while (right <= end) {
            temp[index++] = sum[right++];
        }
        
        for (int i = start; i <= end; i++) {
            sum[i] = temp[i];
        }
    }
}
