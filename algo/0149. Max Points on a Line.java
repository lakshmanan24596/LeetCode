/*
Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number of points that lie on the same straight line.

Example 1:
Input: points = [[1,1],[2,2],[3,3]]
Output: 3

Example 2:
Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
 
Constraints:
1 <= points.length <= 300
points[i].length == 2
-104 <= xi, yi <= 104
All the points are unique.
*/


/*
    logic: hashmap
    time: n^2
    space: n
    
    calculate angle between 2 points using tan
    https://stackoverflow.com/questions/2676719/calculating-the-angle-between-the-line-defined-by-two-points
*/

class Solution {
    public int maxPoints(int[][] points) {
        if (points.length < 3) {
            return points.length;
        }
        int maxPoints = 0, currPoints;
        Map<Double, Integer> angleFreq;
        double angle;
        
        for (int i = 0; i < points.length; i++) {
            angleFreq = new HashMap<Double, Integer>();
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    angle = findAngle(points[i], points[j]);
                    currPoints = 1 + angleFreq.getOrDefault(angle, 0);
                    angleFreq.put(angle, currPoints);
                    maxPoints = Math.max(maxPoints, currPoints);
                }
            }
        }
        return maxPoints + 1;
    }
    
    public double findAngle(int[] point1, int[] point2) {
        double deltaX = point1[0] - point2[0];
        double deltaY = point1[1] - point2[1];
        return Math.toDegrees(Math.atan2(deltaY, deltaX));      // main logic
    }
}