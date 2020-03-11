/*
Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
Note: You are not suppose to use the library's sort function for this problem.

Example:
Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?
*/

class Solution 
{
    public void sortColors(int[] nums) 
    {
        // O(2n) --> 1) take count in first pass and then over write the array in second pass
        // O(2n) --> 2) swap 0,2 in first pass and then swap 1,2 in second pass
        
        // O(n)  --> 3) single pass with 3 pointers start, mid, end
        
        int start = 0, mid = 0, end = nums.length-1;
        
        while(mid <= end)
        {
            if(nums[mid] == 0)
            {
                if(nums[start] != nums[mid])
                    swap(nums, start, mid);     // swap left side
                start++;
                mid++;
            }
            else if (nums[mid] == 1)
            {
                mid++;
            }
            else
            {
                if(nums[mid] != nums[end])
                    swap(nums, mid, end);     // swap right side
                end--;
            }
        }
    }
    
    public void swap(int[] nums, int a, int b)
    {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}