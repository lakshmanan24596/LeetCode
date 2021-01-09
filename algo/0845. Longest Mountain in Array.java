/*
You may recall that an array arr is a mountain array if and only if:
arr.length >= 3
There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
Given an integer array arr, return the length of the longest subarray, which is a mountain. 
Return 0 if there is no mountain subarray.

Example 1:
Input: arr = [2,1,4,7,3,2,5]
Output: 5
Explanation: The largest mountain is [1,4,7,3,2] which has length 5.

Example 2:
Input: arr = [2,2,2]
Output: 0
Explanation: There is no mountain.
 
Constraints:
1 <= arr.length <= 104
0 <= arr[i] <= 104

Follow up:
Can you solve it using only one pass?
Can you solve it in O(1) space?
*/


/*
    1) n^2, 1 (brute force)
    2) 3n, 2n
    3) n, 1
*/

/*
// 3n, 2n
class Solution 
{
    public int longestMountain(int[] arr) 
    {
        int n = arr.length;
        if(n < 3) {
            return 0;
        }
        
        int[] left = new int[n];
        for(int i = 1; i < n; i++) {
            left[i] = (arr[i] > arr[i - 1]) ? left[i - 1] + 1 : 0;
        }
        
        int[] right = new int[n];
        for(int i = n - 2; i >= 0; i--) {
            right[i] = (arr[i] > arr[i + 1]) ? right[i + 1] + 1 : 0;
        }
        
        int output = 0;
        for(int i = 1; i <= n - 1; i++) 
        {
            if(left[i] > 0 && right[i] > 0) {
                output = Math.max(output, left[i] + right[i] + 1);
            }
        }
        return output;
    }
}
*/

// n, 1 (single pass solution)
class Solution
{
    public int longestMountain(int[] arr) 
    {
        int n = arr.length;
        if(n < 3) {
            return 0;
        }
        
        int up = 0, down = 0, output = 0;
        for(int i = 1; i < n; i++)
        {
            if((down > 0 && arr[i] > arr[i-1]) || arr[i] == arr[i-1])   // main logic: (prev down and curr up) or (prev == curr)
            {
                up = 0;
                down = 0;
            }
            
            if(arr[i] > arr[i-1]) {
                up++;
            } 
            else if(arr[i] < arr[i-1]) {
                down++;
            }
            
            if(up > 0 && down > 0) {
                output = Math.max(output, up + down + 1);
            }
        }
        return output;
    }
}
