/*
On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. 
Each worker and bike is a 2D coordinate on this grid.
We assign one unique bike to each worker so that the sum of the Manhattan distances between each worker and their assigned bike is minimized.

The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
Return the minimum possible sum of Manhattan distances between each worker and their assigned bike.

Example 1:
Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
Output: 6
Explanation: 
We assign bike 0 to worker 0, bike 1 to worker 1. The Manhattan distance of both assignments is 3, so the output is 6.

Example 2:
Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
Output: 4
Explanation: 
We first assign bike 0 to worker 0, then assign bike 1 to worker 1 or worker 2, bike 2 to worker 2 or worker 1. Both assignments lead to sum of the Manhattan distances as 4.

Example 3:
Input: workers = [[0,0],[1,0],[2,0],[3,0],[4,0]], bikes = [[0,999],[1,999],[2,999],[3,999],[4,999]]
Output: 4995

Constraints:
N == workers.length
M == bikes.length
1 <= N <= M <= 10
workers[i].length == 2
bikes[i].length == 2
0 <= workers[i][0], workers[i][1], bikes[i][0], bikes[i][1] < 1000
All the workers and the bikes locations are unique.
*/



/*
    1) brute force
        out of b bikes, we need to choose w bikes, which is a permutation 
        time = b! / (b-w)!
        
        recursion: assign wo with either b0 or b1 or b2... 
                   assign w1 with either b0 or b1 or b2... expect the previous bike assigned to w0
                   same for remaining workers
                   
        ex: b = 5, w = 3
            then time = 5 * 4 * 3 which is b! / (b-w)!
        
    2) DP + bitmask 
        main logic: "assign each worker with a bike"
        states: workerIndex, bikeMask
        time: O((w * 2^b) * b)
        space: O(w * 2^b)
        
    3) DP + bitmask 
        main logic: "assign each bike to a worker"
        it is given, w < b in the question, so instead of masking bikes, we need to mask workers
        
        states: bikeIndex, workerMask
        time: O((b * 2^w) * w)
        space: O(b * 2^w)
        If we apply tabulation space optimization, we can reduce space from O(b * 2^w) to just O(2^w)
*/

class Solution {
    int[][] workers, bikes;
    int noOfWorkers, noOfBikes;
    int allWorkerMask;
    Integer[][] memo;
    
    public int assignBikes(int[][] workers, int[][] bikes) {
        this.workers = workers;
        this.bikes = bikes;
        this.noOfWorkers = workers.length;
        this.noOfBikes = bikes.length;
        
        this.allWorkerMask = (1 << noOfWorkers) - 1;
        this.memo = new Integer[noOfBikes][allWorkerMask + 1];
        return assignBikesUtil(0, 0);
    }
    
    public int assignBikesUtil(int bikeIndex, int workerMask) {
        if (bikeIndex >= noOfBikes) {
            return (workerMask == allWorkerMask) ? 0 : Integer.MAX_VALUE;
        }
        if (memo[bikeIndex][workerMask] != null) {
            return memo[bikeIndex][workerMask];
        }
        int minSum = Integer.MAX_VALUE;
        int currSum;
        
        currSum = assignBikesUtil(bikeIndex + 1, workerMask);                           // assign this bike to no one
        minSum = Math.min(minSum, currSum);
        
        for (int i = 0; i < noOfWorkers; i++) {
            if ((workerMask & (1 << i)) == 0) {
                currSum = assignBikesUtil(bikeIndex + 1, workerMask | (1 << i));        // assign this bike to worker i
                if (currSum != Integer.MAX_VALUE) {
                    currSum += getDistance(i, bikeIndex);
                    minSum = Math.min(minSum, currSum);
                }
            }
        }
        return memo[bikeIndex][workerMask] = minSum;
    }
    
    public int getDistance(int workerIndex, int bikeIndex) {
        return Math.abs(workers[workerIndex][0] - bikes[bikeIndex][0]) + Math.abs(workers[workerIndex][1] - bikes[bikeIndex][1]);
    }
}