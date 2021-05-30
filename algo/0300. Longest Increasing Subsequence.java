/*
Given an integer array nums, return the length of the longest strictly increasing subsequence.
A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. 
For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].

Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Example 2:
Input: nums = [0,1,0,3,2,3]
Output: 4

Example 3:
Input: nums = [7,7,7,7,7,7,7]
Output: 1

Constraints:
1 <= nums.length <= 2500
-104 <= nums[i] <= 104
 
Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
*/



/*
    1) recursion     --> O(2^n)
    2) DP            --> O(n^2)
    3) Binary search --> O(n*log n)
*/


/*
// DP - O(n^2) time, O(n) space
class Solution 
{
    public int lengthOfLIS(int[] nums) 
    {
        int n = nums.length;
        int[] DP = new int[n];
        int output = 0;

        for(int i = 0; i < n; i++) 
        {
            DP[i] = 1;
            for(int j = 0; j < i; j++) 
            {
                if(nums[j] < nums[i] && DP[j] + 1 > DP[i]) {
                    DP[i] = DP[j] + 1; 
                }
            }
            output = Math.max(output, DP[i]);
        }
        return output;
    }
}
*/


// Binary search - O(n*logn) time, O(n) space
class Solution 
{
    public int lengthOfLIS(int[] nums) 
    {
        int n = nums.length;
        if(n == 0) {
            return 0;
        }
        
        int[] tailTable = new int[n];       // keep tail table in ascending sorted order
        tailTable[0] = nums[0];
        int length = 1;
        
        for(int i = 1; i < n; i++)
        {
            if(nums[i] <= tailTable[0]) {
                tailTable[0] = nums[i];
            }
            else if(nums[i] > tailTable[length-1]) {
                tailTable[length++] = nums[i];
            }
            else {
                int ceilIndex = ceilBinarySearch(tailTable, 0, length - 1, nums[i]);
                tailTable[ceilIndex] = nums[i];
            }
        }
        return length;
    }
    
    public int ceilBinarySearch(int[] arr, int low, int high, int value)
    {
        if(low > high) {
            return -1;
        }
        
        int mid = (low + high) / 2;
        if(value > arr[mid] && value <= arr[mid+1] ) {
            return mid + 1; 
        }
        
        if(value > arr[mid]) {
            return ceilBinarySearch(arr, mid+1, high, value);
        } 
        else {
            return ceilBinarySearch(arr, low, mid-1, value);
        }    
    }
}