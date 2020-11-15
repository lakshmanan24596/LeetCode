/*
Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). 
Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.
Notice that you may not slant the container.

Example 1:
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

Example 2:
Input: height = [1,1]
Output: 1

Example 3:
Input: height = [4,3,2,1,4]
Output: 16

Example 4:
Input: height = [1,2,1]
Output: 2
 
Constraints:
n = height.length
2 <= n <= 3 * 104
0 <= height[i] <= 3 * 104
*/


/*
    1) O(n^2), O(1) --> brute
       curr element is left bar and search remaining elements in right side for right bar 
    
    2) O(n), O(1) --> 2 pointer logic
       Since we need more area, initially we start with longer breadth. (ie; left end is left bar and right end is right bar)
       Then we look for longer length
*/
class Solution 
{
    public int maxArea(int[] arr) 
    {
        int output = 0, currOutput;
        int i = 0, j = arr.length-1;   // because we are looking for a longer breadth
        int length, breadth;
        
        while(i < j)    // 2 pointer 
        {
            length = Math.min(arr[i], arr[j]);
            breadth = j - i;
            currOutput = length * breadth;
            output = Math.max(output, currOutput);
            
            if(arr[i] < arr[j]) {       // because we are looking for a longer length
                i++;    
            } else {
                j--;
            }
        }
        return output;
    }
}
