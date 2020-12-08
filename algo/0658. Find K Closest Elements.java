/*
Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. 
The result should also be sorted in ascending order.

An integer a is closer to x than an integer b if:
|a - x| < |b - x|, or
|a - x| == |b - x| and a < b

Example 1:
Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]

Example 2:
Input: arr = [1,2,3,4,5], k = 4, x = -1
Output: [1,2,3,4]

Constraints:
1 <= k <= arr.length
1 <= arr.length <= 104
Absolute value of elements in the array and x will not exceed 104
*/


/*
    1) Brute: Linear search --> Time: n + k
    2) Binary Search and 2 pointer technique--> Time: log(n) + k
*/

class Solution 
{
    public List<Integer> findClosestElements(int[] arr, int k, int x)   // Time: log(n) + k
    {
        int n = arr.length;
        if (x <= arr[0]) {
			return createOutputList(arr, 0, k - 1);
		} 
        if (x >= arr[n - 1]) {
			return createOutputList(arr, n - k, n - 1);
		}
        
        int index = binarySearch(arr, x);
        int i = index - 1;      // 2 pointer technique
        int j = index + 1;
        
        while(--k > 0)          // Binary search gives one output value. So now do k-1 times
        {
            int leftDiff = (i >= 0) ? x - arr[i] : Integer.MAX_VALUE;
            int rightDiff = (j < n) ? arr[j] - x: Integer.MAX_VALUE;
            
            if(leftDiff <= rightDiff) {
                i--;
            } else {
                j++;
            }
        }
        return createOutputList(arr, i + 1, j - 1);
    }
    
    public int binarySearch(int[] arr, int x)
    {
        int left = 0;
        int right = arr.length - 1;
        int mid;
        
        while(left < right - 1)  // stop when size <= 2
        {
            mid = (left + right) / 2;
            
            if(x < arr[mid]) {
                right = mid;    // left side
            } else {
                left = mid;     // right side
            }
        }
        
        return (x - arr[left]) <= (arr[right] - x) ? left : right;  // return index closer to x
    }
    
    public List<Integer> createOutputList(int[] arr, int start, int end)
    {
        List<Integer> outputList = new ArrayList<Integer>();
        for(int i = start; i <= end; i++) {
            outputList.add(arr[i]);
        }
        return outputList;
    }
}
