/*
Your are given an array of positive integers nums.
Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.

Example 1:
Input: nums = [10, 5, 2, 6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].

Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
Note:
0 < nums.length <= 50000.
0 < nums[i] < 1000.
0 <= k < 10^6.
*/

/*
    1) Brute:
    Time: n^2
    
    2) Logic: sliding window
    Time: n
    Space: 1
    The idea is, always keep an max-product-window less than K
    Sliding window logic is similar to subarray sum problem
*/
class Solution 
{
    public int numSubarrayProductLessThanK(int[] nums, int k) 
    {
        if(k <= 1) {                        // corner case
            return 0;
        }
        int start = 0;
        int prod = 1;
        int output = 0;
        
        for(int i = 0; i < nums.length; i++)
        {
            while(nums[i] * prod >= k) {
                prod /= nums[start++];      // start++ is done to move the windown
            }
            if(prod == 0) {                 // when k is smaller than nums[i] corner case
                prod = 1;                   // reset
                continue;
            }
            prod *= nums[i];
            output += i - start + 1;        // length of the windown is the currOutput
        }
        return output;
    }
}
