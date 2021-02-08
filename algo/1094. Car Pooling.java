/*
You are driving a vehicle that has capacity empty seats initially available for passengers.  
The vehicle only drives east (ie. it cannot turn around and drive west.)
Given a list of trips, trip[i] = [num_passengers, start_location, end_location] contains information about the i-th trip: the number of passengers that must be picked up, and the locations to pick them up and drop them off.  
The locations are given as the number of kilometers due east from your vehicle's initial location.
Return true if and only if it is possible to pick up and drop off all passengers for all the given trips. 

Example 1:
Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false

Example 2:
Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true

Example 3:
Input: trips = [[2,1,5],[3,5,7]], capacity = 3
Output: true

Example 4:
Input: trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
Output: true

Constraints:
trips.length <= 1000
trips[i].length == 3
1 <= trips[i][0] <= 100
0 <= trips[i][1] < trips[i][2] <= 1000
1 <= capacity <= 100000
*/


/*
    Exactly same as https://www.geeksforgeeks.org/minimum-number-platforms-required-railwaybus-station/
    
    Example-3: (timestamp, capacity)
        node 0 --> 1, +2
        node 1 --> 5, -2
        node 2 --> 5, +3
        node 3 --> 7, -3
    
    Implementation:
    a)  Sort the nodes based on timestamp
        If timestamp are equal, then sort it based on capacity
    b)  currCapacity += nodes[i].capacity
        for any node, if (currCapacity > totalCapacity), then return false, else return true.
    
    Time: n*logn
    Space: n
*/

/*
class Solution {
    public boolean carPooling(int[][] trips, int totalCapacity) {
        int n = trips.length;
        Node[] nodes = new Node[n * 2];
        int i = 0, currCapacity = 0;
        
        for (int[] trip : trips) {
            nodes[i++] = new Node(trip[1], trip[0]);    // start node
            nodes[i++] = new Node(trip[2], -trip[0]);   // end node (negate the capacity)
        }
        Arrays.sort(nodes, (a, b) -> 
                    (a.timestamp == b.timestamp) ? a.capacity - b.capacity : a.timestamp - b.timestamp);
        
        for (i = 0; i < nodes.length; i++) {
            currCapacity += nodes[i].capacity;
            if (currCapacity > totalCapacity) {     // main logic
                return false;
            }
        }
        return true;
    }
    
    class Node {
        int timestamp;
        int capacity;
        
        Node(int timestamp, int capacity) {
            this.timestamp = timestamp;
            this.capacity = capacity;
        }
    }
}
*/


/*
// same as above, implemented using treemap
class Solution {
    public boolean carPooling(int[][] trips, int totalCapacity) {
        Map<Integer, Integer> map = new TreeMap<>();    // key, value = timeStamp, capacity
        int currCapacity = 0;
        
        for (int[] trip : trips) {
            map.put(trip[1], map.getOrDefault(trip[1], 0) + trip[0]);   // start node
            map.put(trip[2], map.getOrDefault(trip[2], 0) - trip[0]);   // end node (negate the capacity)
        }
        for (int capacity : map.values()) {
            currCapacity += capacity;
            if (currCapacity > totalCapacity) {     // main logic
                return false;
            }
        }
        return true;
    }
}
*/


// same as above, implemented using backet sort of length 1000
class Solution {
    public boolean carPooling(int[][] trips, int totalCapacity) {
        int[] bucketArr = new int[1001];
        int currCapacity = 0;
        
        for (int[] trip : trips) {
            bucketArr[trip[1]] += trip[0];          // start node
            bucketArr[trip[2]] -= trip[0];          // end node (negate the capacity)
        }
        for (int capacity : bucketArr) {
            currCapacity += capacity;
            if (currCapacity > totalCapacity) {     // main logic
                return false;
            }
        }
        return true;
    }
}