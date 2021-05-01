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


/*
    Merge sort
        time: n*logn + n
        space: n
        https://www.geeksforgeeks.org/counting-inversions/
    
    similar problems:
        1) https://leetcode.com/problems/reverse-pairs/
        2) https://leetcode.com/problems/count-of-smaller-numbers-after-self/
        
    Implementation of countSum():
        For each i (left side), we need to find two indices lo and up (right side)
            lo is the first index satisfy >= lower range
            up is the first index satisfy > upper range
            now, output += up - lo

    Note: 
        If only +ve numbers are present then no need merge step
        because for only +ve, the prefixSum[] will be always sorted
*/

class Solution {
    int output = 0;
    long[] prefixSum;
    int[] indexArr;
    int lower, upper;
    
    public int countRangeSum(int[] nums, int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
        this.prefixSum = new long[nums.length + 1];
        this.indexArr = new int[nums.length + 1];
        
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
            indexArr[i + 1] = i + 1;
        }
        mergeSort(1, nums.length);
        return output;
    }
    
    public void mergeSort(int left, int right) {
        if (left == right) {
            long sum = prefixSum[indexArr[right]] - prefixSum[indexArr[left - 1]];
            if (sum >= lower && sum <= upper) {
                output++;
            }
        }
        else if (left < right) {
            int mid = left + ((right - left) / 2);
            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            countSum(left, mid, right);                             // calculate before merge
            mergeTwoSortedList(left, mid, right);
        }
    }
    
    public void countSum(int left, int mid, int right) {
        for (int i = left; i <= mid; i++) {
            int lo = mid + 1;
            while (lo <= right && prefixSum[indexArr[lo]] - prefixSum[indexArr[i - 1]] < lower) {
                lo++;
            }
            
            int up = lo;
            while (up <= right && prefixSum[indexArr[up]] - prefixSum[indexArr[i - 1]] <= upper) {
                up++;
            }
            output += up - lo;
        }
    }
    
    public void mergeTwoSortedList(int l, int m, int r) {           // normal merge, with no changes
        int[] left = Arrays.copyOfRange(indexArr, l, m + 1);
        int[] right = Arrays.copyOfRange(indexArr, m + 1, r + 1);
        int i = 0, j = 0, k = l;
        
        while (i < left.length && j < right.length) {
            if (prefixSum[left[i]] < prefixSum[right[j]]) {
                indexArr[k++] = left[i++];
            } else {
                indexArr[k++] = right[j++];
            }
        }
        while (i < left.length) {
            indexArr[k++] = left[i++];
        }
        while (j < right.length) {
            indexArr[k++] = right[j++];
        }
    }
}