/*
Given an array of integers, return the maximum sum for a non-empty subarray (contiguous elements) with at most one element deletion. 
In other words, you want to choose a subarray and optionally delete one element from it so that there is still at least one element left and the sum of the remaining elements is maximum possible.
Note that the subarray needs to be non-empty after deleting one element.

Example 1:
Input: arr = [1,-2,0,3]
Output: 4
Explanation: Because we can choose [1, -2, 0, 3] and drop -2, thus the subarray [1, 0, 3] becomes the maximum value.

Example 2:
Input: arr = [1,-2,-2,3]
Output: 3
Explanation: We just choose [3] and it's the maximum sum.

Example 3:
Input: arr = [-1,-1,-1,-1]
Output: -1
Explanation: The final subarray needs to be non-empty. You can't choose [-1] and delete -1 from it, then get an empty subarray to make the sum equals to 0.

Constraints:
1 <= arr.length <= 105
-104 <= arr[i] <= 104
*/


/*
    Logic: if we delete a index i, then output = kadane(0 to i-1) + kadane (i+1 to n-1) 
    ex-1: if we delete at index 1, then output = kadane(0 to 0 index) + kadane(2 to 3 index) = 1 + 3 = 4
    
    1) n^2 solution:
        For each index we need to check remaining all elements in left and right, so time = n^2
        
    2) n solution:    
        a) find maxEndHere array
        b) similarly find maxStartHere array by iterating the given array in reverse
        c) now we can just check maxEndHere[i-1] + maxStartHere[i+1]
*/
class Solution {
    public int maximumSum(int[] arr) {
        int n = arr.length;
        int[] maxEndHere = new int[n];
        int[] maxStartHere = new int[n];
        int output = Integer.MIN_VALUE, currOutput;
        
        maxEndHere[0] = arr[0];
        output = Math.max(output, maxEndHere[0]); 
        for (int i = 1; i < n; i++) {
            maxEndHere[i] = Math.max(arr[i], arr[i] + maxEndHere[i-1]);         // kadane algo
            output = Math.max(output, maxEndHere[i]);                           // no deletion case
        }
        
        maxStartHere[n-1] = arr[n-1];
        for (int i = n-2; i >= 0; i--) {
            maxStartHere[i] = Math.max(arr[i], arr[i] + maxStartHere[i+1]);     // similar to maxEndHere
        }
        
        for (int i = 1; i < n-1; i++) { 
            currOutput = maxEndHere[i-1] + maxStartHere[i+1];   // main logic: delete i and then check left and right
            output = Math.max(output, currOutput);
        }
        return output;
    }
}