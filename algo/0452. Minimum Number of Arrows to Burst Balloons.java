/*
There are some spherical balloons spread in two-dimensional space. 
For each balloon, provided input is the start and end coordinates of the horizontal diameter. 
Since it's horizontal, y-coordinates don't matter, and hence the x-coordinates of start and end of the diameter suffice. 
The start is always smaller than the end.
An arrow can be shot up exactly vertically from different points along the x-axis. 
A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be shot. 
An arrow once shot keeps traveling up infinitely.
Given an array points where points[i] = [xstart, xend], return the minimum number of arrows that must be shot to burst all balloons.

Example 1:
Input: points = [[10,16],[2,8],[1,6],[7,12]]
Output: 2
Explanation: One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).

Example 2:
Input: points = [[1,2],[3,4],[5,6],[7,8]]
Output: 4

Example 3:
Input: points = [[1,2],[2,3],[3,4],[4,5]]
Output: 2

Example 4:
Input: points = [[1,2]]
Output: 1

Example 5:
Input: points = [[2,3],[2,3]]
Output: 1

Constraints:
0 <= points.length <= 104
points[i].length == 2
-231 <= xstart < xend <= 231 - 1
*/


/*
    This problem is same as problem 435 Non-overlapping Intervals
    Logic: 
        (1,6), (2,8) --> single arraw is enough and it can be shoot at any point between 2 to 6.
        But we greedily shoot the array at point 6, because this gives you the best chance to shoot more furthur balloons. 
*/
class Solution 
{
    public int findMinArrowShots(int[][] points) 
    {
        if(points == null || points.length < 1) {
            return 0;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if(a[0] == b[0]) {
                    return Integer.compare(a[1], b[1]);
                }
                return Integer.compare(a[0], b[0]);      // sort based on start position
                // a[0] - b[0] is not done because it causes integer overflow during subtraction
            }
        });
        
        int output = 1;
        int end = points[0][1];                          // refers to last arrow position
        for(int i = 1; i < points.length; i++)
        {
            if(points[i][0] <= end) {                    // overlap
                end = Math.min(end, points[i][1]);
            } else {                                     // doesn't overlap, so extra arrow needed
                output++;
                end = points[i][1];
            }
        }
        return output;
    }
}
