/*
There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.
Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. 
If there is no such route, output -1.

Example 1:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation: 
The graph looks like this:
The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.

Example 2:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
Output: 500
Explanation: 
The graph looks like this:
The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 
Constraints:
The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
The size of flights will be in range [0, n * (n - 1) / 2].
The format of each flight will be (src, dst, price).
The price of each flight will be in the range [1, 10000].
k is in the range of [0, n - 1].
There will not be any duplicated flights or self cycles.
*/


/*
    We can also solve it using bellman ford algo with "k+1 iterations" instead of n iterations
    Below mentioned solution is based on Dijkstras shortest path algo
*/
class Solution 
{
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) 
    {
        ArrayList<int[]>[] adjList = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<int[]>();
        }
        for(int i = 0; i < flights.length; i++)
        {
            int[] currFlight = flights[i];
            ArrayList<int[]> currList = adjList[currFlight[0]];
            currList.add(new int[]{currFlight[1], currFlight[2]});
        }
        
        PriorityQueue<int[]> pQueue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[2] - b[2];                          // min heap for distance
            }
        });
        
        int output = Integer.MAX_VALUE;
        pQueue.add(new int[] {src, -1, 0});                  // arr[] = {node, k, dist}
        while(!pQueue.isEmpty())
        {
            int[] curr = pQueue.remove();
            int currNode = curr[0]; 
            int currK = curr[1];
            int currDist = curr[2]; 
            if(currNode == dst) {
                return currDist;
            }
            
            if(currK < K)                                   // additional check for this problem
            {
                ArrayList<int[]> neighbours = adjList[currNode];
                for(int[] neighbour : neighbours)
                {
                    int nextNode = neighbour[0];
                    int nextDist = neighbour[1] + currDist;
                    // Normally, only when the nextDist is smaller, we will add it in the pQueue.
                    // But for this problem, we should ignore that check
                    pQueue.add(new int[] {nextNode, currK + 1, nextDist});
                }
            }
        }
        return -1;
    }
}
