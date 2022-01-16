/*
You are given two arrays of integers nums1 and nums2, possibly of different lengths. T
he values in the arrays are between 1 and 6, inclusive.
In one operation, you can change any integer's value in any of the arrays to any value between 1 and 6, inclusive.

Return the minimum number of operations required to make the sum of values in nums1 equal to the sum of values in nums2. 
Return -1 if it is not possible to make the sum of the two arrays equal. 


Example 1:
Input: nums1 = [1,2,3,4,5,6], nums2 = [1,1,2,2,2,2]
Output: 3
Explanation: You can make the sums of nums1 and nums2 equal with 3 operations. All indices are 0-indexed.
- Change nums2[0] to 6. nums1 = [1,2,3,4,5,6], nums2 = [6,1,2,2,2,2].
- Change nums1[5] to 1. nums1 = [1,2,3,4,5,1], nums2 = [6,1,2,2,2,2].
- Change nums1[2] to 2. nums1 = [1,2,2,4,5,1], nums2 = [6,1,2,2,2,2].

Example 2:
Input: nums1 = [1,1,1,1,1,1,1], nums2 = [6]
Output: -1
Explanation: There is no way to decrease the sum of nums1 or to increase the sum of nums2 to make them equal.

Example 3:
Input: nums1 = [6,6], nums2 = [1]
Output: 3
Explanation: You can make the sums of nums1 and nums2 equal with 3 operations. All indices are 0-indexed. 
- Change nums1[0] to 2. nums1 = [2,6], nums2 = [1].
- Change nums1[1] to 2. nums1 = [2,2], nums2 = [1].
- Change nums2[0] to 4. nums1 = [2,2], nums2 = [4].

Constraints:
1 <= nums1.length, nums2.length <= 105
1 <= nums1[i], nums2[i] <= 6
*/



/*
    Logic: mapArray, greedy, 2-pointer
    Time: n1 + n2
    Space: n1 + n2
    
    create arr[6] instead of hashmap and track the summation of both arrays
    make sure that sum(arr1) < sum(arr2)
    now we have 2 options:
        1) increase elements in arr1 or
        2) decrement elements in arr2
*/

class Solution {
    public int minOperations(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length * 6 || nums2.length > nums1.length * 6) {
            return -1;
        }
        int[] freq1 = new int[6];
        int[] freq2 = new int[6];
        int sum1 = 0, sum2 = 0;
        
        for (int num : nums1) {
            freq1[num - 1]++;
            sum1 += num;
        }
        for (int num : nums2) {
            freq2[num - 1]++;
            sum2 += num;
        }
        return (sum1 < sum2) ? minOper(freq1, freq2, sum1, sum2) : minOper(freq2, freq1, sum2, sum1);
    }
    
    public int minOper(int[] freq1, int[] freq2, int sum1, int sum2) {
        int i = 0, j = 5;                           // 2 pointer
        int oper = 0;
        
        while (sum1 < sum2) {
            while (i < freq1.length - 1 && freq1[i] == 0) {
                i++;
            }
            while (j > 0 && freq2[j] == 0) {
                j--;
            }
            
            // main logic:
            if ((5 - i) > j) {                      // case: increase elements in arr1
                sum1 += 5 - i;                      // change the number to 6
                freq1[i]--;
                freq1[5]++;         
            } else {                                // case: decrement elements in arr2
                sum2 -= j;                          // change the number to 1
                freq2[j]--;
                freq2[0]++;         
            }
            oper++;
        }
        return oper;
    }
}
