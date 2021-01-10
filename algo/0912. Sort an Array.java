/*
Given an array of integers nums, sort the array in ascending order.

Example 1:
Input: nums = [5,2,3,1]
Output: [1,2,3,5]

Example 2:
Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]
 
Constraints:
1 <= nums.length <= 50000
-50000 <= nums[i] <= 50000
*/


// https://leetcode.com/problems/sort-an-array/discuss/492042/7-Sorting-Algorithms-(quick-sort-top-downbottom-up-merge-sort-heap-sort-etc.)

// quick sort: n*logn avg case and n^2 worst case
class Solution 
{
    public int[] sortArray(int[] nums) 
    {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }
    
    public void quickSort(int[] nums, int l, int r) 
    {
        if (l >= r) {
            return;
        }
        int partitionIndex = partition(nums, l, r);
        quickSort(nums, l, partitionIndex - 1);
        quickSort(nums, partitionIndex + 1, r);
    }
    
    public int partition(int[] nums, int left, int right) 
    {
        int pivot = left;
        int i = left, j = right;
        
        while (i < j) 
        {
            while (i < right && nums[i] <= nums[pivot]) {
                i++;
            }
            while (j > left && nums[j] > nums[pivot]) {
                j--;
            }
            if(i < j) {
                swap(nums, i, j);
            }
        }
        swap(nums, pivot, j);
        return j;
    }
    
    public void swap(int[] nums, int i, int j)
    {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
