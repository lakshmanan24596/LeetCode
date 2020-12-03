/*
We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
(Here, the distance between two points on a plane is the Euclidean distance.)
You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

Example 1:
Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].

Example 2:
Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)
 
Note:
1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000
*/

class Solution 
{
    public int[][] kClosest(int[][] points, int k) // Time: n*logk, Space: k
    {
        int n = points.length;
        if(k == n) {
            return points;
        }
        
        PriorityQueue<int[]> pQueue = new PriorityQueue<int[]>(new Comparator<int[]>() {    
            public int compare(int[] point1, int[] point2) {
                return getDistance(point2) - getDistance(point1);           // max heap
            }
        });
        
        for(int i = 0; i < n; i++)      
        {
            if(i < k) {                                                     // first K elements
                pQueue.add(points[i]);
            }
            else if(getDistance(points[i]) < getDistance(pQueue.peek())) {  // remaining elemnents
                pQueue.add(points[i]);
                pQueue.remove();
            }
        }
        
        int[][] output = new int[k][];
        int count = 0;
        while(!pQueue.isEmpty()) {
            output[count++] = pQueue.remove();    
        }
        return output;
    }
    
    public int getDistance(int[] point)     // returns the Euclidean distance of a point from the origin
    {        
        int x = point[0];
        int y = point[1];
        return (x * x) + (y * y);           // main logic
        
        // actual formula to calculate distance of a point from the origin is by using pythagoras theorem
        // distance = Math.sqrt(((x2-x1)^2) + ((y2-y1)^2))
        // x1,y1 is 0,0 which is origin and x2,y2 is curr point
    }
}
