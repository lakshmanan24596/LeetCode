/*
You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.
For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. 
You can travel between bus stops by buses only.
Return the least number of buses you must take to travel from source to target. 
Return -1 if it is not possible.

Example 1:
Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
Output: 2
Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.

Example 2:
Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
Output: -1

Constraints:
1 <= routes.length <= 500.
1 <= routes[i].length <= 105
All the values of routes[i] are unique.
sum(routes[i].length) <= 105
0 <= routes[i][j] < 106
0 <= source, target < 106
*/



/*
    logic: plain bfs
    no of nodes in graph = no of buses = rows
    
    time: 
        create stopVsBusMap = rows * cols
        queue processing    = (V + E) * (cols)
                            = (rows + rows) * (cols)
    space: 
        stopVsBusMap = rows * cols
        busQueue     = rows
        visited      = rows
*/

class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        int noOfBus = routes.length;
        int noOfStops;
        Map<Integer, List<Integer>> stopVsBusMap = new HashMap<Integer, List<Integer>>();
        Queue<Integer> busQueue = new LinkedList<Integer>();
        boolean[] visitedBuses = new boolean[noOfBus];
        int minBusNeeded = 0;
        
        for (int i = 0; i < noOfBus; i++) {                     // create stopVsBusMap
            noOfStops = routes[i].length;
            for (int j = 0; j < noOfStops; j++) {
                int currStop = routes[i][j];
                List<Integer> busList = stopVsBusMap.get(currStop);
                if (busList == null) {
                    busList = new ArrayList<Integer>();
                    stopVsBusMap.put(currStop, busList);
                }
                busList.add(i);
            }
        }
        
        List<Integer> busList = stopVsBusMap.get(source);       // push initial bus to queue
        for (int bus : busList) {
            busQueue.add(bus);
            visitedBuses[bus] = true;
        }
        
        while (!busQueue.isEmpty()) {                           // process queue
            minBusNeeded++;
            int busQueueSize = busQueue.size();
            while (busQueueSize-- > 0) {
                int bus = busQueue.remove();
                int[] stops = routes[bus];
                for (int stop : stops) {
                    if (stop == target) {
                        return minBusNeeded;
                    }
                    List<Integer> neighBuses = stopVsBusMap.get(stop);
                    for (int neighBus : neighBuses) {
                        if (!visitedBuses[neighBus]) {
                            visitedBuses[neighBus] = true;
                            busQueue.add(neighBus);
                        }
                    }
                }
            }
        }
        return -1;
    }
}