/*
You are given an integer array nums. You can choose exactly one index (0-indexed) and remove the element. 
Notice that the index of the elements may change after the removal.
For example, if nums = [6,1,7,4,1]:
Choosing to remove index 1 results in nums = [6,7,4,1].
Choosing to remove index 2 results in nums = [6,1,4,1].
Choosing to remove index 4 results in nums = [6,1,7,4].
An array is fair if the sum of the odd-indexed values equals the sum of the even-indexed values.
Return the number of indices that you could choose such that after the removal, nums is fair.

Example 1:
Input: nums = [2,1,6,4]
Output: 1
Explanation:
Remove index 0: [1,6,4] -> Even sum: 1 + 4 = 5. Odd sum: 6. Not fair.
Remove index 1: [2,6,4] -> Even sum: 2 + 4 = 6. Odd sum: 6. Fair.
Remove index 2: [2,1,4] -> Even sum: 2 + 4 = 6. Odd sum: 1. Not fair.
Remove index 3: [2,1,6] -> Even sum: 2 + 6 = 8. Odd sum: 1. Not fair.
There is 1 index that you can remove to make nums fair.

Example 2:
Input: nums = [1,1,1]
Output: 3
Explanation: You can remove any index and the remaining array is fair.

Example 3:
Input: nums = [1,2,3]
Output: 0
Explanation: You cannot make a fair array after removing any index.

Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 104
*/



/*
    1) brute
    time: n^2, space: 1
    
    2) preprocess prefixSum of leftOdd, leftEven, rightOdd, rightEven
    time: 3n, space; 4n
    
    leftOdd   = 0, 2, 2, 8, 8
    leftEven  = 0, 0, 1, 1, 5
    rightOdd  = 5, 5, 4, 4, 0
    rightEven = 8, 6, 6, 0, 0
    formula   = leftOdd[i-1] + rightOdd[i]) == (leftEven[i-1] + rightEven[i]
    
    3) preprocess prefixSum logic is same as above
    time: n, space: 1
*/

/*
class Solution {
    public int waysToMakeFair(int[] nums) {
        int n = nums.length;
        int[] leftOdd = new int[n + 1];
        int[] leftEven = new int[n + 1];
        int[] rightOdd = new int[n + 1];
        int[] rightEven = new int[n + 1];
        int output = 0, num;
        
        for (int i = 1; i <= n; i++) {                  // fill leftEven, leftOdd
            num = nums[i-1];
            if (i % 2 == 1) {
                leftEven[i] = leftEven[i-1];
                leftOdd[i] = leftOdd[i-1] + num;
            } else {
                leftOdd[i] = leftOdd[i-1];
                leftEven[i] = leftEven[i-1] + num;
            }
        }
        for (int i = n - 1; i >= 0; i--) {             // fill rightEven, rightOdd (iterate from last)
            num = nums[i];
            if (i % 2 == 1) {
                rightEven[i] = rightEven[i+1];
                rightOdd[i] = rightOdd[i+1] + num;
            } else {
                rightOdd[i] = rightOdd[i+1];
                rightEven[i] = rightEven[i+1] + num;
            }
        }
        for (int i = 1; i <= n; i++) {
            if ((leftOdd[i-1] + rightOdd[i]) == (leftEven[i-1] + rightEven[i])) {   // main logic
                output++;
            }
        }
        return output;
    }
}
*/


// time: n, space: 1
class Solution {
    public int waysToMakeFair(int[] A) {
        int output = 0, n = A.length;
        int left[] = new int[2];            // store sum of odd index values and even index values "in left side of currIndex"
        int right[] = new int[2];           // store sum of odd index values and even index values "in right side of currIndex"
        
        for (int i = 0; i < n; i++) {
            right[i % 2] += A[i]; 
        }
        for (int i = 0; i < n; i++) {
            right[i % 2] -= A[i];
            if (left[0] + right[1] == left[1] + right[0]) {     // main logic
                output++;
            }
            left[i % 2] += A[i];
        }
        return output;
    }
}