/*
You are given an m x n grid rooms initialized with these three possible values.
-1 A wall or an obstacle.
0 A gate.
INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

Example 1:
Input: rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
Output: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]

Example 2:
Input: rooms = [[-1]]
Output: [[-1]]

Example 3:
Input: rooms = [[2147483647]]
Output: [[2147483647]]

Example 4:
Input: rooms = [[0]]
Output: [[0]]

Constraints:
m == rooms.length
n == rooms[i].length
1 <= m, n <= 250
rooms[i][j] is -1, 0, or 231 - 1.
*/



/*
    Question: We need to the shortest distance from all the empty rooms to the nearest gate
    
    1) brute force: 
        time: rc * rc
        from each empty pos, find the nearest gate
        
    2) Basic bfs
        Logic: Do bfs from all gates position and try to visit all empty rooms
        ie: moving from gates to all empty rooms
        time: r*c*4
        space: r*c
*/

class Solution {
    public void wallsAndGates(int[][] rooms) {
        int rows = rooms.length;
        int cols = rooms[0].length;
        Queue<int[]> roomsPos = new LinkedList<int[]>();
        int roomsCount;
        int distance = 0;
        int[] dir = new int[] {0, -1, 0, 1, 0};
        int[] currRoom, nextRoom;
        int nextX, nextY;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rooms[i][j] == 0) {
                    roomsPos.add(new int[] {i, j});
                }
            }
        }
        
        while (!roomsPos.isEmpty()) {
            roomsCount = roomsPos.size();
            distance++;
            while (roomsCount-- > 0) {
                currRoom = roomsPos.remove();
                for (int i = 0; i < 4; i++) {
                    nextX = currRoom[0] + dir[i];
                    nextY = currRoom[1] + dir[i + 1];
                    if (nextX >= 0 && nextX < rows && nextY >= 0 && nextY < cols 
                            && rooms[nextX][nextY] == Integer.MAX_VALUE) {
                        rooms[nextX][nextY] = distance;                                 // main logic
                        nextRoom = new int[] {nextX, nextY};
                        roomsPos.add(nextRoom);
                    }
                }
            }
        }
    }
}