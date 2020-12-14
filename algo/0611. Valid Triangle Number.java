/*
Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.

Example 1:
Input: [2,2,3,4]
Output: 3

Explanation:
Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3

Note:
The length of the given array won't exceed 1000.
The integers in the given array are in the range of [0, 1000].
*/


/*
    Condition for a triangle is --> a + b > c
    
    1) Brute: n^3                   --> 3 loops for a, b, c
    2) Binary search: n^2 * log n   --> 2 loops for a, b and do binary search to find c
    3) 2 pointer: n^2               --> fix a in left corner and b in right corner. 
    
    Logic of 2 pointer technique:
        if(nums[left] + nums[right] > nums[i]) then output += right - left; 
        Because if (left, right) is a valid pair, then (left+1, right), (left+2, right)...(left+x, right) is also valid because the array is sorted
*/

class Solution 
{
    public int triangleNumber(int[] nums)                       // Time: n*logn + n^2
    {
        if(nums == null || nums.length < 3) {
            return 0;
        }    
        Arrays.sort(nums);
        
        int left, right;                                        // (a, b, c) = (left, right, i)
        int output = 0;
        
        for(int i = 2; i < nums.length; i++)
        {
            left = 0;
            right = i - 1;
            
            while(left < right)                                 // 2 pointer technique
            {
                if(nums[left] + nums[right] > nums[i]) {
                    output += right - left;                     // main logic
                    right--;
                } else {
                    left++;
                } 
            }
        }
        return output;
    }
}
