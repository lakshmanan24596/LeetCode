/*
Given an array of integers A, a move consists of choosing any A[i], and incrementing it by 1.
Return the least number of moves to make every value in A unique.

Example 1:
Input: [1,2,2]
Output: 1
Explanation:  After 1 move, the array could be [1, 2, 3].

Example 2:
Input: [3,2,1,2,1,7]
Output: 6
Explanation:  After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
It can be shown with 5 or less moves that it is impossible for the array to have all unique values.

Note:
0 <= A.length <= 40000
0 <= A[i] < 40000
*/


/*
    Logic 1:
        Time: n^2
        Brute force using hashset
        for each element, check previous element
    Logic 2:
        Time: n*logn, Space: n
        Using treemap with key,value = arr[i], count
    Logic 3:
        Time: n*logn, Space: 1
        a) sort the array
        b) calculate nextVal
        c) steps += nextVal - arr[i]
    Logic 4:
        Time: 40000
*/

// Time: n*logn, Space: 1
class Solution {
    public int minIncrementForUnique(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int nextVal = -1;
        int steps = 0;
        Arrays.sort(arr);
        
        for (int i = 0; i < arr.length; i++) {
            if(nextVal <= arr[i]) {
                nextVal = arr[i] + 1;
            } else {
                steps += nextVal - arr[i];          // main logic
                nextVal++;
            }
        }
        return steps;
    }
}