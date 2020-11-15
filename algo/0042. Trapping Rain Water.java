/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

Example 1:
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

Example 2:
Input: height = [4,2,0,3,2,5]
Output: 9

Constraints:
n == height.length
0 <= n <= 3 * 104
0 <= height[i] <= 105
*/


//*
    1) O(n^2), O(1) --> for each element go left and go right 
    2) O(n), O(n) --> preprocess max_left and max_right before itself
    3) O(n), O(1) --> 2 pointer logic
*/


/*
// logic: water trapped at any element = min(leftMax, rightMax) – arr[i]
class Solution 
{
    public int trap(int[] arr) 
    {
        int n = arr.length;
        int[] right = new int[n];
        
        for(int i = n-2; i > 0; i--) {
            right[i] = Math.max(right[i+1], arr[i+1]);  // pre-process right side
        }
        
        int output = 0;
        int leftMax = 0, rightMax;
        
        for(int i = 1; i < n-1; i++)  // leave first and last index
        {
            leftMax = Math.max(leftMax, arr[i-1]);  // max in left side 
            rightMax = right[i];                    // max in right side
            
            if(leftMax > arr[i] && rightMax > arr[i]) {
                output += Math.min(leftMax, rightMax) - arr[i];     // main logic
            }
        }
        return output;
    }
}
*/


// 2 pointer solution - https://leetcode.com/problems/trapping-rain-water/discuss/153992/Java-O(n)-time-and-O(1)-space-(with-explanations).

class Solution
{
    public int trap(int[] arr)
    {
        int n = arr.length;
        int i = 0, j = n-1;
        int leftMax = 0, rightMax = 0;
        int output = 0;
        
        while(i < j)
        {
            if(arr[i] < arr[j])
            {
                if(arr[i] > leftMax) {
                    leftMax = arr[i];
                } else {
                    output += leftMax - arr[i];
                }
                i++;
            }
            else
            {
                if(arr[j] > rightMax) {
                    rightMax = arr[j];
                } else {
                    output += rightMax - arr[j];
                }
                j--;
            }
        }
        return output;
    }
}
