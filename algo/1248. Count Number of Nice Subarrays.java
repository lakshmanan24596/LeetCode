/*
Given an array of integers nums and an integer k. 
A continuous subarray is called nice if there are k odd numbers on it.
Return the number of nice sub-arrays.

Example 1:
Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].

Example 2:
Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There is no odd numbers in the array.

Example 3:
Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
Output: 16

Constraints:
1 <= nums.length <= 50000
1 <= nums[i] <= 10^5
1 <= k <= nums.length
*/


/*
    time: 2n, space: n
    ex:3 --> odd positions = 3, 6
             prev = -1, point1 = 3, point2 = 6, future = 10
             output += (point1 - prev) * (future - point2), which is left distance * right distance
                     = (3 - -1) * (10 - 6)
                     = 16
*/
/*
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        List<Integer> oddIndexList = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & 1) == 1) {
                oddIndexList.add(i);    // add all odd number in the list
            }
        }
        int point1, point2, prev, future, output = 0, size = oddIndexList.size() - k + 1;
        for (int i = 0; i < size; i++) {
            point1 = oddIndexList.get(i);
            point2 = oddIndexList.get(i + k - 1);
            prev = (i == 0) ? -1 : oddIndexList.get(i - 1);
            future = (i == size - 1) ? nums.length : oddIndexList.get(i + k);
            output += (point1 - prev) * (future - point2);  // main logic
        }
        return output;
    }
}
*/


/*
    Time: n, Space: n
    Logic is similar to above solution
    ex:3 --> 4 + 4 + 4 + 4  (for the last four index)
            = 16
*/
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int oddCount = 0, output = 0;
        int[] mapArr = new int[nums.length + 1];
        mapArr[0] = 1;
        
        for (int num : nums) {
            if ((num & 1) == 1) {
                oddCount++;
            }
            mapArr[oddCount]++;
            if (oddCount >= k) {
                 output += mapArr[oddCount - k];    // main logic
            }
        }
        return output;
    }
}