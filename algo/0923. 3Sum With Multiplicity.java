/*
Given an integer array arr, and an integer target, return the number of tuples i, j, k such that i < j < k and arr[i] + arr[j] + arr[k] == target.
As the answer can be very large, return it modulo 109 + 7.

Example 1:
Input: arr = [1,1,2,2,3,3,4,4,5,5], target = 8
Output: 20
Explanation: 
Enumerating by the values (arr[i], arr[j], arr[k]):
(1, 2, 5) occurs 8 times;
(1, 3, 4) occurs 8 times;
(2, 2, 4) occurs 2 times;
(2, 3, 3) occurs 2 times.

Example 2:
Input: arr = [1,1,2,2,2,2], target = 5
Output: 12
Explanation: 
arr[i] = 1, arr[j] = arr[k] = 2 occurs 12 times:
We choose one 1 from [1,1] in 2 ways,
and two 2s from [2,2,2,2] in 6 ways.

Constraints:
3 <= arr.length <= 3000
0 <= arr[i] <= 100
0 <= target <= 300
*/



/*
    1) brute force: n^3, 1
    2) binary search: n*n*logn, 1
    3) 2-pointer: n^2, 1
*/

class Solution {
    public int threeSumMulti(int[] arr, int target) {
        int n = arr.length;
        int mod = 1_000_000_007;
        Arrays.sort(arr);
        int start, end, sum;
        int noOfTuples = 0;
        
        for (int i = 0; i < n - 2; i++) {
            start = i + 1;
            end = n - 1;
            while (start < end) {                                                 // 2 pointer
                sum = arr[i] + arr[start] + arr[end];
                if (sum < target) {
                    start++;
                } else if (sum > target) {
                    end--;
                } else {
                    if (arr[start] == arr[end]) {
                        noOfTuples += ((end - start) * (end - start + 1)) / 2;   // corner case (exmaple 2)
                        noOfTuples %= mod;
                        break;
                    } else {
                        int j = start + 1;
                        int k = end - 1;
                        while (j < end && arr[j] == arr[start]) {
                            j++;
                        }
                        while (start < k && arr[k] == arr[end]) {
                            k--;
                        }
                        noOfTuples += (j - start) * (end - k);                  // main logic
                        noOfTuples %= mod;
                        start = j;
                        end = k;
                    }
                    
                }
            }
        }
        return noOfTuples;
    }
}