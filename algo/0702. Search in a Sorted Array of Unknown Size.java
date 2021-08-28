/*
This is an interactive problem.
You have a sorted array of unique elements and an unknown size.
You do not have an access to the array but you can use the ArrayReader interface to access it. 
You can call ArrayReader.get(i) that:

returns the value at the ith index (0-indexed) of the secret array (i.e., secret[i]), or
returns 231 - 1 if the i is out of the boundary of the array.
You are also given an integer target.

Return the index k of the hidden array where secret[k] == target or return -1 otherwise.
You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: secret = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in secret and its index is 4.

Example 2:
Input: secret = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in secret so return -1.
 
Constraints:
1 <= secret.length <= 104
-104 <= secret[i], target <= 104
secret is sorted in a strictly increasing order.
*/


/**
 * // This is ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface ArrayReader {
 *     public int get(int index) {}
 * }
 */


/*
    without knowing the range, we cant do binary search
        1) so let size of array be 10^4
        2) or we can also search for the left, right range first
        
    time: O(log target)
    https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/solution/
*/

class Solution {
    public int search(ArrayReader reader, int target) {
        int left = 0;
        int right = 1;
        while (reader.get(right) < target) {    // main logic: find left, right boundaries
            left = right;
            right <<= 1;
        }
        return binarySearch(reader, target, left, right);
    }
    
    // normal binary search
    public int binarySearch(ArrayReader reader, int target, int left, int right) {
        int mid, currVal;
        while (left <= right) {
            mid = left + ((right - left) / 2);
            currVal = reader.get(mid);    
            if (target == currVal) {
                return mid;
            } else if (target > currVal) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}