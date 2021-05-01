/*
Given an integer array nums, return the number of reverse pairs in the array.
A reverse pair is a pair (i, j) where 0 <= i < j < nums.length and nums[i] > 2 * nums[j].

Example 1:
Input: nums = [1,3,2,3,1]
Output: 2

Example 2:
Input: nums = [2,4,3,5,1]
Output: 3

Constraints:
1 <= nums.length <= 5 * 104
231 <= nums[i] <= 231 - 1
*/


/*
    Merge sort
    time: n*logn + n
    space: n
    https://www.geeksforgeeks.org/counting-inversions/
*/

class Solution {
    int[] nums;
    int output = 0;
    
    public int reversePairs(int[] nums) {
        this.nums = nums;
        mergeSort(0, nums.length - 1);
        return output;
    }
    
    public void mergeSort(int left, int right) {
        if (left < right) {
            int mid = left + ((right - left) / 2);
            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            
            int i = left, j = mid + 1;
            while (i <= mid && j <= right) {        // calculate output before mergeTwoSortedList()
                if (nums[i] > (2L * nums[j])) {     // main logic
                    output += mid - i + 1;          // main logic
                    j++;
                } else {
                    i++;
                }
            }
            mergeTwoSortedList(left, mid, right);
        }
    }
    
    public void mergeTwoSortedList(int l, int m, int r) {       // normal merge, with no changes
        int[] left = Arrays.copyOfRange(nums, l, m + 1);
        int[] right = Arrays.copyOfRange(nums, m + 1, r + 1);
        int i = 0, j = 0, k = l;
        
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                nums[k++] = left[i++];
            } else {
                nums[k++] = right[j++];
            }
        }
        while (i < left.length) {
            nums[k++] = left[i++];
        }
        while (j < right.length) {
            nums[k++] = right[j++];
        }
    }
}