/*
There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). 
The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. 
When the ball stops, it could choose the next direction.

Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol], 
return the shortest distance for the ball to stop at the destination. 
If the ball cannot stop at destination, return -1.

The distance is the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included).
You may assume that the borders of the maze are all walls (see examples).

Example 1:
Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
Output: 12
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
The length of the path is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

Example 2:
Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
Output: -1
Explanation: There is no way for the ball to stop at the destination. Notice that you can pass through the destination but you cannot stop there.

Example 3:
Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
Output: -1

Constraints:
m == maze.length
n == maze[i].length
1 <= m, n <= 100
maze[i][j] is 0 or 1.
start.length == 2
destination.length == 2
0 <= startrow, destinationrow <= m
0 <= startcol, destinationcol <= n
Both the ball and the destination exist in an empty space, and they will not be in the same position initially.
The maze contains at least 2 empty spaces.
*/



/*
    We need to find shortest distance, so BFS is best techinique
    In BFS, same cell can be visited multiple ways with different distances
    But we need to process minDist path first, so use priorityQueue
    
    Dijsktra's algo
    time: r*c*log(r*c)*4
    space: r*c
*/
class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int rows = maze.length;
        int cols = maze[0].length;
        int[] dir = new int[] {0, -1, 0, 1, 0};
        int[] currBall;
        int currX, currY, currDist;
        int nextX, nextY, count;
        
        PriorityQueue<int[]> ballPosDist = new PriorityQueue<int[]>((a, b) -> (a[2] - b[2]));  
        ballPosDist.add(new int[] {start[0], start[1], 0});
        int[][] distance = new int[rows][cols];
        for (int[] currDistance : distance) {
            Arrays.fill(currDistance, Integer.MAX_VALUE);
        }
        distance[start[0]][start[1]] = 0;
        
        while (!ballPosDist.isEmpty()) {
            currBall = ballPosDist.remove();
            currX = currBall[0];
            currY = currBall[1];
            currDist = currBall[2];
            if (currX == destination[0] && currY == destination[1]) {
                return distance[currX][currY];
            }
            if (distance[currX][currY] < currDist) {                            // optimization of dijkstra's algo
                continue;
            }
            
            for (int i = 0; i < 4; i++) {
                nextX = currX;
                nextY = currY;
                count = 0;
                while (validate(maze, nextX + dir[i], nextY + dir[i + 1])) {    // same as 490. The Maze
                    count++;                                                    // count of cells moved by the ball
                    nextX += dir[i];
                    nextY += dir[i + 1];
                }
                if (currDist + count < distance[nextX][nextY]) {                // main logic
                    distance[nextX][nextY] = currDist + count;
                    ballPosDist.add(new int[] {nextX, nextY, distance[nextX][nextY]});
                }
            }
        }
        return -1;
    }
    
    public boolean validate(int[][] maze, int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0;
    }
}