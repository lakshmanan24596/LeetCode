/*
N cars are going to the same destination along a one lane road.  
The destination is target miles away.
Each car i has a constant speed speed[i] (in miles per hour), and initial position position[i] miles towards the target along the road.
A car can never pass another car ahead of it, but it can catch up to it, and drive bumper to bumper at the same speed.
The distance between these two cars is ignored - they are assumed to have the same position.
A car fleet is some non-empty set of cars driving at the same position and same speed.  
Note that a single car is also a car fleet.
If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.
How many car fleets will arrive at the destination?

Example 1:
Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
Output: 3

Explanation:
The cars starting at 10 and 8 become a fleet, meeting each other at 12.
The car starting at 0 doesn't catch up to any other car, so it is a fleet by itself.
The cars starting at 5 and 3 become a fleet, meeting each other at 6.
Note that no other cars meet these fleets before the destination, so the answer is 3.

Note:
0 <= N <= 10 ^ 4
0 < target <= 10 ^ 6
0 < speed[i] <= 10 ^ 6
0 <= position[i] < target
All initial positions are different.
*/


/*
    https://www.youtube.com/watch?v=H5w6doOXz10&feature=youtu.be
    https://leetcode.com/problems/car-fleet/discuss/139850/C%2B%2BJavaPython-Straight-Forward
    
    https://leetcode.com/problems/car-fleet/discuss/144825/Could-provide-more-examples-for-clarification-after-car-become-as-a-fleet.
    
    Time: n*logn
    Space: n
*/

class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        double[][] cars = new double[n][2];
        int fleet = 0;
        double lastTime = 0, currTime;
        
        for (int i = 0; i < n; i++) {
            cars[i][0] = (double) position[i];
            cars[i][1] = ((double) (target - position[i])) / speed[i];  // time = distance / speed
        }
        Arrays.sort(cars, (a, b) -> (a[0] - b[0]) ? -1 : 1);            // sort position in ascending order
        
        for (int i = n - 1; i >= 0; i--) {                              // iterate from last (position near to target)
            currTime = cars[i][1];
            if (currTime > lastTime) {                                  // main logic
                fleet++;
                lastTime = currTime;
            }
        }
        return fleet;
    }
}