/*
You are given an integer array heights representing the heights of buildings, some bricks, and some ladders.
You start your journey from building 0 and move to the next building by possibly using bricks or ladders.
While moving from building i to building i+1 (0-indexed),

If the current building's height is greater than or equal to the next building's height, you do not need a ladder or bricks.
If the current building's height is less than the next building's height, you can either use one ladder or (h[i+1] - h[i]) bricks.
Return the furthest building index (0-indexed) you can reach if you use the given ladders and bricks optimally.

Example 1:
Input: heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
Output: 4

Explanation: Starting at building 0, you can follow these steps:
- Go to building 1 without using ladders nor bricks since 4 >= 2.
- Go to building 2 using 5 bricks. You must use either bricks or ladders because 2 < 7.
- Go to building 3 without using ladders nor bricks since 7 >= 6.
- Go to building 4 using your only ladder. You must use either bricks or ladders because 6 < 9.
It is impossible to go beyond building 4 because you do not have any more bricks or ladders.

Example 2:
Input: heights = [4,12,2,7,3,18,20,3,19], bricks = 10, ladders = 2
Output: 7

Example 3:
Input: heights = [14,3,19,3], bricks = 17, ladders = 0
Output: 3

Constraints:
1 <= heights.length <= 105
1 <= heights[i] <= 106
0 <= bricks <= 109
0 <= ladders <= heights.length
*/


/*
    For each taller building (heights[i] > heights[i-1]), we can either use ladder or brick
    
    1) DP can be used with states: currIndex, remainingBricks, remainingLadders
       at each step we either pick ladder or bricks
       
    2) Greedy:
        using ladder, we can climb infinite height
        so the main logic is, 
            a) use ladders for taller height building and 
            b) use bricks for smaller height buildings
        
        For ladder, we use a priorityQueue (minHeap) of size ladder
        when all the ladder are over, we can use brick
        when all the bricks are over, we can return.
        
        Time: n*logk, where k is number of ladders
        Space: k
*/

class Solution {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> ladderQueue = new PriorityQueue<Integer>();
        int i = 1;
        int n = heights.length;
        int diff;
        
        for (; i < n; i++) {
            if (heights[i] > heights[i-1]) {
                diff = heights[i] - heights[i-1];
                if (ladderQueue.size() < ladders) {
                    ladderQueue.add(diff);
                } else {
                    if (!ladderQueue.isEmpty() && diff > ladderQueue.peek()) {      // main logic
                        bricks -= ladderQueue.remove();
                        ladderQueue.add(diff);
                    } else {
                        bricks -= diff;
                    }
                    if (bricks < 0) {
                        break;
                    }
                }
            }
        }
        return i - 1;
    }
}