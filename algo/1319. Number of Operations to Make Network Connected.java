/*
There are n computers numbered from 0 to n-1 connected by ethernet cables connections forming a network where connections[i] = [a, b] represents a connection between computers a and b. 
Any computer can reach any other computer directly or indirectly through the network.
Given an initial computer network connections. 
You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected. 
Return the minimum number of times you need to do this in order to make all the computers connected. 
If it's not possible, return -1. 

Example 1:
Input: n = 4, connections = [[0,1],[0,2],[1,2]]
Output: 1
Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.

Example 2:
Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
Output: 2

Example 3:
Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
Output: -1
Explanation: There are not enough cables.

Example 4:
Input: n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
Output: 0

Constraints:
1 <= n <= 10^5
1 <= connections.length <= min(n*(n-1)/2, 10^5)
connections[i].length == 2
0 <= connections[i][0], connections[i][1] < n
connections[i][0] != connections[i][1]
There are no repeated connections.
No two computers are connected by more than one cable.
*/


/*
    Logic: 
        output = noOfConnectedComponents - 1
        to find noOfConnectedComponents, we can find DSU or DFS
    
    Time: 2n
    Space: n
*/
class Solution {
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {       // not enough cabled to connect all computers
            return -1;
        }
        DSU dsu = new DSU(n);
        int setA, setB;
        int noOfConnectedComponents = n;
        
        for (int[] connection : connections) {
            setA = dsu.find(connection[0]);
            setB = dsu.find(connection[1]);
            if (setA != setB) {
                dsu.union(setA, setB);
                noOfConnectedComponents--;      // main logic
            }
        }
        return noOfConnectedComponents - 1;     // main logic
    }
    
    class DSU {
        int[] parent;
        int[] rank;
        
        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int curr) {
            if (parent[curr] == curr) {
                return curr;
            }
            return parent[curr] = find(parent[curr]);   // path compression
        }
        
        public void union(int a, int b) {
            if (rank[a] > rank[b]) {
                parent[b] = a;
                rank[a] += rank[b];                     // union by rank
            } else {
                parent[a] = b;
                rank[b] += rank[a];
            }
        }
    }
}