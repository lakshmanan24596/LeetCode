/*
We have some permutation A of [0, 1, ..., N - 1], where N is the length of A.
The number of (global) inversions is the number of i < j with 0 <= i < j < N and A[i] > A[j].
The number of local inversions is the number of i with 0 <= i < N and A[i] > A[i+1].
Return true if and only if the number of global inversions is equal to the number of local inversions.

Example 1:
Input: A = [1,0,2]
Output: true
Explanation: There is 1 global inversion, and 1 local inversion.

Example 2:
Input: A = [1,2,0]
Output: false
Explanation: There are 2 global inversions, and 1 local inversion.

Note:
A will be a permutation of [0, 1, ..., A.length - 1].
A will have length in range [1, 5000].
The time limit for this problem has been reduced.
*/


/*
    Time: n^2, Space: 1
    Local Inversions are part of Global Inversions when j == i + 1.
    Thus, whenever we find inversions that j != i + 1, we return false.
*/
/*
class Solution {
    public boolean isIdealPermutation(int[] A) {
        for(int i = 0; i < A.length - 2; i++) {
            for(int j = i + 2; j < A.length; j++) {
                if(A[i] > A[j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
*/


/*
    Time: n, Space: 1
    Logic: same as above.
    arr has elements 0 to n-1, so we can use index
*/
class Solution {
    public boolean isIdealPermutation(int[] A) {
        for (int i = 0; i < A.length; i++) {
            if (Math.abs(A[i] - i) > 1) {       
                return false;
            }
        }
        return true;
    }
}


/*
//  Another solution, which will work even if elements are not in range 0 to n-1. Time: n, Space: 1
class Solution {
    public boolean isIdealPermutation(int[] A) {
        int max = 0;
        for(int i = 0; i < A.length - 2; i++) {
            max = Math.max(max, A[i]);
            if(max > A[i + 2]) {
                return false;
            }
        }
        return true;
    }
}
*/