/*
There is a 3 lane road of length n that consists of n + 1 points labeled from 0 to n. 
A frog starts at point 0 in the second lane and wants to jump to point n. However, there could be obstacles along the way.

You are given an array obstacles of length n + 1 where each obstacles[i] (ranging from 0 to 3) describes an obstacle on the lane obstacles[i] at point i. 
If obstacles[i] == 0, there are no obstacles at point i. 
There will be at most one obstacle in the 3 lanes at each point.

For example, if obstacles[2] == 1, then there is an obstacle on lane 1 at point 2.
The frog can only travel from point i to point i + 1 on the same lane if there is not an obstacle on the lane at point i + 1. 
To avoid obstacles, the frog can also perform a side jump to jump to another lane (even if they are not adjacent) at the same point if there is no obstacle on the new lane.

For example, the frog can jump from lane 3 at point 3 to lane 1 at point 3.
Return the minimum number of side jumps the frog needs to reach any lane at point n starting from lane 2 at point 0.
Note: There will be no obstacles on points 0 and n.


Example 1:
Input: obstacles = [0,1,2,3,0]
Output: 2 
Explanation: The optimal solution is shown by the arrows above. There are 2 side jumps (red arrows).
Note that the frog can jump over obstacles only when making side jumps (as shown at point 2).

Example 2:
Input: obstacles = [0,1,1,3,3,0]
Output: 0
Explanation: There are no obstacles on lane 2. No side jumps are required.

Example 3:
Input: obstacles = [0,2,1,0,3,0]
Output: 2
Explanation: The optimal solution is shown by the arrows above. There are 2 side jumps.

Constraints:
obstacles.length == n + 1
1 <= n <= 5 * 105
0 <= obstacles[i] <= 3
obstacles[0] == obstacles[n] == 0
*/



/*
    DP memo
        states: currIndex, laneNumber
        time: (n*3) * 3
        space: n*3
        
    we can also apply tabulation-space optimzation
    because current index depends only on previous index
    so space = 3
    https://leetcode.com/problems/minimum-sideway-jumps/discuss/1152665/JavaC%2B%2BPython-DP-O(1)-space
    
    After space optimization --> time, space = n, 1
    We can also apply greedy --> time, space = n, 1
    
    Greedy --> The idea is that when we hit an obstacle, we can greedily choose a lane where the next obstacle is further out. This works because we can jump to any line with the same +1 penalty.
*/


// time: n, space: n
class Solution {
    int[] obstacles;
    Integer[][] sideJumpCache;
    int totalLanes = 3;
    
    public int minSideJumps(int[] obstacles) {
        this.obstacles = obstacles;
        this.sideJumpCache = new Integer[obstacles.length][totalLanes + 1];
        return minSideJumpsUtil(0, 2);
    }
    
    public int minSideJumpsUtil(int index, int lane) {
        if (index == obstacles.length - 1) {
            return 0;
        }
        if (sideJumpCache[index][lane] != null) {
            return sideJumpCache[index][lane];
        }
        int minSideJump = Integer.MAX_VALUE;
        int currJump;
        
        if (obstacles[index + 1] != lane) {
            minSideJump = minSideJumpsUtil(index + 1, lane);                    // forward jump
        } else {
            for (int nextLane = 1; nextLane <= totalLanes; nextLane++) {
                if (nextLane != lane && obstacles[index] != nextLane) {         // main conditionx
                    currJump = 1 + minSideJumpsUtil(index, nextLane);           // main logic: side jump
                    minSideJump = Math.min(minSideJump, currJump);
                } 
            }
        }
        return sideJumpCache[index][lane] = minSideJump;
    }
}