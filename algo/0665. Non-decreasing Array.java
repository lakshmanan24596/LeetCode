/*
Given an array nums with n integers, your task is to check if it could become non-decreasing by modifying at most one element.
We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for every i (0-based) such that (0 <= i <= n - 2).

Example 1:
Input: nums = [4,2,3]
Output: true
Explanation: You could modify the first 4 to 1 to get a non-decreasing array.

Example 2:
Input: nums = [4,2,1]
Output: false
Explanation: You can't get a non-decreasing array by modify at most one element.

Constraints:
n == nums.length
1 <= n <= 104
-105 <= nums[i] <= 105
*/


/*
    time: n
    space: 1
    
    corner case:
    input: 1,2,3,4,2,3
    output = false
    explanation:
        1,2,3,(4,2),3 --> is decreasing
        so we should either change 4 to 2 or 2 to 4 because 1 element can be modified
        changing 4 to 2 is not possible because the previous element is 3
        so change 2 to 4 and the nums array becomes: 1,2,3,4,"4",3
        now 1,2,3,4,(4,3) is decreasing, so return false
*/
class Solution {
    public boolean checkPossibility(int[] nums) {
        boolean isDecreasing = false;
        
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {                        // main logic
                if (isDecreasing) {
                    return false;
                }  
                isDecreasing = true;
                if (i != 0 && nums[i + 1] < nums[i - 1]) {      // corner case
                    nums[i + 1] = nums[i];
                } 
            }
        }
        return true;
    }
}