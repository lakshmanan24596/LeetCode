/*
Given a list of intervals, remove all intervals that are covered by another interval in the list.
Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.
After doing so, return the number of remaining intervals.

Example 1:
Input: intervals = [[1,4],[3,6],[2,8]]
Output: 2
Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.

Example 2:
Input: intervals = [[1,4],[2,3]]
Output: 1

Example 3:
Input: intervals = [[0,10],[5,12]]
Output: 2

Example 4:
Input: intervals = [[3,10],[4,10],[5,11]]
Output: 2

Example 5:
Input: intervals = [[1,2],[1,4],[3,4]]
Output: 1

Constraints:
1 <= intervals.length <= 1000
intervals[i].length == 2
0 <= intervals[i][0] < intervals[i][1] <= 10^5
All the intervals are unique.
*/


/*
    Logic: greedy, sorting
    sort the array in ascending order of start time
    if (currEnd <= prevEnd) then it is covered
    
    Ex: (1,4),(3,6) --> 6 <= 4 --> not covered (increment remaining interval and change prevEnd to 6)
        (1,4),(2,3) --> 3 <= 4 --> covered
        
    Time: n*logn
    Space: 1
*/

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> (a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]));   // ascending sort of start time
        int remainingInterval = 1;
        int prevEnd = intervals[0][1];
        
        for (int i = 1; i < intervals.length; i++) {
            if (!(intervals[i][1] <= prevEnd)) {                 // not covered interval
                remainingInterval++;
                prevEnd = intervals[i][1];
            }
        }
        return remainingInterval;
    }
}