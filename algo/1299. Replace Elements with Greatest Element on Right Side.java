/*
Given an array arr, replace every element in that array with the greatest element among the elements to its right, and replace the last element with -1.
After doing so, return the array.

Example 1:
Input: arr = [17,18,5,4,6,1]
Output: [18,6,6,6,1,-1]

Constraints:
1 <= arr.length <= 10^4
1 <= arr[i] <= 10^5
*/

class Solution 
{
    public int[] replaceElements(int[] arr) 
    {
        if(arr == null || arr.length == 0) {
            return arr;
        }
        
        int size = arr.length;
        int max = arr[size-1];
        arr[size-1] = -1;
        int prevMax;
        
        for(int i = size-2; i >= 0; i--)
        {
            prevMax = max;
            max = Math.max(max, arr[i]);
            arr[i] = prevMax;
        }
        return arr;
    }
}
