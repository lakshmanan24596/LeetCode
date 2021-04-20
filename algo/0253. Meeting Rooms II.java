/*
Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.

Example 1:
Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2

Example 2:
Input: intervals = [[7,10],[2,4]]
Output: 1

Constraints:
1 <= intervals.length <= 104
0 <= starti < endi <= 106
*/


/*
    1) sort + priorityQueue
        time: n*logn
        space: n
        
    2) Chronological ordering
        time: n*logn
        space: n
*/
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int noOfIntervals = intervals.length;
        int[] startTime = new int[noOfIntervals];
        int[] endTime = new int[noOfIntervals];
        int meetingsRooms = 0, minMeetingRooms = 0;
        
        for (int i = 0; i < noOfIntervals; i++) {
            startTime[i] = intervals[i][0];
            endTime[i] = intervals[i][1];
        }
        Arrays.sort(startTime);
        Arrays.sort(endTime);
        
        for (int i = 0, j = 0; i < noOfIntervals; ) {
            if (startTime[i] < endTime[j]) {
                meetingsRooms++;
                minMeetingRooms = Math.max(minMeetingRooms, meetingsRooms);
                i++;
            } else {
                meetingsRooms--;
                j++;
            }
        }
        return minMeetingRooms;
    }
}