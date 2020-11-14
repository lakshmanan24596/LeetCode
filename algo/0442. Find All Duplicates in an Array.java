/*
Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
Find all the elements that appear twice in this array.
Could you do it without extra space and in O(n) runtime?

Example:
Input: [4,3,2,7,8,2,3,1]
Output: [2,3]
*/

class Solution 
{
    /*
        Approach:
            1) O(n^2): check every element
            2) O(n*logn): sorting
            3) O(n), O(n): keep extra visited array
            4) O(n), O(1): change in given array
            
        similar to 448. Find All Numbers Disappeared in an Array
            1) swap algo (similar to find first missing positive number - problem 41)
            2) add n and then % n using "nums[nums[i]]"
            3) make as negative using "nums[nums[i]]"
    */
    public List<Integer> findDuplicates(int[] nums) 
    {
        List<Integer> output = new ArrayList<Integer>();
        int index;
        
        for(int i = 0; i < nums.length; i++)
        {
            index = Math.abs(nums[i]) - 1;
            if(nums[index] > 0) { 
                nums[index] *= -1;
            }
            else {  // if nums[index] is -ve then it is already visited 
                output.add(Math.abs(nums[i]));
            }
        }
        return output;
    }
}
