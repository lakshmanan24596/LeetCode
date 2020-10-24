/*
Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).

Example 1:
Input: [3, 2, 1]
Output: 1
Explanation: The third maximum is 1.

Example 2:
Input: [1, 2]
Output: 2
Explanation: The third maximum does not exist, so the maximum (2) is returned instead.

Example 3:
Input: [2, 2, 3, 1]
Output: 1
Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.
*/

class Solution 
{
    public int thirdMax(int[] nums) 
    {
        Integer first = nums[0], second = null, third = null; // If we use Min_Value then it will break for testcase [1,2,-2147483648]. So either we can use Integer object with null value or Long.MIN__VALUE
        
        for(int i = 1; i < nums.length; i++)
        {
            if(nums[i] > first) {
                third = second;
                second = first;
                first = nums[i];
            }
            else if(nums[i] == first) {
                continue;
            }
            else if(second == null || nums[i] > second) {
                third = second;
                second = nums[i];
            }
            else if(nums[i] == second) {
                continue;
            }
            else if(third == null || nums[i] > third) {
                third = nums[i];
            }
        }
        
        return (third != null) ? third : first;
    }
}
