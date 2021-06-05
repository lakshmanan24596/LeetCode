/*
A car travels from a starting position to a destination which is target miles east of the starting position.
Along the way, there are gas stations.  
Each station[i] represents a gas station that is station[i][0] miles east of the starting position, and has station[i][1] liters of gas.

The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it.  
It uses 1 liter of gas per 1 mile that it drives.
When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.

What is the least number of refueling stops the car must make in order to reach its destination?  
If it cannot reach the destination, return -1.

Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there.  
If the car reaches the destination with 0 fuel left, it is still considered to have arrived.


Example 1:
Input: target = 1, startFuel = 1, stations = []
Output: 0
Explanation: We can reach the target without refueling.

Example 2:
Input: target = 100, startFuel = 1, stations = [[10,100]]
Output: -1
Explanation: We can't reach the target (or even the first gas station).

Example 3:
Input: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
Output: 2
Explanation: 
We start with 10 liters of fuel.
We drive to position 10, expending 10 liters of fuel.  We refuel from 0 liters to 60 liters of gas.
Then, we drive from position 10 to position 60 (expending 50 liters of fuel),
and refuel from 10 liters to 50 liters of gas.  We then drive to and reach the target.
We made 2 refueling stops along the way, so we return 2.

Note:
1 <= target, startFuel, stations[i][1] <= 10^9
0 <= stations.length <= 500
0 < stations[0][0] < stations[1][0] < ... < stations[stations.length-1][0] < target
*/



/*
    1) 0/1 knapsack recursion:
        time: 2^n
        space: n

    2) 0/1 knapsack DP:
        time: n*target
        space: n*target
        this doesn't work when target is large
        
    3) optimized DP:
        time: n^2
        space: n^2
        logic:
            regular way:
                i = station index
                j = max reachable position
                dp[i][j] = min stops
            this problem:
                i = station index
                j = min stops                               (main logic)
                dp[i][j] value = max reachable position     (main logic)
        
        This reduces time compexity from n*target to n*n 
        If we apply tabulation space optimization technique, then space can be reduced to O(n)
        https://leetcode.com/problems/minimum-number-of-refueling-stops/discuss/151850/C%2B%2B-DP-solution-Space-complexity-from-O(n2)-to-O(n).
        
        DP table for example-3: (fill only lower half of table)
            station index vs min stops table 
            value = max reachable position
            70
            70, 100
            70, 100, 130
            70, 110, 140, 170
        formula = diagnol cell + current fuel
        for finding answer, iterate last row of DP table and check which column has position >= target
        here, 110 >= 100, so answer = col index = 2


    4) Max heap
        time: n*logn
        space: n
        
        logic: In exmaple-3:
            initial fuel = 10 and at station pos 10, fill fuel. Now fuel = 10 + 60 = 70
            next we can fill fuel at station pos 20, 30, 60 (ie; which are <= 70)
            we can fill it anywhere, but fill the max fuel (using max heap) to reach far position
*/


// max heap solution
class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int stops = 0;
        int position = startFuel;
        if (position >= target) {
            return stops;
        }
        PriorityQueue<Integer> fuelQueue = new PriorityQueue<Integer>((a, b) -> (b - a));
        
        for (int i = 0; i < stations.length; ) {
            if (stations[i][0] <= position) {           // main logic
                fuelQueue.add(stations[i][1]);
                i++;
            } else {
                if (fuelQueue.isEmpty()) {
                    return -1;
                }
                position += fuelQueue.remove();         // main logic: fill max fuel to reach far position
                stops++;
                if (position >= target) {
                    return stops;
                }
            }
        }
        while (!fuelQueue.isEmpty()) {
            position += fuelQueue.remove();
            stops++;
            if (position >= target) {
                return stops;
            }
        }
        return -1;
    }
}
