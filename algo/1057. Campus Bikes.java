/*
On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. 
Each worker and bike is a 2D coordinate on this grid.

Our goal is to assign a bike to each worker. 
Among the available bikes and workers, we choose the (worker, bike) pair with the shortest Manhattan distance between each other, and assign the bike to that worker. 
(If there are multiple (worker, bike) pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index; if there are multiple ways to do that, we choose the pair with the smallest bike index). 
We repeat this process until there are no available workers.

The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the i-th worker is assigned to.

Example 1:
Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
Output: [1,0]
Explanation: 
Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].

Example 2:
Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
Output: [0,2,1]
Explanation: 
Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, thus Worker 1 is assigned to Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].

Note:
0 <= workers[i][j], bikes[i][j] < 1000
All worker and bike locations are distinct.
1 <= workers.length <= bikes.length <= 1000
*/


/*
    1) PriorityQueue
        time: NM * log(NM)
        space: NM
        
        Implemenation:
        Fetch all combinations of worker * bike and add it into priorityQueue
        Now fetch min dist pair
        Check whether worker is not assigned and bike is available
        
    2) BucketSort
        time: NM
        space: NM
        https://leetcode.com/problems/campus-bikes/discuss/343953/Java-Solution-using-Bucket-Sort-with-Video-Explanation
*/

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int noOfWorkers = workers.length;
        int noOfBikes = bikes.length;
        int dist;
        int[] workerBike = new int[noOfWorkers];
        Arrays.fill(workerBike, -1);
        boolean[] isBikeAssigned = new boolean[noOfBikes];
        int count = 0;
        
        PriorityQueue<int[]> workerBikeDist = new PriorityQueue<int[]>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                if (a[2] != b[2]) {
                    return a[2] - b[2];     // dist
                }
                if (a[0] != b[0]) {
                    return a[0] - b[0];     // worker index
                }
                return a[1] - b[1];         // bike index
            }
        });
        
        
        for (int i = 0; i < noOfWorkers; i++) {
            for (int j = 0; j < noOfBikes; j++) {
                dist = getManhattanDist(workers[i], bikes[j]);
                workerBikeDist.add(new int[] {i, j, dist});                 // worker, bike, dist
            }
        }
        
        while (count != noOfWorkers) {
            int[] curr = workerBikeDist.remove();
            if (workerBike[curr[0]] == -1 && !isBikeAssigned[curr[1]]) {    // both W and B available
                isBikeAssigned[curr[1]] = true;
                workerBike[curr[0]] = curr[1];                              // assign bike to a worker
                count++;
            }
        }
        return workerBike;
    }
    
    public int getManhattanDist(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}