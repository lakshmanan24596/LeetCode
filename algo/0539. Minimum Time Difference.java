/*
Given a list of 24-hour clock time points in "HH:MM" format, 
return the minimum minutes difference between any two time-points in the list.

Example 1:
Input: timePoints = ["23:59","00:00"]
Output: 1

Example 2:
Input: timePoints = ["00:00","23:59","00:00"]
Output: 0
 
Constraints:
2 <= timePoints <= 2 * 104
timePoints[i] is in the format "HH:MM".
*/


/*
    1) brute force: n * n
    2) sort: n * logn
    3) bucket sort:
        time: n + c
        space: c
        where c = 24 * 60
*/
class Solution {
    public int findMinDifference(List<String> timePoints) {
        int minute;
        int twentyFourHours = 24 * 60;
        int n = timePoints.size();
        boolean[] minutesArr = new boolean[twentyFourHours];       // bucket sort arr
        String timePoint;
        String[] time;
        Integer prevTime = null;
        int currDiff, minDiff = Integer.MAX_VALUE;
        int firstTime = 0, lastTime = 0;
        
        for (int i = 0; i < n; i++) {
            timePoint = timePoints.get(i);
            time = timePoint.split(":");
            minute = Integer.parseInt(time[0]) * 60;
            minute += Integer.parseInt(time[1]);
            if (minutesArr[minute]) {
                return 0;
            }
            minutesArr[minute] = true;
        }
        
        for (int i = 0; i < twentyFourHours; i++) {
            if (minutesArr[i]) {
                if (prevTime == null) {
                    prevTime = i;
                    firstTime = i;
                } else {
                    currDiff = i - prevTime;                      // main logic
                    minDiff = Math.min(minDiff, currDiff);
                    prevTime = i;
                }
                lastTime = i;
            }
        }
        currDiff = (twentyFourHours + firstTime) - lastTime;      // corner case (rotation)
        minDiff = Math.min(minDiff, currDiff);
        return minDiff;
    }
}