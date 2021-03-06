/*
(This problem is an interactive problem.)
You may recall that an array A is a mountain array if and only if:
A.length >= 3
There exists some i with 0 < i < A.length - 1 such that:
A[0] < A[1] < ... A[i-1] < A[i]
A[i] > A[i+1] > ... > A[A.length - 1]

Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target.  
If such an index doesn't exist, return -1.

You can't access the mountain array directly.  
You may only access the array using a MountainArray interface:

MountainArray.get(k) returns the element of the array at index k (0-indexed).
MountainArray.length() returns the length of the array.
Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer.  Also, any solutions that attempt to circumvent the judge will result in disqualification.


Example 1:
Input: array = [1,2,3,4,5,3,1], target = 3
Output: 2
Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.

Example 2:
Input: array = [0,1,2,4,2,1], target = 3
Output: -1
Explanation: 3 does not exist in the array, so we return -1.

Constraints:
3 <= mountain_arr.length() <= 10000
0 <= target <= 10^9
0 <= mountain_arr.get(index) <= 10^9
*/


/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */
 

/*
    Binary search
    time: O(3*logn)
    space: 1
    
    Logic:
        1) find peak index in mountain array
        2) binary search 0 to peakIndex
        3) if not found in (2), then binary search peakIndex+1 to n-1
*/
class Solution {
    int target;
    MountainArray mountainArr;
    
    public int findInMountainArray(int target, MountainArray mountainArr) {
        this.target = target;
        this.mountainArr = mountainArr;
        
        int peakIndex = findPeakIndex();                                               // step 1
        int left = binarySearch(0, peakIndex, true);                                   // step 2
        if (left != -1) {
            return left;
        }
        int right = binarySearch(peakIndex + 1, mountainArr.length()  - 1, false);     // step 3
        return right;
    }
    
    public int findPeakIndex() {
        int left = 0;
        int right = mountainArr.length() - 1;
        int mid, midVal;
        
        while (left <= right) {
            mid = (left + right) / 2;
            midVal = mountainArr.get(mid);
            
            if (mid == 0 || midVal > mountainArr.get(mid - 1)) {
                if (midVal > mountainArr.get(mid + 1)) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    
    public int binarySearch(int left, int right, boolean isAsc) {
        int mid;
        int midVal;
        
        while (left <= right) {
            mid = (left + right) / 2;
            midVal = mountainArr.get(mid);
            
            if (target == midVal) {
                return mid;
            } else if (target < midVal) {
                if (isAsc) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (isAsc) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}